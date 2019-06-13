package club.vvcr.freerepair.listeners;

import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClickListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent e) {
		
		if (e.getInventory().getType() != InventoryType.ANVIL)
			return;
		
		if (e.getSlot() == 2) {
			
			
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
				
				Player p = (Player) e.getWhoClicked();
				
				Block anvil = e.getInventory().getLocation().getBlock();
				Material anvilType = anvil.getType();
				BlockData oldData = anvil.getBlockData();
				
				Random rand = new Random(System.currentTimeMillis());
				
				int rand1 = rand.nextInt(5);
				int rand2 = rand.nextInt(5);
				
				// String fullData = oldData.getAsString();
				String direction = oldData.getAsString();
				direction = direction.substring(direction.indexOf("facing=") + 7);
				direction = direction.substring(0, direction.indexOf("]"));
				
				//String beforeDirection = fullData.substring(0, fullData.indexOf(direction));
				//String afterDirection = fullData.substring(fullData.indexOf(direction) + direction.length(), fullData.length());
				
				// my crappy RNG
				if (rand1 != rand2)
					return;
				
				if (anvilType == Material.ANVIL || anvilType == Material.LEGACY_ANVIL) {
					
					anvil.setType(Material.CHIPPED_ANVIL);
									
					anvil.setBlockData(p.getServer().createBlockData("minecraft:chipped_anvil[facing=" + direction + "]"), true);
					anvil.getState().update();
					
				} else if (anvilType == Material.CHIPPED_ANVIL) {
					
					anvil.setType(Material.DAMAGED_ANVIL);
					anvil.setBlockData(p.getServer().createBlockData("minecraft:damaged_anvil[facing=" + direction + "]"), true);
					anvil.getState().update();
					
				} else if (anvilType == Material.DAMAGED_ANVIL) {
					
					anvil.setType(Material.AIR);
					p.getPlayer().getWorld().playEffect(anvil.getLocation(), Effect.ANVIL_BREAK, null);
					
					
				}
				

			}
			
		}
		
		
	}
	
}
