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
package io.github.ydwk.bot.handle

import io.github.ydwk.bot.extenders.ISlashCommandExtender
import io.github.ydwk.bot.logger
import io.github.ydwk.yde.builders.slash.SlashCommandBuilder
import io.github.ydwk.ydwk.YDWK

open class SlashHandler(private val ydwk: YDWK) {
    private val slashCommand: MutableMap<String, ISlashCommandExtender> = HashMap()
    private val slashMutableList: MutableList<SlashCommandBuilder> = ArrayList()
    private fun addSlashCommands(command: ISlashCommandExtender) {
        slashCommand[command.slashCommandBuilder.name] = command
        if (command.slashCommandBuilder.guildOnly) {
            slashMutableList.add(
                SlashCommandBuilder(
                        command.slashCommandBuilder.name,
                        command.slashCommandBuilder.description,
                        true)
                    .addOptions(command.slashCommandBuilder.options))
        } else {
            slashMutableList.add(
                SlashCommandBuilder(
                        command.slashCommandBuilder.name,
                        command.slashCommandBuilder.description,
                        false)
                    .addOptions(command.slashCommandBuilder.options))
        }
    }

    fun registerSlashCommands(slashCommands: Collection<ISlashCommandExtender>): SlashHandler {
        slashCommands.forEach { addSlashCommands(it) }
        return this
    }

    fun sendSlash() {
        if (slashMutableList.isNotEmpty()) {
            ydwk.slashBuilder.addSlashCommands(slashMutableList).build()
        } else {
            logger.warn("No slash commands registered")
        }
    }
}
