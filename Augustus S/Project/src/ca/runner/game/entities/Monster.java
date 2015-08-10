package ca.runner.game.entities;

import java.util.Random;

import ca.runner.gfx.Colours;
import ca.runner.gfx.Screen;
import ca.runner.level.Level;

public class Monster extends Mob{

	private int colour = Colours.get(-1, 200, 111, 440);
	private int scale = 1;
	protected boolean isSwimming = false;
	private int tickCount = 0;
	private int health = 50;
	private int moveX = 0;
	private int moveY = 0;
	private int timesX = 0;
	private int timesY = 0;
	private int waitTime = 0;
	private boolean agro = false;
	private Random rand = new Random();
	public Monster(Level level, int x, int y) {
		super(level, "Chicken Man", x, y, 1);
		
	}

	private void followPlayer() {
		
	}
	
	private void wander() {
		int xa = 0;
		int ya = 0;
		
		if(timesX <= 0 && timesY <= 0) {
			waitTime = rand.nextInt(100) + 101;
			moveX = rand.nextInt(15) + 1;
			moveY = rand.nextInt(15) + 1;
			timesX = moveX;
			timesY = moveY;
		}
		
		if (waitTime <= 0) {
			if (timesX > 0 || timesY > 0) {
		
			if (moveX > 0 && moveX < 6) {
				xa--;
			} else if (moveX > 0 && moveX > 5) {
				xa++;
			}  
		
			if (moveY > 0 && moveY < 6) {
				ya--;
			} else if (moveY > 0 && moveY > 5) {
				ya++;
			}  
	
			if (xa != 0 || ya != 0) {
				move(xa, ya);
				isMoving = true;
			}else {
				isMoving = false;
			}
			if (level.getTile(this.x >> 3, this.y >> 3).getId() == 4) {
				isSwimming = true;
			}
			if (isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 4) {
				isSwimming = false;
			}
			timesX--;
			timesY--;
			}
		}
		
		waitTime--;
	}
	
	public void tick() {
		if(agro==false) {
			wander();
		} else {
			followPlayer();
		}
		tickCount++;
	}

	
	public void render(Screen screen) {	
		int xTile = 8;
		int yTile = 28;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;;
		
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
		}
		
		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;
		if (isSwimming) {
			int waterColour = 0;
			yOffset += 4;
			if (tickCount % 60 < 15) {
				waterColour = Colours.get(-1, -1, 225, -1);
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;
				waterColour = Colours.get(-1, 225, 115, -1);
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				waterColour = Colours.get(-1, 115, -1, 225);
			} else {
				yOffset -= 1;
				waterColour = Colours.get(-1, 225, 115, -1);
			}
			screen.render(xOffset, yOffset+3, 0 + 27 * 32, waterColour, 0x00, 1);
			screen.render(xOffset + 8, yOffset+3, 0 + 27 * 32, waterColour, 0x01, 1);
		}
		//Upper Body
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale);
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32,  colour, flipTop, scale);
		
		if (!isSwimming) {
			//Lower Body
			screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile+1) * 32, colour, flipBottom, scale);
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile+1) + (yTile+1) * 32, colour, flipBottom, scale);
		}
	}

	
	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}
		
		return false;
	}

	public int getPlayerHealth() {
		return health;
	}
	
	public int getPlayerMoveDir() {
		return movingDir;
	}
	
}
