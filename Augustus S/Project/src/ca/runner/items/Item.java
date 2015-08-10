package ca.runner.items;

import ca.runner.gfx.Screen;
import ca.runner.level.Level;

public abstract class Item {

	public int x, y;
	protected Level level;
	
	public Item(Level level) {
		init(level);
	}
	
	public final void init(Level level) {
		this.level = level;
	}
	
	public abstract void render(Screen screen);
}
