package starcode.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandBump extends ListenerAdapter {
    // Método de bump
    /*
        Este método é chamado sempre que o comando /bump é executado.
        Ele responde com "Hello!".
    */
    public static void bump(SlashCommandInteractionEvent event) {
        event.reply("/bump").queue();
    }
}
