package ca.runner.game.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ca.runner.game.myMouseListener;
import ca.runner.level.Level;
import ca.runner.level.tiles.Tile;

public abstract class Projectile extends Entity{

	protected String name;
	protected double speed;
	protected int numSteps = 0;
	protected boolean isMoving;
	protected int movingDir = 1;
	protected double scale;
	protected double damage;
	protected boolean emitter;
	protected int moveDir;
	private List<Entity> lvlEntities;
	protected double vecX;
	protected double vecY;
	protected double angle;
	
	public Projectile(Level level, boolean isEmitter, String name, int x, int y, double speed, double damage, double scale, int MoveDir, double angle) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.damage = damage;
		this.emitter = isEmitter;
		this.scale = scale;
		this.angle = angle;
	}
	
	public boolean isEmitter() {
		return emitter;
	}
	
	public void move(int xa, int ya, double angle) { 
		//bullet.Position += bullet.Direction * bullet.Speed;
		
		if(!hasCollided(xa, ya)) {
			//x+= xa * speed;
			//y += ya * speed;
			x += Math.cos(angle * Math.PI/180) * speed;
			y += Math.sin(angle * Math.PI/180) * speed;
			//x +=  vecX * speed;
			//y += vecY * speed;
			
		} else {
			level.removeProjectile(this);	
		}
	}
	
	public abstract boolean hasCollided(int xa, int ya);
	
	protected boolean isSolidTile(int xa, int ya, int x, int y) {
		if (level == null) { return false;}
		Tile lastTile = level.getTile((this.x + x) >> 3, (this.y + y) >> 3);
		Tile newTile = level.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
		if (!lastTile.equals(newTile) && newTile.isSolid()) {
			return true;
		}
		return false;
	}
	
	protected boolean isMonster(int xa, int ya) {
		for(int z = 0; z < level.getEntities().size(); z++) {
			lvlEntities = level.Entities();
			if(this.x == lvlEntities.get(z).x && this.y == lvlEntities.get(z).y) {
				System.out.println("Hit!");
				level.Entities().get(z).damage(damage);
				level.removeProjectile(this);
			}
		}

		return true;
		
	}
	
	public String getName() {
		return name;
	}
	
}