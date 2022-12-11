import handler.SlashHandler
import io.github.realyusufismail.jconfig.util.JConfigUtils
import io.github.ydwk.ydwk.BotBuilder.createDefaultBot
import io.github.ydwk.ydwk.YDWK
import io.github.ydwk.ydwk.evm.backend.event.on
import io.github.ydwk.ydwk.evm.event.events.gateway.ReadyEvent
import org.slf4j.LoggerFactory

val logger = LoggerFactory.getLogger("Main")

fun main() {
    val token = JConfigUtils.getString("token") ?: throw Exception("Token not found")
    val ydwk : YDWK = createDefaultBot(token).build()
    ydwk.waitForReady.addEvent(SlashHandler(ydwk))
    ydwk.on<ReadyEvent> {
        logger.info("Bot is ready")
    }
}