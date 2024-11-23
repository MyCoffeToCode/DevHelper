package DevHelper.Commands.StudyCommands.PomodoroCommands;

import DevHelper.ICommand;
import DevHelper.Listeners.PomodoroListener;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PomodoroCreateTicket implements ICommand {
    @Override
    public String getName() {
        return "criar-pomodoro";
    }

    @Override
    public String getDescription() {
        return "Cria um canal dedicado para seu pomodoro.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Guild guild = event.getGuild();
        Member member = event.getMember();

        if(guild != null && member != null){
            PomodoroListener.createPomodoroTicket(guild, member);
            event.reply("🍅 Canal do Pomodoro criado!").setEphemeral(true).queue();
        } else{
            event.reply("⚠️ Ocorreu um erro ao criar o canal.").setEphemeral(true).queue();
        }
    }
}
