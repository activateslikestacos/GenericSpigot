package club.vvcr.freerepair;

import org.bukkit.plugin.java.JavaPlugin;

import club.vvcr.freerepair.listeners.InventoryOpenListener;
import club.vvcr.freerepair.listeners.PlayerConsumeListener;
import club.vvcr.freerepair.listeners.PlayerDisconnectListener;
import club.vvcr.freerepair.listeners.PlayerInteractListener;
import club.vvcr.freerepair.commands.XPDCommand;
import club.vvcr.freerepair.listeners.BlockBreakListener;
import club.vvcr.freerepair.listeners.BlockPlaceListener;
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
		
		// Register commands
		this.getCommand("xpd").setExecutor(new XPDCommand());
		
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
	
}
