package DevHelper.Commands.StudyCommands.PomodoroCommands;

import DevHelper.ICommand;
import DevHelper.Listeners.PomodoroListener;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PomodoroStart implements ICommand {
    @Override
    public String getName() {
        return "pomodoro-start";
    }

    @Override
    public String getDescription() {
        return "Interrompe o método Pomodoro no canal atual.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        PomodoroListener.startPomodoro(event.getChannel().asTextChannel());
    }
}
