package ca.runner.game.entities;

import ca.runner.gfx.Screen;
import ca.runner.level.Level;

public abstract class Entity {

	public int x, y;
	public double health;
	protected Level level;
	
	public Entity(Level level) {
		init(level);
	}
	
	public final void init(Level level) {
		this.level = level;
	}
	
	public void damage(double damage) {
		health = health - damage;
	}
	
	public abstract void tick();
	
	public abstract void render(Screen screen);
	
}
