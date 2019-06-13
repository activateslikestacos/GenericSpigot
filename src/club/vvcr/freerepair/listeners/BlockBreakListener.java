package club.vvcr.freerepair.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockBreakListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent e) {
		
		if (e.getBlock().getType() != Material.SPAWNER)
			return;
		
		Block block = e.getBlock();
	
		CreatureSpawner spawner = (CreatureSpawner) block.getState();
		
		spawner.getBlockData();
		
		ItemStack stack = new ItemStack(Material.SPAWNER, 1);

		ItemMeta meta = stack.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		lore.add(spawner.getSpawnedType().name());
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
		
		e.getPlayer().getWorld().dropItemNaturally(block.getLocation(), stack);
		
	}
	
}
