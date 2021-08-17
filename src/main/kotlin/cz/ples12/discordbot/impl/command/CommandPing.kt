package cz.ples12.discordbot.impl.command

import cz.ples12.discordbot.BotMain
import cz.ples12.discordbot.api.command.Command
import cz.ples12.discordbot.api.command.CommandInfo
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent
import java.awt.Color

@CommandInfo(names = ["ping", "p"],description =  "sends random message (ping / pong)")
class CommandPing : Command() {


    override fun execute(args: Array<String>, event: MessageCreateEvent) {
        val embed = EmbedBuilder()

        embed.setAuthor("Ping")

        embed.setDescription(if (java.util.Random().nextBoolean()) "Pong!" else "Miss!")

        embed.setFooter(BotMain.name + " v" + BotMain.version)

        embed.setColor(Color.ORANGE)

        event.channel.sendMessage(embed)
    }
}