package demolition.world.characters;

import java.util.Random;

public class RedAI extends EnemyAI {

	public RedAI(Enemy enemy) {
		super(enemy);
	}

	@Override
	protected void changeDirection() {
		DIRECTION dirs[] = DIRECTION.values();
		Random random = new Random();

		//take a random direction
		while (true) {
			DIRECTION dir = dirs[random.nextInt(dirs.length)];
			if (enemy.canMove(dir)) {
				this.dir = dir;
				break;
			}
		}
	}

}
