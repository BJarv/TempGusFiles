package ca.runner.items;

import ca.runner.level.Level;

public abstract class Boots extends Item{

	protected String name;
	protected int armor;
	protected int magicResist;
	
	public Boots(Level level, String name, int x, int y, int armor, int magicResist) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.armor = armor;
		this.magicResist = magicResist;
	}

	
	public String getName() {
		return name;
	}
	
}