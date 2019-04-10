import java.awt.Image;

import javax.swing.ImageIcon;

public class ThrowingStar extends Item {

	private boolean dead;
	private Player thrower;

	public ThrowingStar(Player plyr) {
		super(plyr.getMyX() + (plyr.getMyWidth() / 2), plyr.getMyY() + (plyr.getMyHeight() / 2), DD.KUNAIWIDTH,
				DD.KUNAIHEIGHT, DD.KUNAISPEED, 0);
		
		thrower = plyr;
		
		if (plyr.getSpriteDirection() == 1) {
			myXSpeed = -DD.KUNAISPEED;
		}

		if (plyr.getSpriteDirection() == 0) {
			ClassLoader cldr = this.getClass().getClassLoader();
			ImageIcon pic = new ImageIcon(cldr.getResource("Kunai.png"));
			ImageIcon kunaiIcon = pic;
			Image resized = kunaiIcon.getImage();
			Image picresized = resized.getScaledInstance((int) myWidth, (int) myHeight, Image.SCALE_SMOOTH);
			kunaiIcon = new ImageIcon(picresized);
			Image kunai = kunaiIcon.getImage();
			img = kunai;
		}
		else if (plyr.getSpriteDirection() == 1) {
			ClassLoader cldr = this.getClass().getClassLoader();
			ImageIcon pic = new ImageIcon(cldr.getResource("KunaiLeft.png"));
			ImageIcon kunaiIcon = pic;
			Image resized = kunaiIcon.getImage();
			Image picresized = resized.getScaledInstance((int) myWidth, (int) myHeight, Image.SCALE_SMOOTH);
			kunaiIcon = new ImageIcon(picresized);
			Image kunai = kunaiIcon.getImage();
			img = kunai;
		}
	}

	@Override
	public void interact(Item i) {
		if (i instanceof Platform) {
			Platform iPlat = (Platform) i;
			// Checking if in boundaries, which side the platform is on, and whether the
			// platform is blocking horizontally already or vertical
			// Checking if the platform is already blocking the horizontally or vertically
			// allows the player to move smoothly around corners without glitching
			if (inBoundaries(iPlat)) {
				dead = true;
			}
		}

		if (i instanceof Player) {
			Player iPlyr = (Player) i;
			if(inBoundaries(iPlyr) && thrower != iPlyr && !iPlyr.isDead()) {
				iPlyr.setHealth(iPlyr.getHealth()-1);
				dead = true;
			}
		}

	}

	public void move() {
		myY = myY + myYSpeed;
		myX = myX + myXSpeed;

		// making sure the player doesnt go out of bounds
		if (myX > DD.SCREENWIDTH - (DD.XBORDER + DD.PLYRWIDTH))
			dead = true;
		if (myX < DD.XBORDER)
			dead = true;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
