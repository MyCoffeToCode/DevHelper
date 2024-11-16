package starcode.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.entities.User;

public class EmojiReaction extends ListenerAdapter {

    // Evento de reação a mensagem
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user = event.getUser(); // Obtém o usuário que reagiu
        String emoji = event.getReaction().getEmoji().getAsReactionCode(); // Obtém o emoji que foi reagido
        String channelMention = event.getChannel().getAsMention(); // Obtém o canal que a mensagem foi reagida
        String jumpLink = event.getJumpUrl(); // Obtém o link da mensagem

        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Reação")
                .setDescription("Reação de " + user.getAsMention() + " com " + emoji + " no canal " + channelMention)
                .addField("Link da Mensagem", jumpLink, false)
                .setColor(0x65D8C5) // Cor verde
                .build();
        String message = user.getAsMention() + " reagiu com " + emoji + " no canal " + channelMention + " no link " + jumpLink; // Mensagem
        event.getGuild().getTextChannelById("1124423573994479737").sendMessageEmbeds(embed).queue(); // Envia a mensagem
    }
}
