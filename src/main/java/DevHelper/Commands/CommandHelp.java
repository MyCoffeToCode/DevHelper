package DevHelper.Commands;

import DevHelper.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class CommandHelp implements ICommand {

        @Override
        public String getName() {
            return "help";
        }

        @Override
        public String getDescription() {
            return "Mostra os comandos disponíveis";
        }

        @Override
        public void execute(SlashCommandInteractionEvent event) {
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

            event.replyEmbeds(embed).addActionRow(menu).setEphemeral(true).queue();
        }
}
