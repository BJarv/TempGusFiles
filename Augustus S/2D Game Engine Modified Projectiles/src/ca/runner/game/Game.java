package ca.runner.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import ca.runner.game.entities.Monster;
import ca.runner.game.entities.Player;
import ca.runner.gfx.Screen;
import ca.runner.gfx.SpriteSheet;
import ca.runner.items.BasicHealthPotion;
import ca.runner.items.BronzeChestplate;
import ca.runner.level.Level;
import ca.runner.level.Templates;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 160; //original is 160
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Dungeon Runner";
	
	private JFrame frame;
	
	public boolean running = false;
	public int tickCount = 0;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()) .getData();
	private int[] colours = new int[6*6*6];
	private BufferedImage hud;
	
	private Screen screen;
	public InputHandler input;
	public myMouseListener mml;
	public Level level;
	public Player player;
	public BasicHealthPotion hpPotion;
	
	public Game() {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(true);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);  
	}	

	public void init() {
		int index = 0;
		
		//RGB
		for(int r = 0; r < 6; r++) {
			for(int g = 0; g < 6; g++) {
				for(int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					
					colours[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}
		
		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sprite_sheet.png"));
		input = new InputHandler(this);
		level = new Level(null, Templates.normalDungeon());
		mml = new myMouseListener(this);
		
		player = new Player(level, 500, 500, input, mml);
		Monster monster = new Monster(level, 500, 500);
		level.addEntity(monster);
		level.addEntity(player);
		
		hpPotion = new BasicHealthPotion(level, 500, 500);
		//level.addItem(hpPotion);
		BronzeChestplate plate= new BronzeChestplate(level, 500, 500);
		level.addItem(plate);
	}
	
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void run() {	
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		init();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while(delta >= 1) {
				ticks++;
				tick();
				delta -=1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			
			if(shouldRender) {
			frames++;
			render();
			}
			
			if(System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
			//	System.out.println(ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}
			if(player.getPlayerHealth() == 0) {
				level = new Level("/levels/small_test_level.png", null);
			}
		}
	}
	
	private void tick() {	
		tickCount++;	
		level.tick();
		level.projectileTick();
	}

	private void render() {
		
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
	
	int xOffset = player.x - (screen.width / 2);
	int yOffset = player.y - (screen.height / 2);
		
	level.renderTiles(screen, xOffset, yOffset);
	
	level.renderItems(screen);
	
	level.renderEnvPiece(screen);
	
	level.renderProjectiles(screen);

	
	level.renderEntities(screen);
	
	
	for (int y = 0; y < screen.height; y++) {
		for (int x = 0; x < screen.width; x++) {
			int colourCode = screen.pixels[x+y * screen.width];
			if(colourCode < 255) pixels[x + y * WIDTH] = colours[colourCode];
		}
	}
	Graphics g = bs.getDrawGraphics();	
	g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	
		try {
			hud = ImageIO.read(
				getClass().getResourceAsStream(
					"/HUD/RealmOfTheMadGodBlankInventory.png"
				)
			);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		g.drawRect (frame.getWidth() - frame.getWidth() / 5, 0, frame.getWidth() / 5, frame.getHeight());    
		g.fillRect (frame.getWidth() - frame.getWidth() / 5, 0, frame.getWidth() / 5, frame.getHeight());
		g.setColor(null);
		
		Color grey = new Color(128, 128, 128);
		for(int l = 0; l<10; l++)  {
			g.drawRect (frame.getWidth() - frame.getWidth() / 5, 0, frame.getWidth() / 5, frame.getHeight());    
			g.fillRect (frame.getWidth() - frame.getWidth() / 5, 0, frame.getWidth() / 5, frame.getHeight());
			g.setColor(grey);
		}
	
	g.dispose();
	bs.show();
	}
	
	public static void main(String[] args) {
		new Game().start();
	} 
}
