package demolition;

import processing.core.PApplet;

public class App extends PApplet {
	
	public static App applet;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 480;

	public static final int FPS = 60;

	private GameScreen gameScreen;
	private GameEndScreen gameEndScreen;
	private Screen screen;

	public void settings() {
		size(WIDTH, HEIGHT);
	}

	public void setup() {
		applet = this;
		frameRate(FPS);

		// Load images during setup
		AssetManager.load();
		
		//load screens
		gameScreen = new GameScreen();
		gameEndScreen = new GameEndScreen();
		screen = gameScreen;
	}

	public void draw() {
		//update
		screen.update(1f/FPS);
		
		//render
		background(0,0,0);
		screen.render(this.g);
	}

	public void endGame(String message) {
		gameEndScreen.setMessage(message);
		screen = gameEndScreen;
	}
	
	public static void main(String args[]) {
		PApplet.main("demolition.App");
	}

}
