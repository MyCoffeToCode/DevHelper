package DevHelper.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MenuInteractionListener extends ListenerAdapter {

    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if(!event.getComponentId().equals("command-menu")) return;

        EmbedBuilder embed = new EmbedBuilder();

        switch (event.getValues().get(0)) {
            case "Estudos" -> embed.setTitle("Comandos de Estudos")
                    .setDescription("Aqui estão os comandos voltados para aprendizado:\n" +
                            "`/startcode` - Roadmap de estudos\n" +
                            "`/pomodoro` - Modo produtividade\n" +
                            "`/links` - Links úteis para programação")
                    .setColor(0x65D8C5);
            case "Diversão" -> embed.setTitle("Comandos de Diversão")
                    .setDescription("Aqui estão os comandos para diversão:\n" +
                            "`/desafio` - Desafios de lógica\n" +
                            "`/curiosidade` - Curiosidades sobre programação\n" +
                            "`/meme` - Memes do mundo tech")
                    .setColor(0x65D8C5);
            case "Iniciante" -> embed.setTitle("Comandos para Iniciantes")
                    .setDescription("Aqui estão os comandos para iniciantes:\n" +
                            "`/linguagens` - Aprenda sobre linguagens básicas\n" +
                            "`/exercicios` - Exercícios simples\n" +
                            "`/dicas` - Dicas práticas para começar")
                    .setColor(0x65D8C5);
            case "GitHub" -> embed.setTitle("GitHub")
                    .setDescription("Acesse o repositório oficial do bot no GitHub:\n" +
                            "[Clique aqui](https://github.com/filipedhunior/DevHelper)")
                    .setColor(0x65D8C5);
            case "Menu" -> embed.setTitle("Menu Principal")
                    .setDescription("Volte ao menu principal para escolher uma opção.")
                    .setColor(0x65D8C5);
            default -> embed.setTitle("Erro").setDescription("Opção inválida!").setColor(0xFF0000);
        }

        event.replyEmbeds(embed.build()).setEphemeral(true).queue();
    }
}
