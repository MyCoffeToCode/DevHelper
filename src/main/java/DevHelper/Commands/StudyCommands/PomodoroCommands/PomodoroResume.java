package DevHelper.Commands.StudyCommands.PomodoroCommands;

import DevHelper.ICommand;
import DevHelper.Listeners.PomodoroListener;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PomodoroResume implements ICommand {
    @Override
    public String getName() {
        return "pomodoro-resume";
    }

    @Override
    public String getDescription() {
        return "Retoma o método Pomodoro no canal atual.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        PomodoroListener.resumePomodoro(event.getChannel().asTextChannel());
        event.reply("▶️ Método Pomodoro retomado!").setEphemeral(true).queue();
    }
}
