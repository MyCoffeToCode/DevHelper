package DevHelper.Commands.StudyCommands;

import DevHelper.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Pomodoro implements ICommand {
    @Override
    public String getName() {
        return "pomodoro";
    }

    @Override
    public String getDescription() {
        return "Orientações sobre como usar os comando de Pomodoro.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Comandos de Pomodoro")
                .setDescription("O método Pomodoro é uma técnica de gerenciamento de tempo que consiste em dividir o trabalho em períodos de 25 minutos, separados por breves intervalos. O DevHelper possui comandos para te ajudar a manter o foco e a produtividade.")
                .addField("Comandos disponíveis:", """
                        `/pomodoro-start` - Inicia um ciclo de Pomodoro no canal atual.
                        `/pomodoro-pause` - Pausa o ciclo de Pomodoro no canal atual.
                        `/pomodoro-resume` - Retoma o ciclo de Pomodoro no canal atual.
                        `/pomodoro-stop` - Para o ciclo de Pomodoro no canal atual.
                        `/criar-pomodoro` - Criar seu canal de pomodoro.
                        `/fechar-pomodoro` - Fechar seu canal de pomodoro.
                        """, false)
                .addField("Instruções de uso:", """
                        1. Utiliza o comando `/criar-pomodoro` para criar um canal de Pomodoro.
                        2. Utitliza o comando `/pomodoro-start` para iniciar seu pomodoro.
                        3. Para pausar o Pomodoro, utilize o comando `/pomodoro-pause`.
                        4. Para retomar o Pomodoro, utilize o comando `/pomodoro-resume`.
                        5. Ao finalizar o ciclo, utilize o comando `/pomodoro-stop` para encerrar o Pomodoro.
                        6. Utilize o comando `/fechar-pomodoro` para fechar seu canal de Pomodoro.
                        """, false)
                .addField("Dicas:", "Recomenda-se utilizar o Pomodoro em um ambiente tranquilo e sem interrupções. Mantenha o foco na tarefa durante o ciclo de 25 minutos.", false)
                .setAuthor(event.getUser().getName(), null, event.getUser().getAvatarUrl())
                .setFooter("DevHelper - Seu assistente de desenvolvimento")
                .setColor(0x00FF00);

                event.replyEmbeds(embed.build()).queue();

    }
}
