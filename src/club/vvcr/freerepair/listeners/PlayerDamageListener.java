package club.vvcr.freerepair.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerDamageListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerDamage(EntityDamageEvent e) {
		
		if (e.getEntityType() != EntityType.PLAYER)
			return;
		
		if (e.getCause() != DamageCause.FLY_INTO_WALL && e.getCause() != DamageCause.FALL)
			return;
		
		Player player = (Player) e.getEntity();
		
		PlayerInventory pInv = player.getInventory();
		
		ItemStack boots = pInv.getBoots();
		
		if (boots == null || boots.getType() == Material.AIR)
			return;
		
		
		if (boots.containsEnchantment(Enchantment.PROTECTION_FALL) && boots.getEnchantmentLevel(Enchantment.PROTECTION_FALL) == 4) {
			
			e.setCancelled(true);
			
			return;
			
		}
		
		return;
		
	}
	
}
