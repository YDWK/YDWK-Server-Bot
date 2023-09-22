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
package io.github.ydwk.bot.extenders

import io.github.ydwk.bot.builder.SlashBuildResult
import io.github.ydwk.yde.interaction.application.type.SlashCommand

/** An interface defining the behavior of a SlashCommandExtender. */
interface ISlashCommandExtender {

    /**
     * Handles an incoming [SlashCommand]. This should be implemented to handle specific logic
     * related to the provided command.
     *
     * @param slashCommand - The command to handle.
     */
    suspend fun onIncomingSlashCommand(slashCommand: SlashCommand)

    /**
     * The [SlashBuildResult] to be constructed by the specific implementation. Use the [SlashBuild]
     * class to construct the key components like name, string, and options. Call build to get the
     * final [SlashBuildResult].
     *
     * This should be implemented to provide the required setup for the handling of the Slash
     * Commands.
     */
    val slashCommandBuilder: SlashBuildResult
}
