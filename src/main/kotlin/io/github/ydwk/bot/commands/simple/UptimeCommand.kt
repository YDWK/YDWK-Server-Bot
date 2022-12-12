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
package io.github.ydwk.bot.commands.simple

import io.github.ydwk.bot.handler.slash.SlashCommandExtender
import io.github.ydwk.ydwk.interaction.application.type.SlashCommand
import io.github.ydwk.ydwk.interaction.message.ActionRow
import io.github.ydwk.ydwk.interaction.message.button.Button
import io.github.ydwk.ydwk.interaction.message.button.ButtonStyle
import java.time.Duration
import java.time.Instant

class UptimeCommand : SlashCommandExtender {

    override fun onSlashCommand(event: SlashCommand) {
        // this is start time
        val uptime = event.ydwk.uptime

        // this is current time
        val now = Instant.now()

        // compare the two
        val duration = Duration.between(uptime, now)

        // format it where you have the days, hours, minutes, seconds
        val formattedUptime = formatUptime(duration)

        // send the message
        event
            .reply("Uptime: $formattedUptime")
            .addActionRow(ActionRow.of(Button.of(ButtonStyle.DANGER, "delete", "Delete")))
            .reply()
    }

    override val name: String
        get() = "uptime"

    override val description: String
        get() = "Get the uptime of the bot"

    override val isGuildOnly: Boolean
        get() = false

    private fun formatUptime(duration: Duration): String {
        val days = duration.toDays()
        val hours = duration.toHours() % 24
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60
        return if (days > 0) {
            String.format(
                "%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds)
        } else if (hours > 0) {
            String.format("%d hours, %d minutes, %d seconds", hours, minutes, seconds)
        } else if (minutes > 0) {
            String.format("%d minutes, %d seconds", minutes, seconds)
        } else {
            String.format("%d seconds", seconds)
        }
    }
}
