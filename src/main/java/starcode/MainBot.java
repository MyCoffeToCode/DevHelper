package starcode;

import javax.security.auth.login.LoginException;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import starcode.commands.CommandHelp;
import starcode.listeners.SlashCommandListener;
import starcode.listeners.LogsListener;

public class MainBot extends ListenerAdapter {
    private final Dotenv config; // Configurações do Dotenv
    private final ShardManager shardManager; // ShardManager é uma classe que gerencia os shards do bot

    public MainBot() throws LoginException {
        // Inicializa o Dotenv e pega o token
        this.config = Dotenv.load();
        String token = config.get("TOKEN");
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("ERROR: Token do bot não encontrado no arquivo .env!");
        }

        // Configura o bot com o token
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.IDLE); // Status inicial
        builder.setActivity(Activity.playing("Digite /help")); // Atividade do bot
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT); // Permite ler conteúdo das mensagens

        // Inicializa o ShardManager
        this.shardManager = builder.build();

        // Adiciona os listeners
        shardManager.addEventListener(new LogsListener());
        shardManager.addEventListener(new SlashCommandListener());
        shardManager.addEventListener(new CommandHelp()); // Adiciona o listener CommandHelp


        // Registra os comandos do bot
        shardManager.getShards().forEach(shard -> shard.updateCommands().addCommands(
                Commands.slash("ping", "Responde com pong!"),
                Commands.slash("help", "Mostra a lista de comandos"),
                Commands.slash("bump", "Bumpa o servidor")
        ).queue());
    }

    public static void main(String[] args) {
        try {
            new MainBot(); // Inicializa o bot
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
