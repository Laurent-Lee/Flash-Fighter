import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Player extends Item {
	
	protected int availableJumps;
	protected int currentHorizontalDirection; // 0 = not moving, 1 = left, 2 = right
	protected int currentVerticalDirection;// 0 = not moving, 1 = up, 2 = down
	protected boolean intentRightMovement;
	protected boolean intentLeftMovement;
	protected boolean intentToUncrouch;
	protected boolean heldDownKey;
	protected boolean inAir;
	protected boolean upBlock; // records how many items are blocking the player in the respective direction
	protected boolean downBlock;
	protected boolean leftBlock;
	protected boolean rightBlock;
	protected boolean verticalContact;
	protected boolean horizontalContact;
	protected ArrayList<Image> idleSprites;
	protected ArrayList<Image> runSprites;
	protected ArrayList<Image> jumpSprites;
	protected ArrayList<Image> idleLeftSprites;
	protected ArrayList<Image> runLeftSprites;
	protected ArrayList<Image> jumpLeftSprites;
	protected ArrayList<Image> attackSprites;
	protected ArrayList<Image> attackLeftSprites;
	protected ArrayList<Image> jumpAttackSprites;
	protected ArrayList<Image> jumpAttackLeftSprites;
	protected ArrayList<Image> deadSprites;
	protected ArrayList<Image> deadLeftSprites;
	protected int spriteDirection; // 0 = right, 1 = left
	protected boolean attacking;
	protected boolean attackHit;
	protected boolean jumpAttacking;
	protected long attackStartTime;
	
	protected double deadX, deadY, deadWidth, deadHeight;
	protected long deadUpdateTime;

	protected int health;
	protected boolean dead;

	protected ImageIcon playerImageIcon;
	protected Image playerImage;
	protected ClassLoader cldr;

	public Player(double x, double y, double width, double height, double xSpd, double ySpd) {
		super(x, y, width, height, xSpd, ySpd);
		
		availableJumps = 2;

		idleSprites = new ArrayList<Image>();
		runSprites = new ArrayList<Image>();
		jumpSprites = new ArrayList<Image>();
		attackSprites = new ArrayList<Image>();
		jumpAttackSprites = new ArrayList<Image>();
		deadSprites = new ArrayList<Image>();

		idleLeftSprites = new ArrayList<Image>();
		runLeftSprites = new ArrayList<Image>();
		jumpLeftSprites = new ArrayList<Image>();
		attackLeftSprites = new ArrayList<Image>();
		jumpAttackLeftSprites = new ArrayList<Image>();
		deadLeftSprites = new ArrayList<Image>();

		cldr = this.getClass().getClassLoader();


	}

	public void addImages(String name, ArrayList<Image> sprites, ClassLoader cldr) {
		ImageIcon playerImageIcon = new ImageIcon(cldr.getResource(name));
		Image holderImage = playerImageIcon.getImage();
		Image resizedImage = holderImage.getScaledInstance((int) (myWidth + 2 * DD.INTERACTDISTANCE),
				(int) (myHeight + DD.INTERACTDISTANCE), Image.SCALE_SMOOTH);
		playerImageIcon = new ImageIcon(resizedImage);
		playerImage = playerImageIcon.getImage();
		sprites.add(playerImage);
	}

	public void addImagesCustomDimensions(String name, ArrayList<Image> sprites, ClassLoader cldr, int width, int height) {
		ImageIcon playerImageIcon = new ImageIcon(cldr.getResource(name));
		Image holderImage = playerImageIcon.getImage();
		Image resizedImage = holderImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		playerImageIcon = new ImageIcon(resizedImage);
		playerImage = playerImageIcon.getImage();
		sprites.add(playerImage);
	}
	
	@Override
	public void interact(Item i) {

		if (i == this) {
			return;
		}

		if (i instanceof Platform) {
			Platform iPlat = (Platform) i;
			// Checking if in boundaries, which side the platform is on, and whether the
			// platform is blocking horizontally already or vertical
			// Checking if the platform is already blocking the horizontally or vertically
			// allows the player to move smoothly around corners without glitching
			if (inBoundaries(iPlat)) {

				// if platform is directly under
				if (inVicinity(getMyY() + getMyHeight(), iPlat.getMyY()) && !iPlat.isLeftContact()
						&& !iPlat.isRightContact()) {
					if (myYSpeed > 0)
						setMyYSpeed(0);
					iPlat.setUpContact(true);
					availableJumps = 2;
				} else {
					iPlat.setUpContact(false);
				}

				// if platform is directly above
				if (inVicinity(getMyY(), iPlat.getMyY() + iPlat.getMyHeight()) && !iPlat.isLeftContact()
						&& !iPlat.isRightContact()) {
					setMyYSpeed(DD.BOUNCESPEED);
					iPlat.setDownContact(true);
				} else {
					iPlat.setDownContact(false);
				}

				// if platform is directly to the right
				if (inVicinity(getMyX() + getMyWidth(), iPlat.getMyX()) && !iPlat.isUpContact()
						&& !iPlat.isDownContact()) {
					setMyXSpeed(0);
					iPlat.setLeftContact(true);
				} else {
					iPlat.setLeftContact(false);
				}

				// if platform is directly to the left
				if (inVicinity(getMyX(), iPlat.getMyX() + iPlat.getMyWidth()) && !iPlat.isUpContact()
						&& !iPlat.isDownContact()) {
					setMyXSpeed(0);
					iPlat.setRightContact(true);

				} else {
					iPlat.setRightContact(false);
				}
			} else {
				iPlat.setUpContact(false);
				iPlat.setDownContact(false);
				iPlat.setLeftContact(false);
				iPlat.setRightContact(false);
			}
		}

		if (i instanceof Player) {
			Player iPlyr = (Player) i;
			if (attacking) {
				if (inAttackRange(iPlyr)) {
					if (!attackHit && !iPlyr.isDead()) {
						iPlyr.setHealth(iPlyr.getHealth() - 1);
						attackHit = true;
					}
				}
			}
		}
	}

	public boolean inAttackRange(Item i) {
		if (spriteDirection == 0) {
			if (getMyX() + getMyWidth() + DD.ATTACKRANGE >= i.getMyX()
					&& getMyX() + getMyWidth() / 2 <= i.getMyX() + i.getMyWidth()
					&& getMyY() <= i.getMyY() + i.getMyHeight() && getMyY() + getMyHeight() >= i.getMyY()) {
				return true;
			} else {
				return false;
			}
		} else if (spriteDirection == 1) {
			if (getMyX() + getMyWidth() / 2 >= i.getMyX() && getMyX() - DD.ATTACKRANGE <= i.getMyX() + i.getMyWidth()
					&& getMyY() <= i.getMyY() + i.getMyHeight() && getMyY() + getMyHeight() >= i.getMyY()) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	public boolean inUncrouchBoundaries(Item i) {
		if (getMyX() + getMyWidth() >= i.getMyX() && getMyX() <= i.getMyX() + i.getMyWidth()
				&& getMyY() + getMyHeight() >= i.getMyY() + DD.INTERACTDISTANCE
				&& getMyY() <= i.getMyY() + i.getMyHeight() + DD.INTERACTDISTANCE) {
			return true;
		} else {
			return false;
		}
	}

	public void move() {
		myY = myY + myYSpeed;
		myX = myX + myXSpeed;

		if (inAir) {
			if (Math.abs(myYSpeed) < DD.TERMINALSPEED)
				jumpDecay();
		}

		// allows the player to fluidly go over blocks when jumping
		if (intentLeftMovement) {
			myXSpeed = -DD.PLYRXSPD;
		} else if (!intentLeftMovement && !intentRightMovement) {
			myXSpeed = 0;
		}
		if (intentRightMovement) {
			myXSpeed = DD.PLYRXSPD;
		} else if (!intentRightMovement && !intentLeftMovement) {
			myXSpeed = 0;
		}

		// making sure the player doesnt go out of bounds
		if (myX > DD.SCREENWIDTH - (DD.XBORDER + DD.PLYRWIDTH))
			myXSpeed = 0;
		if (myX < DD.XBORDER)
			myXSpeed = 0;
		if (myY < DD.YTOPBORDER)
			myYSpeed = DD.BOUNCESPEED;
		if (myY > DD.SCREENHEIGHT - (DD.YBOTBORDER + DD.PLYRHEIGHT))
			myYSpeed = 0;
	}

	public void jump() {
		if (!upBlock && availableJumps != 0) {
			myYSpeed = DD.JUMPHEIGHT;
			inAir = true;
			availableJumps--;
		}
	}

	public void jumpDecay() {
		myYSpeed += DD.GRAVITY;
	}

	// https://www.youtube.com/watch?v=HJXl2hmapdo
	public Image flipImage(Image i) {

		BufferedImage image = convertToBufferedImage(i);
		int height = image.getHeight(null);
		int width = image.getWidth(null);
		BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				flipped.setRGB((width - 1) - x, y, image.getRGB(x, y));
			}
		}
		return (Image) flipped;
	}

	// https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
	public BufferedImage convertToBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		return bimage;
	}

	public boolean isInAir() {
		return inAir;
	}

	public void setInAir(boolean inAir) {
		this.inAir = inAir;
	}

	public int getAvailableJumps() {
		return availableJumps;
	}

	public void setAvailableJumps(int availableJumps) {
		this.availableJumps = availableJumps;
	}

	public boolean isUpBlock() {
		return upBlock;
	}

	public void setUpBlock(boolean upBlock) {
		this.upBlock = upBlock;
	}

	public boolean isDownBlock() {
		return downBlock;
	}

	public void setDownBlock(boolean downBlock) {
		this.downBlock = downBlock;
	}

	public boolean isLeftBlock() {
		return leftBlock;
	}

	public void setLeftBlock(boolean leftBlock) {
		this.leftBlock = leftBlock;
	}

	public boolean isRightBlock() {
		return rightBlock;
	}

	public void setRightBlock(boolean rightBlock) {
		this.rightBlock = rightBlock;
	}

	public boolean isVerticalContact() {
		return verticalContact;
	}

	public void setVerticalContact(boolean verticalContact) {
		this.verticalContact = verticalContact;
	}

	public boolean isHorizontalContact() {
		return horizontalContact;
	}

	public void setHorizontalContact(boolean horizontalContact) {
		this.horizontalContact = horizontalContact;
	}

	public int getCurrentHorizontalDirection() {
		return currentHorizontalDirection;
	}

	public void setCurrentHorizontalDirection(int currentHorizontalDirection) {
		this.currentHorizontalDirection = currentHorizontalDirection;
	}

	public int getCurrentVerticalDirection() {
		return currentVerticalDirection;
	}

	public void setCurrentVerticalDirection(int currentVerticalDirection) {
		this.currentVerticalDirection = currentVerticalDirection;
	}

	public boolean isHeldDownKey() {
		return heldDownKey;
	}

	public void setHeldDownKey(boolean heldDownKey) {
		this.heldDownKey = heldDownKey;
	}

	public boolean isIntentRightMovement() {
		return intentRightMovement;
	}

	public void setIntentRightMovement(boolean intentRightMovement) {
		this.intentRightMovement = intentRightMovement;
	}

	public boolean isIntentLeftMovement() {
		return intentLeftMovement;
	}

	public void setIntentLeftMovement(boolean intentLeftMovement) {
		this.intentLeftMovement = intentLeftMovement;
	}

	public boolean isIntentToUncrouch() {
		return intentToUncrouch;
	}

	public void setIntentToUncrouch(boolean intentToUncrouch) {
		this.intentToUncrouch = intentToUncrouch;
	}

	public ArrayList<Image> getIdleSprites() {
		return idleSprites;
	}

	public void setIdleSprites(ArrayList<Image> idleSprites) {
		this.idleSprites = idleSprites;
	}

	public ArrayList<Image> getRunSprites() {
		return runSprites;
	}

	public void setRunSprites(ArrayList<Image> runSprites) {
		this.runSprites = runSprites;
	}

	public ArrayList<Image> getJumpSprites() {
		return jumpSprites;
	}

	public void setJumpSprites(ArrayList<Image> jumpSprites) {
		this.jumpSprites = jumpSprites;
	}

	public int getSpriteDirection() {
		return spriteDirection;
	}

	public void setSpriteDirection(int spriteDirection) {
		this.spriteDirection = spriteDirection;
	}

	public ArrayList<Image> getIdleLeftSprites() {
		return idleLeftSprites;
	}

	public void setIdleLeftSprites(ArrayList<Image> idleLeftSprites) {
		this.idleLeftSprites = idleLeftSprites;
	}

	public ArrayList<Image> getRunLeftSprites() {
		return runLeftSprites;
	}

	public void setRunLeftSprites(ArrayList<Image> runLeftSprites) {
		this.runLeftSprites = runLeftSprites;
	}

	public ArrayList<Image> getJumpLeftSprites() {
		return jumpLeftSprites;
	}

	public void setJumpLeftSprites(ArrayList<Image> jumpLeftSprites) {
		this.jumpLeftSprites = jumpLeftSprites;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public ArrayList<Image> getAttackSprites() {
		return attackSprites;
	}

	public void setAttackSprites(ArrayList<Image> attackSprites) {
		this.attackSprites = attackSprites;
	}

	public long getAttackStartTime() {
		return attackStartTime;
	}

	public void setAttackStartTime(long attackStartTime) {
		this.attackStartTime = attackStartTime;
	}

	public ArrayList<Image> getAttackLeftSprites() {
		return attackLeftSprites;
	}

	public void setAttackLeftSprites(ArrayList<Image> attackLeftSprites) {
		this.attackLeftSprites = attackLeftSprites;
	}

	public ArrayList<Image> getJumpAttackSprites() {
		return jumpAttackSprites;
	}

	public void setJumpAttackSprites(ArrayList<Image> jumpAttackSprites) {
		this.jumpAttackSprites = jumpAttackSprites;
	}

	public ArrayList<Image> getJumpAttackLeftSprites() {
		return jumpAttackLeftSprites;
	}

	public void setJumpAttackLeftSprites(ArrayList<Image> jumpAttackLeftSprites) {
		this.jumpAttackLeftSprites = jumpAttackLeftSprites;
	}

	public boolean isJumpAttacking() {
		return jumpAttacking;
	}

	public void setJumpAttacking(boolean jumpAttacking) {
		this.jumpAttacking = jumpAttacking;
	}

	public boolean isAttackHit() {
		return attackHit;
	}

	public void setAttackHit(boolean attackHit) {
		this.attackHit = attackHit;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public ArrayList<Image> getDeadSprites() {
		return deadSprites;
	}

	public void setDeadSprites(ArrayList<Image> deadSprites) {
		this.deadSprites = deadSprites;
	}

	public double getDeadX() {
		return deadX;
	}

	public void setDeadX(double deadX) {
		this.deadX = deadX;
	}

	public double getDeadY() {
		return deadY;
	}

	public void setDeadY(double deadY) {
		this.deadY = deadY;
	}

	public double getDeadWidth() {
		return deadWidth;
	}

	public void setDeadWidth(double deadWidth) {
		this.deadWidth = deadWidth;
	}

	public double getDeadHeight() {
		return deadHeight;
	}

	public void setDeadHeight(double deadHeight) {
		this.deadHeight = deadHeight;
	}

	public long getDeadUpdateTime() {
		return deadUpdateTime;
	}

	public void setDeadUpdateTime(long deadUpdateTime) {
		this.deadUpdateTime = deadUpdateTime;
	}

	public ArrayList<Image> getDeadLeftSprites() {
		return deadLeftSprites;
	}

	public void setDeadLeftSprites(ArrayList<Image> deadLeftSprites) {
		this.deadLeftSprites = deadLeftSprites;
	}


}
