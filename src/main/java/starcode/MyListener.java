package starcode;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MyListener extends ListenerAdapter { // ListenerAdapter é uma classe que implementa Listener
    @Override
    public void onMessageReceived(MessageReceivedEvent event) { // Evento de mensagem recebida
        // Ignora bots
        if (event.getAuthor().isBot()) {
            return; 
        }
        // Message
        Message message = event.getMessage(); // Mensagem
        String content = message.getContentRaw(); // Conteúdo da mensagem
        if (content.equals("!ping")) {
            MessageChannel channel = event.getChannel(); // Canal
            channel.sendMessage("Pong!").queue(); // Resposta
        }

    }
    
}
