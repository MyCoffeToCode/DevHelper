package starcode;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.Command;

public class CommandHandler {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandHandler(){
        registerCommand(new PingCommand()); // salva o comando !ping e chama PingCommand
    } 
    public void registerCommand(PingCommand command){
            commands.put(command.getCommandName(), PingCommand); 
    }
    public void handleCommand(MessageReceivedEvent event){
        String message = event.getMessage().getContentRaw();
        Command command = commands.get(message);

        if (command !=null) {command.execute(event);}
    }

}
