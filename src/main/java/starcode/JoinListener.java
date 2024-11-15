package starcode;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;

public class JoinListener extends ListenerAdapter{
    //@Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        String welcomeChannelId = "IDCANAL";
        event.getGuild().getTextChannelById(welcomeChannelId)
            .sendMessage("Bem-vindo ao servidor!")
            .queue();
    }
}

