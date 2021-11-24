package demolition.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CollisionBoxTest {

	@Test
	public void collisionTest() {
		CollisionBox box1 = new CollisionBox(0,0,10,10);
		CollisionBox box2 = new CollisionBox(15,15,10,10);
		CollisionBox box3 = new CollisionBox(5,5,10,10);
		
		assertEquals(false,box1.overlaps(box2));
		assertEquals(true, box1.overlaps(box3));
	}
}
