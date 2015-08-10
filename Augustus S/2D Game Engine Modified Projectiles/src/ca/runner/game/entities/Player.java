package ca.runner.game.entities;

import java.awt.ItemSelectable;

import java.lang.Object.*;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ca.runner.game.Game;
import ca.runner.game.InputHandler;
import ca.runner.game.myMouseListener;
import ca.runner.gfx.Colours;
import ca.runner.gfx.Screen;
import ca.runner.items.Boots;
import ca.runner.items.Chestplate;
import ca.runner.items.Helmet;
import ca.runner.items.Item;
import ca.runner.items.Pants;
import ca.runner.items.Relic;
import ca.runner.level.Level;

public class Player extends Mob{

	private InputHandler input;
	private int colour = Colours.get(-1, 111, 145, 543);
	private int scale = 1;
	protected boolean isSwimming = false;
	private int tickCount = 0;
	private int currCooldown = 0;
	private int projectileCooldown = 50;
	private myMouseListener mml;

	
	//Equipment
	private Chestplate chest;
	private Boots boots;
	private Helmet helm;
	private Pants pants;
	private Relic relic;
	
	//Stats
	private int armor;
	private int magicresist;
	private int ad;
	private int spellDmg;
	private int moveSpeed;
	private int attackSpeed;
	private int health = 100;
	private int vecX;
	private int vecY;
	
	public ArrayList<Item> inventory = new ArrayList<Item>();
	public Player(Level level, int x, int y, InputHandler input, myMouseListener mml) {
		super(level, "Player", x, y, 1);
		this.input = input;
		this.health = health;
		this.mml = mml;
	}

	
	public void tick() {
		//Vector mousePosition = new Vector(mml.getMouseX(), mml.getMouseY());
		Vector playerPos = new Vector(x, y);
		Point playerPos1 = new Point(x, y);
		Point p = mml.getPoint();
		
		//Vector mousePoint = new Vector(p.x, p.y);
		
		//Two ways of finding points, find out screen /2 and then find mouse coords on screen, then minus the two
		//Or find player position in game, and mouse position in game, and use those two
		//2nd one sounds nicer, but no idea if you can do that
		
		//Vector test = p - playerPos1;
		
		//Vector target = new Vector ( p.x - x, p.y - y);
	//	double angle = Math.atan2( p.x - x, p.y - y);
		//System.out.println(playerX);
		vecX = 400;
		vecY = 400;
		
		float mouseX = mml.getMouseX();
		float mouseY = mml.getMouseY();
		//System.out.println(mml.getPoint().x);
		//float mouseX = (float) mml.getPoint().getX();
		//float mouseY = (float) mml.getPoint().getY();
		
		//get new mouse value
		float differenceX = mouseX - vecX;
		float differenceY = mouseY - vecY;
		double angle = (float)Math.atan2(differenceY, differenceX) * 180 / Math.PI;
		//vecY = mml.getMouseY() - y;
		//System.out.println(vecX);
		//System.out.println(x - (Game.WIDTH / 2));
		//System.out.println(mml.getMouseY());
		
		double len = length();
		/*if (len > 0) {
		  vecX /= len;
		  vecY /= len;
		} */
		//System.out.println(vecX);
		Vector movement = new Vector(vecX, vecY);
		
		int xa = 0;
		int ya = 0;
		if(input.up.isPressed()) {ya--;}
		if(input.down.isPressed()) {ya++;}
		if(input.left.isPressed()) {xa--;}
		if(input.right.isPressed()) {xa++;}
		
		if(input.space.isPressed()) {
			
		}
		
		if(mml.getMouseState() == true) {
			if(currCooldown <= 0) {
				System.out.println("mouse coord X: "+mml.getMouseX());
				System.out.println("center of screen? X: "+vecX);
				//System.out.println("differenceX: "+differenceX);
				System.out.println("mouse coord Y: "+mml.getMouseY());
				System.out.println("center of screen? Y: "+vecY);
				//System.out.println("differenceY: "+differenceY);
				//System.out.println("angle: "+angle);
				
				BasicAttack Fireball = new BasicAttack(level, false, "Fireball", x, y, 1, 1, 1, getPlayerMoveDir(), angle);
				level.addProjectile(Fireball);
				currCooldown = projectileCooldown;
			}
		}
		//Allows you to pick up items
		if(input.z.isPressed()) {
			for (int i = 0; i < level.items.size(); i++) {
				int itemX = level.items.get(i).x;
				int itemY = level.items.get(i).y;
				itemX = itemX - x;
				itemY = itemY - y;
				if(itemX <= 10 && itemX >= -10 && itemY <= 10 && itemY >= -10) {
					addToInventory(level.items.get(i));
					System.out.println("Added " +level.items.get(i)+ " to your inventory");
					level.removeItem(level.items.get(i));
				}
			}
		}
		
		//Use special
		if(input.space.isPressed()) {
			
		}
		
		//Gets Inventory: Placeholder
		if(input.i.isPressed()) {
			for (int x = 0; x < inventory.size(); x++) {
				System.out.println(inventory.get(x));
			}
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
		if(currCooldown > 0) {
			currCooldown--;
		}
		tickCount++;
	}

	
	public void render(Screen screen) {	
		int xTile = 0;
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

	//Collision detection
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
	
	public synchronized List<Item> getInventory() {
		return this.inventory;
	}
	
	public void addToInventory(Item item) {
		this.getInventory().add(item);
	}
	
	//Gets current armor
	public Chestplate getChest() {
		return chest;
	}
	
	public Boots getBoots() {
		return boots;
	}
	
	public Pants getPants() {
		return pants;
	}
	
	public Helmet getHelm() {
		return helm;
	}
	
	//Adds armor
	public void addChest(Chestplate newChest) {
		if(chest==null) {
			chest = newChest;
		}
	}
	
	public void addHelm(Helmet newHelm) {
		if(helm==null) {
			helm = newHelm;
		}
	}
	
	public void addBoots(Boots newBoots) {
		if(boots==null) {
			boots = newBoots;
		}
	}
	
	public void addPants(Pants newPants) {
		if(pants==null) {
			pants = newPants;
		}
	}
	
	//Removes armor
	public void removeChest() {
		chest=null;
	}
	
	public void removeHelm() {
		helm=null;
	}
	
	public void removeBoots() {
		boots=null;
	}
	
	public void removePants() {
		pants=null;
	}
	
	public double length(){
	    return Math.sqrt(vecX*vecX + vecY*vecY);
	}
}