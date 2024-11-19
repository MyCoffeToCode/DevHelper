package DevHelper.Listeners;
import DevHelper.CommandManager;
import DevHelper.Commands.FunCommand.MemeCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import DevHelper.Commands.CommandPing;
import java.io.IOException;

public class SlashCommandListener extends ListenerAdapter {
    private final CommandManager commandManager;
    /**
     * Cria um novo listener de comandos.
     */
    public SlashCommandListener(CommandManager commandManager){
        this.commandManager = commandManager;
    }
        @Override
        public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
            try {
                commandManager.handleCommand(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}
