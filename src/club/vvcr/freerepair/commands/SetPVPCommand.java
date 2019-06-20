package club.vvcr.freerepair.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import club.vvcr.freerepair.FreeRepair;

public class SetPVPCommand implements CommandExecutor {

	private FreeRepair plugin;
	public SetPVPCommand(FreeRepair plugin) {
		this.plugin = plugin;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		if (sender instanceof Player && !((Player)sender).getName().equalsIgnoreCase("activates")) {
			return true;
		}
		
		if (args.length < 1) {
			sender.sendMessage("PvP is set to: " + Boolean.toString(plugin.getPVPEnabled()));
			return true;
		}
		
		String arg = args[0];
		
		if (arg.equalsIgnoreCase("on")) {
			
			plugin.setPVP(true);
			sender.sendMessage("PvP is set to: " + Boolean.toString(plugin.getPVPEnabled()));
			
		} else if (arg.equalsIgnoreCase("off")) {
			
			plugin.setPVP(false);
			sender.sendMessage("PvP is set to: " + Boolean.toString(plugin.getPVPEnabled()));
			
		} else {
			
			sender.sendMessage("You must specify either on or off");
			
		}
		
		return true;
	}

	
	
}
