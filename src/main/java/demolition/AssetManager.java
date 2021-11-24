package demolition;

import java.util.HashMap;

import processing.core.PImage;

public class AssetManager {

	private static HashMap<String,PImage> allImages = new HashMap<String,PImage>();
	private static HashMap<String,PImage[]> allFrames = new HashMap<String,PImage[]>();
	
	public static void load() {
		loadImages( App.applet);
		loadAnimations( App.applet);
	}
	
	private static void loadImages(App applet) {
		//tiles
		allImages.put("B", applet.loadImage("broken/broken.png"));
		allImages.put("W", applet.loadImage("wall/solid.png"));
		allImages.put(" ", applet.loadImage("empty/empty.png"));
		allImages.put("G", applet.loadImage("goal/goal.png"));
		
		//explosion
		allImages.put("centre", applet.loadImage("explosion/centre.png"));
		allImages.put("horizontal", applet.loadImage("explosion/horizontal.png"));
		allImages.put("vertical", applet.loadImage("explosion/vertical.png"));
		allImages.put("up", applet.loadImage("explosion/end_top.png"));
		allImages.put("down", applet.loadImage("explosion/end_bottom.png"));
		allImages.put("left", applet.loadImage("explosion/end_left.png"));
		allImages.put("right", applet.loadImage("explosion/end_right.png"));
		
		//icons
		allImages.put("clock_icon", applet.loadImage("icons/clock.png"));
		allImages.put("player_icon", applet.loadImage("icons/player.png"));
		
	}
	
	private static void loadAnimations(App applet) {
		//player
		PImage[] left,right,up,down;
		left = new PImage[4];
		right = new PImage[4];
		up = new PImage[4];
		down = new PImage[4];
		
		for(int i = 0; i < 4;i++) {
			left[i] = applet.loadImage("player/player_left" + (i + 1) + ".png");
			right[i] = applet.loadImage("player/player_right" + (i + 1) + ".png");
			up[i] = applet.loadImage("player/player_up" + (i + 1) + ".png");
			down[i] = applet.loadImage("player/player" + (i + 1) + ".png");
		}
		
		
		allFrames.put("player_left", left);
		allFrames.put("player_up", up);
		allFrames.put("player_down",down);
		allFrames.put("player_right", right);
		
		//red enemy
		PImage[] red_left,red_right,red_up,red_down;
		red_left = new PImage[4];
		red_right = new PImage[4];
		red_up = new PImage[4];
		red_down = new PImage[4];
		
		for(int i = 0; i < 4;i++) {
			red_left[i] = applet.loadImage("red_enemy/red_left" + (i + 1) + ".png");
			red_right[i] = applet.loadImage("red_enemy/red_right" + (i + 1) + ".png");
			red_up[i] = applet.loadImage("red_enemy/red_up" + (i + 1) + ".png");
			red_down[i] = applet.loadImage("red_enemy/red_down" + (i + 1) + ".png");
		}
		
		
		allFrames.put("red_left", red_left);
		allFrames.put("red_up", red_up);
		allFrames.put("red_down",red_down);
		allFrames.put("red_right", red_right);
		
		//yellow enemy
		PImage[] yellow_left,yellow_right,yellow_up,yellow_down;
		yellow_left = new PImage[4];
		yellow_right = new PImage[4];
		yellow_up = new PImage[4];
		yellow_down = new PImage[4];
		
		for(int i = 0; i < 4;i++) {
			yellow_left[i] = applet.loadImage("yellow_enemy/yellow_left" + (i + 1) + ".png");
			yellow_right[i] = applet.loadImage("yellow_enemy/yellow_right" + (i + 1) + ".png");
			yellow_up[i] = applet.loadImage("yellow_enemy/yellow_up" + (i + 1) + ".png");
			yellow_down[i] = applet.loadImage("yellow_enemy/yellow_down" + (i + 1) + ".png");
		}
		
		
		allFrames.put("yellow_left", yellow_left);
		allFrames.put("yellow_up", yellow_up);
		allFrames.put("yellow_down",yellow_down);
		allFrames.put("yellow_right", yellow_right);
	
		
		//bomb
		PImage[] bombFrames = new PImage[8];
		for(int i = 0; i < 8;i++)
			bombFrames[i] = applet.loadImage("bomb/bomb" + (i+1) + ".png");
		
		allFrames.put("bomb", bombFrames);
		
		
	}
	
	public static PImage getImage(String key) {
		return allImages.get(key);
	}
	
	public static PImage[] getFrames(String key) {
		return allFrames.get(key);
	}
}
