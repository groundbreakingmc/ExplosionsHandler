package graundbreaking.explosionshandler.listeners;

import graundbreaking.explosionshandler.utils.config.ConfigValues;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public final class EntitiesDamage implements Listener {

    @EventHandler
    public void onEntityDamage(final EntityDamageByEntityEvent e) {
        final World world = e.getDamager().getWorld();
        final EntityType damager = e.getDamager().getType();

        if (damager == EntityType.CREEPER && ConfigValues.isCreeperPreventEntitiesDamage()) {
            if (ConfigValues.getCreeperBlockedWorlds().isEmpty() 
                    || ConfigValues.getCreeperBlockedWorlds().contains(world.getName())) {
                    
                e.setCancelled(true);
            }
        }

        else if (damager == EntityType.PRIMED_TNT && ConfigValues.isTntPreventEntitiesDamage()) {
            if (ConfigValues.getTntBlockedWorlds().isEmpty() 
                    || ConfigValues.getTntBlockedWorlds().contains(world.getName())) {
                
                e.setCancelled(true);
            }
        }

        else if (damager == EntityType.MINECART_TNT && ConfigValues.isTntInMinecartPreventEntitiesDamage()) {
            if (ConfigValues.getTntInMinecartBlockedWorlds().isEmpty() 
                    || ConfigValues.getTntInMinecartBlockedWorlds().contains(world.getName())) {
                    
                e.setCancelled(true);
            }
        }

        else if (damager == EntityType.WITHER && ConfigValues.isWitherPreventEntitiesDamage()) {
            if (ConfigValues.getWitherBlockedWorlds().isEmpty() 
                    || ConfigValues.getWitherBlockedWorlds().contains(world.getName())) {
                    
                e.setCancelled(true);
            }
        }

        else if (damager == EntityType.WITHER_SKULL && ConfigValues.isWitherHeadsPreventEntitiesDamage()) {
            if (ConfigValues.getWitherHeadsBlockedWorlds().isEmpty() 
                    || ConfigValues.getWitherHeadsBlockedWorlds().contains(world.getName())) {
                    
                e.setCancelled(true);
            }
        }
    }
}
