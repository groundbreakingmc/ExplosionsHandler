package graundbreaking.explosionshandler.listeners;

import graundbreaking.explosionshandler.utils.MessageColorizer;
import graundbreaking.explosionshandler.utils.UpdateChecker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public final class UpdateNotifier implements Listener {

    private static final List<String> messages = new ArrayList<>();

    public static void generateMessage(final MessageColorizer messageColorizer) {
        messages.add("");
        messages.add(messageColorizer.colorize("&c[ExplosionsBlocker] &6New update is available to download!"));
        messages.add(messageColorizer.colorize("&c[ExplosionsBlocker] &fDownload link: " + UpdateChecker.getDownloadLink()));
        messages.add(messageColorizer.colorize("&c[ExplosionsBlocker] &fCurrently version: " + UpdateChecker.getCurrentVersion()));
        messages.add(messageColorizer.colorize("&c[ExplosionsBlocker] &fNewest version: " + UpdateChecker.getLatestVersion()));
        messages.add("");
    }

    @EventHandler
    private void onJoinNotification(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if ((p.isOp() || p.hasPermission("flightsystem.updates")) && UpdateChecker.getNew_version()) {
            messages.forEach(p::sendMessage);
        }
    }
}
