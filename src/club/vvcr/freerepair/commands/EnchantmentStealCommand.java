package club.vvcr.freerepair.commands;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class EnchantmentStealCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can run this command");
			return true;
		}
		
		Player player = (Player) sender;
				
		ItemStack inHand = player.getInventory().getItemInMainHand();
		
		Map<Enchantment, Integer> enchantments = inHand.getEnchantments();
		
		if (enchantments.size() < 1) {
			player.sendMessage("There are no enchantments on this item?");
			return true;
		}
		
		int selectedEnchant = genRandomInt(enchantments.size());
				
		if (!player.getInventory().containsAtLeast(new ItemStack(Material.BOOK), 1)) {
			player.sendMessage("You need a book to store the enchant");
			return true;
		}
		
		Iterator<Map.Entry<Enchantment,Integer>> it = enchantments.entrySet().iterator();
		int i = 0;
		
		Map.Entry<Enchantment, Integer> pair = null;
		
		while (it.hasNext()) {
			
			pair = (Map.Entry<Enchantment, Integer>)it.next();
			
			if (selectedEnchant <= i) 
				break;
			
			i++;
			
		}
		
		ItemStack bookToGive = new ItemStack(Material.ENCHANTED_BOOK, 1);
		bookToGive.getItemMeta().addEnchant(pair.getKey(), pair.getValue(), true);
		
		EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) bookToGive.getItemMeta();
		bookMeta.addStoredEnchant(pair.getKey(), pair.getValue(), true);
		bookToGive.setItemMeta(bookMeta);
		
		player.getInventory().removeItem(new ItemStack(Material.BOOK, 1));
		inHand.removeEnchantment(pair.getKey());
		
		Map<Integer, ItemStack> returnedItems = player.getInventory().addItem(bookToGive);
		
		if (returnedItems.size() > 0) {
			
			for (Integer amount : returnedItems.keySet()) {
				
				player.getWorld().dropItemNaturally(player.getLocation(), returnedItems.get(amount));
				
			}
			
		}
		
		return true;
	}

	private int genRandomInt(int max) {
		
		Random rand = new Random(System.currentTimeMillis());
		
		return rand.nextInt(max);
		
	}
	
}
