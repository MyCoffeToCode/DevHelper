package DevHelper.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class PomodoroListener {

    private static final int WORK_DURATION = 25 * 60; // 25 minutos
    private static final int SHORT_BREAK = 5 * 60;    // 5 minutos
    private static final int LONG_BREAK = 15 * 60;   // 15 minutos
    private static final int CYCLES_BEFORE_LONG_BREAK = 4;

    private static int currentCycle = 0; // Ciclo atual do Pomodoro

    // Mapeamento de timers ativos
    private static final HashMap<Long, Timer> activeTimers = new HashMap<>();
    private static final HashMap<Long, Integer> pausedTimers = new HashMap<>();
    // Mapeia os canais e os momentos em que foram pausados
    private static final HashMap<Long, Long> pauseTimestamps = new HashMap<>();
    // Mapeia os canais e o tempo restante quando pausados
    private static final HashMap<Long, Integer> pausedDurations = new HashMap<>();


    private static final Long ALLOWED_CHANNEL_ID = 1307044379235713084L;

    public static void startPomodoro(TextChannel channel) {
        if(channel.getIdLong() != ALLOWED_CHANNEL_ID) {
            channel.sendMessage("⚠️ Este canal não é permitido para o método Pomodoro.").queue();
            return;
        }

        Timer existingTimer = activeTimers.remove(channel.getIdLong());
        if(existingTimer != null){
            existingTimer.cancel();
        }
        currentCycle = 0;
        runPomodoroCycle(channel, WORK_DURATION, true);
    }

    public static void stopPomodoro(TextChannel channel) {
        if(channel.getIdLong() != ALLOWED_CHANNEL_ID) {
            channel.sendMessage("⚠️ Este canal não é permitido para o método Pomodoro.").queue();
            return;
        }

        Timer timer = activeTimers.remove(channel.getIdLong());
        if (timer != null) {
            timer.cancel();
        } else {
            channel.sendMessage("⚠️ Nenhum Pomodoro ativo para este canal.").queue();
        }
    }

    public static void pausePomodoro(TextChannel channel){
        if(channel.getIdLong() != ALLOWED_CHANNEL_ID) {
            channel.sendMessage("⚠️ Este canal não é permitido para o método Pomodoro.").queue();
            return;
        }

        Timer timer = activeTimers.get(channel.getIdLong());
        if (timer != null) {
            timer.cancel();
            activeTimers.remove(channel.getIdLong());

            long timeLeft = pausedDurations.getOrDefault(channel.getIdLong(), 0);
            pauseTimestamps.put(channel.getIdLong(), System.currentTimeMillis());
            pauseTimestamps.put(channel.getIdLong(), timeLeft);
        } else {
            channel.sendMessage("⚠️ Nenhum Pomodoro ativo para este canal.").queue();
        }
    }

    public static void resumePomodoro(TextChannel channel){
        if(channel.getIdLong() != ALLOWED_CHANNEL_ID){
            channel.sendMessage("⚠️ Este canal não é permitido para o método Pomodoro.").queue();
            return;
        }

        Long pauseTime = pauseTimestamps.remove(channel.getIdLong());
        Integer previosDuration = pausedDurations.remove(channel.getIdLong());

        if(pauseTime != null && previosDuration != null){

            long elapsedTime = (System.currentTimeMillis() - pauseTime) / 1000;
            int timeLeft = Math.max(previosDuration - (int) elapsedTime, 0);

            if(timeLeft > 0){
                runPomodoroCycle(channel, timeLeft, currentCycle % 2 == 0);
            } else {
                handlePhaseEnd(channel, currentCycle % 2 == 0);
            }
        }
    }

    private static void runPomodoroCycle(TextChannel channel, int durationInSeconds, boolean isWorkPhase) {
        String phase = isWorkPhase ? "Trabalho" : "Pausa";
        Color phaseColor = isWorkPhase ? Color.RED : Color.GREEN;

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("🕒 Método Pomodoro")
                .setDescription(String.format("**Fase atual:** %s\nTempo restante: %d minutos", phase, durationInSeconds / 60))
                .setAuthor(channel.getGuild().getSelfMember().getEffectiveName(), null, channel.getGuild().getSelfMember().getUser().getAvatarUrl())
                .setColor(phaseColor);


        channel.sendMessageEmbeds(embed.build()).queue(message -> {
            Timer timer = new Timer();
            activeTimers.put(channel.getIdLong(), timer); // Armazena o timer para permitir interrupções

            pausedDurations.put(channel.getIdLong(), durationInSeconds);

            timer.scheduleAtFixedRate(new TimerTask() {
                int timeLeft = durationInSeconds;

                @Override
                public void run() {
                    if (timeLeft <= 0) {
                        timer.cancel();
                        activeTimers.remove(channel.getIdLong());
                        handlePhaseEnd(channel, isWorkPhase);
                    } else {
                        timeLeft -= 60; // Reduz o tempo restante em 1 minuto
                        updateEmbed(message, phase, timeLeft, phaseColor);
                    }
                }
            }, 0, 60000); // Atualiza a cada minuto
        });
    }

    private static void handlePhaseEnd(TextChannel channel, boolean isWorkPhase) {
        if (isWorkPhase) {
            currentCycle++;
            if (currentCycle == CYCLES_BEFORE_LONG_BREAK) {
                currentCycle = 0;
                runPomodoroCycle(channel, LONG_BREAK, false);
            } else {
                runPomodoroCycle(channel, SHORT_BREAK, false);
            }
        } else {
            runPomodoroCycle(channel, WORK_DURATION, true);
        }
    }

    private static void updateEmbed(Message message, String phase, int timeLeft, Color phaseColor) {
        EmbedBuilder updatedEmbed = new EmbedBuilder()
                .setTitle("🕒 Método Pomodoro")
                .setDescription(String.format("**Fase atual:** %s\nTempo restante: %d minutos", phase, timeLeft / 60))
                .setAuthor(message.getAuthor().getName(), null, message.getAuthor().getAvatarUrl())
                .setColor(phaseColor);

        message.editMessageEmbeds(updatedEmbed.build()).queue();
    }

}
