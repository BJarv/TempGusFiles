package ca.runner.items;

import ca.runner.gfx.Colours;
import ca.runner.gfx.Screen;
import ca.runner.level.Level;

public class BasicHealthPotion extends HealthPotion{

	private int colour = Colours.get(-1, 300, 400, 500);
	private int scale = 1;
	
	public BasicHealthPotion(Level level, int x, int y) {
		super(level, "Health Potion", x, y, 10, 0);
	}
	
	public void render(Screen screen) {	
		screen.render(x, y, 0 + 23 * 32,  colour, 0, scale);
	}
	
}