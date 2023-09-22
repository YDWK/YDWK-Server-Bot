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
package io.github.ydwk.bot.commands

import io.github.ydwk.bot.annotation.Slash
import io.github.ydwk.bot.builder.SlashBuildResult
import io.github.ydwk.bot.builder.SlashBuilder
import io.github.ydwk.bot.extenders.ISlashCommandExtender
import io.github.ydwk.yde.interaction.application.type.SlashCommand
import io.github.ydwk.ydwk.util.ydwk

@Slash
class Ping : ISlashCommandExtender {
    override suspend fun onIncomingSlashCommand(slashCommand: SlashCommand) {
        slashCommand.reply("The bots ping is " + slashCommand.ydwk.uptime).trigger()
    }

    override val slashCommandBuilder: SlashBuildResult
        get() = SlashBuilder("ping", "Used to get the bots ping").build()
}
