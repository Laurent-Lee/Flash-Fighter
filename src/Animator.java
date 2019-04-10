import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// taken from https://www.youtube.com/watch?v=zRi0vzQbuqY
public class Animator {

	private ArrayList<Image> frames;

	private Image sprite;

	private long lastUpdateTime, delay;
	private int currentFrame;
	private int playerType;

	private int animationState; // 0 = idle, 1 = running, 2 = jumping, 3 = attack, 4 = jumpattack; if 1 is in
								// front it means the left version ofot
	// ex: 10 = idle left, 11 = running left ... etc

	public Animator(ArrayList<Image> f) {
		frames = f;
		delay = (int) DD.SPRITEDELAYTIME;
	}

	public Animator(ArrayList<Image> f, Player plyr) {
		frames = f;
		delay = (int) DD.SPRITEDELAYTIME;
		playerType = plyr.getCharacterType();
	}

	public void setDelay(long d) {
		delay = d;
	}

	public void update(long time) {
		if (time - lastUpdateTime >= delay) {

			if (playerType != 1) {

				if (animationState != 5 && animationState != 15) {
					currentFrame++;
				} else if (animationState == 5 || animationState == 15) {
					if (currentFrame < 9) {
						currentFrame++;
					} else {
						return;

					}
				}
			}
			
			else if (playerType == 1) {
				if (animationState != 5 && animationState != 15 && animationState != 2 && animationState != 12) {
					currentFrame++;
				} else if (animationState == 5 || animationState == 15 || animationState == 2 || animationState == 12) {
					if (currentFrame < 9) {
						currentFrame++;
					} else {
						return;

					}
				}
			}
			
			try {
				sprite = frames.get(currentFrame);

			} catch (IndexOutOfBoundsException e) {
				currentFrame = 0;
				sprite = frames.get(currentFrame);

			}
			lastUpdateTime = time;

		}
	}

	public void start() {
		lastUpdateTime = 0;
		currentFrame = 0;
	}

	public void stop() {
		lastUpdateTime = 0;
		currentFrame = 0;

	}

	public void jumpReset() {
		lastUpdateTime = 0;
		currentFrame = 0;
		animationState = 2;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public ArrayList<Image> getFrames() {
		return frames;
	}

	public void setFrames(ArrayList<Image> frames, int state) {
		this.frames = frames;
		lastUpdateTime = 0;
		currentFrame = 0;
		animationState = state;
		if(state == 3 || state == 13 || state == 4 || state == 14) {
			delay = (long)DD.SPRITEDELAYTIME /2;
		}
		else {
			delay = (long)DD.SPRITEDELAYTIME;
		}
	}

	public void setFrames(ArrayList<Image> frames, int state, int currentFrame) {
		this.frames = frames;
		this.currentFrame = currentFrame;
		animationState = state;
		if(state == 3 || state == 13 || state == 4 || state == 14) {
			delay = (long)DD.SPRITEDELAYTIME /2;
		}
		else {
			delay = (long)DD.SPRITEDELAYTIME;
		}
	}

	public int getAnimationState() {
		return animationState;
	}

	public void setAnimationState(int animationState) {
		this.animationState = animationState;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getDelay() {
		return delay;
	}

	public void setFrames(ArrayList<Image> frames) {
		this.frames = frames;
	}
}
