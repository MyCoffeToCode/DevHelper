package DevHelper.Listeners;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.audit.ActionType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogsListener extends ListenerAdapter {
    private final Map<String, String> messageCache = new ConcurrentHashMap<>();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        messageCache.put(event.getMessageId(), event.getMessage().getContentRaw());
    }

    // Mensagem de Banimento
    @Override
    public void onGuildBan(@NotNull GuildBanEvent event) {
        String reason = event.getGuild().retrieveBan(event.getUser()).complete().getReason(); // Pega o motivo do banimento
        MessageEmbed embed = new EmbedBuilder()
                .setThumbnail(event.getUser().getAvatarUrl())
                .setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl())
                .setTitle("Banimento :no_entry_sign:")
                .setDescription("O usuário " + event.getUser().getAsMention() + " foi banido!")
                .addField("Motivo", reason != null ? reason : "Não especificado", false)
                .setFooter("ID do Usuário: " + event.getUser().getId(), null)
                .setTimestamp(java.time.OffsetDateTime.now().withNano(0).withSecond(0))
                .setColor(0xFF0000)
                .build();
        event.getGuild().getTextChannelById("1124423573994479737").sendMessageEmbeds(embed).queue(); // Envia a mensagem
    }

    // Mensagem de Desbanimento
    @Override
    public void onGuildUnban(@NotNull GuildUnbanEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setThumbnail(event.getUser().getAvatarUrl())
                .setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl())
                .setTitle("Desbanimento :white_check_mark:")
                .setDescription("O usuário " + event.getUser().getAsMention() + " foi desbanido!")
                .setFooter("ID do Usuário: " + event.getUser().getId(), null)
                .setTimestamp(java.time.OffsetDateTime.now().withNano(0).withSecond(0))
                .setColor(0x00FF00)
                .build();

        event.getGuild().getTextChannelById("1124423573994479737").sendMessageEmbeds(embed).queue(); // Envia a mensagem
    }

    // Evento de mensagem excluida
    @Override
    public void onMessageDelete(@NotNull MessageDeleteEvent event) {
        String messageId = event.getMessageId();
        String messageContent = messageCache.getOrDefault(messageId, "Conteúdo da mensagem não encontrado");
        String channelMention = event.getChannel().getAsMention();

        event.getGuild().retrieveAuditLogs().type(ActionType.MESSAGE_DELETE).limit(1).queue(logs -> {
            User user = logs.isEmpty() ? null : logs.get(0).getUser(); // Obtém o usuário que deletou a mensagem
            String userMention = user != null ? user.getAsMention() : "Desconhecido"; // Se o usuário for nulo, então é desconhecido

            MessageEmbed embed = new EmbedBuilder()
                    .setTitle("Mensagem de texto excluída :pencil:")
                    .setDescription("**Mensagem: \n\n**" + "```" + messageContent + "```" + "\nID: " + messageId + "\nCanal: " + channelMention + "\nDeletado por: " + userMention)
                    .setColor(0xFF0000) // Cor
                    .setAuthor(user != null ? user.getName() : "Desconhecido", null, user != null ? user.getAvatarUrl() : null)
                    .build();
            event.getGuild().getTextChannelById("1124423573994479737").sendMessageEmbeds(embed).queue();
        });
    }
}
