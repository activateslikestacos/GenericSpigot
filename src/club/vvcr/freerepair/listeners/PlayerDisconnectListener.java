package club.vvcr.freerepair.listeners;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import club.vvcr.freerepair.AnvilHandler;
import club.vvcr.freerepair.FreeRepair;
import club.vvcr.freerepair.PlayerXP;

public class PlayerDisconnectListener implements Listener {

	private FreeRepair plugin;
	public PlayerDisconnectListener(FreeRepair plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onPlayerDisconnect(PlayerQuitEvent e) {
		
		Player player = (Player) e.getPlayer();
		AnvilHandler anvil = plugin.getAnvilHandler();
		UUID uuid = player.getUniqueId();
		
		if (!plugin.getAnvilHandler().hasPlayer(player.getUniqueId()))
			return;
			
			PlayerXP pXP = anvil.getPlayerExperience(uuid);
			
			player.setLevel(pXP.getPlayerLevel());
			player.setExp(pXP.getPlayerExp());
			player.setGameMode(pXP.getGameMode());
			
			anvil.removePlayer(uuid);

			
	}
	
}
