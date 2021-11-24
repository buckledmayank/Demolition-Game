package demolition.world;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import demolition.App;
import demolition.world.characters.Enemy;
import demolition.world.characters.Player;
import demolition.world.map.Tile;
import demolition.world.map.TileMap;
import processing.data.JSONObject;

public class WorldLoader {

	public static World load(int level) {
		JSONObject configFile = App.applet.loadJSONObject("config.json");
		JSONObject levelData = (JSONObject) configFile.getJSONArray("levels").get(level - 1);
		
		String filePath = levelData.getString("path");
		int time = levelData.getInt("time");

		String mapData[] = loadTextFileAsString(filePath).split("\n");
		int rows = mapData.length;
		int cols = mapData[0].length();

		// load tile,player & enemies
		Tile[][] tiles = new Tile[rows][cols];
		Player player = null;
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				char type = mapData[i].charAt(j);

				switch (type) {
				case 'P':
					player = new Player(j * TileMap.TILE_WIDTH, i * TileMap.TILE_HEIGHT + TileMap.TILE_HEIGHT);
					tiles[i][j] = new Tile(Tile.EMPTY);
					break;

				case 'R':
					enemies.add(new Enemy(Enemy.RED_AI,"red",j * TileMap.TILE_WIDTH, i * TileMap.TILE_HEIGHT + TileMap.TILE_HEIGHT));
					tiles[i][j] = new Tile(Tile.EMPTY);
					break;
				case 'Y':
					enemies.add(new Enemy(Enemy.YELLOW_AI,"yellow",j * TileMap.TILE_WIDTH, i * TileMap.TILE_HEIGHT + TileMap.TILE_HEIGHT));
					tiles[i][j] = new Tile(Tile.EMPTY);
					break;

				default:
					tiles[i][j] = new Tile(type);
					break;

				}
			}
		
		World world = new World(new TileMap(rows,cols,tiles),enemies,player);;
		world.setTime(time);
		return world;
	}

	private static String loadTextFileAsString(String path) {

		try (Scanner scan = new Scanner(new File(path))) {
			String file = "";
			while (scan.hasNext()) {
				file += scan.nextLine() + "\n";
			}
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
}
