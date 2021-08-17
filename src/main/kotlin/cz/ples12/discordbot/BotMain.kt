package cz.ples12.discordbot

import cz.ples12.discordbot.api.command.CommandManager
import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder

object BotMain {

    var api : DiscordApi ? = null

    const val name = "Moderation Bot"

    const val version = 1.0

    const val prefix = "!"

}

    fun main(args: Array<String>){
        println("Starting bot...")
        BotMain.api = DiscordApiBuilder().setToken(args[0]).login().join()

        val api = BotMain.api

        CommandManager.initCommands()

        api?.addMessageCreateListener{event ->
            if (event.messageContent.startsWith(BotMain.prefix)){
                val args1 = event.messageContent.replace(BotMain.prefix, "").split(" ")
                CommandManager.runCommands(args1.toTypedArray(), event)
            }
        }
    }