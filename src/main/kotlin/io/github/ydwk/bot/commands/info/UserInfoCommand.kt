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

import io.github.ydwk.bot.commands.util.defaultColor
import io.github.ydwk.bot.handler.slash.SlashCommandExtender
import io.github.ydwk.ydwk.entities.User
import io.github.ydwk.ydwk.entities.guild.Member
import io.github.ydwk.ydwk.entities.message.Embed
import io.github.ydwk.ydwk.interaction.application.type.SlashCommand
import io.github.ydwk.ydwk.slash.SlashOption
import io.github.ydwk.ydwk.slash.SlashOptionType

class UserInfoCommand : SlashCommandExtender {
    override fun onSlashCommand(event: SlashCommand) {
        val user: User? = event.getOption("user")?.asUser
        val member: Member? = event.getOption("user")?.asMember
        val slashMember = event.member

        if (user != null) {
            event.reply(userInfoEmbed(user, event))
        } else if (member != null) {
            event.reply(memberInfoEmbed(member, event))
        } else {
            event.reply(memberInfoEmbed(slashMember!!, event))
        }
    }

    private fun memberInfoEmbed(member: Member, event: SlashCommand): Embed {
        val embed = event.ydwk.embedBuilder
        embed.setTitle(member.user.name + "#" + member.user.discriminator + "'s info")
        embed.setColor(defaultColor)
        embed.addField("User ID", member.user.id, true)
        embed.addField("Nickname", member.nick ?: "None", true)
        embed.addField("Joined at", member.joinedAt.toString(), true)
        embed.addField("Roles", member.roles.joinToString { it?.name ?: "" }, true)
        embed.addField("Boosting since", member.premiumSince.toString(), true)
        return embed.build()
    }

    private fun userInfoEmbed(user: User, event: SlashCommand): Embed {
        val embed = event.ydwk.embedBuilder
        embed.setTitle(user.name + "#" + user.discriminator + "'s info")
        embed.setColor(defaultColor)
        embed.addField("User ID", user.id, true)
        embed.addField("Bot", user.bot.toString(), true)
        return embed.build()
    }

    override fun name(): String {
        return "userinfo"
    }

    override fun description(): String {
        return "Get information about a user"
    }

    override fun isGuildOnly(): Boolean {
        return false
    }

    override fun options(): MutableList<SlashOption> {
        return mutableListOf(
            SlashOption("user", "The user to get info about", SlashOptionType.USER, false))
    }
}
