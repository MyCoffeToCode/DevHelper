package DevHelper.Commands.Lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import java.util.Map;

import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;

public class PlayerManager {
    private static PlayerManager INSTANCE; // Instância única da classe PlayerManager
    private final AudioPlayerManager playerManager; // Gerenciador de players de áudio
    private final Map<Long, GuildMusicManager> musicManagers;

    /**
     * Construtor privado da classe PlayerManager.
     * Inicializa os gerenciadores de música e o gerenciador de players de áudio.
     * Registra as fontes de áudio remotas e locais.
     */
    private PlayerManager(){
        this.musicManagers = new HashMap<>();
        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }
    // Método para obter a instância da classe PlayerManager
    public synchronized static PlayerManager getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }
    public synchronized GuildMusicManager getGuildMusicManager(Guild guild){
        long guildId = Long.parseLong(guild.getId()); // Obtém o ID do servidor
        GuildMusicManager musicManager = musicManagers.get(guildId); // Obtém o gerenciador de música do servidor

        // Se o gerenciador de música não existir, cria um novo e o adiciona ao mapa
        if (musicManager == null){
            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guildId, musicManager);
        }
        //
        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
        return musicManager;
    }
    // Método para carregar e tocar uma faixa de áudio
    public void loadAndPlay(Guild guild, String trackUrl){
        //
        GuildMusicManager musicManager = getGuildMusicManager(guild);
        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                musicManager.scheduler.queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();
                if (firstTrack == null){
                    firstTrack = playlist.getTracks().get(0);
                }
                musicManager.scheduler.queue(firstTrack);
            }

            @Override
            public void noMatches() {
                // guild.getDefaultChannel().sendMessage("Falha ao carregar a faixa" + exception.getMessage()).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                // Notify the user that everything exploded
            }
        });
    }
}
