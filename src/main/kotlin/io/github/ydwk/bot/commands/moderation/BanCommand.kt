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
package io.github.ydwk.bot.commands.moderation

import io.github.ydwk.bot.handler.slash.SlashCommandExtender
import io.github.ydwk.yde.builders.slash.SlashOption
import io.github.ydwk.yde.builders.slash.SlashOptionType
import io.github.ydwk.yde.entities.User
import io.github.ydwk.yde.entities.guild.Member
import io.github.ydwk.yde.entities.guild.enums.GuildPermission
import io.github.ydwk.yde.interaction.application.type.SlashCommand
import io.github.ydwk.ydwk.util.ydwk
import kotlinx.coroutines.runBlocking

class BanCommand : SlashCommandExtender {
    override fun onSlashCommand(event: SlashCommand) {
        val member: Member = event.member!!
        val bot: Member = event.ydwk.getMemberById(event.guild?.id!!, event.ydwk.bot?.id!!)!!

        runBlocking {

            if (!member.hasPermission(GuildPermission.BAN_MEMBERS)) {
                event.reply("You do not have permission to ban members.").setEphemeral(true).trigger()
                return@runBlocking
            }

            if (!bot.hasPermission(GuildPermission.BAN_MEMBERS)) {
                event.reply("I do not have permission to ban members.").setEphemeral(true).trigger()
                return@runBlocking
            }

            val user: User? = event.getOption("user")?.asUser
            if (user == null) {
                event.reply("You must provide a user to ban.").setEphemeral(true).trigger()
                return@runBlocking
            }

            val reason: String? = event.getOption("reason")?.asString
            if (reason == null) {
                event.reply("You must provide a reason to ban.").setEphemeral(true).trigger()
                return@runBlocking
            }

            event.guild!!.banUser(user.id, reason).await().let {
                event.reply("Banned ${user.name}#${user.discriminator} for $reason.").setEphemeral(true).trigger()
            }
        }
    }

    override fun name(): String {
        return "ban"
    }

    override fun description(): String {
        return "Ban a user from the server"
    }

    override fun isGuildOnly(): Boolean {
        return true
    }

    override fun options(): MutableList<SlashOption> {
        return mutableListOf(
            SlashOption("user", "The user to ban", SlashOptionType.USER, true),
            SlashOption("reason", "The reason for the ban", SlashOptionType.STRING, true))
    }
}
