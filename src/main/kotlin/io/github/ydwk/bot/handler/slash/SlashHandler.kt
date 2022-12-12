/*
 * Copyright 2022 YDWK inc.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.ydwk.bot.handler.slash

import io.github.ydwk.ydwk.YDWK
import io.github.ydwk.ydwk.evm.ListenerAdapter
import io.github.ydwk.ydwk.evm.event.events.interaction.slash.SlashCommandEvent
import io.github.ydwk.ydwk.slash.Slash

open class SlashHandler(private val ydwk: YDWK) : ListenerAdapter() {
    private val slashCommand: MutableMap<String, SlashCommandExtender> = HashMap()
    private val slashMutableList: MutableList<Slash> = ArrayList()

    private fun addSlashCommands(command: SlashCommandExtender) {
        slashCommand[command.name] = command
        if (command.isGuildOnly) {
            slashMutableList.add(Slash(command.name, command.description, true))
        } else {
            slashMutableList.add(Slash(command.name, command.description, false))
        }
    }

    fun registerSlashCommands(slashCommands: Collection<SlashCommandExtender>) {
        slashCommands.forEach { addSlashCommands(it) }
        onFirstSlash()
    }

    private fun onFirstSlash() {
        slashMutableList.forEach { ydwk.slashBuilder.addSlashCommand(it).build() }
    }

    override fun onSlashCommand(event: SlashCommandEvent) {
        val cmd: SlashCommandExtender = slashCommand[event.slash.name] ?: return
        cmd.onSlashCommand(event.slash)
    }
}
