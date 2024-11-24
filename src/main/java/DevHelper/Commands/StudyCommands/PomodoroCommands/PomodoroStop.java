package DevHelper.Commands.StudyCommands.PomodoroCommands;

import DevHelper.ICommand;
import DevHelper.Listeners.PomodoroListener;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PomodoroStop implements ICommand {
    @Override
    public String getName() {
        return "pomodoro-stop";
    }

    @Override
    public String getDescription() {
        return "Interrompe o método Pomodoro no canal atual.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Pomodoro Encerrado com sucesso!").setEphemeral(true).queue();
        PomodoroListener.stopPomodoro(event.getChannel().asTextChannel());
    }
}
