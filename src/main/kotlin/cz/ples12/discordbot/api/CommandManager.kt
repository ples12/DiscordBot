package cz.ples12.discordbot.api

import cz.ples12.discordbot.impl.CommandPing
import org.javacord.api.event.message.MessageCreateEvent

object CommandManager {

    val savedCommands = ArrayList<Command>()

    fun initCommands(){
        savedCommands.add(CommandPing())
    }

    fun runCommands(args : List<String>, event: MessageCreateEvent){
        savedCommands.forEach {command ->
            command.commandNames.forEach{ name ->
                if (name == args[0]){
                    command.execute(args, event)
                }
            }
        }
    }
}