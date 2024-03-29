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

import io.github.ydwk.yde.builders.slash.SlashOption
import io.github.ydwk.yde.interaction.application.type.SlashCommand

interface SlashCommandExtender {

    suspend fun onSlashCommand(event: SlashCommand)

    fun name(): String

    fun description(): String

    fun isGuildOnly(): Boolean

    fun options(): MutableList<SlashOption> = mutableListOf()
}
