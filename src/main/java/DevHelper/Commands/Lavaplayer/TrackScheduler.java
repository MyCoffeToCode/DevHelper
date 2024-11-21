package DevHelper.Commands.Lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventListener;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;
/*
*  Classe que gerencia as faixas de áudio
* */
public class TrackScheduler extends com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter implements AudioEventListener {
  private final AudioPlayer player; // O player de áudio
  private final BlockingQueue<AudioTrack> queue;

  // Construtor
  public TrackScheduler(AudioPlayer player){
    this.player = player;
    this.queue = new LinkedBlockingQueue<>();
  }
  // Adiciona uma faixa de áudio à fila
  public void queue(AudioTrack track){
    if(!player.startTrack(track, true)){
      queue.offer(track); // Adiciona a faixa à fila
    }
  }
  @Override
  // Método chamado quando uma faixa termina
  public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
      if (endReason.mayStartNext) {
            player.startTrack(queue.poll(), false); // Toca a próxima faixa
        }
    }
}
