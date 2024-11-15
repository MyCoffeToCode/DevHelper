package starcode;

import javax.security.auth.login.LoginException;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import starcode.listeners.EventListener;

public class MainBot {
    private final Dotenv config; // Configurações do Dotenv
    
    private ShardManager shardManager; // ShardManager é uma classe que gerencia os shards do bot

    public MainBot() throws LoginException {
        config = Dotenv.load(); // Carrega o arquivo .env
        String token = config.get("TOKEN"); // Pega o token do arquivo .env

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token); // Token do bot
        builder.setStatus(OnlineStatus.IDLE);  // Stus do bot
        builder.setActivity(Activity.playing("Digite /help")); // Atividade do bot
        shardManager = builder.build();

        // Adiciona os listeners
        shardManager.addEventListener(new EventListener());
    }

    // Retorna o Dotenv
    public Dotenv getConfig() {
        return config;
    }
    
    public ShardManager getShardManager() {
        return shardManager;
    }
    public static void main(String[] args) {
        try{
            MainBot bot = new MainBot();
        } catch (LoginException e){
            System.out.println("ERROR: Provided bot token is invalid");
        }
    }
}