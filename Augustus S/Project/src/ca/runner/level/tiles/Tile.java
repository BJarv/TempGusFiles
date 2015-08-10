package ca.runner.level.tiles;

import ca.runner.gfx.Colours;
import ca.runner.gfx.Screen;
import ca.runner.level.Level;

public abstract class Tile {

	//Colours work like this Each number is 1 color: RGB
	//This number goes up to 5, gets * by 255 then divided by 5
	/* If num is 1: 51
 	 if num is 2: 102 
	 if num is 3:153
	 if num is 4:204
	 if num is 5:255
	 */
	public static final Tile[] tiles = new Tile[256];							//Ignore the 0xFF the next 6 numbers are what the map uses
	public static final Tile VOID = new BasicSolidTile(0, 0, 0, Colours.get(000, -1, -1, -1), 0xFF000000);
	public static final Tile STONE = new BasicSolidTile(1, 1, 0, Colours.get(-1, 333, -1, -1), 0xFF555555);
	public static final Tile GRASS = new BasicTile(2, 2, 0, Colours.get(-1, 131, 141, -1), 0xFF00FF00);
	public static final Tile SAND = new BasicTile(3, 3, 0, Colours.get(-1, 550, 440, -1), 0xFFFFF600);
	public static final Tile WATER = new AnimatedTile(4, new int[][] {{0,5}, {1,5}, {2,5}, {1,5}}, Colours.get(-1, 004, 115, -1), 0xFF0000FF, 1000);
	public static final Tile BEDROCK = new BasicSolidTile(5, 4, 0, Colours.get(-1, 333, -1, -1), 0xFF232222);
	
	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	private int levelColour;

	public Tile(int id, boolean isSolid, boolean isEmitter, int levelColour) {
		this.id = (byte) id;
		if (tiles[id] != null) throw new RuntimeException("Dublication tile id on " + id);
		this.solid = isSolid;
		this.emitter = isEmitter;
		this.levelColour = levelColour;
		tiles[id] = this;
	}

	public byte getId() {
		return id;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isEmitter() {
		return emitter;
	}

	public int getLevelColour() {
		return levelColour;
	}

	public abstract void tick();
	
	public abstract void render(Screen screen, Level level, int x, int y);

}