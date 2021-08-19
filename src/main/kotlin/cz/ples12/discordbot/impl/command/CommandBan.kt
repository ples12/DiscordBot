package cz.ples12.discordbot.impl.command

import cz.ples12.discordbot.api.command.Command
import cz.ples12.discordbot.api.command.CommandInfo
import org.javacord.api.event.message.MessageCreateEvent

@CommandInfo(names = ["ban"],description =  "bans typed user")
class CommandBan : Command() {

    override fun execute(args: Array<String>, event: MessageCreateEvent) {
        if (event.messageAuthor.canBanUsersFromServer()) {
            when (args.size) {
                1 -> event.channel.sendMessage("You must define user to ban!")
                2 -> {
                    event.server.orElseGet(null).banUser(event.message.mentionedUsers[0])
                    event.channel.sendMessage("User was banned! ")
                }
            }
        }else{
            event.channel.sendMessage("You dont have permission to ban ppl!")
        }
    }

}