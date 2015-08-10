package ca.runner.enviroment;

import ca.runner.gfx.Colours;
import ca.runner.gfx.Screen;
import ca.runner.level.Level;

public class WoodenChest extends Chest{

	private int colour = Colours.get(-1, 300, 400, 500);
	private int scale = 1;
	
	public WoodenChest(Level level, int x, int y) {
		super(level, "Wooden Chest", x, y, 1);
	}
	
	public void render(Screen screen) {	
		screen.render(x, y, 0 + 19 * 32,  colour, 0, scale);
	}
	
}