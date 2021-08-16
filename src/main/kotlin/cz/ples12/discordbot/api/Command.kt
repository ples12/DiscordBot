package cz.ples12.discordbot.api

import org.javacord.api.event.message.MessageCreateEvent

interface Command {

    var commandNames: List<String>

    var commandDescription: String

    fun execute(args:List<String>, event: MessageCreateEvent)

}