package io.github.ydwk.bot.commands.moderation

import io.github.ydwk.bot.handler.slash.SlashCommandExtender
import io.github.ydwk.ydwk.builders.slash.SlashOption
import io.github.ydwk.ydwk.builders.slash.SlashOptionType
import io.github.ydwk.ydwk.entities.User
import io.github.ydwk.ydwk.entities.guild.Member
import io.github.ydwk.ydwk.entities.guild.enums.GuildPermission
import io.github.ydwk.ydwk.interaction.application.type.SlashCommand

class KickCommand: SlashCommandExtender {
    override fun onSlashCommand(event: SlashCommand) {
        val member: Member = event.member!!
        val bot: Member = event.ydwk.getMemberById(event.guild?.id!!, event.ydwk.bot?.id!!)!!
        val user: Member? = event.getOption("user")?.asMember
        val reason: String? = event.getOption("reason")?.asString

        if (!member.hasPermission(GuildPermission.KICK_MEMBERS)) {
            event.reply("You do not have permission to kick members.").isEphemeral(true).trigger()
            return
        }

        if (!bot.hasPermission(GuildPermission.KICK_MEMBERS)) {
            event.reply("I do not have permission to kick members.").isEphemeral(true).trigger()
            return
        }

        if (user == null) {
            event.reply("You must provide a member to kick.").isEphemeral(true).trigger()
            return
        }

        if (reason == null) {
            event.reply("You must provide a reason to kick.").isEphemeral(true).trigger()
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
            SlashOption("reason", "The reason for the kick", SlashOptionType.STRING, true)
        )
    }
}