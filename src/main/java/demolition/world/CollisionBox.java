package demolition.world;

import processing.core.PGraphics;

/**
 * Defines a collision box for an entity
 * 
 *
 */
public class CollisionBox {

	public float x, y, width, height;

	/**
	 * Constructor
	 * 
	 * @param x      x coordinate
	 * @param y      y coordinate
	 * @param width  width
	 * @param height height
	 */
	public CollisionBox(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	public void render(PGraphics g) {
		g.rect(x,y, width, height,5);
	}

	/**
	 * Checks if this box overlaps with the other
	 * 
	 * @param r the other box
	 * @return true if it overlaps
	 */
	public boolean overlaps(CollisionBox r) {
		return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
	}
	
	public void set(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

}
