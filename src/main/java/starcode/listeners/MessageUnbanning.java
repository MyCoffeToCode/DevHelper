package starcode.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageUnbanning extends ListenerAdapter {
    @Override
    public void onGuildUnban(@NotNull GuildUnbanEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setThumbnail(event.getUser().getAvatarUrl())
                .setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl())
                .setTitle("Desbanimento :white_check_mark:")
                .setDescription("O usuário " + event.getUser().getAsMention() + " foi desbanido!")
                .setFooter("ID do Usuário: " + event.getUser().getId(), null)
                .setTimestamp(java.time.OffsetDateTime.now().withNano(0).withSecond(0))
                .setColor(0x00FF00)
                .build();

        event.getGuild().getTextChannelById("1124423573994479737").sendMessageEmbeds(embed).queue(); // Envia a mensagem
    }
}
