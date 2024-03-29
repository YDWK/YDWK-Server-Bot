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
package io.github.ydwk.bot

import io.github.realyusufismail.jconfig.util.JConfigUtils
import io.github.ydwk.bot.handler.slash.AutoSlashAdder
import io.github.ydwk.bot.handler.slash.SlashHandler
import io.github.ydwk.ydwk.BotBuilder.createDefaultBot
import io.github.ydwk.ydwk.YDWK
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger: Logger = LoggerFactory.getLogger("Main")

suspend fun main() {
    val token = JConfigUtils.getString("token") ?: throw Exception("Token not found")
    val ydwk: YDWK = createDefaultBot(token).setETFInsteadOfJson(true).build()

    ydwk.eventListener.onReadyEvent { logger.info("Bot is ready") }

    ydwk.eventListener.onButtonClickEvent {
        val button = it.button
        when (button.customId) {
            "delete" -> {
                button.message.delete()
                    .await()
            }
        }
    }

    ydwk.eventListener.onSlashCommandEvent {
        val handler = SlashHandler(ydwk)
        handler.slashCommand.forEach { (name, cmd) ->
            if (name == it.slash.name) {
                cmd.onSlashCommand(it.slash)
            }
        }
    }
}
