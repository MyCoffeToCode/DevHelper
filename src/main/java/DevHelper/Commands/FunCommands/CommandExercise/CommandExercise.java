package DevHelper.Commands.FunCommands.CommandExercise;

import DevHelper.DatabaseManager;
import DevHelper.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandExercise implements ICommand {
    @Override
    public String getName() {
        return "exercise";
    }

    @Override
    public String getDescription() {
        return "Mostra um exercício aleatório";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String linguagem = event.getOption("linguagem").getAsString().toLowerCase();
        String dificuldade = event.getOption("dificuldade").getAsString().toLowerCase();

        String textDifficulty = "";

        if (dificuldade.equalsIgnoreCase("fácil")) {
            textDifficulty = ":green_square:";
        } else if (dificuldade.equalsIgnoreCase("médio")) {
            textDifficulty = ":yellow_square:";
        } else if (dificuldade.equalsIgnoreCase("difícil")) {
            textDifficulty = ":red_square:";
        }

        String query = "SELECT title, exercise FROM exercises WHERE language = ? AND difficulty = ? ORDER BY RANDOM() LIMIT 1";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, linguagem);
            stmt.setString(2, dificuldade);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String exercise = rs.getString("exercise");

                MessageEmbed embed = new EmbedBuilder()
                        .setTitle("Exercício de " + linguagem + " - " + dificuldade)
                        .setDescription("Aqui está um exercício para você praticar! 🚀")
                        .addField("Linguagem:", "``" + linguagem + "``", true)
                        .addField("Dificuldade:", "``" + dificuldade + "``" + " " + textDifficulty, true)
                        .addField("Título:", title, false)
                        .addField("Exercício:\n", "```" + exercise + "```", false)
                        .setColor(getColorByDifficulty(dificuldade))
                        .build();

                event.replyEmbeds(embed).queue();
            } else {
                System.out.println("Nenhum exercício encontrado para a linguagem e dificuldade especificadas.");
                event.reply("Nenhum exercício encontrado para a linguagem e dificuldade especificadas! 🧐").setEphemeral(true).queue();
            }
        } catch (SQLException e) {
            event.reply("Erro ao buscar o exercício! 😢 Detalhes: " + e.getMessage()).setEphemeral(true).queue();
            e.printStackTrace();
        }
    }

    private int getColorByDifficulty(String dificuldade) {
        switch (dificuldade.toLowerCase()) {
            case "fácil":
                return 0x00FF00; // Verde
            case "médio":
                return 0xFFFF00; // Amarelo
            case "difícil":
                return 0xFF0000; // Vermelho
            default:
                return 0x65D8C5; // Cor padrão
        }
    }
}