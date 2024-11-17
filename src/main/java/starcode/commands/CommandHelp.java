package starcode.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class CommandHelp extends ListenerAdapter {
    // Método de ajuda
    /*
        Este método é chamado sempre que o comando /help é executado.
        Ele cria um menu de seleção com os comandos disponíveis e
        mostra uma mensagem de ajuda com a lista de comandos.
    */
    public static void help(SlashCommandInteractionEvent event) {
        StringSelectMenu menu = StringSelectMenu.create("command-menu")
                .setPlaceholder("Escolha um comando")
                .addOption("Ping", "ping", "Responde com 'Pong!'")
                .addOption("Help", "help", "Mostra esta mensagem de ajuda")
                .build();

        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Lista de comandos")
                .setDescription("Aqui está a lista de comandos disponíveis:")
                .addField("/ping", "Responde com 'Pong!'", false)
                .addField("/help", "Mostra esta mensagem de ajuda", false)
                .setColor(0x65D8C5)
                .build();

        event.replyEmbeds(embed).addActionRow(menu).queue();
    }
}
