package DevHelper.Commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventListener;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TrackScheduler extends com.sedmelluq.discord.lavaplayer.event.AudioEventAdapter implements AudioEventListener {
  private final AudioPlayer player; // O player de áudio
  private final BlockinkQueue<AudioTrack> queue;

  public TrackScheduler(AudioPlayer player){
    this.player = player;
    this.queue = new LinkedBlockingQueue<>();
  }
  public void queue(AudioTrack track){
    if(!player.startTrack(track, true)){
      queue.offer(track);
    }
  }
  @Override
  public void onTrackEnd(AudioTrack player, AudioTrack track, AudioTrackEndReason endReason){
    if (endReason.mayStartNext){
      player.startTrack(queue.poll(), false);
    }
  }

  @Override
  public void onEvent(AudioEvent audioEvent) {

  }
}
