package club.vvcr.freerepair;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AnvilHandler {

	private Map<UUID, PlayerXP> playerExp;
	
	public AnvilHandler() {
		playerExp = new HashMap<UUID, PlayerXP>();
	}
	
	public void clearTable() {
		playerExp.clear();;
	}
	
	public boolean isInTable(UUID uuid) {
		return playerExp.containsKey(uuid);
	}
	
	public void addPlayer(UUID uuid, PlayerXP pXP) {
		
		playerExp.put(uuid, pXP);
		
	}
	
	public PlayerXP getPlayerExperience(UUID uuid) {
		
		return playerExp.get(uuid);
		
	}
	
	public void removePlayer(UUID uuid) {
		playerExp.remove(uuid);
	}
	
	public boolean hasPlayer(UUID uuid) {
		return playerExp.containsKey(uuid);
	}
	
}
