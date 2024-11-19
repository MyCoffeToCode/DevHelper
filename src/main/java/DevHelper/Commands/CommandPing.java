package DevHelper.Commands;

import DevHelper.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.Instant;

public class CommandPing implements ICommand {

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Mostra a latência do bot";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        long time = Instant.now().toEpochMilli() - event.getTimeCreated().toInstant().toEpochMilli(); // Pega o tempo atual// Pega o tempo atual
        MessageEmbed embed = new EmbedBuilder()
                .setDescription("Pong! :ping_pong: Latência: " + "**" + time + "ms**")
                .setColor(0x65D8C5)
                .setImage("https://media.tenor.com/yREHDIXM6uEAAAAM/pingpong-pingpong-ani.gif")
                .build();
        event.replyEmbeds(embed).queue();
    }
}
