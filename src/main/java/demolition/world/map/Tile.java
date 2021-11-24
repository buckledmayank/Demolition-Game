package demolition.world.map;

import demolition.AssetManager;
import processing.core.PGraphics;
import processing.core.PImage;
public class Tile {

	public static final char WALL = 'W';
	public static final char BROKEN = 'B';
	public static final char GOAL = 'G';
	public static final char EMPTY = ' ';
	
	
	private PImage image;
	private char type;
	
	public Tile(char type) {
		this.type = type;
		image = AssetManager.getImage( Character.toString(type));
	}

	public char getType() {
		return type;
	}

	public PImage getImage() {
		return image;
	}

	public void render(PGraphics g, int x,int y) {
		g.image(image, x,y,TileMap.TILE_WIDTH,TileMap.TILE_HEIGHT);
		
	}

	public boolean isBrickTile() {
		return type == BROKEN;
	}

	public void destroy() {
		type = EMPTY;
		image = AssetManager.getImage( Character.toString(type));
	}

	public boolean isSolidTile() {
		return type == BROKEN || type == WALL;
	}
	
}
