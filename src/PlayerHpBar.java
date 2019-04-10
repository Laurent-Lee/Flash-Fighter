import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PlayerHpBar {
	private int health;
	private Image heartImage;
	private int myX;
	private int myY;
	
	public PlayerHpBar(Player plyr,int x, int y) {
		health = plyr.getHealth();
		myX = x;
		myY = y;
		
		ClassLoader cldr = this.getClass().getClassLoader();
		//https://opengameart.org/content/larger-simple-heart
		ImageIcon heartImageIcon = new ImageIcon(cldr.getResource("Heart.png"));
		Image holderImage = heartImageIcon.getImage();
		Image resizedImage = holderImage.getScaledInstance((int) (DD.HEARTWIDTH),
				(int) (DD.HEARTHEIGHT), Image.SCALE_SMOOTH);
		heartImageIcon = new ImageIcon(resizedImage);
		heartImage = heartImageIcon.getImage();
	}

	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Image getHeartImage() {
		return heartImage;
	}

	public void setHeartImage(Image heartImage) {
		this.heartImage = heartImage;
	}


	public int getMyX() {
		return myX;
	}


	public void setMyX(int myX) {
		this.myX = myX;
	}


	public int getMyY() {
		return myY;
	}


	public void setMyY(int myY) {
		this.myY = myY;
	}
	
	

}
