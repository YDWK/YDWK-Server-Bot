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

import io.github.ydwk.bot.commands.util.defaultColor
import io.github.ydwk.bot.handler.slash.SlashCommandExtender
import io.github.ydwk.ydwk.interaction.application.type.SlashCommand

class RulesCommand : SlashCommandExtender {
    override fun onSlashCommand(event: SlashCommand) {
        val embed = event.ydwk.embedBuilder
        embed.setTitle("YDWK Rules")
        embed
            .setDescription(
                """
            
            ## General Rules
            No blank nicknames.
            No inappropriate nicknames.
            No sexually explicit nicknames.
            No offensive nicknames.
            No nicknames with unusual or unreadable Unicode.
            No blank profile pictures.
            No inappropriate profile pictures.
            No sexually explicit profile pictures.
            No offensive profile pictures.
            No membership granted to minors (under 18 years).
            Moderators reserve the right to change nicknames.
            Moderators reserve the right to use their own discretion regardless of any rule.
            No exploiting loopholes in the rules (please report them).
            No DMing other members of the server.
            Rules apply to DMing other members of the server.
            No inviting unofficial bots.
            No bugs, exploits, glitches, hacks, bugs, etc.
            
            ### Text chat rules
            No spamming, swearing, or excessive cursing. (This includes emojis)
            No harassing, bullying, or threatening other members.
            No posting links to other servers.
            Dont say can I ask without asking a question.
            No posting links to other servers.
            Follow the topic of the channel you are in.
            
            Discord Terms of Service Apply: https://discord.com/terms
            Discord Community Guidelines Apply: https://discord.com/guidelines
            Discord Privacy Policy Applies: https://discord.com/privacy
        """
                    .trimIndent())
            .setColor(defaultColor)
    }

    override val name: String
        get() = "rules"
    override val description: String
        get() = "Get the rules of the server"
    override val isGuildOnly: Boolean
        get() = true
}
