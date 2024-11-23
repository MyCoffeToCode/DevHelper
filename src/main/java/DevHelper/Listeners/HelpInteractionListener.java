package DevHelper.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelpInteractionListener extends ListenerAdapter {

    private static final Logger log = LoggerFactory.getLogger(HelpInteractionListener.class);

    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if(!event.getComponentId().equals("command-menu")) return;

        switch (event.getValues().get(0)) {
            case "Estudos" -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("Comandos de Estudos")
                    .setDescription("Aqui estão os comandos voltados para aprendizado:\n" +
                            "`/startcode` - Roadmap de estudos\n" +
                            "`/pomodoro` - Modo produtividade\n" +
                            "`/links` - Links úteis para programação")
                    .setColor(0x65D8C5)
                    .build()).queue();
            case "Diversão" -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("Comandos de Diversão")
                    .setDescription("Aqui estão os comandos para diversão:\n" +
                            "`/desafio` - Desafios de lógica\n" +
                            "`/curiosidade` - Curiosidades sobre programação\n" +
                            "`/meme` - Memes do mundo tech")
                    .setColor(0x65D8C5)
                    .build()).queue();
            case "Iniciante" -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("Comandos para Iniciantes")
                    .setDescription("Aqui estão os comandos para iniciantes:\n" +
                            "`/linguagens` - Aprenda sobre linguagens básicas\n" +
                            "`/exercicios` - Exercícios simples\n" +
                            "`/dicas` - Dicas práticas para começar")
                    .setColor(0x65D8C5)
                    .build()).queue();
            case "GitHub" -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("GitHub")
                    .setDescription("Acesse o repositório oficial do bot no GitHub:\n" +
                            "[Clique aqui](https://github.com/filipedhunior/DevHelper)")
                    .setColor(0x65D8C5)
                    .build()).queue();
            case "Menu" -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("Ajuda DevHelper")
                    .setDescription(
                            "Olá, eu sou a Lynx, mais conhecida como DevHelper, o bot projetado para transformar sua jornada na programação em uma experiência incrível! \uD83D\uDE80\n\n" +
                                    "Sou mais que um simples bot, sou sua parceira na jornada de aprendizado e desenvolvimento. Se precisar de algo, é só chamar com um comando e estarei pronta para ajudar. \uD83D\uDCBB\n\n" +
                                    "**\uD83D\uDCA1 Para visualizar os comandos, selecione abaixo uma das opções:**"
                    )
                    .addField(":one: Estudos", "Acesse comandos voltados para aprendizado, como ``/startcode`` (roadmap), ``/pomodoro`` (produtividade) e links úteis para programação.", false)
                    .addField(":two: Diversão", "Descubra comandos para descontrair, como pequenos desafios de lógica, curiosidades sobre programação e até memes do mundo da tecnologia!", false)
                    .addField(":three: Iniciante", "Obtenha orientações para começar do zero na programação, incluindo explicações sobre linguagens básicas, exercícios simples e dicas práticas.", false)
                    .addField(":four: GitHub", "\n" +
                            "Confira informações sobre o Bot no repositório oficial no GitHub, incluindo o código-fonte, sugestões de melhorias e oportunidades para contribuir com novas funcionalidades.", false)
                    .setFooter("Desenvolvido por StarCode", "https://cdn.discordapp.com/attachments/1308098418975182948/1308098746558578738/Design_sem_nome_1.png?ex=6740a9e4&is=673f5864&hm=d547c58795a35022ac9c7ced19a0b2544ec5a65d5d6065dccd35903fc5d87fdf&")
                    .setThumbnail("https://cdn.discordapp.com/attachments/1308098418975182948/1308098746558578738/Design_sem_nome_1.png?ex=6740a9e4&is=673f5864&hm=d547c58795a35022ac9c7ced19a0b2544ec5a65d5d6065dccd35903fc5d87fdf&")
                    .setImage("https://cdn.discordapp.com/attachments/1308098418975182948/1309343979346591846/standard_2.gif?ex=67413d1b&is=673feb9b&hm=812ae5ce71f8b9eed5460e32d3248c1f290fea705111f11053fc7182b3d413d4&")
                    .setColor(0x65D8C5)
                    .build()).queue();
            default -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("Erro")
                    .setDescription("Opção inválida!")
                    .setColor(0xFF0000)
                    .build()).queue();
        }
    }
}
