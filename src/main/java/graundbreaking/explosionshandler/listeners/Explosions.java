package graundbreaking.explosionshandler.listeners;

import graundbreaking.explosionshandler.utils.config.ConfigValues;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public final class Explosions implements Listener {

    @EventHandler
    public void explosions(final EntityExplodeEvent e) {
        final Entity entity = e.getEntity();
        final World world = e.getEntity().getWorld();

        if (entity instanceof Creeper) {
            if (ConfigValues.isCreeperPreventBlocksDamage()
                    && (ConfigValues.getCreeperBlockedWorlds().isEmpty()
                    || ConfigValues.getCreeperBlockedWorlds().contains(world.getName()))
            ) {

                e.setCancelled(true);
            }

            if (ConfigValues.isCreeperAddEffect()) {
                world.spawnParticle(Particle.EXPLOSION_LARGE, e.getEntity().getLocation(), 0);
            }

            if (ConfigValues.isCreeperPlaySound()) {
                world.playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
            }
        }

        else if (entity instanceof TNTPrimed) {
            if (ConfigValues.isTntPreventBlocksDamage()
                    && (ConfigValues.getTntBlockedWorlds().isEmpty()
                    || ConfigValues.getTntBlockedWorlds().contains(world.getName()))
            ) {

                e.setCancelled(true);
            }

            if (ConfigValues.isTntAddEffect()) {
                world.spawnParticle(Particle.EXPLOSION_LARGE, e.getEntity().getLocation(), 0);
            }

            if (ConfigValues.isTntPlaySound()) {
                world.playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
            }
        }

        else if (entity instanceof org.bukkit.entity.minecart.ExplosiveMinecart) {
            if (ConfigValues.isTntInMinecartPreventBlocksDamage()
                    && (ConfigValues.getTntInMinecartBlockedWorlds().isEmpty()
                    || ConfigValues.getTntInMinecartBlockedWorlds().contains(world.getName()))
            ) {

                e.setCancelled(true);
            }

            if (ConfigValues.isTntInMinecartAddEffect()) {
                world.spawnParticle(Particle.EXPLOSION_LARGE, e.getEntity().getLocation(), 0);
            }

            if (ConfigValues.isTntInMinecartPlaySound()) {
                world.playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
            }
        }

        else if (entity instanceof Wither) {
            if (ConfigValues.isWitherPreventBlocksDamage()
                    && (ConfigValues.getWitherBlockedWorlds().isEmpty()
                    || ConfigValues.getWitherBlockedWorlds().contains(world.getName()))
            ) {

                e.setCancelled(true);
            }

            if (ConfigValues.isWitherActivateAddEffect()) {
                world.spawnParticle(Particle.EXPLOSION_LARGE, e.getEntity().getLocation(), 0);
            }

            if (ConfigValues.isWitherActivatePlaySound()) {
                world.playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
            }
        }

        else if (entity instanceof WitherSkull) {
            if (ConfigValues.isWitherHeadsPreventBlocksDamage()
                    && (ConfigValues.getWitherHeadsBlockedWorlds().isEmpty()
                    || ConfigValues.getWitherHeadsBlockedWorlds().contains(world.getName()))
            ) {

                e.setCancelled(true);
            }

            if (ConfigValues.isWitherHeadsAddEffect()) {
                world.spawnParticle(Particle.EXPLOSION_LARGE, e.getEntity().getLocation(), 0);
            }

            if (ConfigValues.isWitherHeadsPlaySound()) {
                world.playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
            }
        }
    }

    @EventHandler
    public void witherBlockBreak(final EntityChangeBlockEvent e) {
        final EntityType entity = e.getEntity().getType();
        final World world = e.getEntity().getWorld();

        if (entity == EntityType.WITHER && ConfigValues.isWitherPreventBlocksDamage()) {
            if (ConfigValues.getWitherBlockedWorlds().isEmpty() || ConfigValues.getWitherBlockedWorlds().contains(world.getName())) {
                e.setCancelled(true);
            }
        }
    }
}
