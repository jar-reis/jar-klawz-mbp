package io.jar.klawz.mbp.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * macOS notification helper using the native osascript / AppleScript bridge.
 * Falls back to logging if the native call fails.
 */
@Component
public class MacOSNotification {

    private static final Logger log = LoggerFactory.getLogger(MacOSNotification.class);

    public void show(String title, String text) {
        String appleScript = String.format(
            "display notification \"%s\" with title \"%s\"",
            escape(text), escape(title)
        );
        ProcessBuilder pb = new ProcessBuilder("osascript", "-e", appleScript);
        try {
            Process p = pb.start();
            int exit = p.waitFor();
            if (exit != 0) {
                log.warn("macOS notification failed (exit {}): {}", exit, title);
            }
        } catch (IOException | InterruptedException e) {
            log.warn("Could not trigger macOS notification: {}", title, e);
        }
    }

    private String escape(String raw) {
        return raw.replace("\"", "\\\"").replace("\\", "\\\\");
    }
}
