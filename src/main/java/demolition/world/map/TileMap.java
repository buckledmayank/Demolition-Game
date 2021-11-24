package demolition.world.map;

import processing.core.PGraphics;

/**
 * map of tiles
 *
 */
public class TileMap {

	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;

	private Tile[][] tiles;
	private int rows, cols;

	/**
	 * Constructor
	 * 
	 * @param rows  rows of tiles in map
	 * @param cols  columns of tiles in map
	 * @param tiles the tiles
	 */
	public TileMap(int rows, int cols, Tile[][] tiles) {
		this.rows = rows;
		this.cols = cols;

		this.tiles = tiles;
	}


	public void render(PGraphics g) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j].render(g, (int) (j * TILE_WIDTH), (int) (i * TILE_HEIGHT));
			}
		}

	}

	/**
	 * Checks if the tile is solid at x,y
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return true if tile is solid
	 */
	public boolean isSolidTile(float x, float y) {

		int row = (int) (y / TILE_HEIGHT);
		int col = (int) (x / TILE_WIDTH);
		return isSolidTile(row, col);
	}

	/**
	 * Checks if the tile is solid at row,col
	 * 
	 * @param row row
	 * @param col col
	 * @return true if tile is solid
	 */
	public boolean isSolidTile(int row, int col) {

		if (row < 0 || row >= rows || col < 0 || col >= cols)
			return false;

		return tiles[row][col].isSolidTile();
	}

	/**
	 * Checks if the tie is valid, within bounds of map
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return true if tile is valid
	 */
	public boolean isValidTile(float x, float y) {
		int row = (int) (y / TILE_HEIGHT);
		int col = (int) (x / TILE_WIDTH);

		if (row < 0 || row >= rows || col < 0 || col >= cols)
			return false;

		return true;
	}

	// getters

	public Tile getTile(int row, int col) {
		return tiles[row][col];
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

}
