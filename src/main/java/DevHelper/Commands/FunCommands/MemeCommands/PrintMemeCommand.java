package DevHelper.Commands.FunCommands.MemeCommands;

import DevHelper.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import java.sql.Connection;
import DevHelper.DatabaseManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintMemeCommand implements ICommand {
    @Override
    public String getName() {
        return "meme";
    }

    @Override
    public String getDescription() {
        return "Mostra um meme aleatório";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        // Implementação do comando
        String memeUrl = null;
        String memeCategory = null;

        // Consulta ao banco de dados
        String query = "SELECT url, category FROM memes ORDER BY RANDOM() LIMIT 1";

        // Buscar um meme aleatório no banco de dados
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) { // Se encontrou um meme
                memeUrl = rs.getString("url");
                memeCategory = rs.getString("category");
            }
        } catch (SQLException e) { // Tratar erros
            event.reply("Erro ao buscar um meme! 😢 Detalhes: " + e.getMessage()).setEphemeral(true).queue();
            e.printStackTrace();
            return;
        }

        // Enviar o meme no Discord
        if (memeUrl != null) {  // Se encontrou um meme
            String message = (memeCategory != null)
                    ? "\n" + memeUrl
                    : memeUrl;

            event.reply(message).queue(); // Envia o meme no chat
        } else { // Se não encontrou um meme
            event.reply("Nenhum meme encontrado no banco! 🧐").setEphemeral(true).queue();
        }
    }
}
