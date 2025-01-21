package DevHelper.Commands.AdminCommands;

import DevHelper.Config.Config;
import DevHelper.Config.ConfigDAO;
import DevHelper.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.EnumSet;
import java.util.Objects;

public class SetPomodoroRoleCommand implements ICommand {
    @Override
    public String getName() {
        return "setar-cargo-pomodoro";
    }

    @Override
    public String getDescription() {
        return "Define o cargo que o bot precisa para criar pomodoros";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Role role = Objects.requireNonNull(event.getOption("cargo")).getAsRole();
        EnumSet<Permission> permissions = role.getPermissions();
        if (permissions.contains(Permission.MANAGE_CHANNEL)) {
            Config config = ConfigDAO.getConfig();
            config.setPomodoroRole(role.getId());
            boolean saved = ConfigDAO.saveConfig(config);
            if (saved) {
                event.reply("Cargo para pomodoro definido!").setEphemeral(true).queue();
                return;
            }
            event.reply("Não foi possível definir o cargo por erro no banco de dados").setEphemeral(true).queue();
            return;
        }
        event.reply("Este cargo não pode ser usado, pois não possui permissão para GERENCIAR CANAIS").setEphemeral(true).queue();
    }
}
