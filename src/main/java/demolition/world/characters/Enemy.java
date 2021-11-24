package demolition.world.characters;

import animation.Animation;
import demolition.world.map.TileMap;

public class Enemy extends Character {

	public static final int RED_AI = 1;
	public static final int YELLOW_AI = 2;

	private EnemyAI ai;
	private boolean isDead;

	public Enemy(int aiType, String enemyName, float x, float y) {
		super(x, y - 44);

		bounds.set((int) x, (int) (y - TileMap.TILE_HEIGHT), TileMap.TILE_WIDTH, TileMap.TILE_HEIGHT);

		walkAnimation.put(DIRECTION.UP, new Animation(enemyName + "_up", ANIM_TIME / 2f));
		walkAnimation.put(DIRECTION.RIGHT, new Animation(enemyName + "_right", ANIM_TIME / 2f));
		walkAnimation.put(DIRECTION.LEFT, new Animation(enemyName + "_left", ANIM_TIME / 2f));
		walkAnimation.put(DIRECTION.DOWN, new Animation(enemyName + "_down", ANIM_TIME / 2f));

		standingAnimation.put(DIRECTION.UP, walkAnimation.get(DIRECTION.UP).getFrames()[0]);
		standingAnimation.put(DIRECTION.RIGHT, walkAnimation.get(DIRECTION.RIGHT).getFrames()[0]);
		standingAnimation.put(DIRECTION.LEFT, walkAnimation.get(DIRECTION.LEFT).getFrames()[0]);
		standingAnimation.put(DIRECTION.DOWN, walkAnimation.get(DIRECTION.DOWN).getFrames()[0]);

		state = STATE.STANDING;
		facing = DIRECTION.DOWN;

		ai = aiType == RED_AI ? new RedAI(this) : new YellowAI(this);

	}

	@Override
	public void update(float delta) {
		ai.update(delta);
		super.update(delta);
	}

	public boolean move(DIRECTION dir) {
		if (state != STATE.STANDING)
			return false;
		boolean moved = super.move(dir);
		return moved;

	}

	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		sx = x;
		sy = y;
		dx = x;
		dy = y;
	}

	public boolean isDead() {
		return isDead;
	}

	public void die() {
		isDead = true;
	}
}
