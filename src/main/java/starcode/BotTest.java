package starcode;

import javax.security.auth.login.LoginException;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class BotTest {
    static Dotenv dotenv = Dotenv.load();
    static String token = dotenv.get("TOKEN");
    
    private ShardManager shardManager; // ShardManager é uma classe que gerencia os shards do bot

    public BotTest() throws LoginException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token); // Token do bot
        builder.setStatus(OnlineStatus.ONLINE);  // Stus do bot
        builder.setActivity(Activity.playing("Digite /help")); // Atividade do bot
        shardManager = builder.build();
    }
    
    public ShardManager getShardManager() {
        return shardManager;
    }
    public static void main(String[] args) {
        try{
            BotTest bot = new BotTest(); 
        } catch (LoginException e){
            System.out.println("ERROR: Provided bot token is invalid");
        }
    }
}