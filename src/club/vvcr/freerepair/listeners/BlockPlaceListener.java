package club.vvcr.freerepair.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent e) {
		
		if (e.getBlock().getType() != Material.SPAWNER)
			return;
				
		HumanEntity human = e.getPlayer();
		
		ItemStack stack = human.getInventory().getItemInMainHand();
		
		List<String> lore = stack.getItemMeta().getLore();
		
		if (lore.size() < 1)
			return;
		
		for (EntityType entity : EntityType.values()) {
			
			if (entity.name().equalsIgnoreCase(lore.get(0))) {
				
				CreatureSpawner placedBlock = (CreatureSpawner) e.getBlock().getState();
				
				placedBlock.setDelay(1);
				placedBlock.setMaxNearbyEntities(10000);
				placedBlock.setSpawnCount(200);
				placedBlock.setMinSpawnDelay(1);
				placedBlock.setSpawnRange(5);
				
				placedBlock.setSpawnedType(entity);
				
				placedBlock.update();
			}
			
		}
		
	}
	
}
