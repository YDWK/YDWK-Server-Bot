import handler.SlashHandler
import io.github.realyusufismail.jconfig.util.JConfigUtils
import io.github.ydwk.ydwk.YDWK
import io.github.ydwk.ydwk.createDefaultBot

fun main() {
    val token = JConfigUtils.getString("token") ?: throw Exception("Token not found")
    val ydwk : YDWK = createDefaultBot(token)
    ydwk.waitForReady.addEvent(SlashHandler(ydwk))
}