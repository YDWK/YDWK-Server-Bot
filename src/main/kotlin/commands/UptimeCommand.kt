package commands

import handler.SlashCommandExtender
import io.github.ydwk.ydwk.evm.event.events.interaction.SlashCommandEvent

class UptimeCommand : SlashCommandExtender {

    override fun onSlashCommand(event: SlashCommandEvent) {
        event.slash.reply("Uptime: ${event.slash.ydwk.uptime}ms").get()
    }

    override val name: String
        get() = "uptime"

    override val description: String
        get() = "Get the uptime of the bot"

    override val isGuildOnly: Boolean
        get() = false
}