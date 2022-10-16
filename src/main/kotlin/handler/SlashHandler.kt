package handler

import io.github.ydwk.ydwk.YDWK
import io.github.ydwk.ydwk.event.ListenerAdapter
import io.github.ydwk.ydwk.event.backend.event.on
import io.github.ydwk.ydwk.event.events.interaction.SlashCommandEvent
import io.github.ydwk.ydwk.slash.Slash

class SlashHandler(val ydwk : YDWK) : ListenerAdapter() {
    private val slashCommand: MutableMap<String, SlashCommandExtender> = HashMap()
    private val slashs : MutableList<Slash> = ArrayList()

    init {
       ydwk.slashBuilder.getSlashCommands()
    }

    private fun addSlashCommands(command : SlashCommandExtender) {
        slashCommand[command.name] = command
        if (command.isGuildOnly) {
            slashs.add(Slash(command.name, command.description, true))
        } else {
            slashs.add(Slash(command.name, command.description, false))
        }
    }

    fun registerSlashCommands(slashCommands : Collection<SlashCommandExtender>) {
        slashCommands.forEach { addSlashCommands(it) }
        onFirstSlash()
    }

    private fun onFirstSlash() {
       slashs.forEach { ydwk.slashBuilder.addSlashCommand(it).build() }
    }

    override fun onSlashCommand(event: SlashCommandEvent) {
        val cmd : SlashCommandExtender = slashCommand[event.slash.name] ?: return
        cmd.onSlashCommand(event)
    }
}