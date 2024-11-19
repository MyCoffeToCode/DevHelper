package DevHelper.Commands.FunCommand;

import DevHelper.ICommand;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class MemeCommand implements ICommand {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) throws IOException {
        // Lê o arquivo com os memes
        FileReader reader = new FileReader("/home/arthursousa/Documentos/CodeHelper_BOT/DevHelper - 16-11-2024/src/main/java/DevHelper/Commands/FunCommand/Memes.json");
        StringBuilder content = new StringBuilder(); // Cria um StringBuilder para armazenar o conteúdo do arquivo
        int i;
        while ((i = reader.read()) != -1) {
            content.append((char) i);
        } // Lê o conteúdo do arquivo
        reader.close(); // Fecha o arquivo

        // Converte o conteúdo do arquivo para um objeto JSON
        JSONObject json = new JSONObject(content.toString());
        JSONArray memes = json.getJSONArray("memes");

        // Pega um meme aleatório
        Random random = new Random();
        int index = random.nextInt(memes.length());

        String randomMeme = memes.getString(index);

        event.reply(randomMeme).queue(interactionHook -> {
            interactionHook.retrieveOriginal().queue(message -> {
                // Adiciona uma reação ao meme
                message.addReaction(Emoji.fromUnicode("U+1F602")).queue();
            });
        });
    }
}