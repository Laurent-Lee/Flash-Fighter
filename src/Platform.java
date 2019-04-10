import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Platform extends Item{

	private boolean currentlyContact;
	private boolean leftContact;
	private boolean rightContact;
	private boolean upContact;
	private boolean downContact;
	private Image platformImage;
	private ImageIcon platformImageIcon;
	
	public Platform(double x, double y, double width, double height, double xSpd, double ySpd) {
		super(x,y,width,height,xSpd,ySpd);
	}
	
	public Platform(double x, double y, double width, double height) {
		super(x,y,width,height, 0, 0);
		ClassLoader cldr = this.getClass().getClassLoader();
		
		ImageIcon platformPic = new ImageIcon(cldr.getResource("PlatformMiddle.png"));
		platformImageIcon = platformPic;
		Image platformResized = platformImageIcon.getImage();
		Image platformImageResized = platformResized.getScaledInstance((int)(myWidth + 2 * DD.INTERACTDISTANCE), (int) myHeight, platformResized.SCALE_SMOOTH);
		platformImageIcon = new ImageIcon(platformImageResized);
		platformImage = platformImageIcon.getImage();
		img = platformImage;
	}
	

	@Override
	public void interact(Item i) {
	}

	public boolean isCurrentlyContact() {
		return currentlyContact;
	}

	public void setCurrentlyContact(boolean currentlyContact) {
		this.currentlyContact = currentlyContact;
	}

	public boolean isLeftContact() {
		return leftContact;
	}

	public void setLeftContact(boolean leftContact) {
		this.leftContact = leftContact;
	}

	public boolean isRightContact() {
		return rightContact;
	}

	public void setRightContact(boolean rightContact) {
		this.rightContact = rightContact;
	}

	public boolean isUpContact() {
		return upContact;
	}

	public void setUpContact(boolean upContact) {
		this.upContact = upContact;
	}

	public boolean isDownContact() {
		return downContact;
	}

	public void setDownContact(boolean downContact) {
		this.downContact = downContact;
	}
	
	public String toString() {
		return "x =  " + myX + "y =  " + myY + "width =  " + myWidth + "height =  " + myHeight;
	}
}
