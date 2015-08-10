package ca.runner.items;

import ca.runner.level.Level;

public abstract class Relic extends Item{

	protected String name;
	protected int armor;
	protected int magicResist;
	protected int attack;
	protected int health;
	
	public Relic(Level level, String name, int x, int y, int armor, int magicResist) {
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