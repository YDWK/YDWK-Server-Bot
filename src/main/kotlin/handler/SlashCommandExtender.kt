package handler

import io.github.ydwk.ydwk.event.events.interaction.SlashCommandEvent

interface SlashCommandExtender {

    fun onSlashCommand(event : SlashCommandEvent)

    val name : String

    val description : String

    val isGuildOnly : Boolean
}