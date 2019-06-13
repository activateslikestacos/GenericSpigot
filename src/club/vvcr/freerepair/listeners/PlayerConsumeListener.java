package club.vvcr.freerepair.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerConsumeListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerConsume(PlayerItemConsumeEvent e) {
		
		Player cody = e.getPlayer();
		
		if (!e.getPlayer().getName().equalsIgnoreCase("realfoxtalks"))
			return;
		
		if (e.getItem().getType() == Material.MILK_BUCKET) {
			e.setCancelled(true);
			cody.getInventory().setItemInMainHand(new ItemStack(Material.GRAVEL, 64));
			cody.sendMessage("Gravel is part of a healthy diet");
		}
		
	}
	
}
