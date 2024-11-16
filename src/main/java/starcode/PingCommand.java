package starcode;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class PingCommand implements ICommand {
    @Override
    public void execute(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        channel.sendMessage("Pong!").queue();
    }

    @Override
    public String getCommandName() {
        return "!ping";
    }
}