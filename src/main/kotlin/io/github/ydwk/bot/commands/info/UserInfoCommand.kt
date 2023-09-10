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
import io.github.ydwk.yde.builders.slash.SlashOption
import io.github.ydwk.yde.builders.slash.SlashOptionType
import io.github.ydwk.yde.entities.User
import io.github.ydwk.yde.entities.guild.Member
import io.github.ydwk.yde.entities.message.Embed
import io.github.ydwk.yde.interaction.application.type.SlashCommand
import io.github.ydwk.yde.interaction.message.ActionRow
import io.github.ydwk.yde.interaction.message.button.Button
import io.github.ydwk.yde.interaction.message.button.ButtonStyle
import io.github.ydwk.ydwk.util.ydwk

class UserInfoCommand : SlashCommandExtender {
    override suspend fun onSlashCommand(event: SlashCommand) {
        val member: Member? =
            if (event.getOption("user")?.asMember == null) null
            else event.getOption("user")?.asMember
        val slashMember = event.member

        if (member != null) {
            event
                .reply(memberInfoEmbed(member, event))
                .addActionRow(ActionRow(Button(ButtonStyle.DANGER, "delete", "Delete")))
                .trigger()
        } else if (slashMember != null) {
            event
                .reply(memberInfoEmbed(slashMember, event))
                .addActionRow(ActionRow(Button(ButtonStyle.DANGER, "delete", "Delete")))
                .trigger()
        } else {
            event
                .reply(userInfoEmbed(event.user, event))
                .addActionRow(ActionRow(Button(ButtonStyle.DANGER, "delete", "Delete")))
                .trigger()
        }
    }

    private fun memberInfoEmbed(member: Member, event: SlashCommand): Embed {
        val embed = event.ydwk.embedBuilder
        embed.setTitle("""${member.user.name}'s info""")
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
        embed.setTitle("""${user.name}'s info""")
        embed.setColor(defaultColor)
        embed.addField("User ID", user.id, true)
        embed.addField("Bot", if (user.bot != null) "Yes" else "No", true)
        return embed.build()
    }

    override fun name(): String {
        return "userinfo"
    }

    override fun description(): String {
        return "Get information about a user"
    }

    override fun isGuildOnly(): Boolean {
        return true
    }

    override fun options(): MutableList<SlashOption> {
        return mutableListOf(
            SlashOption("user", "The user to get info about", SlashOptionType.USER, false))
    }
}
