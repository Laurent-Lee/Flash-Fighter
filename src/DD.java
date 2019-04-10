import java.awt.Dimension;

import java.awt.Toolkit;
 
public class DD {
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double SCREENWIDTH = screenSize.getWidth();
	public static final double SCREENHEIGHT = screenSize.getHeight();
	
	public static final double TIMERSPEED = 1;
	
	public static final double XBORDER = screenSize.getWidth()*.0;
	public static final double YTOPBORDER = screenSize.getHeight()*.0;
	public static final double YBOTBORDER = screenSize.getHeight()*.015;
	
	public static final double PLYRXSPD = screenSize.getWidth()*.0015;
			
	public static final double BOUNCESPEED = screenSize.getHeight()*.0003;
	public static final double JUMPHEIGHT = -SCREENHEIGHT*.005; 
	public static final double GRAVITY = SCREENHEIGHT*.00008;
	public static final double TERMINALSPEED = PLYRXSPD*2;
	
	public static final double INTERACTDISTANCE = SCREENHEIGHT *.0075;

	public static final double PLATSMALLWIDTH = SCREENWIDTH*.03;
	public static final double PLATMEDIUMWIDTH = SCREENWIDTH*.05;
	public static final double PLATLARGEWIDTH = SCREENWIDTH*.1;
	
	public static final double THIRDSPACING = SCREENWIDTH*.175;
	public static final double FOURTHSPACING = SCREENWIDTH*.1733333;
	
	public static final double PLATSMALLHEIGHT = SCREENHEIGHT*.03;
	public static final double PLATMEDIUMHEIGHT = SCREENHEIGHT*.05;
	public static final double PLATLARGEHEIGHT = SCREENHEIGHT*.1;
	
	
	
	public static final double PLYRWIDTH = screenSize.getWidth()*.05;
	public static final double PLYRHEIGHT = screenSize.getHeight()*.12;
	
	public static final double HEARTWIDTH = screenSize.getWidth()*.02; 
	public static final double HEARTHEIGHT = screenSize.getHeight()*.04;
	public static final double PLAYERHEARTDISTANCE = screenSize.getWidth()*.253;
	
	public static final double PLAYER2HEARTX = 3*HEARTWIDTH + PLAYERHEARTDISTANCE;
	public static final double PLAYER3HEARTX = 6*HEARTWIDTH + 2*PLAYERHEARTDISTANCE;
	public static final double PLAYER4HEARTX = 9*HEARTWIDTH + 3*PLAYERHEARTDISTANCE;
	
	
	
	public static final double KNIGHTSPRITEWIDTH = PLYRWIDTH + 2* INTERACTDISTANCE;
	public static final double KNIGHTSPRITEHEIGHT = PLYRHEIGHT + INTERACTDISTANCE;
	public static final double SPRITEDELAYTIME = 85;
	public static final double ATTACKDURATION = SPRITEDELAYTIME*10/2;
	public static final double ATTACKRANGE = 1*INTERACTDISTANCE;
	
	public static final double NINJAIDLESPRITEWIDTH = PLYRWIDTH - 2*INTERACTDISTANCE;
	public static final double NINJAATTACKSPRITEWIDTH = 2*PLYRWIDTH - 2*INTERACTDISTANCE;
	public static final double NINJAJUMPATTACKSPRITEWIDTH = 2*PLYRWIDTH - 4*INTERACTDISTANCE;
	public static final double NINJAATTACKSPRITEHEIGHT = PLYRHEIGHT + 2 * INTERACTDISTANCE;
	public static final double NINJASPRITEHEIGHT = PLYRHEIGHT + 0*INTERACTDISTANCE;
	
	public static final double KUNAIWIDTH = SCREENWIDTH * .04;
	public static final double KUNAIHEIGHT = SCREENHEIGHT * .02;
	public static final double KUNAISPEED = PLYRXSPD * 3;
	
	
	public static final double OPPWIDTH = screenSize.getWidth()*.03;
	public static final double OPPHEIGHT = screenSize.getHeight()*.05;
	public static final double CANWIDTH = screenSize.getWidth()*.03;
	public static final double CANHEIGHT = screenSize.getHeight()*.05;
	public static final double CBLWIDTH = screenSize.getWidth()*.03;
	public static final double CBLHEIGHT = screenSize.getHeight()*.05;
	public static final double PWRWIDTH = screenSize.getWidth()*.03;
	public static final double PWRHEIGHT = screenSize.getHeight()*.05;
	
	public static final double PLYRXSIZEADJUST = 1.5;
	public static final double PLYRYSIZEADJUST = 1.5;
	public static final double OPPXSIZEADJUST = 1.2;
	public static final double OPPYSIZEADJUST = 1.2;
			
	public static final double PLYRXPOSADJUST = screenSize.getWidth()*.003;
	public static final double PLYRYPOSADJUST = screenSize.getHeight()*.01;
	
	public static final double CANXPOSADJUST = screenSize.getWidth()*.01;
	
	public static final double FONTSIZE = 20;
	
	public static final double XSPAWN = XBORDER;
	public static final double XSPAWN2 = SCREENWIDTH-PLYRWIDTH;
	public static final double XSPAWN3 = XSPAWN+(SCREENWIDTH-PLYRWIDTH)/3;
	public static final double XSPAWN4 = XSPAWN2-(SCREENWIDTH -PLYRWIDTH)/3;
	public static final double YSPAWN = SCREENHEIGHT - PLATLARGEHEIGHT - PLYRHEIGHT;
}