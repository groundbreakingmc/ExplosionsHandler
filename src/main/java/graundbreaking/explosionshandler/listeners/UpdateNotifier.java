package graundbreaking.explosionshandler.listeners;

import graundbreaking.explosionshandler.utils.MessageSender;
import graundbreaking.explosionshandler.utils.UpdateChecker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class UpdateNotifier implements Listener {

    private final MessageSender messageSender;

    public UpdateNotifier(final MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @EventHandler
    private void onJoinNotification(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if ((p.isOp() || p.hasPermission("flightsystem.updates")) && UpdateChecker.getNew_version()) {
            messageSender.sendMessage(p, "");
            messageSender.sendMessage(p, "&c[ExplosionsBlocker] &6New update is available to download!");
            messageSender.sendMessage(p, "&c[ExplosionsBlocker] &fDownload link: " + UpdateChecker.getDownloadLink());
            messageSender.sendMessage(p, "&c[ExplosionsBlocker] &fCurrently version: " + UpdateChecker.getCurrentVersion());
            messageSender.sendMessage(p, "&c[ExplosionsBlocker] &fNewest version: " + UpdateChecker.getLatestVersion());
            messageSender.sendMessage(p, "");
        }
    }
}
