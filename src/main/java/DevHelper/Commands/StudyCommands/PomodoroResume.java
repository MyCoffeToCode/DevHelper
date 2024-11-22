package DevHelper.Commands.StudyCommands;

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

        if(!event.getChannel().getId().equals("1307044379235713084")){
            event.reply("⚠️ Este comando só pode ser usado no canal permitido.").setEphemeral(true).queue();
            return;
        }

        PomodoroListener.resumePomodoro(event.getChannel().asTextChannel());
        event.reply("▶️ Método Pomodoro retomado!").setEphemeral(true).queue();
    }
}
