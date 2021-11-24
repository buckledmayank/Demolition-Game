package demolition.world.characters;

public class YellowAI extends EnemyAI {

	public YellowAI(Enemy enemy) {
		super(enemy);
	}

	@Override
	protected void changeDirection() {
		DIRECTION newDir = getNextClockWise(dir);

		//keep changing direction clockwise until enemy can move
		while (!enemy.canMove(newDir))
			newDir = getNextClockWise(dir);
		
		this.dir = newDir;
	}

	private DIRECTION getNextClockWise(DIRECTION dir) {
		switch (dir) {
		case UP:
			return DIRECTION.RIGHT;
		case RIGHT:
			return DIRECTION.DOWN;
		case DOWN:
			return DIRECTION.LEFT;
		case LEFT:
			return DIRECTION.UP;
		default:
			return null;
		}
	}

}
