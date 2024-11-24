package DevHelper;

import javax.security.auth.login.LoginException;

import DevHelper.Commands.CommandHelp;
import DevHelper.Commands.CommandPing;
import DevHelper.Commands.FunCommands.CommandExercise.CommandExercise;
import DevHelper.Commands.FunCommands.MemeCommands.PrintMemeCommand;
import DevHelper.Commands.FunCommands.MemeCommands.SendMemeCommand;
import DevHelper.Commands.StudyCommands.*;
import DevHelper.Commands.StudyCommands.PomodoroCommands.*;
import DevHelper.DataBase.MemeDatabaseManager;
import DevHelper.Listeners.RegisterListener;
import DevHelper.Listeners.RuleListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import DevHelper.Listeners.LogsListener;
import DevHelper.Listeners.HelpInteractionListener;
import net.dv8tion.jda.internal.utils.JDALogger;

import java.util.List;

public class MainBot extends ListenerAdapter {

    private final Dotenv config; // Configurações do Dotenv
    private final CommandManager commandManager; // Gerenciador de comandos
    private final JDA jda; // Instância principal do bot

    public MainBot() throws LoginException {
        // Inicializa o Dotenv e pega o token
        this.config = Dotenv.load();
        String token = config.get("TOKEN");

        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("ERROR: Token do bot não encontrado no arquivo .env!");
        }

        // Inicializa o CommandManager
        this.commandManager = new CommandManager();
        registerCommands(); // Registra os comandos no CommandManager

        // Configura o bot
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setStatus(OnlineStatus.IDLE); // Define status inicial
        builder.setActivity(Activity.playing("Digite /help")); // Atividade do bot
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT); // Permite ler o conteúdo das mensagens

        // Registra os listeners
        registerListeners(builder);

        // Constrói o bot
        this.jda = builder.build();

        // Sincroniza os comandos com o Discord
        syncCommands();

        //
        removeUnusedCommands();
    }

    private void registerCommands() {
        // Registra os comandos no CommandManager
        commandManager.registerCommand(new CommandHelp());
        commandManager.registerCommand(new CommandPing());

        // Fun Commands
        commandManager.registerCommand(new PrintMemeCommand());
        commandManager.registerCommand(new SendMemeCommand());
        commandManager.registerCommand(new CommandExercise());

        // Study Commands
        commandManager.registerCommand(new Pomodoro());

        // Pomodoro Commands
        commandManager.registerCommand(new PomodoroStart());
        commandManager.registerCommand(new PomodoroPause());
        commandManager.registerCommand(new PomodoroStop());
        commandManager.registerCommand(new PomodoroResume());
        commandManager.registerCommand(new PomodoroCreateTicket());
        commandManager.registerCommand(new PomodoroClosedTicket());
        // ------------------------------------------------
    }

    private void registerListeners(JDABuilder builder) {
        // Registra os listeners no builder
        builder.addEventListeners(
                this, // Registra a própria classe como Listener
                new LogsListener(),
                new HelpInteractionListener(),
                new RegisterListener(),
                new RuleListener()
        );
    }



    private void syncCommands() {
        // Sincroniza os comandos dinamicamente com base no CommandManager
        jda.updateCommands().addCommands(
                commandManager.getCommands().stream()
                        .map(command -> Commands.slash(command.getName(), command.getDescription()))
                        .toList()
        ).queue();
    }

    public void removeUnusedCommands(){

        jda.retrieveCommands().queue(existingCommands -> {
            for(Command command : existingCommands){

                boolean isStillUsed = commandManager.getCommands().stream()
                        .anyMatch(cmd -> cmd.getName().equals(command.getName()));


                if(!isStillUsed){
                    command.delete().queue(
                            success -> System.out.println("Comando deletado: " + command.getName()),
                            error -> System.out.println("Erro ao deletar comando: " + command.getName())
                    );
                }
            }
        });
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        commandManager.handleCommand(event); // Processa o comando
    }

    public static void main(String[] args) {
        JDALogger.setFallbackLoggerEnabled(false);
        try {
            new MainBot(); // Inicializa o bot
            DatabaseManager.initialize();

            // Adiciona alguns memes ao banco de dados
            MemeDatabaseManager.memes();
            // Lista todos os memes do banco de dados
            List<String> memes = DatabaseManager.listMemes();
            System.out.println("Memes no banco de dados:");
            memes.forEach(System.out::println);

        } catch (LoginException e) {
            System.out.println("ERROR: Token do bot é inválido.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: Ocorreu um erro inesperado.");
            e.printStackTrace();
        }
    }
}
