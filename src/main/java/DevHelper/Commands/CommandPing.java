package DevHelper.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;

public class CommandPing extends ListenerAdapter {
    // Método de ping
    /*
        Este método é chamado sempre que o comando /ping é executado.
        Ele responde com "Pong!" e mostra a latência do bot.
    */
    public static void ping(SlashCommandInteractionEvent event) {
        long time = Instant.now().toEpochMilli() - event.getTimeCreated().toInstant().toEpochMilli(); // Pega o tempo atual// Pega o tempo atual
        MessageEmbed embed = new EmbedBuilder()
                .setDescription("Pong! :ping_pong: Latência: " + "**" + time + "ms**")
                .setColor(0x65D8C5)
                .setImage("https://media.tenor.com/yREHDIXM6uEAAAAM/pingpong-pingpong-ani.gif")
                .build();
        event.replyEmbeds(embed).queue();
    }
}
