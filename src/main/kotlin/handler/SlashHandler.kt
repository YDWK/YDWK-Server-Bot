package handler

import io.github.ydwk.ydwk.YDWK
import io.github.ydwk.ydwk.evm.ListenerAdapter
import io.github.ydwk.ydwk.evm.event.events.interaction.SlashCommandEvent
import io.github.ydwk.ydwk.slash.Slash

class SlashHandler(private val ydwk : YDWK) : ListenerAdapter() {
    private val slashCommand: MutableMap<String, SlashCommandExtender> = HashMap()
    private val slashMutableList : MutableList<Slash> = ArrayList()

    init {
       ydwk.slashBuilder.getSlashCommands()
    }

    private fun addSlashCommands(command : SlashCommandExtender) {
        slashCommand[command.name] = command
        if (command.isGuildOnly) {
            slashMutableList.add(Slash(command.name, command.description, true))
        } else {
            slashMutableList.add(Slash(command.name, command.description, false))
        }
    }

    fun registerSlashCommands(slashCommands : Collection<SlashCommandExtender>) {
        slashCommands.forEach { addSlashCommands(it) }
        onFirstSlash()
    }

    private fun onFirstSlash() {
       slashMutableList.forEach { ydwk.slashBuilder.addSlashCommand(it).build() }
    }

    override fun onSlashCommand(event: SlashCommandEvent) {
        val cmd : SlashCommandExtender = slashCommand[event.slash.name] ?: return
        cmd.onSlashCommand(event)
    }
}