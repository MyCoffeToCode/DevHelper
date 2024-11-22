package DevHelper.Commands.Lavaplayer;

import DevHelper.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.IOException;

import DevHelper.Commands.Lavaplayer.PlayerManager;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class PlayerCommand implements ICommand {
    @Override
    public String getName() {
        return "tocar";
    }

    @Override
    public String getDescription() {
        return "Toca uma faixa de áudio!";
    }
    // Aqui é onde você vai colocar o código que será executado quando o comando for chamado
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping trackUrlOption = event.getOption("trackUrl");
        if (trackUrlOption == null) {
            event.reply("Você precisa fornecer um URL de faixa de áudio!").setEphemeral(true).queue();
            return;
        }
        PlayerManager.getInstance().loadAndPlay(event.getGuild(), trackUrlOption.getAsString());
        event.reply("Música carregada e tocando!").queue();
    }
}
