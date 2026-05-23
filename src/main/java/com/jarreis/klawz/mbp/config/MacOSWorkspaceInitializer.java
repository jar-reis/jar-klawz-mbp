package io.jar.klawz.mbp.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class MacOSWorkspaceInitializer {

    private static final Logger log = LoggerFactory.getLogger(MacOSWorkspaceInitializer.class);

    private final Path workspace;

    public MacOSWorkspaceInitializer(Path workspace) {
        this.workspace = workspace;
    }

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(workspace)) {
                Files.createDirectories(workspace);
                log.info("Created MBP workspace at {}", workspace);
            }

            // Standard subdirectories
            String[] dirs = {"downloads", "uploads", "temp", "logs", "scripts"};
            for (String dir : dirs) {
                Path sub = workspace.resolve(dir);
                if (!Files.exists(sub)) {
                    Files.createDirectories(sub);
                }
            }

            log.info("MBP workspace ready: {}", workspace);
        } catch (IOException e) {
            log.error("Failed to initialize MBP workspace at {}", workspace, e);
        }
    }
}
