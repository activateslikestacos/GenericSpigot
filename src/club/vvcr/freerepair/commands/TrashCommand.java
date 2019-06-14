package club.vvcr.freerepair.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TrashCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to run this command!");
			return true;
		}
		
		Player player = (Player) sender;
	
		Inventory tInventory = player.getServer().createInventory(null, 36);
		
		player.openInventory(tInventory);
		
		return true;
		
	}

	
	
}
