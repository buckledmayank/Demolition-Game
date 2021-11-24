package demolition.world.characters;

import java.util.HashMap;
import java.util.Map;

import animation.Animation;
import demolition.world.CollisionBox;
import demolition.world.Entity;
import demolition.world.World;
import processing.core.PGraphics;
import processing.core.PImage;

public abstract class Character extends Entity {

	protected static final float ANIM_TIME = 0.2f, WALK_TIME = (ANIM_TIME) * 3f;

	protected Map<DIRECTION, Animation> walkAnimation;
	protected Map<DIRECTION, PImage> standingAnimation;

	protected enum STATE {
		WALKING, STANDING;
	}

	protected STATE state;
	protected int sx, sy, dx, dy;
	protected DIRECTION facing;
	protected float animTimer;
	protected float walkTimer;

	protected Entity collidingEntity;
	protected World world;
	protected CollisionBox bounds;

	public Character(float x, float y) {
		super(x, y);

		walkAnimation = new HashMap<DIRECTION, Animation>();
		standingAnimation = new HashMap<DIRECTION, PImage>();
		bounds = new CollisionBox(0,0,0,0);

		state = STATE.STANDING;
		facing = DIRECTION.DOWN;
	}

	@Override
	public void update(float delta) {
		if (state == STATE.WALKING) {
			animTimer += delta;
			walkTimer += delta;
			x = apply(sx, dx, animTimer / ANIM_TIME);
			y = apply(sy, dy, animTimer / ANIM_TIME);
			if (animTimer > ANIM_TIME) {
				setPosition(dx, dy);

				state = STATE.STANDING;
				walkTimer -= animTimer - ANIM_TIME;
				if (walkTimer > WALK_TIME)
					walkTimer = 0;
				animTimer = 0;

			}

		}

	}

	@Override
	public void render(PGraphics g) {
		g.image(getImage(), x, y);
	}

	public boolean move(DIRECTION dir) {
		if (state != STATE.STANDING)
			return false;

		state = STATE.WALKING;
		facing = dir;

		boolean collided = world.isCollisionAt(this, bounds.x + dir.getDx(), bounds.y + dir.getDy());
		if (collided || world.isSolidTile(bounds.x + dir.getDx(), bounds.y + dir.getDy())) {
			sx = (int) x;
			sy = (int) y;
			this.dx = sx;
			this.dy = sy;
			return false;
		}

		this.sx = (int) x;
		this.sy = (int) y;
		this.dx = sx + dir.getDx();
		this.dy = sy + dir.getDy();
		bounds.x += dir.getDx();
		bounds.y += dir.getDy();

		return true;
	}
	
	public boolean canMove(DIRECTION dir) {
		return !world.isSolidTile(bounds.x + dir.getDx(), bounds.y + dir.getDy());
	}

	public PImage getImage() {

		if (state == STATE.WALKING)
			return walkAnimation.get(facing).getCurrentFrame(walkTimer);
		return standingAnimation.get(facing);
	}

	public void setPosition(int dx, int dy) {
		x = dx;
		y = dy;
	}

	private float apply(float a, float b, float p) {
		return (b - a) * p + a;
	}

	public DIRECTION getFacingDirection() {
		return facing;
	}

	public void setFacing(DIRECTION d) {
		facing = d;
	}

	public boolean isStanding() {
		return state == STATE.STANDING;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	public CollisionBox getBounds() {
		return bounds;
	}


}
