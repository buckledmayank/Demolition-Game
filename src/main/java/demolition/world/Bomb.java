package demolition.world;

import animation.Animation;
import demolition.AssetManager;
import demolition.world.map.TileMap;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * Defines the bomb in the game. The bomb has 3 states: -idle state: when the
 * bomb is waiting to be exploded -explosion state: when the bomb is exploding -
 * explosion completed state: when the explosion of the bomb is completed
 * 
 * 
 * 
 * @author freel_000
 *
 */
public class Bomb extends Entity {

	public static final int IDLE_STATE = 1;
	public static final int EXPLOSION_STATE = 2;
	public static final int EXPLOSION_COMPLETED_STATE = 3;

	private Animation bombAnimation;
	private PImage center, left, right, up, down;
	private PImage horizontal, vertical;
	private int leftX, leftWidth;
	private int rightX, rightWidth;
	private int upY, upHeight;
	private int downY, downHeight;

	private float waitTime = 2f;
	private float timer;
	private int power = 3;

	private TileMap tileMap;
	private int row, col;
	private int state = IDLE_STATE;

	private float explosionTime = 2f;
	private float explosionTimer;

	/**
	 * Constructor
	 * 
	 * @param tileMap tile map
	 * @param row     row
	 * @param col     col
	 */
	public Bomb(TileMap tileMap, int row, int col) {
		super(row, col);
		this.tileMap = tileMap;
		this.row = row;
		this.col = col;
		this.x = col * TileMap.TILE_WIDTH;
		this.y = row * TileMap.TILE_HEIGHT;

		bombAnimation = new Animation("bomb", 0.55f);
		bombAnimation.setRestartAble(true);

		center = AssetManager.getImage("centre");
		left = AssetManager.getImage("left");
		right = AssetManager.getImage("right");
		up = AssetManager.getImage("up");
		down = AssetManager.getImage("down");
		horizontal = AssetManager.getImage("horizontal");
		vertical = AssetManager.getImage("vertical");
	}

	@Override
	public void update(float delta) {
		switch (state) {
		case IDLE_STATE:
			bombAnimation.update(delta);

			timer += delta;
			if (timer >= waitTime) {
				timer = 0;
				state = EXPLOSION_STATE;
				bomb();
			}

			break;
		case EXPLOSION_STATE:
			explosionTimer += delta;

			if (explosionTimer >= explosionTime) {
				state = EXPLOSION_COMPLETED_STATE;
			}

			break;
		}
	}

	@Override
	public void render(PGraphics g) {
		if (state == EXPLOSION_COMPLETED_STATE)
			return;

		if (state == IDLE_STATE) {
			g.image(bombAnimation.getCurrentFrame(), x, y);
			return;
		}

		// centre
		g.image(center, x, y);

		// left
		int x = leftX;
		for (int i = 0; i < leftWidth; i++, x += TileMap.TILE_WIDTH)
			g.image(i == 0 ? left : horizontal, x, this.y);

		// up
		int y = upY;
		for (int i = 0; i < upHeight; i++, y += TileMap.TILE_HEIGHT)
			g.image(i == 0 ? up : vertical, this.x, y);

		// right
		x = rightX;
		for (int i = 0; i < rightWidth; i++, x += TileMap.TILE_WIDTH)
			g.image(i == rightWidth - 1 ? right : horizontal, x, this.y);

		// down
		y = downY;
		for (int i = 0; i < downHeight; i++, y += TileMap.TILE_HEIGHT)
			g.image(i == downHeight - 1 ? down : vertical, this.x, y);

	}

	/**
	 * Check collision
	 * 
	 * @param box box
	 * @return true if the box collides with the bomb explosion
	 */
	public boolean collides(CollisionBox box) {
		if (state != EXPLOSION_STATE)
			return false;

		// bomb collision boxes
		CollisionBox center = new CollisionBox(x, y, TileMap.TILE_WIDTH, TileMap.TILE_HEIGHT);
		CollisionBox left = new CollisionBox(leftX, y, leftWidth * TileMap.TILE_WIDTH, TileMap.TILE_HEIGHT);
		CollisionBox right = new CollisionBox(rightX, y, rightWidth * TileMap.TILE_WIDTH, TileMap.TILE_HEIGHT);
		CollisionBox up = new CollisionBox(x, upY, TileMap.TILE_WIDTH, upHeight * TileMap.TILE_HEIGHT);
		CollisionBox down = new CollisionBox(x, downY, TileMap.TILE_WIDTH, downHeight * TileMap.TILE_HEIGHT);

		return box.overlaps(center) || box.overlaps(left) || box.overlaps(right) || box.overlaps(up)
				|| box.overlaps(down);
	}

	/**
	 * Starts the bomb.
	 * 
	 * The bomb first destroys the nearby brick tiles and the sets its
	 * left,right,up,down animation length
	 * 
	 */
	private void bomb() {
		/* SETIING UP ANIMATION */
		int p = 0;// power
		
		// right
		rightX = (int) x + TileMap.TILE_WIDTH;
		for (int i = col + 1; i < tileMap.getCols(); i++) {
			if (tileMap.isSolidTile(rightX + rightWidth, y))
				break;
			else
				rightWidth++;

			if (++p >= power)
				break;
		}

		// down
		p = 0;
		downY = (int) y + TileMap.TILE_HEIGHT;
		for (int i = row + 1; i < tileMap.getRows(); i++) {
			if (tileMap.isSolidTile(x, downY + downHeight))
				break;
			else
				downHeight++;

			if (++p >= power)
				break;
		}

		// left
		p = 0;
		leftX = (int) x;
		for (int i = col - 1; i >= 0; i--) {
			if (tileMap.isSolidTile(leftX - TileMap.TILE_WIDTH, y))
				break;
			else {
				leftWidth++;
				leftX -= TileMap.TILE_WIDTH;
			}

			if (++p >= power)
				break;
		}

		// up
		p = 0;
		upY = (int) y;
		for (int i = row - 1; i >= 0; i--) {
			if (tileMap.isSolidTile(x, upY - TileMap.TILE_HEIGHT))
				break;
			else {
				upY -= TileMap.TILE_HEIGHT;
				upHeight++;
			}

			if (++p >= power)
				break;
		}

		p = 0;// power

		/* DESTROYING BRICK TILES */
		// right
		for (int i = col + 1; i < tileMap.getCols() && p < power; i++, p++)
			if (tileMap.isSolidTile(row, i)) {
				if (tileMap.getTile(row, i).isBrickTile())
					tileMap.getTile(row, i).destroy();
				break;
			}

		// left
		p = 0;
		for (int i = col - 1; i >= 0 && p < power; i--, p++)
			if (tileMap.isSolidTile(row, i)) {
				if (tileMap.getTile(row, i).isBrickTile())
					tileMap.getTile(row, i).destroy();
				break;
			}

		// up
		p = 0;
		for (int i = row - 1; i >= 0 && p < power; i--, p++)
			if (tileMap.isSolidTile(i, col)) {
				if (tileMap.getTile(i, col).isBrickTile())
					tileMap.getTile(i, col).destroy();
				break;
			}

		// down
		p = 0;
		for (int i = row + 1; i < tileMap.getRows() && p < power; i++, p++)
			if (tileMap.isSolidTile(i, col)) {
				if (tileMap.getTile(i, col).isBrickTile())
					tileMap.getTile(i, col).destroy();
				break;
			}

	}

	// getters and setters

	public boolean isExplosionCompleted() {
		return state == EXPLOSION_COMPLETED_STATE;
	}

	public void setPower(int power) {
		this.power = power;
	}

}
