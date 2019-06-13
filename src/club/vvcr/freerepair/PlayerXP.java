package club.vvcr.freerepair;

import org.bukkit.GameMode;

public class PlayerXP {

	private int playerLevel;
	private float playerExp;
	private GameMode gameMode;
	
	public PlayerXP(int level, float xp, GameMode gameMode) {
		this.playerLevel = level;
		this.playerExp = xp;
		this.gameMode = gameMode;
	}
	
	public void setLevel(int level) {
		this.playerLevel = level;
	}
	
	public void setExp(float xp) {
		this.playerExp = xp;
	}
	
	public int getPlayerLevel() {
		return playerLevel;
	}
	
	public float getPlayerExp() {
		return playerExp;
	}
	
	public GameMode getGameMode() {
		return gameMode;
	}
	
	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}
	
}
