package cz.ples12.discordbot

import cz.ples12.discordbot.api.CommandManager
import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder

object BotMain {

    var api : DiscordApi ? = null

    val name = "Moderation Bot"

    val version = 1.0

    val prefix = "!"

}

    fun main(args: Array<String>){
        println("Starting bot...")
        BotMain.api = DiscordApiBuilder().setToken(args[0]).login().join()

        val api = BotMain.api

        CommandManager.initCommands()

        api?.addMessageCreateListener{event ->
            if (event.messageContent.startsWith(BotMain.prefix)){
                val args = event.messageContent.replace(BotMain.prefix, "").split(" ")
                CommandManager.runCommands(args, event)
            }
        }
    }