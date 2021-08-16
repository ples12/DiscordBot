package cz.ples12.discordbot.impl

import cz.ples12.discordbot.api.Command
import org.javacord.api.event.message.MessageCreateEvent

class CommandPing : Command {

    override var commandNames: List<String>
        get() = listOf("ping", "p")
        set(value) {}

    override var commandDescription: String
        get() = "sends random message (ping / pong)"
        set(value) {}

    override fun execute(args: List<String>, event: MessageCreateEvent) {
        if (java.util.Random().nextBoolean()) event.channel.sendMessage("Pong!") else event.channel.sendMessage("Miss!")
    }
}