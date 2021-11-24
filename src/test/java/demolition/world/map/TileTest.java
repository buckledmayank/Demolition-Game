package demolition.world.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TileTest {

	@Test
	public void brokenTileTest() {
		Tile brokenTile = new Tile(Tile.BROKEN);
		assertEquals(true,brokenTile.isSolidTile());

		brokenTile.destroy();
		assertEquals(false,brokenTile.isSolidTile());
	}
}
