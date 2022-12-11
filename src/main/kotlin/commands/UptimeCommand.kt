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
package commands

import handler.SlashCommandExtender
import io.github.ydwk.ydwk.evm.event.events.interaction.slash.SlashCommandEvent

class UptimeCommand : SlashCommandExtender {

    override fun onSlashCommand(event: SlashCommandEvent) {
        event.slash.reply("Uptime: ${event.slash.ydwk.uptime}ms").reply()
    }

    override val name: String
        get() = "uptime"

    override val description: String
        get() = "Get the uptime of the bot"

    override val isGuildOnly: Boolean
        get() = false
}
