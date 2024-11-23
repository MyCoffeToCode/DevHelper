package DevHelper.Commands.StudyCommands.PomodoroCommands;

import DevHelper.ICommand;
import DevHelper.Listeners.PomodoroListener;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PomodoroPause implements ICommand {
    @Override
    public String getName() {
        return "pomodoro-pause";
    }

    @Override
    public String getDescription() {
        return "Pausa o método Pomodoro no canal atual.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        PomodoroListener.pausePomodoro(event.getChannel().asTextChannel());
        event.reply("⏸️ Método Pomodoro pausado!").setEphemeral(true).queue();
    }
}
