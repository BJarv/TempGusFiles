package ca.runner.level.tiles;

public class BasicWallTile extends BasicTile{

	public BasicWallTile(int id, int x, int y, int tileColour, int levelColour) {
		super(id, x, y, tileColour, levelColour);
		this.solid = true;
	}

}
