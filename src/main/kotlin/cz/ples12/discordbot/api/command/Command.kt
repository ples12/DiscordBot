package cz.ples12.discordbot.api.command

import org.javacord.api.event.message.MessageCreateEvent

abstract class Command {

    abstract fun execute(args: Array<String>, event: MessageCreateEvent)

    open val commandNames: Array<String>
        get() = javaClass.getAnnotation(CommandInfo::class.java).names


    open val commandDescription: String
        get() = javaClass.getAnnotation(CommandInfo::class.java).description
}