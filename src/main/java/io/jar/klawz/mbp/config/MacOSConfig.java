package io.jar.klawz.mbp.config;

import io.jar.klawz.tools.ShellExecutor;
import io.jar.klawz.tools.impl.LocalShellExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

/**
 * macOS-specific configuration for the MBP bootstrap.
 * Provides the widest shell allowlist since MBP is the trusted dev machine.
 */
@Configuration
@Profile("mbp")
public class MacOSConfig {

    @Value("${klawz.mbp.workspace:~/klawz-workspace}")
    private String workspacePath;

    @Value("${klawz.mbp.shell.allowlist:}")
    private List<String> extraAllowlist;

    @Bean
    public ShellExecutor shellExecutor() {
        Set<String> baseAllowlist = Set.of(
            // Core Unix
            "sh", "bash", "zsh", "cat", "grep", "awk", "sed", "cut", "sort",
            "uniq", "wc", "head", "tail", "find", "xargs", "tee", "tr",
            // File system
            "ls", "cd", "pwd", "mkdir", "rm", "cp", "mv", "touch", "chmod",
            "chown", "ln", "df", "du", "stat", "file", "tree",
            // Process / system
            "ps", "top", "htop", "kill", "killall", "pgrep", "pkill",
            "sysctl", "sw_vers", "uname", "id", "whoami", "env", "printenv",
            // macOS specific
            "open", "osascript", "say", "defaults", "plutil", "mdls",
            "mdfind", "xattr", "codesign", "spctl", "pkgutil", "installer",
            // Network
            "curl", "wget", "ping", "netstat", "lsof", "nc", "dig", "host",
            "ifconfig", "ipconfig", "scutil", "networksetup",
            // Dev tools
            "git", "gh", "brew", "npm", "node", "python3", "pip3", "ruby",
            "gem", "swift", "xcrun", "xcodebuild", "make", "cmake",
            // Archives
            "tar", "gzip", "gunzip", "zip", "unzip", "bz2", "xz",
            // Text / docs
            "pandoc", "pdftotext", "jq", "yq",
            // Rsync
            "rsync", "rclone"
        );

        return new LocalShellExecutor(baseAllowlist, extraAllowlist);
    }

    @Bean
    public MacOSWorkspaceInitializer workspaceInitializer() {
        return new MacOSWorkspaceInitializer(Paths.get(workspacePath));
    }
}
