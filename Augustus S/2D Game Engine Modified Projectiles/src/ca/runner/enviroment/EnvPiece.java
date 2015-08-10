package ca.runner.enviroment;

import ca.runner.gfx.Screen;
import ca.runner.level.Level;

public abstract class EnvPiece {

	public int x, y;
	protected Level level;
	
	public EnvPiece(Level level) {
		init(level);
	}
	
	public final void init(Level level) {
		this.level = level;
	}
	
	public abstract void render(Screen screen);
}
