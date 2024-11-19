package DevHelper;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    /**
    *
    * Registra um novo comando.
    * @param command O comando a ser registrado
    *
    * */

    public void registerCommand(ICommand command){
        commands.add(command);
    }

    /**
     * Obtém um comando pelo nome.
     *
     * @return O comando correspondente, ou null se não existir.
     */

    public List<ICommand> getCommands(){
        return commands;
    }

    /**
     * Processa um evento de interação e executa o comando correspondente.
     *
     * @param event O evento de interação.
     */

    public void handleCommand(SlashCommandInteractionEvent event){
        String commandName = event.getName(); // Nome do comando executado
        ICommand foundCommand = null;

        // Busca o comando correspondente na lista
        for (ICommand command : commands) {
            if (command.getName().equalsIgnoreCase(commandName)) {
                foundCommand = command;
                break;
            }
        }
        // Executa o comando ou responde com erro
        if (foundCommand != null) {
            foundCommand.execute(event);
        } else {
            event.reply("Comando não encontrado!").setEphemeral(true).queue();
        }
    }


}
