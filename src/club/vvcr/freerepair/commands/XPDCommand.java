package club.vvcr.freerepair.commands;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class XPDCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command can only be ran by a player.. Sorry");
			return true;
		}
		
		Player player = (Player) sender;
		
		int currentLevel = player.getLevel();
		
		if (currentLevel < 30) {
			player.sendMessage("You need at least 30 levels to get a diamond!");
			return true;
		}
		
		if (args.length < 1) {
			player.sendMessage("You need to specify how many diamonds you want, or type 'max'");
			return true;
		}
		
		// now go through our args[0] and see what we get
		String argument = args[0];
		
		if (StringUtils.isNumeric(argument)) {
			
			// gave us a number
			
			// determine how many diamonds they are asking for
			int howManyDiamonds = Integer.parseInt(argument);
			
			int levelAfterDiamonds = this.getNewLevel(currentLevel, howManyDiamonds);
			
			// can they actually get that much?
			if (levelAfterDiamonds < 1) {
				player.sendMessage("That would put you at " + levelAfterDiamonds + " levels.. which wouldn't work");
				return true;
			}
			
			int newLevel = this.giveDiamonds(player, howManyDiamonds, this.getNewLevel(currentLevel, howManyDiamonds));
			
			player.setLevel(newLevel);
			
			player.sendMessage(newLevel + " is now your current level");
			
		} else {
			
			// didn't give us a number
			
			// ensure they gave us max, if not back out
			if (!argument.equalsIgnoreCase("max")) {
				player.sendMessage("'" + argument + "' is not a recognized argument!");
				return true;
			}
			
			// now we calculate how many levels they can take
			double calculatedDiamonds = this.calculateDiamonds(currentLevel);
			
			int choppedCalculation = (int) calculatedDiamonds;
			
			int newLevel = this.giveDiamonds(player, choppedCalculation, this.getNewLevel(currentLevel, choppedCalculation));
			
			player.setLevel(newLevel);
			
			player.sendMessage(newLevel + " is now your current level");
			
		}
		
		return true;
	}

	private double calculateDiamonds(int currentLevel) {
		return (currentLevel / 30.0);
	}
	
	private int getNewLevel(int currentLevel, int diamondsToGive) {
		
		return currentLevel - (diamondsToGive * 30);
		
	}
	
	private int giveDiamonds(Player player, int diamondsToGive, int newLevel) {
		
		player.sendMessage("You will be given '" + diamondsToGive + "' diamonds");
		
		int levelsToGive = newLevel;
		
		ItemStack diamonds = new ItemStack(Material.DIAMOND, diamondsToGive);
		Map<Integer, ItemStack> returnedItems = player.getInventory().addItem(diamonds);
		
		if (returnedItems.size() > 0) {
			
			int totalReturnedDiamonds = 0;
			// items that couldn't be stored
			for (Integer i : returnedItems.keySet()) {
				
				totalReturnedDiamonds += returnedItems.get(i).getAmount();
				
			}
			
			// determine how much xp to give back
			levelsToGive = (totalReturnedDiamonds * 30) + newLevel;
			player.sendMessage(newLevel + " levels were not used because you don't have enough inventory space");
			
		}
		
		return levelsToGive;
		
	}
	
}
