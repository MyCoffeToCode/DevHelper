package DevHelper.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import DevHelper.ICommand;

public class CodeCommand implements ICommand {

    @Override
    public String getName() {
        return "codigo";
    }

    @Override
    public String getDescription() {
        return "Mostra um código do bot";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("https://github.com/filipedhunior/DevHelper").queue();
    }
}
