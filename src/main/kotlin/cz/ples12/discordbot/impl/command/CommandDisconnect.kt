package cz.ples12.discordbot.impl.command

import cz.ples12.discordbot.BotMain
import cz.ples12.discordbot.api.command.Command
import cz.ples12.discordbot.api.command.CommandInfo
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent
import java.awt.Color

@CommandInfo(names = ["disconnect"],description =  "disconnects bot from voice channel")
class CommandDisconnect : Command(){
    override fun execute(args: Array<String>, event: MessageCreateEvent) {

        val channel = event.messageAuthor.connectedVoiceChannel.orElse(null)

        channel?.connect()?.thenAccept { audioConnection ->
            audioConnection.close()
        }


        val embed = EmbedBuilder()

        embed.setAuthor("Diconnected")

        embed.setDescription("Disconnected from voice channel!")

        embed.setFooter(BotMain.name + " v" + BotMain.version)

        embed.setColor(Color.GREEN)

        event.channel.sendMessage(embed)
    }
}