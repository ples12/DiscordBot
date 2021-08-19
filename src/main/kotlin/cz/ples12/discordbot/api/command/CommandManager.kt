package cz.ples12.discordbot.api.command

import cz.ples12.discordbot.impl.command.*
import org.javacord.api.event.message.MessageCreateEvent

object CommandManager {

    val savedCommands = ArrayList<Command>()

    fun initCommands(){
        savedCommands.add(CommandPing())
        savedCommands.add(CommandHelp())
        savedCommands.add(CommandPlay())
        savedCommands.add(CommandDisconnect())
        savedCommands.add(CommandBan())
    }

    fun runCommands(args : Array<String>, event: MessageCreateEvent){
        savedCommands.forEach { command ->
            command.commandNames.forEach{ name ->
                if (name == args[0]){
                    command.execute(args, event)
                }
            }
        }
    }
}