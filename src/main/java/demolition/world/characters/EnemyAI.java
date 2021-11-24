package demolition.world.characters;

public abstract class EnemyAI {

	protected Enemy enemy;
	private float timer;
	protected DIRECTION dir;

	public EnemyAI(Enemy enemy) {
		this.enemy = enemy;
		dir = DIRECTION.DOWN;
	}

	public void update(float delta) {
		timer += delta;
		if (timer >= 1f) {
			timer = 0;

			if (enemy.canMove(dir))
				enemy.move(dir);
			else {
				changeDirection();
				enemy.move(dir);
			}
		}
	}

	protected abstract void changeDirection();

}
