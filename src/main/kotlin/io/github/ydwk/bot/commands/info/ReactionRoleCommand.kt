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
package io.github.ydwk.bot.commands.info

import io.github.ydwk.bot.handler.slash.SlashCommandExtender
import io.github.ydwk.ydwk.builders.slash.SlashOption
import io.github.ydwk.ydwk.builders.slash.SlashOptionType
import io.github.ydwk.ydwk.interaction.application.type.SlashCommand

class ReactionRoleCommand : SlashCommandExtender {
    override fun onSlashCommand(event: SlashCommand) {
        TODO("Not yet implemented")
    }

    override fun name(): String {
        return "reactionrole"
    }

    override fun description(): String {
        return "Used to create a reaction role message"
    }

    override fun isGuildOnly(): Boolean {
        return true
    }

    override fun options(): MutableList<SlashOption> {
        return mutableListOf(
            SlashOption(
                "message", "The message to add the reaction role to", SlashOptionType.STRING, true),
            SlashOption("emoji", "The emoji to add to the message", SlashOptionType.STRING, true),
            SlashOption("role", "The role to add to the user", SlashOptionType.ROLE, true))
    }
}
