package DevHelper.Commands.FunCommands.MemeCommands;

import DevHelper.DatabaseManager;
import DevHelper.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

public class SendMemeCommand implements ICommand {
    @Override
    public String getName() {
        return  "send-meme";
    }

    @Override
    public String getDescription() {
        return "Envie seu meme para aparecer no /meme";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String url = Objects.requireNonNull(event.getOption("url")).getAsString();

        String query = "INSERT INTO memes VALUES ((SELECT MAX(id) + 1 FROM memes),?,?,?)";

        try(Connection conn = DatabaseManager.connect()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, url);
            stmt.setString(2, "Programação");
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            int result = stmt.executeUpdate();

            if (result != 0) {
                event.reply("Aeeê! Seu meme foi adicionado com sucesso! 🎉").setEphemeral(true).queue();
            } else {
                event.reply("Erro ao adicionar seu meme! 😢 Por favor, tenta outra vez.").setEphemeral(true).queue();
            }
        } catch (SQLException e) {
            event.reply("Erro ao adicionar o meme! 😢 Detalhes: " + e.getMessage()).setEphemeral(true).queue();
            throw new RuntimeException(e);
        }

    }
}
