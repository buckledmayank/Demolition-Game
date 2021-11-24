package demolition;

import processing.core.PGraphics;

public abstract class Screen {

	public abstract void update(float delta);
	public abstract void render(PGraphics g);
}
