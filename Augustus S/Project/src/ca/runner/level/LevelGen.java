package ca.runner.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.runner.level.tiles.Tile;

public class LevelGen {
	private byte[] tiles;
	public int width;
	public int height;
	private String imagePath;
	private BufferedImage image;
	
	
	
	public LevelGen(String imagePath) {
				this.imagePath = imagePath;
				this.loadLevelFromFile();
	}
	
	private void loadLevelFromFile() {
		try {
			this.image = ImageIO.read(new File(imagePath));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tiles = new byte[width * height];
			this.loadTiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadTiles() {
		int[] tileColours = this.image.getRGB(0, 0, width, height, null, 0, width);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileCheck: for (Tile t : Tile.tiles) {
					if (t != null && t.getLevelColour() == tileColours[x + y * width]) {
						this.tiles[x + y * width] = t.getId();
						break tileCheck;
					}
				}
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (0 > x || x >= width || 0 > y || y >= height) return Tile.VOID;
		return Tile.tiles[tiles[x + y * width]];
	}
	
}
