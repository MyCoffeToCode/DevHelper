package starcode.listeners;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import starcode.commands.CommandHelp;
import starcode.commands.CommandPing;

public class SlashCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Identificar o comandos e chamar o método correspondente
        /*
            Sempre que um comando é executado, o evento é chamado e o
            método onSlashCommandInteraction é executado.
        */
        if (event.getName().equals("ping")) {
            CommandPing.ping(event);
        } else if (event.getName().equals("help")) {
            CommandHelp.help(event);
        }
    }
}
