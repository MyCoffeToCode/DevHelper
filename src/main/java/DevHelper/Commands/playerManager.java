package DevHelper.Commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;


public class PlayerManager {
    private static PlayerManager INSTANCE;
    private final AudioPlayerManager playerManager;
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
        long guildId = Long.parserLong(guild.getId()); // Obtém o ID do servidor
        GuildMusicManager musicManager = musicManagers.get(guildId); // Obtém o gerenciador de música do servidor

        // Se o gerenciador de música não existir, cria um novo e o adiciona ao mapa
        if (musicManager == null){
            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guildId, musicManager);
        }
        //
        guild.getAudioManager().setSendindHandler(musicManager.getSendHandler());
        return musicManager;
    }
    // Método para carregar uma música
    public void longAndPlay(Guild guild, String trackUrl){
        GuildMusicManager.msicManager = getGuildMusicManager(guild);
        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler());

        /**
         * Método chamado quando uma música é carregada.
         */
        /
        @Override
        public void trackLoaded(AudioTrack track){
            musicManager.scheduler.queue(track);
        }
        @Override
        public void playlistLoaded(AudioPlaylist playlist){
            for (AudioTrack track : playlist.getTracks()){
                musicManager.scheduler.queue(track);
            }
        }
        @Override
        public void noMathes(){
            System.out.println("Nenhuma música encontrada.");
        }
        @Override
        public void loadFailed(Exception exception){
            System.out.println("Falha ao carregar a música.");
        }
    }
}
