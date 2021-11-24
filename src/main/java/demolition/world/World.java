package demolition.world;

import java.util.ArrayList;

import demolition.world.characters.Character;
import demolition.world.characters.Enemy;
import demolition.world.characters.Player;
import demolition.world.map.TileMap;
import processing.core.PGraphics;

public class World {

	private TileMap tileMap;
	private Player player;
	private ArrayList<Enemy> enemies;
	private int time;

	public World(TileMap tileMap, ArrayList<Enemy> enemies, Player player) {
		this.tileMap = tileMap;
		this.enemies = enemies;
		this.player = player;

		player.setWorld(this);
		for (Enemy e : enemies)
			e.setWorld(this);
	}

	public void update(float delta) {
		player.update(delta);
		for (Enemy e : enemies)
			e.update(delta);
		
		//remove dead enemies
		for (int i = enemies.size() - 1; i >= 0; i--)
			if (enemies.get(i).isDead())
				enemies.remove(i);
	}

	public void render(PGraphics g) {
		tileMap.render(g);
		player.render(g);
		for (Enemy e : enemies)
			e.render(g);
	}

	public boolean isCollisionAt(Character character, float f, float g) {
		return false;
	}

	public boolean isSolidTile(float f, float g) {
		return tileMap.isSolidTile(f, g);
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public Player getPlayer() {
		return player;
	}

}
