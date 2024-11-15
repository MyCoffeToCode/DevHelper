package starcode.listeners;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.entities.User;

public class EventListener extends ListenerAdapter {

    // Evento de reação a mensagem
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user = event.getUser(); // Obtém o usuário que reagiu
        String emoji = event.getReaction().getEmoji().getAsReactionCode(); // Obtém o emoji que foi reagido
        String channelMention = event.getChannel().getAsMention(); // Obtém o canal que a mensagem foi reagida
        String jumpLink = event.getJumpUrl(); // Obtém o link da mensagem

        String message = user.getAsMention() + " reagiu com " + emoji + " no canal " + channelMention + " no link " + jumpLink; // Mensagem
        event.getGuild().getTextChannelById("1124423573994479737").sendMessage(message).queue(); // Envia a mensagem
    }
}
