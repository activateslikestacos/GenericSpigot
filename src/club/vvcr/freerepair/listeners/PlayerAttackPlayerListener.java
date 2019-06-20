package club.vvcr.freerepair.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import club.vvcr.freerepair.FreeRepair;

public class PlayerAttackPlayerListener implements Listener {

	private FreeRepair plugin;
	public PlayerAttackPlayerListener(FreeRepair plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPVP(EntityDamageByEntityEvent e) {
		
		if (e.getDamager().getType() != EntityType.PLAYER && e.getEntityType() != EntityType.PLAYER)
			return;
		
		e.setCancelled(!plugin.getPVPEnabled());
		
	}
	
}
