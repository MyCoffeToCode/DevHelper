package DevHelper.Commands.StudyCommands.CourseListCommands;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DevHelper.DatabaseManager;
import DevHelper.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class GetCourseListCommand implements ICommand {
    @Override
    public String getName() {
        return "cursos";
    }

    @Override
    public String getDescription() {
        return "Listar os cursos sugeridos pela comunidade.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        // Consulta ao banco de dados
        String query = "SELECT title, url, category FROM courses ORDER BY title";

        // Buscar lista de cursos no banco de dados
        try (Connection conn = DatabaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Lista de Cursos")
                .setDescription("Aqui está a seleção de cursos que a comunidade sugeriu!");

            while (rs.next()) { // Se encontrou lista de cursos
                embed.addField(rs.getString("title"), rs.getString("url"), false);
            }

            embed.setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl())
                .setFooter("DevHelper - Seu assistente de desenvolvimento")
                .setColor(0x00FF00);

            event.replyEmbeds(embed.build()).queue();

        } catch (SQLException e) { // Tratar erros
            event.reply("Erro ao buscar a lista de cursos! 😢 Detalhes: " + e.getMessage()).setEphemeral(true).queue();
        }
    }
}
