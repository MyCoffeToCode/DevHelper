package DevHelper.Commands.FunCommands.CommandExercise;

import DevHelper.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandExercise implements ICommand {
    @Override
    public String getName() {
        return "exercise";
    }

    @Override
    public String getDescription() {
        return "Mostra um exercício aleatório";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Comando em desenvolvimento!").setEphemeral(true).queue();
    }
}
