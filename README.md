# Better Death Screen

Client-seitige Minecraft-Mod (Architectury), die den Vanilla-Todesscreen durch einen informativen Screen ersetzt: Todesursache, Koordinaten, Dimension, verlorene XP und Inventar-Übersicht.

**Mod-ID:** `better_death_screen`  
**Paket:** `com.phiro.betterdeathscreen`  
**Copyright:** TheMFCraft (Phiro Studios)

## Unterstützte Versionen

| Minecraft | Fabric | NeoForge | Java |
|-----------|--------|----------|------|
| 1.20.1    | ✓      | ✓        | 21   |
| 1.21.1    | ✓      | ✓        | 21   |
| 1.21.8    | ✓      | ✓        | 21   |
| 1.21.11   | ✓      | ✓        | 21   |
| 26.1      | ✓*     | ✓*       | 25   |

\* 26.1 nutzt Loom 1.15 und unverschlüsselte Mojang-Mappings; Build ggf. nach API-Updates anpassen.

## Bauen

```bash
./gradlew :fabric-1.21.1:build
./gradlew :neoforge-1.21.1:build
./gradlew buildAll   # alle Varianten
```

JARs liegen unter `fabric-<version>/build/libs/` bzw. `neoforge-<version>/build/libs/`.

## Konfiguration (Spiel)

Datei: `.minecraft/config/better_death_screen.json`

```json
{
  "showInventory": true,
  "showCoordinates": true,
  "showDeathCause": true,
  "showXPLevel": true,
  "enableCopyCoords": true
}
```

## Veröffentlichen (Modrinth & CurseForge)

Publishing nutzt das [Mod Publish Plugin](https://github.com/modmuss50/mod-publish-plugin) — **ein Task** für beide Plattformen.

### 1. Lokale Einrichtung

```bash
cp .env.example .env
# .env bearbeiten: Tokens eintragen, PUBLISH_ENABLED=true
```

| Variable | Beschreibung |
|----------|--------------|
| `PUBLISH_ENABLED` | Muss `true` sein, sonst werden Uploads übersprungen |
| `MODRINTH_TOKEN` | [Personal Access Token](https://modrinth.com/settings/personal-access-tokens) |
| `MODRINTH_PROJECT_ID` | Slug oder UUID (z. B. `better-death-screen`) |
| `CURSEFORGE_TOKEN` | [API-Token](https://console.curseforge.com/#/api-tokens) |
| `CURSEFORGE_PROJECT_ID` | Numerische Projekt-ID von CurseForge |
| `CHANGELOG` | Text für die Release-Beschreibung |
| `RELEASE_TYPE` | `STABLE`, `BETA` oder `ALPHA` |

### 2. Manuell hochladen

```bash
# Eine Variante (nach Build)
./gradlew :fabric-1.21.1:build :fabric-1.21.1:publishMods

# Alle konfigurierten Varianten
./gradlew publishAll
```

Es wird nur hochgeladen, wenn `PUBLISH_ENABLED=true` **und** mindestens ein Token gesetzt ist. Modrinth und CurseForge können unabhängig voneinander aktiv sein.

### 3. Automatisch via GitHub

Bei **GitHub Release → Published** läuft `.github/workflows/publish.yml` und führt `publishAll` aus.

Repository-Secrets anlegen:

- `MODRINTH_TOKEN`
- `MODRINTH_PROJECT_ID` (optional)
- `CURSEFORGE_TOKEN` (optional)
- `CURSEFORGE_PROJECT_ID` (optional)

Manuell in der Actions-UI: Workflow **Publish mod** → **Run workflow** → Subprojekt wählen oder `all`.

**Hinweis:** Modrinth- und CurseForge-Projekte müssen vorher auf den jeweiligen Seiten angelegt sein. Die Mod-ID auf CurseForge ist die **Zahl** in der Projekt-URL/API, nicht der Slug.

## Projektstruktur

- `common/` — geteilte Logik (Screen, DeathData, Config)
- `fabric-<version>/` — Fabric-Loader, Mixins
- `neoforge-<version>/` — NeoForge-Events & Mixins
- `gradle/publish.gradle` — Upload-Konfiguration
- `.env.example` — Vorlage für lokale Secrets

## Lizenz

**Better Death Screen Source Available License (BDS-SAL) v1.0** — siehe [LICENSE](LICENSE).

- Quellcode ist öffentlich einsehbar; private Nutzung und eigene Builds sind erlaubt.
- **Weiterveröffentlichung** (Modrinth, CurseForge, Mirrors, fremde Modpacks usw.) nur mit **schriftlicher Zustimmung** des Rechteinhabers.
