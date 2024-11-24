package DevHelper.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class RuleListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        /*
         *
         * @Author OyakXD
         *
         *  Aqui o código iŕa enviar uma mensagem de regras no canal de texto escolhido do servidor
         *
         *
         * */
        Guild guild = event.getJDA().getGuildById("1120887160288051211");
        if (guild == null) return;

        TextChannel channel = guild.getTextChannelById("1307044379235713084");
        if (channel == null) return;

        // Verifica se já existe uma mensagem com o título específico no canal
        channel.getHistory().retrievePast(50).queue(messages -> {
            boolean messageExists = messages.stream().anyMatch(message ->
                    message.getEmbeds().stream().anyMatch(embed ->
                            "\uD83D\uDCDC REGRAS DO SERVIDOR \uD83D\uDCDC".equals(embed.getTitle())
                    )
            );

            if (!messageExists) {
                // Cria e envia a mensagem, pois não foi encontrada no histórico
                MessageEmbed embed = new EmbedBuilder()
                        .setTitle("\uD83D\uDCDC REGRAS DO SERVIDOR \uD83D\uDCDC")
                        .setDescription("Olá, bem-vindo(a) ao canal de regras do nosso servidor! Aqui você encontrará as orientações para garantir uma experiência positiva. Por favor, leia atentamente as regras abaixo para evitar a punição de banimento permanente.\n\n")
                        .setColor(Color.BLUE)
                        .setThumbnail("https://images-ext-1.discordapp.net/external/YCNct9j_z--cx-tHV1tscCaYCktmilhrb4kkztcALO0/https/cdn-icons-png.flaticon.com/512/2279/2279095.png?format=webp&quality=lossless")
                        .addField(
                                "\uD83D\uDCCC Regras Gerais \uD83D\uDCCC \n", """
                                        • Respeite todos os membros do servidor. Evite o uso de xingamentos e insultos.
                                        • Utilize os canais apropriados para postar conteúdo ou buscar informações sobre cargos.
                                        • Evite mencionar ou marcar outros membros sem motivo relevante, seja da equipe ou não.
                                        • Não compartilhe ou discuta imagens de natureza sexual relacionadas a temas como Furry ou Loli.\n
                        """, false
                        )
                        .addField(
                                "\uD83D\uDEAB Proibições \uD83D\uDEAB \n", """
                                        • Racismo, discriminação ou qualquer forma de preconceito não serão tolerados.
                                        • Não permitimos ideologias nazistas ou qualquer forma de apologia ao ódio.
                                        • A homofobia e qualquer tipo de discriminação com base na orientação sexual são estritamente proibidas.
                                        • Não compartilhe vídeos ou imagens contendo violência explícita ou gore.
                                        • Não compartilhe links falsos que possam ser considerados maliciosos.
                                        • Conteúdo pornográfico, incluindo ecchi e hentai, é estritamente proibido.
                                        • Evite o uso de linguagem explícita ou discussões excessivamente vulgares.
                                        • Siga as instruções e orientações fornecidas pelos membros da equipe de moderação.\n
                        """, false
                        )
                        .addField(
                                "\uD83D\uDCAC Chat Principal \uD83D\uDCAC \n", """
                                       • Evite discussões e brigas desnecessárias que possam perturbar outros membros.
                                       • Não faça spam ou envie mensagens repetitivas no chat.
                                       • Evite spoilers sem aviso prévio ou sem utilizar a formatação adequada.\n
                        """, false
                        )
                        .addField(
                                "\uD83D\uDD09 Canais de Voz \uD83D\uDD09 \n", """
                                       • Evite abuso verbal, como o uso de xingamentos ou linguagem ofensiva.
                                      
                                       • Não faça ruídos altos ou irritantes que possam perturbar os outros usuários.
                                        
                                       • Não atrapalhe as conversas ou jogos em andamento das outras pessoas.\n
                        """, false
                        )
                        .addField(
                                "⚠\uFE0F ATENÇÃO! ⚠\uFE0F \n", """
                                       Se alguém estiver infringindo as regras ou atrapalhando seu uso do Discord, contate a Staff. Os envolvidos serão advertidos, se o problema persistir sofrerão punições mais severas! Expulsões e Banimentos farão com que você perca seus cargos!
                                        
                                       Agradecemos por seguir as regras e contribuir para a construção de um ambiente saudável e acolhedor para todos. Divirta-se e aproveite sua estadia em nosso servidor de programação!
                        """, false
                        )
                        .setFooter("Desenvolvido por StarCode", "https://cdn.discordapp.com/attachments/1308098418975182948/1308098746558578738/Design_sem_nome_1.png?ex=6740a9e4&is=673f5864&hm=d547c58795a35022ac9c7ced19a0b2544ec5a65d5d6065dccd35903fc5d87fdf&")
                        .build();

                channel.sendMessageEmbeds(embed).queue();
            }
        });
    }
}
