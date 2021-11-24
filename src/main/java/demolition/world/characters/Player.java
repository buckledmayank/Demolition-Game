package demolition.world.characters;

import java.util.ArrayList;

import animation.Animation;
import demolition.App;
import demolition.world.Bomb;
import demolition.world.map.TileMap;
import processing.core.PGraphics;

public class Player extends Character {

	private boolean up, left, down, right, space;
	private ArrayList<Bomb> bombs;
	private boolean isDead;

	public Player(float x, float y) {
		super(x, y - 44);

		bounds.set((int) x, (int) (y - TileMap.TILE_HEIGHT), TileMap.TILE_WIDTH, TileMap.TILE_HEIGHT);

		walkAnimation.put(DIRECTION.UP, new Animation("player_up", ANIM_TIME / 2f));
		walkAnimation.put(DIRECTION.RIGHT, new Animation("player_right", ANIM_TIME / 2f));
		walkAnimation.put(DIRECTION.LEFT, new Animation("player_left", ANIM_TIME / 2f));
		walkAnimation.put(DIRECTION.DOWN, new Animation("player_down", ANIM_TIME / 2f));

		standingAnimation.put(DIRECTION.UP, walkAnimation.get(DIRECTION.UP).getFrames()[0]);
		standingAnimation.put(DIRECTION.RIGHT, walkAnimation.get(DIRECTION.RIGHT).getFrames()[0]);
		standingAnimation.put(DIRECTION.LEFT, walkAnimation.get(DIRECTION.LEFT).getFrames()[0]);
		standingAnimation.put(DIRECTION.DOWN, walkAnimation.get(DIRECTION.DOWN).getFrames()[0]);

		state = STATE.STANDING;
		facing = DIRECTION.DOWN;

		bombs = new ArrayList<Bomb>();
	}

	@Override
	public void update(float delta) {
		input();
		super.update(delta);

		for (Bomb b : bombs)
			b.update(delta);

		// remove exploded bombs
		for (int i = bombs.size() - 1; i >= 0; i--)
			if (bombs.get(i).isExplosionCompleted())
				bombs.remove(i);

	}

	@Override
	public void render(PGraphics g) {
		super.render(g);
		for (Bomb b : bombs)
			b.render(g);
	}

	private void input() {
		if (state == STATE.WALKING)
			return;

		if (App.applet.keyPressed) {
			if (App.applet.keyCode == App.UP && !up) {
				up = true;
				move(DIRECTION.UP);
			} else if (App.applet.keyCode == App.DOWN && !down) {
				down = true;
				move(DIRECTION.DOWN);
			} else if (App.applet.keyCode == App.RIGHT && !right) {
				right = true;
				move(DIRECTION.RIGHT);
			} else if (App.applet.keyCode == App.LEFT && !left) {
				left = true;
				move(DIRECTION.LEFT);
			} else if (App.applet.key == ' ' && !space) {
				space = true;

				// place bomb
				Bomb bomb = new Bomb(world.getTileMap(), (int) (bounds.y / TileMap.TILE_HEIGHT),
						(int) (bounds.x / TileMap.TILE_WIDTH));
				bomb.setPower(2);
				bombs.add(bomb);
			}
		} else {
			if (App.applet.keyCode == App.UP)
				up = false;
			else if (App.applet.keyCode == App.DOWN)
				down = false;
			else if (App.applet.keyCode == App.RIGHT)
				right = false;
			else if (App.applet.keyCode == App.LEFT)
				left = false;
			else if (App.applet.key == ' ')
				space = false;
		}
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

	public ArrayList<Bomb> getBombs() {
		return bombs;
	}

}
