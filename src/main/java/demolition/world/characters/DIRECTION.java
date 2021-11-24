package demolition.world.characters;

import demolition.world.map.TileMap;

public enum DIRECTION {

	UP(0,-TileMap.TILE_HEIGHT),
	DOWN(0,TileMap.TILE_HEIGHT),
	RIGHT(TileMap.TILE_WIDTH,0),
	LEFT(-TileMap.TILE_WIDTH,0);
	
	private int dx,dy;
	
	private DIRECTION(int dx,int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}

	public DIRECTION opposite() {
		if(this == UP)return DOWN;
		if(this == DOWN)return UP;
		if(this ==  LEFT)return RIGHT;
		if(this == RIGHT)return LEFT;
		return null;
	}
}
