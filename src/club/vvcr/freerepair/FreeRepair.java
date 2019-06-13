package club.vvcr.freerepair;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import club.vvcr.freerepair.listeners.InventoryOpenListener;
import club.vvcr.freerepair.listeners.PlayerConsumeListener;
import club.vvcr.freerepair.listeners.PlayerDisconnectListener;
import club.vvcr.freerepair.listeners.PlayerInteractListener;
import club.vvcr.freerepair.commands.EnchantmentStealCommand;
import club.vvcr.freerepair.commands.XPDCommand;
import club.vvcr.freerepair.listeners.BlockBreakListener;
import club.vvcr.freerepair.listeners.BlockPlaceListener;
import club.vvcr.freerepair.listeners.InventoryClickListener;
import club.vvcr.freerepair.listeners.InventoryCloseListener;

public class FreeRepair extends JavaPlugin {

	private AnvilHandler anvilHandler;

	@Override
	public void onEnable() {
		
		anvilHandler = new AnvilHandler();
		
		// Register listeners
		this.getServer().getPluginManager().registerEvents(new InventoryOpenListener(this), this);
		this.getServer().getPluginManager().registerEvents(new InventoryCloseListener(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerDisconnectListener(this), this);
		this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerConsumeListener(), this);
		this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
		
		// Register commands
		this.getCommand("xpd").setExecutor(new XPDCommand());
		this.getCommand("esteal").setExecutor(new EnchantmentStealCommand());
		
		// Add custom recipe
		this.generateEGoldenApple();
		this.generateDragonsBreath();
		this.generateDolphinMelon();
		this.generateGunpowder();
		
		this.getLogger().info("This is a test!\n");
		
	}
	
	@Override
	public void onDisable() {
		
		anvilHandler.clearTable();
		
		this.getLogger().info("It has been turned off :D");
		
	}
	
	public AnvilHandler getAnvilHandler() {
		return anvilHandler;
	}
	
	public void generateEGoldenApple() {
		
		ItemStack enchantedApple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
		
		ShapedRecipe eApple = new ShapedRecipe(new NamespacedKey(this, "x_enchanted_golden_apple"), enchantedApple);
		
		eApple.shape("***", "*A*", "***");
		
		eApple.setIngredient('*', Material.GOLD_BLOCK);
		eApple.setIngredient('A', Material.APPLE);
		
		this.getServer().addRecipe(eApple);
		
	}
	
	public void generateDragonsBreath() {
		
		ItemStack dragonsBreath = new ItemStack(Material.DRAGON_BREATH, 3);
		
		ShapedRecipe dBreath = new ShapedRecipe(new NamespacedKey(this, "x_dragons_breath"), dragonsBreath);
	
		dBreath.shape("drd", "gbg", "dgd");
		
		dBreath.setIngredient('d', Material.DIAMOND);
		dBreath.setIngredient('r', Material.RABBIT_FOOT);
		dBreath.setIngredient('g', Material.GUNPOWDER);
		dBreath.setIngredient('b', Material.GLASS_BOTTLE);
		
		this.getServer().addRecipe(dBreath);
		
	}

	public void generateGunpowder() {
		
		ItemStack gunPowder = new ItemStack(Material.GUNPOWDER, 1);
		
		ShapelessRecipe gPowder = new ShapelessRecipe(new NamespacedKey(this, "easy_gpowder"), gunPowder);
		
		gPowder.addIngredient(Material.SAND);
		gPowder.addIngredient(Material.COAL);
		
		this.getServer().addRecipe(gPowder);
		
	}
	
	public void generateDolphinMelon() {
		
		ItemStack melonSlice = new ItemStack(Material.MELON_SLICE, 1);
		
		ItemMeta melonMeta = melonSlice.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		lore.add("Dolphin Melon");
		
		melonMeta.setLore(lore);
		melonSlice.setItemMeta(melonMeta);
		
		ShapelessRecipe dolphinMelon = new ShapelessRecipe(new NamespacedKey(this, "dolphin_melon"), melonSlice);
		
		dolphinMelon.addIngredient(Material.SUGAR);
		dolphinMelon.addIngredient(Material.MELON_SLICE);
		
		this.getServer().addRecipe(dolphinMelon);
		
	}
	
}
