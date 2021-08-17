package cz.ples12.discordbot.impl.command

import cz.ples12.discordbot.BotMain
import cz.ples12.discordbot.api.command.Command
import cz.ples12.discordbot.api.command.CommandInfo
import cz.ples12.discordbot.api.command.CommandManager
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent
import java.awt.Color
import kotlin.text.StringBuilder


@CommandInfo(names = ["help", "h"],description =  "sends a help about other commands")
class CommandHelp : Command() {


    override fun execute(args: Array<String>, event: MessageCreateEvent) {

        val builder = StringBuilder()

        CommandManager.savedCommands.forEach{command ->
            builder.append(command.commandNames[0]+  " : " + command.commandDescription + "\n")
        }

        val embed = EmbedBuilder()

        embed.setAuthor("Help")

        embed.setDescription(builder.toString())

        embed.setFooter(BotMain.name + " v" + BotMain.version)

        embed.setColor(Color.CYAN)

        event.channel.sendMessage(embed)

    }
}