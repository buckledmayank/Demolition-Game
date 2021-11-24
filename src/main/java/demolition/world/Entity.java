package demolition.world;

import processing.core.PGraphics;

/**
 * An abstract entity in game
 * @author 
 *
 */
public abstract class Entity {

	protected float x,y;
	
	/**
	 * Constructor
	 * @param x	x coordinate
	 * @param y	y coordinate
	 */
	public Entity(float x,float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * update entity
	 * @param delta	frame delta
	 */
	public abstract void update(float delta);
	
	/**
	 * render entity
	 * @param g	PGraphics
	 * @param camera	camera
	 */
	public abstract void render(PGraphics g);

	
	//getters and setters
	
	public void setLocation(float x,float y) {
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
}
