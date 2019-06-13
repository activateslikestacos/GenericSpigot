package club.vvcr.freerepair.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerConsumeListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerConsume(PlayerItemConsumeEvent e) {
		
		Player player = e.getPlayer();
		
		if (e.getItem().getType() == Material.MELON_SLICE) {
			
			ItemMeta melonMeta = e.getItem().getItemMeta();
			
			if (!melonMeta.hasLore() || melonMeta.getLore().size() < 1)
				return;
			
			if (melonMeta.getLore().get(0).equalsIgnoreCase("dolphin melon"))
				player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 10000, 5));
			
		}
		
	}
	
}
