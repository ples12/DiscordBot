package cz.ples12.discordbot.impl.command

import cz.ples12.discordbot.api.command.Command
import cz.ples12.discordbot.api.command.CommandInfo
import org.javacord.api.event.message.MessageCreateEvent

@CommandInfo(names = ["kick"],description =  "kicks typed user")
class CommandKick : Command() {

    override fun execute(args: Array<String>, event: MessageCreateEvent) {
        if (event.messageAuthor.canBanUsersFromServer()) {
            when (args.size) {
                1 -> event.channel.sendMessage("You must define user to kick!")
                2 -> {
                    event.server.orElseGet(null).kickUser(event.message.mentionedUsers[0])
                    event.channel.sendMessage("User was kicked!")
                }
            }
        }else{
            event.channel.sendMessage("You dont have permission to kick ppl!")
        }
    }

}