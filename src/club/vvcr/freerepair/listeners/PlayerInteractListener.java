package club.vvcr.freerepair.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
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

		if (e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getClickedBlock() == null)
			return;

		Block block = e.getClickedBlock();
		Material clickedType = block.getType();
		
		if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WOODEN_SHOVEL) {

			if (e.getPlayer().getName().equalsIgnoreCase("realfoxtalks")) {
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10000, 500));
				e.getPlayer().sendMessage("Be careful what you wish for! :)");
			}

			if (clickedType == Material.BEDROCK) {

				ItemStack stack = new ItemStack(Material.BEDROCK, 1);

				block.breakNaturally();
				block.getWorld().dropItemNaturally(block.getLocation(), stack);
				
			}

		} else if ((clickedType == Material.ANVIL || clickedType == Material.CHIPPED_ANVIL || clickedType == Material.DAMAGED_ANVIL) && e.getPlayer().getName().equalsIgnoreCase("apopanda")) {
			
			Block anvil = e.getClickedBlock();
			
			BlockData oldData = anvil.getBlockData();
			String direction = oldData.getAsString();
			direction = direction.substring(direction.indexOf("facing=") + 7);
			direction = direction.substring(0, direction.indexOf("]"));
			
			String newDirection;
			
			if (direction.equals("north")) {
				newDirection = "east";
			} else if (direction.equalsIgnoreCase("east")) {
				newDirection = "south";
			} else if (direction.equalsIgnoreCase("south")) {
				newDirection = "west";
			} else {
				newDirection = "north";
			}
			
			e.getClickedBlock().setBlockData(e.getPlayer().getServer().createBlockData("minecraft:anvil[facing=" + newDirection + "]"));
			
		}

	}

}
