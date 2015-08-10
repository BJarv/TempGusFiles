package ca.runner.game.entities;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.Vector;

import ca.runner.gfx.Colours;
import ca.runner.gfx.Screen;
import ca.runner.level.Level;

public class BasicAttack extends Projectile{

	private int colour = Colours.get(-1, 300, 400, 500);
	private int scale = 1;
	protected boolean isSwimming = false;
	private int tickCount = 0;
	private double speed = 1;
	private double damage = 5;
	
	//Sets up Mouse Coords and Angle, ect
	PointerInfo a = MouseInfo.getPointerInfo();
	Point b = a.getLocation();
	double finalX =  b.getX();
	double finalY =  b.getY();
	int gradient = 0;
	int numofTimes = 0;
	int dirMouse;
	double angle;
	
	
	public BasicAttack(Level level, boolean isEmitter, String name, int x, int y, double speedModifier, double damageModifier, int scaleModifier, int moveDir, double angle) {
		super(level, false, name, x, y, 2, 5, 1, moveDir, angle);
		this.movingDir = moveDir;
		this.speed = speed  * speedModifier;
		this.damage = damage  * damageModifier;
		this.scale = scale * scaleModifier;
		this.angle = angle;
	}

	public void tick() {
		int xa = 0;
		int ya = 0;
		if (movingDir == 0) {
			ya--;
		} else if (movingDir == 1) {
			ya++;
		} else if (movingDir == 2) {
			xa--;
		} else if (movingDir == 3) {
			xa++;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya, angle);
			isMoving = true;
		}else {
			isMoving = false;
		}
		tickCount++;
	}

	public void render(Screen screen) {	
		screen.render(x, y, 0 + 25 * 32,  colour, 0, scale);	
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
}