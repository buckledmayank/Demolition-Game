package demolition;

import demolition.world.Bomb;
import demolition.world.World;
import demolition.world.WorldLoader;
import demolition.world.characters.Enemy;
import demolition.world.characters.Player;
import demolition.world.map.Tile;
import demolition.world.map.TileMap;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;

public class GameScreen extends Screen{

	private World world;
	private Player player;

	private int level = 1;
	private int lives;
	private int time;
	private float timer;

	private PImage clockIcon, playerIcon;
	private PFont font;

	public GameScreen() {
		//world
		world = WorldLoader.load(level);
		player = world.getPlayer();
		lives = App.applet.loadJSONObject("config.json").getInt("lives");
		time = world.getTime();

		//icon
		clockIcon = AssetManager.getImage("clock_icon");
		playerIcon = AssetManager.getImage("player_icon");
		
		//font
		font = App.applet.createFont("PressStart2P-Regular.ttf", 20);
		
	}

	public void update(float delta) {
		timer += delta;
		if (timer >= 1f) {
			timer = 0;
			time--;
		}

		world.update(delta);

		// player dies if collided with enemy
		for (Enemy e : world.getEnemies())
			if (e.getBounds().overlaps(player.getBounds())) {
				// reset level,if has lives
				// else game over
				if (--lives > 0) {
					world = WorldLoader.load(level);
					time = world.getTime();
					player = world.getPlayer();
					return;
				} else
					gameOver(false);
			}

		// player dies if collided with its own bomb
		for (Bomb b : player.getBombs())
			if (b.collides(player.getBounds())) {
				// reset level
				// else game over
				if (--lives > 0) {
					world = WorldLoader.load(level);
					time = world.getTime();
					player = world.getPlayer();
					return;
				} else
					gameOver(false);
			}

		// enemies die by bomb
		for (Bomb b : player.getBombs())
			for (Enemy e : world.getEnemies())
				if (b.collides(e.getBounds()))
					e.die();

		// if player reaches goal, then
		if (world.getTileMap().getTile((int) player.getBounds().y / TileMap.TILE_HEIGHT,
				(int) player.getBounds().x / TileMap.TILE_WIDTH).getType() == Tile.GOAL) {

			// advance to next level, if level is 1
			// else end game
			if (level == 1) {
				world = WorldLoader.load(++level);
				player = world.getPlayer();
			} else
				gameOver(true);
		}

	}

	public void render(PGraphics g) {
		// hud
		g.textFont(font);
		g.fill(0, 255, 0);
		g.rect(0, 0, App.WIDTH, 64);
		
		g.fill(0,0,0);
		int playerIconX = App.WIDTH / 2 - playerIcon.width / 2 - 100;
		int playerIconY = 32 - playerIcon.height / 2;
		g.image(playerIcon, playerIconX, playerIconY);
		g.text(" " + lives, playerIconX + playerIcon.width, 42);

		int clockIconX = App.WIDTH / 2 - clockIcon.width / 2 + 100;
		int clockIconY = 32 - clockIcon.height / 2;
		g.image(clockIcon, clockIconX, clockIconY);
		g.text(" " + time, clockIconX + clockIcon.width, 42);

		// world
		g.translate(0, 64);
		world.render(g);
	}
	
	private void gameOver(boolean won) {
		String message = won ? "YOU WIN": "GAME OVER";
		App.applet.endGame(message);
	}

}
