package graundbreaking.explosionshandler;

import graundbreaking.explosionshandler.listeners.EntitiesDamage;
import graundbreaking.explosionshandler.listeners.Explosions;
import graundbreaking.explosionshandler.listeners.UpdateNotifier;
import graundbreaking.explosionshandler.utils.config.ConfigHandler;
import graundbreaking.explosionshandler.utils.config.ConfigValues;
import graundbreaking.explosionshandler.utils.MessageColorizer;
import graundbreaking.explosionshandler.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class ExplosionsBlocker extends JavaPlugin {

    private FileConfiguration config = null;

    @Override
    public void onEnable() {
        final long startTime = System.currentTimeMillis();

        final Logger logger = getLogger();
        final ConfigHandler configHandler = new ConfigHandler(this, logger);
        this.config = configHandler.loadAndSetup();
        configHandler.checkVersion();
        new ConfigValues(this.config, logger).setValues(new MessageColorizer(extractMainVersion() >= 16));

        {
            getServer().getPluginManager().registerEvents(new EntitiesDamage(), this);
            getServer().getPluginManager().registerEvents(new Explosions(), this);

            UpdateNotifier.generateMessage(new MessageColorizer(extractMainVersion() >= 16));
            getServer().getPluginManager().registerEvents(new UpdateNotifier(), this);

            getCommand("exhandler").setExecutor((sender, command, label, args) -> {

                final long reloadStartTime = System.currentTimeMillis();

                if (!sender.hasPermission("exblocker.reload")) {
                    ConfigValues.getNoPermMessages().forEach(sender::sendMessage);
                    return true;
                }

                this.reloadConfig(new MessageColorizer(extractMainVersion() >= 16));
                ConfigValues.getReloadMessages().forEach(msg ->
                        sender.sendMessage(msg.replace("%time%", String.valueOf(System.currentTimeMillis() - reloadStartTime)))
                );

                return true;
            });
        }

        Bukkit.getScheduler().runTaskAsynchronously(this, () ->
                new UpdateChecker(this, logger).startCheck()
        );

        getLogger().info("Plugin was successfully started in: " + (System.currentTimeMillis() - startTime) + "ms.");
    }

    public int extractMainVersion() {
        try {
            return Integer.parseInt(getServer().getMinecraftVersion().split("\\.", 3)[1]);
        } catch (NumberFormatException ex) {
            getLogger().warning("\u001b[32mFailed to extract server version. Plugin may not work correctly!");
            return 0;
        }
    }

    @Override
    public @NotNull FileConfiguration getConfig() {
        return config;
    }

    public void reloadConfig(final MessageColorizer messageColorizer) {
        final Logger logger = getLogger();
        new ConfigHandler(this, logger).loadAndSetup();
        new ConfigValues(config, logger).setValues(messageColorizer);
    }
}
