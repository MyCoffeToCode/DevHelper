package DevHelper;

import javax.security.auth.login.LoginException;

import DevHelper.Commands.CommandHelp;
import DevHelper.Commands.CommandPing;
import DevHelper.Commands.FunCommands.CommandExercise.CommandExercise;
import DevHelper.Commands.FunCommands.MemeCommands.PrintMemeCommand;
import DevHelper.Commands.FunCommands.MemeCommands.SendMemeCommand;
import DevHelper.Commands.StudyCommands.CourseListCommands.GetCourseListCommand;
import DevHelper.Commands.StudyCommands.CourseListCommands.SetCourseListCommand;
import DevHelper.Commands.StudyCommands.Pomodoro;
import DevHelper.Commands.StudyCommands.PomodoroCommands.PomodoroClosedTicket;
import DevHelper.Commands.StudyCommands.PomodoroCommands.PomodoroCreateTicket;
import DevHelper.Commands.StudyCommands.PomodoroCommands.PomodoroPause;
import DevHelper.Commands.StudyCommands.PomodoroCommands.PomodoroResume;
import DevHelper.Commands.StudyCommands.PomodoroCommands.PomodoroStart;
import DevHelper.Commands.StudyCommands.PomodoroCommands.PomodoroStop;
import DevHelper.Listeners.HelpInteractionListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.internal.utils.JDALogger;

public class MainBot extends ListenerAdapter {

    private Dotenv config = null; // Configurações do Dotenv
    private final CommandManager commandManager; // Gerenciador de comandos
    private final JDA jda; // Instância principal do bot

    public MainBot() throws LoginException {
        String token = System.getenv("DISCORD_TOKEN");

        if (token == null || token.isEmpty()) {
            this.config = Dotenv.load();
            token = config.get("TOKEN");
            if (token == null || token.isEmpty()) {
                    throw new IllegalArgumentException("ERROR: Token do bot não encontrado nem nas variáveis de ambiente, nem no arquivo .env!");
            }
        }

        // Inicializa o CommandManager
        this.commandManager = new CommandManager();
        registerCommands(); // Registra os comandos no CommandManager

        // Configura o bot
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE); // Define status inicial
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

        System.out.println("[BOT] Online!");
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
        commandManager.registerCommand(new GetCourseListCommand());
        commandManager.registerCommand(new SetCourseListCommand());
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
                new HelpInteractionListener()
        );
    }



    private void syncCommands() {
        // Sincroniza os comandos dinamicamente com base no CommandManager
        jda.updateCommands().addCommands(
                commandManager.getCommands().stream()
                        .map(command -> Commands.slash(command.getName(), command.getDescription()))
                        .map(command -> {
                            if (command.getName().equals("sendmeme")) {
                                return Commands.slash(command.getName(), command.getDescription())
                                        .addOption(OptionType.STRING, "url", "URL do meme", true);
                            }
                            return Commands.slash(command.getName(), command.getDescription());
                        })
                        .map(command -> {
                            if (command.getName().equals("exercise")) {
                                return Commands.slash(command.getName(), command.getDescription())
                                        .addOption(OptionType.STRING, "linguagem", "Escolha uma linguagem para o desafio", true)
                                        .addOption(OptionType.STRING, "dificuldade", "Escolha a dificuldade do desafio. Dificuldades: Fácil, Médio, Difícil", true);
                            }
                            return Commands.slash(command.getName(), command.getDescription());
                        })
                        .map(command -> {
                            if (command.getName().equals("adicionar-curso")) {
                                return Commands.slash(command.getName(), command.getDescription())
                                        .addOption(OptionType.STRING, "título", "Defina o título do curso", true)
                                        .addOption(OptionType.STRING, "categoria", "Defina a categoria: Programação, DevOps, Infra, Banco de Dados", true)
                                        .addOption(OptionType.STRING, "url", "Insira a URL do curso", true);
                            }
                            return Commands.slash(command.getName(), command.getDescription());
                        })
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
            DatabaseManager.initialize(); // Inicializa o banco de dados

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
