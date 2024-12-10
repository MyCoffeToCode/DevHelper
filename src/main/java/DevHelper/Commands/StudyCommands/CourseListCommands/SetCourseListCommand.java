package DevHelper.Commands.StudyCommands.CourseListCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DevHelper.DatabaseManager;
import DevHelper.ICommand;
import DevHelper.Utils.BannedWords;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SetCourseListCommand implements ICommand {
    @Override
    public String getName() {
        return "adicionar-curso";
    }

    @Override
    public String getDescription() {
        return "Permite que você adicione um curso à lista de cursos";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String title = event.getOption("título").getAsString().toLowerCase();
        String category = event.getOption("categoria").getAsString().toLowerCase();
        String url = event.getOption("url").getAsString().toLowerCase();

        var bannedWords = new BannedWords();

        if (bannedWords.WordIsValid(title) &&
            bannedWords.WordIsValid(category) &&
            bannedWords.WordIsValid(url)) {

            // Inserindo os inputs no banco de dados
            String query = "INSERT INTO courses VALUES ((SELECT MAX(id) + 1 FROM courses),?,?,?,0)";
    
            // Buscar lista de cursos no banco de dados
            try (Connection conn = DatabaseManager.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {
                
                stmt.setString(1, title);
                stmt.setString(2, url);
                stmt.setString(3, category);
    
                var result = stmt.executeUpdate();
    
                if (result == 0)
                    event.reply("Erro ao adicionar o curso! 😢 Por favor, tenta outra vez.").setEphemeral(true).queue();
                else
                    event.reply("Aeeê! Seu curso foi adicionado com sucesso! 🎉").setEphemeral(true).queue();
    
            } catch (SQLException e) { // Tratar erros
                event.reply("Erro ao adicionar o curso! 😢 Detalhes: " + e.getMessage()).setEphemeral(true).queue();
            }
        } else {
            event.reply("Oh não! 😢 Os dados que você está tentando cadastrar são inválidos. Dá uma olhadinha se não tem nada errado nisso aí, por favor.").setEphemeral(true).queue();
        }

    }
}
