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
import io.github.ydwk.ydwk.builders.slash.SlashOption
import io.github.ydwk.ydwk.builders.slash.SlashOptionType
import io.github.ydwk.ydwk.entities.guild.Member
import io.github.ydwk.ydwk.entities.guild.enums.GuildPermission
import io.github.ydwk.ydwk.interaction.application.type.SlashCommand

class KickCommand : SlashCommandExtender {
    override fun onSlashCommand(event: SlashCommand) {
        val member: Member = event.member!!
        val bot: Member = event.ydwk.getMemberById(event.guild?.id!!, event.ydwk.bot?.id!!)!!
        val user: Member? = event.getOption("user")?.asMember
        val reason: String? = event.getOption("reason")?.asString

        if (!member.hasPermission(GuildPermission.KICK_MEMBERS)) {
            event.reply("You do not have permission to kick members.").setEphemeral(true).trigger()
            return
        }

        if (!bot.hasPermission(GuildPermission.KICK_MEMBERS)) {
            event.reply("I do not have permission to kick members.").setEphemeral(true).trigger()
            return
        }

        if (user == null) {
            event.reply("You must provide a member to kick.").setEphemeral(true).trigger()
            return
        }

        if (reason == null) {
            event.reply("You must provide a reason to kick.").setEphemeral(true).trigger()
            return
        }

        event.guild!!.kickMember(user.id, reason).completeAsync {
            event.reply("Kicked ${user.name}#${user.user.discriminator} for $reason.").trigger()
        }
    }

    override fun name(): String {
        return "kick"
    }

    override fun description(): String {
        return "Kick a user from the server"
    }

    override fun isGuildOnly(): Boolean {
        return true
    }

    override fun options(): MutableList<SlashOption> {
        return mutableListOf(
            SlashOption("user", "The user to kick", SlashOptionType.USER, true),
            SlashOption("reason", "The reason for the kick", SlashOptionType.STRING, true))
    }
}
