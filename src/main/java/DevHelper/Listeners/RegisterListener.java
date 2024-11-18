package DevHelper.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
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
                .addField("Stacks", "Selecione as stacks que você trabalha.", false)
                .build();
        channel.sendMessageEmbeds(embed)
                .setComponents(
                        ActionRow.of(createAreaMenu()),
                        ActionRow.of(createStackMenu())
                )
                .queue();

    }

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
                .addOption("Java", "java")
                .addOption("JavaScript", "javascript")
                .addOption("Python", "python")
                .addOption("C#", "csharp")
                .addOption("C++", "cpp")
                .addOption("HTML", "html")
                .addOption("CSS", "css")
                .addOption("GO", "go")
                .addOption("PHP", "php")
                .addOption("Ruby", "ruby")
                .addOption("Kotlin", "kotlin")
                .addOption("Angular", "angular")
                .addOption("Typescript", "typescript")
                .setMinValues(1)
                .build();
    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if(event.getComponentId().equals("menu-areas") || event.getComponentId().equals("menu-stack")){
            handleRoleAssignment(event, event.getValues());
        }
    }

    private void handleRoleAssignment(StringSelectInteractionEvent event, List<String> selectedRoles) {
        Guild guild = event.getGuild();
        Member member = event.getMember();

        if (guild == null || member == null) {
            event.getHook().sendMessage("Ocorreu um erro.").setEphemeral(true).queue();
            return;
        }

        event.deferReply(true).queue();

        List<String> areaRoles = List.of("front-end", "back-end", "mobile", "full-stack");
        List<String> stackRoles = List.of("java", "javascript", "python", "csharp", "cpp", "html", "css", "go", "php", "ruby", "kotlin", "angular", "typescript");

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

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).thenRun(() -> {
            if (response.length() > 0) {
                event.getHook().sendMessage(response.toString()).setEphemeral(true).queue();
            } else {
                event.getHook().sendMessage("Nenhuma alteração de cargo foi feita.").setEphemeral(true).queue();
            }
        });
    }

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
