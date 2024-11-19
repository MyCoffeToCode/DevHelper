package DevHelper.Commands;

import DevHelper.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.IOException;
import DevHelper.ICommand;
import DevHelper.playerManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class playerCommand implements ICommand {
    @Override
    public String getName() {
        return "player";
    }

    @Override
    public String getDescription() {
        return "Aqui tem informações sobre o player";
    }
    // Aqui é onde você vai colocar o código que será executado quando o comando for chamado
    @Override
    public void execute(SlashCommandInteractionEvent event) throws IOException {
        String trackUrl = event.getOption("trackUrl").getAsString();
        PlayerManager.getInstance().loadAndPlay(event.getGuild(), trackUrl);
        event.reply("Música carregada e tocando!").queue();
    }
}
