package animation;

import demolition.AssetManager;
import processing.core.PImage;

public class Animation {

	private PImage[] frames;
	private int index;
	private float frameDelay;
	private float timer;
	private int step = 1;

	private boolean restartAble = true;
	private boolean reverseAble;
	private boolean reverseMode;

	public Animation(PImage[] frames, float frameDelay) {
		this.frames = frames;
		index = 0;
		this.frameDelay = frameDelay;
	}

	public Animation(String framesKey, float frameDelay) {
		this(AssetManager.getFrames(framesKey), frameDelay);
	}

	public void update(float delta) {
		timer += delta;
		if (timer >= frameDelay) {
			index += reverseMode ? -step : step;
			timer = 0;
		}

		if (index >= frames.length) {
			if (restartAble) {
				if (reverseAble) {
					index = frames.length - 1;
					reverseMode = true;
				}
				else
					index = 0;

			} else
				index = frames.length - 1;
		}
		
		if(index < 0) {
			index = 0;
			reverseMode = false;
		}
	}

	public PImage getCurrentFrame(float stateTime) {
		int i = (int) (stateTime / frameDelay);
		if (i >= frames.length)
			i = frames.length - 1;
		return frames[i];
	}

	public void setRestartAble(boolean restartable) {
		this.restartAble = restartable;
	}

	public void reset() {
		index = 0;
		timer = 0;
	}

	public void setIndex(int i) {
		index = i;

	}
	
	public void setFrameDelay(float frameDelay) {
		this.frameDelay = frameDelay;
	}

	public int getIndex() {
		return index;
	}

	public PImage getCurrentFrame() {
		return frames[index];
	}

	public PImage[] getFrames() {
		return this.frames;
	}

	public boolean isAtLastIndex() {
		return index == frames.length - 1;
	}

	public boolean isComplete() {
		return isAtLastIndex();
	}

	public void copyState(Animation animation) {
		timer = animation.getTimer();
		index = animation.getIndex();
	}

	public float getTimer() {
		return timer;
	}

	public void setReverseAble(boolean reverseAble) {
		this.reverseAble = reverseAble;
		
	}

	public float getFrameDelaly() {
		return frameDelay;
	}

}
