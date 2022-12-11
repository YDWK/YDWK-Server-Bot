package handler

import io.github.ydwk.ydwk.evm.event.events.interaction.slash.SlashCommandEvent

interface SlashCommandExtender {

    fun onSlashCommand(event : SlashCommandEvent)

    val name : String

    val description : String

    val isGuildOnly : Boolean
}