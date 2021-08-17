package cz.ples12.discordbot.impl.command

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import cz.ples12.discordbot.BotMain
import cz.ples12.discordbot.api.command.Command
import cz.ples12.discordbot.api.command.CommandInfo
import cz.ples12.discordbot.api.utils.LavaplayerAudioSource
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent
import java.awt.Color

@CommandInfo(names = ["play", "p"],description =  "sends random message (ping / pong)")
class CommandPlay : Command() {

    override fun execute(args: Array<String>, event: MessageCreateEvent) {
        val channel = event.messageAuthor.connectedVoiceChannel.orElse(null)

        if (channel == null){
            val embed = EmbedBuilder()

            embed.setAuthor("Not connected")

            embed.setDescription("You have to be connected in voice channel")

            embed.setFooter(BotMain.name + " v" + BotMain.version)

            embed.setColor(Color.RED)

            event.channel.sendMessage(embed)

        }

        if (args.size == 1){
            val embed = EmbedBuilder()

            embed.setAuthor("Link not found")

            embed.setDescription("You didn't specify the youtube link")

            embed.setFooter(BotMain.name + " v" + BotMain.version)

            embed.setColor(Color.RED)

            event.channel.sendMessage(embed)

        }else {
            val embed = EmbedBuilder()

            embed.setAuthor("Playing song")

            embed.setDescription("Playing " + args[1])

            embed.setFooter(BotMain.name + " v" + BotMain.version)

            embed.setColor(Color.GREEN)

            event.channel.sendMessage(embed)
            channel?.connect()?.thenAccept { audioConnection ->
                val playerManager = DefaultAudioPlayerManager()
                playerManager.registerSourceManager(YoutubeAudioSourceManager())
                val player = playerManager.createPlayer()

                val audioSource = LavaplayerAudioSource(BotMain.api, player)
                audioConnection.setAudioSource(audioSource)

                playerManager.loadItem(args[1], object : AudioLoadResultHandler {
                    override fun trackLoaded(track: AudioTrack) {
                        player.playTrack(track)
                    }

                    override fun playlistLoaded(playlist: AudioPlaylist) {
                        for (track in playlist.tracks) {
                            player.playTrack(track)
                        }
                    }

                    override fun noMatches() {
                    }

                    override fun loadFailed(throwable: FriendlyException) {
                    }
                })


            }
        }
    }


}




