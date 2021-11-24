package demolition;

import processing.core.PGraphics;

public class GameEndScreen extends Screen{

	private String message;

	@Override
	public void update(float delta) {
		
	}
	
	public void render(PGraphics g) {
		g.fill(255,255, 0);
		g.rect(0, 0, App.WIDTH, App.HEIGHT);

		g.fill(0, 0, 0);
		g.text(message, App.WIDTH / 2 - g.textWidth(message) / 2, App.HEIGHT / 2);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
