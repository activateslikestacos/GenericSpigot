package club.vvcr.freerepair.listeners;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import club.vvcr.freerepair.AnvilHandler;
import club.vvcr.freerepair.FreeRepair;
import club.vvcr.freerepair.PlayerXP;

public class InventoryCloseListener implements Listener {

	FreeRepair plugin;
	public InventoryCloseListener(FreeRepair plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onInventoryClose(InventoryCloseEvent e) {
		
		Player player = (Player) e.getPlayer();
		UUID uuid = player.getUniqueId();
		AnvilHandler anvil = plugin.getAnvilHandler();
		
		if (e.getInventory().getType() == InventoryType.ANVIL && plugin.getAnvilHandler().hasPlayer(player.getUniqueId())) {
			
			if (!plugin.getAnvilHandler().hasPlayer(uuid))
				return;
			
			PlayerXP pXP = anvil.getPlayerExperience(uuid);
			
			player.setLevel(pXP.getPlayerLevel());
			player.setExp(pXP.getPlayerExp());
			
			anvil.removePlayer(uuid);
			
			player.sendMessage("Your experience has been set back to normal!");
			
			player.setGameMode(pXP.getGameMode());
			
		}
		
	}
	
}
