package DevHelper;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, ICommand> commands = new HashMap<>();

    /**
    *
    * Registra um novo comando.
    * @param command O comando a ser registrado
    *
    * */

    public void registerCommand(ICommand command){
        commands.put(command.getName(), command);
    }

    /**
     * Obtém um comando pelo nome.
     *
     * @param name O nome do comando.
     * @return O comando correspondente, ou null se não existir.
     */

    public ICommand getCommand(String name){
        return commands.get(name);
    }

    /**
     * Processa um evento de interação e executa o comando correspondente.
     *
     * @param event O evento de interação.
     */

    public void handleCommand(SlashCommandInteractionEvent event){
        ICommand command = commands.get(event.getName());
        if(command != null) {
            command.execute(event);
        } else {
            event.reply("Comando não encontrado!").setEphemeral(true).queue();
        }
    }


}
