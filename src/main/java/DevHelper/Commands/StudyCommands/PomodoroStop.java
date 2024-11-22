package DevHelper.Commands.StudyCommands;

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

        if(!event.getChannel().getId().equals("1307044379235713084")){
            event.reply("⚠️ Este comando só pode ser usado no canal permitido.").setEphemeral(true).queue();
            return;
        }

        PomodoroListener.stopPomodoro(event.getChannel().asTextChannel());
        event.reply("⏹️ Método Pomodoro interrompido!").queue();

    }
}
