import java.awt.*;

import javax.swing.ImageIcon;

public abstract class Item {

	protected double myWidth;
	protected double myHeight;
	protected double myX;
	protected double myY;
	protected double myXSpeed;
	protected double myYSpeed;
	protected ImageIcon imgIcon;
	protected Image img;
	
	public Item(double x, double y, double width, double height, double xSpd, double ySpd) {
		myX = x;
		myXSpeed = xSpd;
		myY = y;
		myYSpeed = ySpd;
		myWidth = width;
		myHeight = height;
	}
	
	public abstract void interact(Item i);
	
	public boolean inBoundaries(Item i) {
		if (getMyX() + getMyWidth() >= i.getMyX() && getMyX() <= i.getMyX() + i.getMyWidth()
				&& getMyY() <= i.getMyY() + i.getMyHeight() && getMyY() + getMyHeight() >= i.getMyY()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean inVicinity(double myBorder, double itemBorder) {
		if (Math.abs(myBorder - itemBorder) <= DD.INTERACTDISTANCE) {
			return true;
		} else {
			return false;
		}
	}
	
	

	public double getMyWidth() {
		return myWidth;
	}

	public void setMyWidth(double width) {
		this.myWidth = width;
	}

	public double getMyHeight() {
		return myHeight;
	}

	public void setMyHeight(double height) {
		this.myHeight = height;
	}

	public double getMyX() {
		return myX;
	}

	public void setMyX(double myX) {
		this.myX = myX;
	}

	public double getMyY() {
		return myY;
	}

	public void setMyY(double myY) {
		this.myY = myY;
	}

	public double getMyXSpeed() {
		return myXSpeed;
	}

	public void setMyXSpeed(double myXSpeed) {
		this.myXSpeed = myXSpeed;
	}

	public double getMyYSpeed() {
		return myYSpeed;
	}

	public void setMyYSpeed(double myYSpeed) {
		this.myYSpeed = myYSpeed;
	}

	public ImageIcon getImgIcon() {
		return imgIcon;
	}

	public void setImgIcon(ImageIcon imgIcon) {
		this.imgIcon = imgIcon;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
}
