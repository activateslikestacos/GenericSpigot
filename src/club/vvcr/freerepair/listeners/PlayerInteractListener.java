package club.vvcr.freerepair.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerInteractListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK 
				|| e.getPlayer().getInventory().getItemInMainHand().getType() != Material.WOODEN_SHOVEL)
			return;
		
		if (e.getPlayer().getName().equalsIgnoreCase("realfoxtalks")) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10000, 500));
			e.getPlayer().sendMessage("Be careful what you wish for! :)");
		}
		
		Block block = e.getClickedBlock();
		
		if (block != null && block.getType() == Material.BEDROCK) {
			
			ItemStack stack = new ItemStack(Material.BEDROCK, 1);
			
			block.breakNaturally();
			block.getWorld().dropItemNaturally(block.getLocation(), stack);
			
		}
		
	}
	
}
