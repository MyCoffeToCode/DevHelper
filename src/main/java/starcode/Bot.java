package starcode;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class Bot {
    public static void main(String[] args) {
        String token = "TOKEN";

        GatewayDiscordClient client = DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();

        client.on(MessageCreateEvent.class).subscribe(event -> {
            if (event.getMessage().getContent().equalsIgnoreCase("Oi")){
                event.getMessage().getChannel().block().createMessage("Ola").block();
            }
        });
    }
}
