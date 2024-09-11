package graundbreaking.explosionshandler.utils.config;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public final class ConfigValues {

    @Getter
    private static boolean
            creeperPreventBlocksDamage,
            tntPreventBlocksDamage,
            tntInMinecartPreventBlocksDamage,
            witherPreventBlocksDamage,
            witherActivatePreventBlocksDamage,
            witherHeadsPreventBlocksDamage;

    @Getter
    private static boolean
            creeperPreventEntitiesDamage,
            tntPreventEntitiesDamage,
            tntInMinecartPreventEntitiesDamage,
            witherPreventEntitiesDamage,
            witherActivatePreventEntitiesDamage,
            witherHeadsPreventEntitiesDamage;

    @Getter
    private static boolean
            creeperAddEffect,
            tntAddEffect,
            tntInMinecartAddEffect,
            witherActivateAddEffect,
            witherHeadsAddEffect;

    @Getter
    private static boolean
            creeperPlaySound,
            tntPlaySound,
            tntInMinecartPlaySound,
            witherActivatePlaySound,
            witherHeadsPlaySound;

    @Getter
    private final static Set<String>
            creeperBlockedWorlds = new HashSet<>(),
            tntBlockedWorlds = new HashSet<>(),
            tntInMinecartBlockedWorlds = new HashSet<>(),
            witherBlockedWorlds = new HashSet<>(),
            witherActivateBlockedWorlds = new HashSet<>(),
            witherHeadsBlockedWorlds = new HashSet<>();

    @Getter
    private static final List<String>
            noPermMessages = new ArrayList<>(),
            reloadMessages = new ArrayList<>();

    private final FileConfiguration config;
    private final Logger logger;

    public ConfigValues(final FileConfiguration config, final Logger logger) {
        this.config = config;
        this.logger = logger;
    }

    public void setValues() {

        final ConfigurationSection settings = config.getConfigurationSection("settings");
        final ConfigurationSection messages = config.getConfigurationSection("messages");

        if (settings != null) {
            creeperPreventBlocksDamage = settings.getBoolean("creeper.prevent-explosion-blocks-damage");
            tntPreventBlocksDamage = settings.getBoolean("tnt.prevent-explosion-blocks-damage");
            tntInMinecartPreventBlocksDamage = settings.getBoolean("tnt-in-minecart.prevent-explosion-blocks-damage");
            witherPreventBlocksDamage = settings.getBoolean("wither.prevent-blocks-breaking");
            witherActivatePreventBlocksDamage = settings.getBoolean("wither-charging.prevent-explosion-blocks-damage");
            witherHeadsPreventBlocksDamage = settings.getBoolean("wither-heads.prevent-explosion-blocks-damage");

            creeperPreventEntitiesDamage = settings.getBoolean("creeper.prevent-explosion-entities-damage");
            tntPreventEntitiesDamage = settings.getBoolean("tnt.prevent-explosion-entities-damage");
            tntInMinecartPreventEntitiesDamage = settings.getBoolean("tnt-in-minecart.prevent-explosion-entities-damage");
            witherPreventEntitiesDamage = settings.getBoolean("wither.prevent-entities-damage");
            witherActivatePreventEntitiesDamage = settings.getBoolean("wither-charging.prevent-explosion-entities-damage");
            witherHeadsPreventEntitiesDamage = settings.getBoolean("wither-heads.prevent-entities-damage");

            creeperAddEffect = settings.getBoolean("creeper.add-explosion-large-effect");
            tntAddEffect = settings.getBoolean("tnt.add-explosion-large-effect");
            tntInMinecartAddEffect = settings.getBoolean("tnt-in-minecart.add-explosion-large-effect");
            witherActivateAddEffect = settings.getBoolean("wither-charging.add-explosion-large-effect");
            witherHeadsAddEffect = settings.getBoolean("wither-heads.add-explosion-large-effect");

            creeperPlaySound = settings.getBoolean("creeper.play-generic-explode-sound");
            tntPlaySound = settings.getBoolean("tnt.play-generic-explode-sound");
            tntInMinecartPlaySound = settings.getBoolean("tnt-in-minecart.play-generic-explode-sound");
            witherActivatePlaySound = settings.getBoolean("wither-charging.play-generic-explode-sound");
            witherHeadsPlaySound = settings.getBoolean("wither-heads.play-generic-explode-sound");

            creeperBlockedWorlds.clear();
            creeperBlockedWorlds.addAll(settings.getStringList("creeper.blocked-worlds"));
            tntBlockedWorlds.clear();
            tntBlockedWorlds.addAll(settings.getStringList("tnt.blocked-worlds"));
            tntInMinecartBlockedWorlds.clear();
            tntInMinecartBlockedWorlds.addAll(settings.getStringList("tnt-in-minecart.blocked-worlds"));
            witherBlockedWorlds.clear();
            witherBlockedWorlds.addAll(settings.getStringList("wither.blocked-worlds"));
            witherActivateBlockedWorlds.clear();
            witherActivateBlockedWorlds.addAll(settings.getStringList("wither-charging.blocked-worlds"));
            witherHeadsBlockedWorlds.clear();
            witherHeadsBlockedWorlds.addAll(settings.getStringList("wither-heads.blocked-worlds"));
        } else {
            logger.warning("\u001b[91mFailed to load values from \"settings\" section. Please check your configuration file, or delete it and restart your server!\u001b[0m");
        }

        if (messages != null) {
            noPermMessages.clear();
            noPermMessages.addAll(getMessages(messages, "no-perm"));
            reloadMessages.clear();
            reloadMessages.addAll(getMessages(messages, "reload"));
        } else {
            logger.warning("\u001b[91mFailed to load messages from \"messages\" section. Please check your configuration file, or delete it and restart your server!\u001b[0m");
        }
    }

    private static List<String> getMessages(final @NotNull ConfigurationSection section, final String path) {
        if (section.get(path) instanceof String) {
            return List.of(section.getString(path, "&4(!) &cFailed to get message on path: " + section + "." + path));
        } else if (!section.getStringList(path).isEmpty()) {
            return section.getStringList(path);
        } else {
            return List.of("&4(!) &cFailed to get message on path: " + section + "." + path);
        }
    }
}
