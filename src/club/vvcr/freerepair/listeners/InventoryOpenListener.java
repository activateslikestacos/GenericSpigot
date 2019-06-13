package club.vvcr.freerepair.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

import club.vvcr.freerepair.FreeRepair;
import club.vvcr.freerepair.PlayerXP;

public class InventoryOpenListener implements Listener {

	FreeRepair plugin;

	public InventoryOpenListener(FreeRepair plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryOpen(InventoryOpenEvent e) {

		HumanEntity player = e.getPlayer();
		Player realPlayer = (Player) player;
		
		if (e.getInventory().getType() != InventoryType.ANVIL)
			return;

		player.sendMessage("A shit ton of XP added for anvil!");
		Player thePlayer = (Player) player;

		plugin.getAnvilHandler().addPlayer(player.getUniqueId(), new PlayerXP(realPlayer.getLevel(), realPlayer.getExp(), realPlayer.getGameMode()));

		player.sendMessage("Your current xp is " + realPlayer.getLevel());
		
		thePlayer.setLevel(2147483647);

		thePlayer.setGameMode(GameMode.CREATIVE);
		
	}

}
