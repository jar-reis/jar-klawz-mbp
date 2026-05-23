# JAR Klawz Stack — macOS Bootstrap

Spring Boot bootstrap for the **macOS (MBP)** agent variant.

## Quick Start

```bash
# 1. Build
mvn clean package

# 2. Run
java -jar target/jar-klawz-mbp-*.jar

# Or with Spring Boot plugin:
mvn spring-boot:run
```

## What It Does

- Imports `klawz-starter` (core auto-configuration)
- H2 file-backed database at `~/.klawz/mbp-data`
- Full shell access with the widest allowlist (Unix + macOS tools)
- macOS workspace initialized at `~/.klawz/workspace`
- Native notification support via AppleScript

## Config

| Key | Default | Description |
|-----|---------|-------------|
| `klawz.mbp.workspace` | `~/.klawz/workspace` | Local file workspace |
| `klawz.mbp.shell.allowlist` | *(empty)* | Extra shell commands beyond base set |
| `klawz.provider` | `kimi` | AI provider |
| `server.port` | `8080` | HTTP port |

## Shell Allowlist

The MBP gets the widest shell allowlist because it's the primary dev/ops machine:

- **Core Unix:** `sh`, `bash`, `zsh`, `cat`, `grep`, `awk`, `sed`, `find`, `xargs`, …
- **File system:** `ls`, `cd`, `mkdir`, `rm`, `cp`, `mv`, `chmod`, `chown`, `df`, `du`, `tree`
- **Process:** `ps`, `top`, `htop`, `kill`, `killall`, `pgrep`, `pkill`, `sysctl`, `sw_vers`
- **Network:** `curl`, `wget`, `ping`, `netstat`, `lsof`, `nc`, `dig`, `scutil`
- **Dev tools:** `git`, `gh`, `brew`, `npm`, `node`, `python3`, `swift`, `xcrun`, `make`
- **macOS native:** `open`, `osascript`, `say`, `defaults`, `plutil`, `mdls`, `mdfind`, `xattr`
- **Archives:** `tar`, `gzip`, `zip`, `unzip`, `rsync`, `rclone`

Extra tools can be added via `klawz.mbp.shell.allowlist` in `application.yml`.

## Native Hooks

- **Notifications:** `MacOSNotification` component sends AppleScript `display notification` calls for alerts.
- **Workspace initializer:** Auto-creates `downloads/`, `uploads/`, `temp/`, `logs/`, `scripts/` under the workspace.
- **H2 console:** Enabled at `/h2-console` for local debugging.

## Testing

```bash
mvn test
```

## Troubleshooting

| Issue | Fix |
|-------|-----|
| Port 8080 in use | `server.port=8081` in `application.yml` |
| H2 locked | Kill stale process; DB auto-recovers |
| Notification fails | Check `osascript` in PATH; falls back to log |
