package DevHelper.Commands.FunCommands.MemeCommands;

import DevHelper.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SendMemeCommand implements ICommand {
    @Override
    public String getName() {
        return  "send-meme";
    }

    @Override
    public String getDescription() {
        return "Envie seu meme para aparecer no /meme";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        // Implementação do comando
        event.reply("Comando em desenvolvimento!").setEphemeral(true).queue();
    }
}
