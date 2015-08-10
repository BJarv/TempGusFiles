package ca.runner.enviroment;

import ca.runner.level.Level;

public abstract class Chest extends EnvPiece{

	protected String name;
	protected int rarity;
	
	public Chest(Level level, String name, int x, int y, int rarity) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.rarity = rarity;
	}

	
	public String getName() {
		return name;
	}
	
}