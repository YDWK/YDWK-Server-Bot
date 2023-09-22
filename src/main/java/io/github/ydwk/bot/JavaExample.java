package io.github.ydwk.bot;

import io.github.realyusufismail.jconfig.util.JConfigUtils;
import io.github.ydwk.yde.rest.result.NoResult;
import io.github.ydwk.ydwk.BotBuilder;
import kotlin.coroutines.Continuation;

import java.util.Objects;

public class JavaExample {

    public static void main(String[] args) {
        var token = JConfigUtils.getString("token");
        var ydwk = BotBuilder.INSTANCE.createDefaultBot(Objects.requireNonNull(token, "Token is null")).build();

        ydwk.getEventListener().onSlashCommandEvent(((genericEvent, slashCommandEvent, continuation) -> {
            slashCommandEvent.getSlash().reply("Test").trigger((Continuation<? super NoResult>) continuation);
            return continuation;
        }));
    }
}
