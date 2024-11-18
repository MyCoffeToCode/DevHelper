package DevHelper;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface ICommand {

    /**
    *
    *   Retorna o nome do comando
    *   @return O nome do comando
    * */

    String getName();

    /**
    * Retorna a descrição do comando
    * @return A descrição do comando
    * */
    String getDescription();


    /**
    *   Método executado quando é chamado.
    *   @param event o Evento que contém as informações do comando
    * */

    void execute(SlashCommandInteractionEvent event);
}
