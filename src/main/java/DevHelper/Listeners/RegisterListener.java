package DevHelper.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RegisterListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event){

        /*
        *
        * @Author OyakXD
        *
        *  Aqui o código iŕa enviar uma mensagem de boas vindas no canal de texto escolhido do servidor
        *  e também verifica se o canal e o servidor existem. Logo ele criará a mensagem de boas vindas.
        *
        * */
        Guild guild = event.getJDA().getGuildById("1120887160288051211");
        if(guild == null) return;

        TextChannel channel = guild.getTextChannelById("1307044379235713084");
        if(channel == null) return;

        MessageEmbed embed = new EmbedBuilder()
                .setTitle("✨ Registra-se ✨")
                .setDescription("Olá, eu sou o DevHelper, um bot desenvolvido para ajudar vocês em suas dúvidas e problemas com programação. \n\n" +
                        "Para começar a usar o bot, digite `!help` para ver todos os comandos disponíveis.")
                .setColor(new Color(128,0,255))
                .setThumbnail("https://media.discordapp.net/attachments/1308098418975182948/1308098747376336926/AlbedoBase_XL_A_minimalist_logo_design_that_represents_coding_2_1.jpg?ex=673cb564&is=673b63e4&hm=a0dcb0fbb4ecd43cc8619a37de3b267f36b4e78d65f88f31cd5065740a223d3c&=&format=webp&width=586&height=586")
                .addField("🔧 Cargos", "Para adicionar cargos, clique nos menus abaixo.", false)
                .addField("\uD83D\uDCBC  Stacks", "Selecione as stacks que você trabalha.", false)
                .build();
        channel.sendMessageEmbeds(embed)
                .setComponents(
                        ActionRow.of(createAreaMenu()),
                        ActionRow.of(createStackMenu())
                )
                .queue();

    }

    /*
    *
    * Nessa seção do código, o bot irá criar um menu de seleção para que o usuário possa escolher
    * no caso eu fiz duas opções, uma para escolher a área de atuação e outra para escolher as stacks
    *
    * */

    private StringSelectMenu createAreaMenu() {
        return StringSelectMenu.create("menu-areas")
                .setPlaceholder("Selecione suas áreas de atuação")
                .addOption("Front-end", "front-end")
                .addOption("Back-end", "back-end")
                .addOption("Mobile", "mobile")
                .addOption("Full-Stack", "full-stack")
                .build();
    }

    private StringSelectMenu createStackMenu(){
        return StringSelectMenu.create("menu-stack")
                .setPlaceholder("Selecione suas stacks")
                .addOption("Java", "java",  "", Emoji.fromCustom("java", 1299842354190225419L, false))
                .addOption("JavaScript", "javascript", "", Emoji.fromCustom("javascript", 1299842348469059674L, false))
                .addOption("Python", "python", "", Emoji.fromCustom("python", 1299845020429324308L, false))
                .addOption("C#", "csharp", "",  Emoji.fromCustom("csharp", 1299842356215943310L, false ))
                .addOption("C++", "cpp", "", Emoji.fromCustom("cpp", 1299842357977551049L, false))
                .addOption("GO", "go", "", Emoji.fromCustom("go", 1299842339761684520L, false))
                .addOption("PHP", "php", "", Emoji.fromCustom("php", 1299842342081269862L, false))
                .addOption("Ruby", "ruby", "", Emoji.fromCustom("ruby", 1299842369310691390L, false))
                .addOption("Kotlin", "kotlin", "", Emoji.fromCustom("kotlin", 1299842364017479742L, false))
                .addOption("Angular", "angular", "", Emoji.fromCustom("angular", 1299781275980140554L, false))
                .addOption("Typescript", "typescript", "", Emoji.fromCustom("typescript", 1299842344753037442L, false))
                .addOption("ReactJs", "reactjs", "", Emoji.fromCustom("reactjs", 1299842366848499792L, false))
                .addOption("NodeJS", "nodejs", "", Emoji.fromCustom("nodejs", 1299842352961028106L, false))
                .setMinValues(1)
                .build();
    }

    /*
    *
    * Nessa parte do codigo, o bot irá verificar se o usuário selecionou as opções corretas
    *
    * */

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if(event.getComponentId().equals("menu-areas") || event.getComponentId().equals("menu-stack")){
            handleRoleAssignment(event, event.getValues());
        }
    }

    private void handleRoleAssignment(StringSelectInteractionEvent event, List<String> selectedRoles) {
        Guild guild = event.getGuild();
        Member member = event.getMember();

        // Verifica se o servidor é nulo ou o membro é nulo
        if (guild == null || member == null) {
            event.getHook().sendMessage("Ocorreu um erro.").setEphemeral(true).queue();
            return;
        }

        // Defer reply para evitar timeout
        event.deferReply(true).queue();

        // Aqui ele cria uma lista de cargos que o bot irá verificar, das duas opções de menu.
        List<String> areaRoles = List.of("front-end", "back-end", "mobile", "full-stack");
        List<String> stackRoles = List.of("java", "javascript", "python", "csharp", "cpp", "html", "css", "go", "php", "ruby", "kotlin", "angular", "typescript", "reactjs", "nodejs");

        // Aqui ele cria um mapa de cargos, onde ele irá verificar se o cargo existe no servidor
        Map<String, Role> roleMap = new HashMap<>();
        for (String roleName : areaRoles) {
            Role role = guild.getRolesByName(roleName, true).stream().findFirst().orElse(null);
            if (role != null) {
                roleMap.put(roleName, role);
            }
        }
        for (String roleName : stackRoles) {
            Role role = guild.getRolesByName(roleName, true).stream().findFirst().orElse(null);
            if (role != null) {
                roleMap.put(roleName, role);
            }
        }

        StringBuilder response = new StringBuilder();
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        // Nesse for ele irá verificar se o cargo existe no servidor, se existir ele irá adicionar ou remover o cargo do usuário
        for (String roleName : selectedRoles) {
            Role role = roleMap.get(roleName);
            if (role != null) {
                if (!member.getRoles().contains(role)) {
                    futures.add(addRoleToMember(guild, member, role, response));
                } else {
                    futures.add(removeRoleFromMember(guild, member, role, response));
                }
            }
        }

        // Nessa parte do código ele irá verificar se o cargo foi atribuído ou removido, e irá enviar uma mensagem para o usuário
        // Esse CompletableFuture é para esperar todas as operações de adicionar/remover cargos serem completadas
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).thenRun(() -> {
            if (!response.isEmpty()) {
                event.getHook().sendMessage(response.toString()).setEphemeral(true).queue();
            } else {
                event.getHook().sendMessage("Nenhuma alteração de cargo foi feita.").setEphemeral(true).queue();
            }
        });
    }

    // Essa função irá adicionar o cargo ao usuário
    private CompletableFuture<Void> addRoleToMember(Guild guild, Member member, Role role, StringBuilder response) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        guild.addRoleToMember(member, role).queue(
                success -> {
                    response.append("Cargo Atribuído: ").append(role.getName()).append("\n");
                    future.complete(null);
                },
                error -> {
                    response.append("Erro ao atribuir cargo: ").append(role.getName()).append("\n");
                    future.complete(null);
                }
        );
        return future;
    }

    // E nessa irá remover
    private CompletableFuture<Void> removeRoleFromMember(Guild guild, Member member, Role role, StringBuilder response) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        guild.removeRoleFromMember(member, role).queue(
                success -> {
                    response.append("Cargo Removido: ").append(role.getName()).append("\n");
                    future.complete(null);
                },
                error -> {
                    response.append("Erro ao remover cargo: ").append(role.getName()).append("\n");
                    future.complete(null);
                }
        );
        return future;
    }
}
