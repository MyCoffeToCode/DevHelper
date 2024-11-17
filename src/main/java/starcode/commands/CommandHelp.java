package starcode.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
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
                .setPlaceholder("Escolha uma opção")
                .addOption("Estudos", "Estudos", "Acesse comandos voltados para aprendizado")
                .addOption("Diversão", "Diversão", "Descubra comandos para se divertir")
                .addOption("Iniciante", "Iniciante", "Obtenha orientações para começar na programação")
                .addOption("GitHub", "GitHub", "Confira o repositório oficial no GitHub")
                .addOption("Menu", "Menu", "Volte ao menu principal")
                .build();

        MessageEmbed embed = new EmbedBuilder()
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
                .setFooter("Desenvolvido por StarCode", "https://cdn.discordapp.com/attachments/1307044379235713084/1307547647753850951/Design_sem_nome_1.png?ex=673ab424&is=673962a4&hm=48c4dab9266be43588b722fd5bda1823bac0f2617580090570c7d5d106a6162c&")
                .setThumbnail("https://cdn.discordapp.com/attachments/1307044379235713084/1307547647753850951/Design_sem_nome_1.png?ex=673ab424&is=673962a4&hm=48c4dab9266be43588b722fd5bda1823bac0f2617580090570c7d5d106a6162c&")
                .setColor(0x65D8C5)
                .build();

        event.replyEmbeds(embed).addActionRow(menu).queue();
    }

    public static void help(StringSelectInteractionEvent event) {
        StringSelectMenu menu = StringSelectMenu.create("command-menu")
                .setPlaceholder("Escolha uma opção")
                .addOption("Estudos", "Estudos", "Acesse comandos voltados para aprendizado")
                .addOption("Diversão", "Diversão", "Descubra comandos para se divertir")
                .addOption("Iniciante", "Iniciante", "Obtenha orientações para começar na programação")
                .addOption("GitHub", "GitHub", "Confira o repositório oficial no GitHub")
                .addOption("Menu", "Menu", "Volte ao menu principal")
                .build();

        MessageEmbed embed = new EmbedBuilder()
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
                .setFooter("Desenvolvido por StarCode", "https://cdn.discordapp.com/attachments/1307044379235713084/1307547647753850951/Design_sem_nome_1.png?ex=673ab424&is=673962a4&hm=48c4dab9266be43588b722fd5bda1823bac0f2617580090570c7d5d106a6162c&")
                .setThumbnail("https://cdn.discordapp.com/attachments/1307044379235713084/1307547647753850951/Design_sem_nome_1.png?ex=673ab424&is=673962a4&hm=48c4dab9266be43588b722fd5bda1823bac0f2617580090570c7d5d106a6162c&")
                .setColor(0x65D8C5)
                .build();

        event.replyEmbeds(embed).addActionRow(menu).queue();
    }

        @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if (!event.getComponentId().equals("command-menu")) return;

        MessageEmbed embed = null;
        switch (event.getValues().get(0)) {
            case "Estudos":
                event.editMessageEmbeds(new EmbedBuilder()
                        .setTitle("Comandos de Estudos")
                        .setDescription("Aqui estão os comandos voltados para aprendizado:\n\n" +
                                "`/startcode` - Roadmap de estudos\n" +
                                "`/pomodoro` - Modo produtividade\n" +
                                "`/links` - Links úteis para programação")
                        .setColor(0x65D8C5)
                        .build()).queue();
                break;
            case "Diversão":
                event.editMessageEmbeds(new EmbedBuilder()
                        .setTitle("Comandos de Diversão")
                        .setDescription("Aqui estão os comandos para se divertir:\n\n" +
                                "`/desafio` - Desafios de lógica\n" +
                                "`/curiosidade` - Curiosidades sobre programação\n" +
                                "`/meme` - Memes do mundo tech")
                        .setColor(0x65D8C5)
                        .build()).queue();
                break;
            case "Iniciante":
                event.editMessageEmbeds(new EmbedBuilder()
                        .setTitle("Comandos para Iniciantes")
                        .setDescription("Aqui estão os comandos para iniciantes:\n\n" +
                                "`/linguagens` - Aprenda sobre linguagens básicas\n" +
                                "`/exercicios` - Exercícios simples\n" +
                                "`/dicas` - Dicas práticas para começar")
                        .setColor(0x65D8C5)
                        .build()).queue();
                break;
            case "GitHub":
                event.editMessageEmbeds(new EmbedBuilder()
                        .setAuthor("DevHelper", null, "https://cdn.discordapp.com/attachments/1307044379235713084/1307547647753850951/Design_sem_nome_1.png?ex=673ab424&is=673962a4&hm=48c4dab9266be43588b722fd5bda1823bac0f2617580090570c7d5d106a6162c&")
                        .setThumbnail("https://cdn.discordapp.com/attachments/1307044379235713084/1307571062783082517/github_1.png?ex=673ac9f2&is=67397872&hm=2f87bd581054fa7ec2ce8ad7ebea95ce09248b3709257b4aaf9894ebd786ebf6&")
                        .setTitle("Sobre o DevHelper")
                        .setDescription(
                                "Este bot Discord, utilizando JDA, é projetado especialmente para apoiar e entreter **programadores** \uD83D\uDCBB, criando um ambiente no servidor onde a administração do Discord se alia ao incentivo ao **aprendizado de programação** \uD83D\uDCDA. O foco principal do bot é facilitar a **interação entre os membros** do servidor \uD83E\uDD1D, promovendo um espaço de **aprendizado e diversão** \uD83C\uDFAE voltado para **programadores de todos os níveis** \uD83D\uDC68\u200D\uD83D\uDCBB\uD83D\uDC69\u200D\uD83D\uDCBB."
                                )
                                .addField("Repositório no GitHub", "Confira o repositório oficial no GitHub para mais informações sobre o bot, incluindo o código-fonte, sugestões de melhorias e oportunidades para contribuir com novas funcionalidades.\n" + "[Clique aqui](https://github.com/filipedhunior/DevHelper)", false)
                        .setColor(0x65D8C5)
                        .build()).queue();
                break;
            case "Menu":
                help(event);
                break;
            default:
                embed = new EmbedBuilder()
                        .setTitle("Erro")
                        .setDescription("A opção selecionada não é válida!")
                        .setColor(0xFF0000)
                        .build();
                break;
        }

        if (embed != null) {
            event.replyEmbeds(embed).queue();
        } else {
            event.reply("Erro ao processar a seleção!").setEphemeral(true).queue();
        }
    }
}
