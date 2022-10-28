import handler.SlashHandler
import io.github.realyusufismail.jconfig.util.JConfigUtils
import io.github.ydwk.ydwk.BotBuilder.createDefaultBot
import io.github.ydwk.ydwk.YDWK

fun main() {
    val token = JConfigUtils.getString("token") ?: throw Exception("Token not found")
    val ydwk : YDWK = createDefaultBot(token).build()
    ydwk.waitForReady.addEvent(SlashHandler(ydwk))
}