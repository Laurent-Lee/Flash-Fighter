import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;

public class ArrKeyListener extends JPanel implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Timer t;
	int numberOfPlayers;
	private Player plyr;
	private Player plyr2;
	private Player plyr3;
	private Player plyr4;

	private ArrayList<ThrowingStar> plyr1Stars;
	private ArrayList<ThrowingStar> plyr2Stars;
	private ArrayList<ThrowingStar> plyr3Stars;
	private ArrayList<ThrowingStar> plyr4Stars;

	private ThrowingStar star1;

	private PlayerHpBar plyrHp;
	private PlayerHpBar plyr2Hp;
	private PlayerHpBar plyr3Hp;
	private PlayerHpBar plyr4Hp;

	private ArrayList<Player> playerList;
	private ArrayList<PlayerHpBar> playerHpList;

	private int mapNumber;
	private ArrayList<Platform> map;
	private ArrayList<Platform> mapCopy2;
	private ArrayList<Platform> mapCopy3;
	private ArrayList<Platform> mapCopy4;
	private MapCreation mapCreator;
	private boolean paused;
	private boolean backToMenu;

	private Animator player1Animator;
	private Animator player2Animator;
	private Animator player3Animator;
	private Animator player4Animator;

	private ImageIcon backgroundIcon;
	private Image background;

	public ArrKeyListener(int playerNum) {
		setLayout(null);
		// https://www.gameart2d.com/free-platformer-game-tileset.html
		ClassLoader cldr = this.getClass().getClassLoader();
		ImageIcon pic = new ImageIcon(cldr.getResource("BackgroundSky.png"));
		backgroundIcon = pic;
		Image resized = backgroundIcon.getImage();
		Image picresized = resized.getScaledInstance((int) DD.SCREENWIDTH, (int) DD.SCREENHEIGHT, Image.SCALE_SMOOTH);
		backgroundIcon = new ImageIcon(picresized);
		background = backgroundIcon.getImage();

		t = new Timer((int) DD.TIMERSPEED, this);
		mapNumber = 1;
		numberOfPlayers = playerNum;
		t.start();

		playerList = new ArrayList<Player>();
		playerHpList = new ArrayList<PlayerHpBar>();

		plyr = new Ninja(DD.XSPAWN, DD.YSPAWN, DD.PLYRWIDTH, DD.PLYRHEIGHT, 0, 0);
		playerList.add(plyr);

		plyrHp = new PlayerHpBar(plyr, 0, (int) DD.FONTSIZE);
		playerHpList.add(plyrHp);

		map = new MapCreation(mapNumber).getMap();
		plyr1Stars = new ArrayList<ThrowingStar>();

		player1Animator = new Animator(plyr.getIdleSprites());
		player1Animator.start();

		if (numberOfPlayers >= 2) {
			plyr2 = new Knight(DD.XSPAWN2, DD.YSPAWN, DD.PLYRWIDTH, DD.PLYRHEIGHT, 0, 0);
			playerList.add(plyr2);
			plyr2Hp = new PlayerHpBar(plyr2, (int) DD.PLAYER2HEARTX, (int) DD.FONTSIZE);
			playerHpList.add(plyr2Hp);
			mapCopy2 = new MapCreation(mapNumber).getMap();
			plyr2Stars = new ArrayList<ThrowingStar>();
			player2Animator = new Animator(plyr2.getIdleSprites(), plyr2);
			player2Animator.start();
		}
		if (numberOfPlayers >= 3) {
			plyr3 = new Knight(DD.XSPAWN3, DD.YSPAWN, DD.PLYRWIDTH, DD.PLYRHEIGHT, 0, 0);
			playerList.add(plyr3);
			plyr3Hp = new PlayerHpBar(plyr3, (int) DD.PLAYER3HEARTX, (int) DD.FONTSIZE);
			playerHpList.add(plyr3Hp);
			mapCopy3 = new MapCreation(mapNumber).getMap();
			plyr3Stars = new ArrayList<ThrowingStar>();
			player3Animator = new Animator(plyr3.getIdleSprites());
			player3Animator.start();
		}
		if (numberOfPlayers >= 4) {
			plyr4 = new Knight(DD.XSPAWN4, DD.YSPAWN, DD.PLYRWIDTH, DD.PLYRHEIGHT, 0, 0);
			playerList.add(plyr4);
			plyr4Hp = new PlayerHpBar(plyr4, (int) DD.PLAYER4HEARTX, (int) DD.FONTSIZE);
			playerHpList.add(plyr4Hp);
			mapCopy4 = new MapCreation(mapNumber).getMap();
			plyr4Stars = new ArrayList<ThrowingStar>();
			player4Animator = new Animator(plyr4.getIdleSprites());
			player4Animator.start();
		}

		// The map copy is made so the player 2 can interact with platforms without
		// causing the interactions of player 1 and platforms to bug

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(background, 0, 0, this);
		Graphics2D g2 = (Graphics2D) g;
		Font f = new Font("Helvetica", Font.BOLD, (int) DD.FONTSIZE);
		g2.setFont(f);

		if (!paused) {

			player1Animator.update(System.currentTimeMillis());

			g.drawImage(player1Animator.getSprite(), (int) plyr.getMyX(), (int) plyr.getMyY(), this);

			drawHearts(plyrHp, plyrHp.getMyX(), plyrHp.getMyY(), g);
			g2.drawString("Player 1", 0, (int) DD.FONTSIZE);

			paintThrowingStars(plyr1Stars, g);

			if (numberOfPlayers >= 2) {

				player2Animator.update(System.currentTimeMillis());

				g.drawImage(player2Animator.getSprite(), (int) plyr2.getMyX(), (int) plyr2.getMyY(), this);

				drawHearts(plyr2Hp, plyr2Hp.getMyX(), plyr2Hp.getMyY(), g);
				g2.drawString("Player 2", plyr2Hp.getMyX(), (int) DD.FONTSIZE);

				paintThrowingStars(plyr2Stars, g);
			}
			if (numberOfPlayers >= 3) {
				player3Animator.update(System.currentTimeMillis());

				g.drawImage(player3Animator.getSprite(), (int) plyr3.getMyX(), (int) plyr3.getMyY(), this);

				drawHearts(plyr3Hp, plyr3Hp.getMyX(), plyr3Hp.getMyY(), g);
				g2.drawString("Player 3", plyr3Hp.getMyX(), (int) DD.FONTSIZE);

				paintThrowingStars(plyr3Stars, g);
			}
			if (numberOfPlayers >= 4) {
				player4Animator.update(System.currentTimeMillis());

				g.drawImage(player4Animator.getSprite(), (int) plyr4.getMyX(), (int) plyr4.getMyY(), this);

				drawHearts(plyr4Hp, plyr4Hp.getMyX(), plyr4Hp.getMyY(), g);
				g2.drawString("Player 4", plyr4Hp.getMyX(), (int) DD.FONTSIZE);

				paintThrowingStars(plyr4Stars, g);
			}
			for (int a = 0; a < map.size(); a++) {
				g.drawImage(map.get(a).getImg(), (int) map.get(a).getMyX(), (int) map.get(a).getMyY(), this);
			}
		} else if (paused) {

		}
	}

	public void paintThrowingStars(ArrayList<ThrowingStar> plyrStars, Graphics g) {
		if (plyrStars.size() > 0) {
			for (int a = 0; a < plyrStars.size(); a++) {
				if (!plyrStars.get(a).isDead()) {
					g.drawImage(plyrStars.get(a).getImg(), (int) plyrStars.get(a).getMyX(),
							(int) plyrStars.get(a).getMyY(), this);
				}
			}
		}
	}

	public void updateThrowingStars(ArrayList<ThrowingStar> plyrStars, ArrayList<Platform> map,
			ArrayList<Player> playerList) {
		if (plyrStars != null && plyrStars.size() > 0) {
			for (int a = 0; a < plyrStars.size(); a++) {
				if (!plyrStars.get(a).isDead()) {
					for (int b = 0; b < map.size(); b++) {
						plyrStars.get(a).interact(map.get(b));
					}
					for (int c = 0; c < playerList.size(); c++) {
						plyrStars.get(a).interact(playerList.get(c));
					}
					plyrStars.get(a).move();
				} else if (plyrStars.get(a).isDead()) {
					plyrStars.remove(a);
					a--;
				}
			}
		}
	}

	public void updateCharge(Knight knight) {
		if(knight.isCharging()) {

			if(knight.getSpriteDirection() == 0) {
				if (!knight.isDead()) {
					if (!knight.isAttacking()) {
						knight.setIntentRightMovement(true);
						knight.setIntentLeftMovement(false);
						knight.setSpriteDirection(0);
						if (!knight.isRightBlock()) {
							knight.setMyXSpeed(DD.CHARGEXSPD);
							knight.setCurrentHorizontalDirection(2);
							if (knight.getMyX() > DD.SCREENWIDTH - (DD.XBORDER + DD.PLYRWIDTH))
								knight.setMyXSpeed(0);
						}
					}
				}
			} else if (knight.getSpriteDirection() == 1) {
				if (!knight.isDead()) {
					if (!knight.isAttacking()) {
						knight.setIntentLeftMovement(true);
						knight.setIntentRightMovement(false);
						knight.setSpriteDirection(1);
						if (!knight.isLeftBlock()) {
							knight.setMyXSpeed(-DD.CHARGEXSPD);
							knight.setCurrentHorizontalDirection(1);
							if (knight.getMyX() < DD.XBORDER)
								knight.setMyXSpeed(0);
						}
					}
				}
			}
			if(System.currentTimeMillis() - knight.getChargeStartTime() > Knight.CHARGE_DURATION) {
				knight.setCharging(false);
			}
		}
		if(knight.isChargeOnCD()) {
			if(System.currentTimeMillis() - knight.getChargeStartTime() > Knight.CHARGE_CD) {
				knight.setChargeOnCD(false);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (!paused) {
			removeAll();
			plyr.move();
			updateAttackAnimation(plyr);
			updateSpriteSettings(plyr, player1Animator);
			interactWithPlayers(plyr, playerList);
			checkIfDead(plyr, player1Animator);

			if(plyr instanceof Knight) {
				updateCharge((Knight) plyr);
			}
			
			updateThrowingStars(plyr1Stars, map, playerList);

			if (numberOfPlayers >= 2) {
				plyr2.move();
				updateAttackAnimation(plyr2);
				updateSpriteSettings(plyr2, player2Animator);
				interactWithPlayers(plyr2, playerList);
				checkIfDead(plyr2, player2Animator);
				
				if(plyr2 instanceof Knight) {
					updateCharge((Knight) plyr2);
				}

				updateThrowingStars(plyr2Stars, map, playerList);
			}
			if (numberOfPlayers >= 3) {
				plyr3.move();
				updateAttackAnimation(plyr3);
				updateSpriteSettings(plyr3, player3Animator);
				interactWithPlayers(plyr3, playerList);
				checkIfDead(plyr3, player3Animator);
				
				if(plyr3 instanceof Knight) {
					updateCharge((Knight) plyr3);
				}

				updateThrowingStars(plyr3Stars, map, playerList);
			}
			if (numberOfPlayers >= 4) {
				plyr4.move();
				updateAttackAnimation(plyr4);
				updateSpriteSettings(plyr4, player4Animator);
				interactWithPlayers(plyr4, playerList);
				checkIfDead(plyr4, player4Animator);

				if(plyr4 instanceof Knight) {
					updateCharge((Knight) plyr4);
				}
				
				updateThrowingStars(plyr4Stars, map, playerList);
			}
			updatePlayerBlocks(plyr, map);
			if (numberOfPlayers >= 2)
				updatePlayerBlocks(plyr2, mapCopy2);
			if (numberOfPlayers >= 3)
				updatePlayerBlocks(plyr3, mapCopy3);
			if (numberOfPlayers >= 4)
				updatePlayerBlocks(plyr4, mapCopy4);
			updatePlayerHp(playerHpList, playerList);
		}

		if (paused) {
			removeAll();
			repaint();
			revalidate();

			JButton resumeButton = new JButton("Resume Game");
			resumeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					paused = false;
				}
			});
			add(resumeButton);
			resumeButton.setForeground(Color.WHITE);
			resumeButton.setBackground(Color.RED);
			resumeButton.setBounds(500, 332, 155, 155);

			JButton menuButton = new JButton("Back To Main Menu");
			menuButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					backToMenu = true;
				}
			});
			add(menuButton);
			menuButton.setForeground(Color.WHITE);
			menuButton.setBackground(Color.RED);
			menuButton.setBounds(700, 332, 155, 155);

			JButton exitButton = new JButton("Exit Game");
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			add(exitButton);
			exitButton.setForeground(Color.WHITE);
			exitButton.setBackground(Color.RED);
			exitButton.setBounds(900, 332, 155, 155);
		}
		repaint();

	}

	public void updatePlayerHp(ArrayList<PlayerHpBar> playerHpList, ArrayList<Player> playerList) {
		for (int a = 0; a < playerHpList.size(); a++) {
			playerHpList.get(a).setHealth(playerList.get(a).getHealth());
		}
	}

	public void drawHearts(PlayerHpBar plyrHp, int startingX, int startingY, Graphics g) {
		if (plyrHp.getHealth() > 0) {
			g.drawImage(plyrHp.getHeartImage(), startingX, startingY, this);
		}
		if (plyrHp.getHealth() > 1) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + DD.HEARTWIDTH), startingY, this);
		}
		if (plyrHp.getHealth() > 2) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 2 * DD.HEARTWIDTH), startingY, this);
		}
		if (plyrHp.getHealth() > 3) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 3 * DD.HEARTWIDTH), startingY, this);
		}
		if (plyrHp.getHealth() > 4) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 4 * DD.HEARTWIDTH), startingY, this);
		}
		if (plyrHp.getHealth() > 5) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 5 * DD.HEARTWIDTH), startingY, this);
		}
		
		
		if (plyrHp.getHealth() > 6) {
			g.drawImage(plyrHp.getHeartImage(), startingX, (int) (startingY + DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 7) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + DD.HEARTWIDTH), (int) (startingY + DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 8) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 2 * DD.HEARTWIDTH), (int) (startingY + DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 9) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 3 * DD.HEARTWIDTH), (int) (startingY + DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 10) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 4 * DD.HEARTWIDTH), (int) (startingY + DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 11) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 5 * DD.HEARTWIDTH), (int) (startingY + DD.HEARTHEIGHT), this);
		}
		
		
		
		if (plyrHp.getHealth() > 12) {
			g.drawImage(plyrHp.getHeartImage(), startingX, (int) (startingY + 2*DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 13) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + DD.HEARTWIDTH), (int) (startingY + 2*DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 14) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 2 * DD.HEARTWIDTH), (int) (startingY + 2*DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 15) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 3 * DD.HEARTWIDTH), (int) (startingY + 2*DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 16) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 4 * DD.HEARTWIDTH), (int) (startingY + 2*DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 17) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 5 * DD.HEARTWIDTH), (int) (startingY + 2*DD.HEARTHEIGHT), this);
		}
		
		
		
		if (plyrHp.getHealth() > 18) {
			g.drawImage(plyrHp.getHeartImage(), startingX, (int) (startingY + 3*DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 19) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + DD.HEARTWIDTH), (int) (startingY + 3*DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 20) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 2 * DD.HEARTWIDTH), (int) (startingY + 3*DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 21) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 3 * DD.HEARTWIDTH), (int) (startingY + 3*DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 22) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 4 * DD.HEARTWIDTH), (int) (startingY + 3*DD.HEARTHEIGHT), this);
		}
		if (plyrHp.getHealth() > 23) {
			g.drawImage(plyrHp.getHeartImage(), (int) (startingX + 5 * DD.HEARTWIDTH), (int) (startingY + 3*DD.HEARTHEIGHT), this);
		}
		
	}

	public void interactWithPlayers(Player plyr, ArrayList<Player> playerList) {
		for (int a = 0; a < playerList.size(); a++) {
			plyr.interact(playerList.get(a));
		}
	}

	public void checkIfDead(Player plyr, Animator playerAnimator) {
		if (plyr.getHealth() <= 0) {
			if (!plyr.isDead()) {
				plyr.setDead(true);
			}
		}
	}

	public void updateAttackAnimation(Player plyr) {
		if (plyr.isAttacking()) {
			if (System.currentTimeMillis() - plyr.getAttackStartTime() > DD.ATTACKDURATION) {
				plyr.setAttacking(false);
			}
			if (!plyr.isJumpAttacking()) {
				plyr.setMyXSpeed(0);
			}

		}
	}

	public void updateSpriteSettings(Player plyr, Animator playerAnimator) {

		// when player is dead facing right
		if (plyr.isDead() && plyr.getSpriteDirection() == 0 && playerAnimator.getAnimationState() != 5) {
			playerAnimator.setFrames(plyr.getDeadSprites(), 5);
		}

		// when player is dead facing left
		if (plyr.isDead() && plyr.getSpriteDirection() == 1 && playerAnimator.getAnimationState() != 15) {
			playerAnimator.setFrames(plyr.getDeadLeftSprites(), 15);
		}

		// when player is in air facing right
		else if (!plyr.isDead() && !plyr.isAttacking() && plyr.getSpriteDirection() == 0 && plyr.isInAir()
				&& !plyr.isDownBlock() && playerAnimator.getAnimationState() != 2) {
			playerAnimator.setFrames(plyr.getJumpSprites(), 2);
		} else if (!plyr.isDead() && plyr.isAttacking() && plyr.getSpriteDirection() == 0 && plyr.isInAir()
				&& !plyr.isDownBlock() && playerAnimator.getAnimationState() != 4) {
			playerAnimator.setFrames(plyr.getJumpAttackSprites(), 4);
			plyr.setJumpAttacking(true);
		}
		// when player is in air facing left
		else if (!plyr.isDead() && !plyr.isAttacking() && plyr.getSpriteDirection() == 1 && plyr.isInAir()
				&& !plyr.isDownBlock() && playerAnimator.getAnimationState() != 12) {
			playerAnimator.setFrames(plyr.getJumpLeftSprites(), 12);
		} else if (!plyr.isDead() && plyr.isAttacking() && plyr.getSpriteDirection() == 1 && plyr.isInAir()
				&& !plyr.isDownBlock() && playerAnimator.getAnimationState() != 14) {
			playerAnimator.setFrames(plyr.getJumpAttackLeftSprites(), 14);
			plyr.setJumpAttacking(true);
		}
		// when player is running right
		else if (!plyr.isDead() && !plyr.isAttacking() && plyr.getSpriteDirection() == 0
				&& plyr.getCurrentHorizontalDirection() != 0 && plyr.isDownBlock()
				&& playerAnimator.getAnimationState() != 1) {
			playerAnimator.setFrames(plyr.getRunSprites(), 1);
		} else if (!plyr.isDead() && plyr.isAttacking() && plyr.getSpriteDirection() == 0 && plyr.isDownBlock()
				&& playerAnimator.getAnimationState() != 3) {
			if (plyr.isJumpAttacking() && playerAnimator.getAnimationState() != 3) {
				playerAnimator.setFrames(plyr.getAttackSprites(), 3, playerAnimator.getCurrentFrame());
				plyr.setJumpAttacking(false);
			} else if (!plyr.isJumpAttacking() && playerAnimator.getAnimationState() != 3) {
				playerAnimator.setFrames(plyr.getAttackSprites(), 3);
			}
		}
		// when player is running left
		else if (!plyr.isDead() && !plyr.isAttacking() && plyr.getSpriteDirection() == 1
				&& plyr.getCurrentHorizontalDirection() != 0 && plyr.isDownBlock()
				&& playerAnimator.getAnimationState() != 11) {
			playerAnimator.setFrames(plyr.getRunLeftSprites(), 11);
		} else if (!plyr.isDead() && plyr.isAttacking() && plyr.getSpriteDirection() == 1 && plyr.isDownBlock()
				&& playerAnimator.getAnimationState() != 13) {
			if (plyr.isJumpAttacking() && playerAnimator.getAnimationState() != 13) {
				playerAnimator.setFrames(plyr.getAttackLeftSprites(), 13, playerAnimator.getCurrentFrame());
				plyr.setJumpAttacking(false);
			} else if (!plyr.isJumpAttacking() && playerAnimator.getAnimationState() != 13) {
				playerAnimator.setFrames(plyr.getAttackLeftSprites(), 13);
			}

		}
		// when player is idle facing right
		else if (!plyr.isDead() && !plyr.isAttacking() && plyr.getSpriteDirection() == 0
				&& plyr.getCurrentHorizontalDirection() == 0 && plyr.isDownBlock()
				&& playerAnimator.getAnimationState() != 0) {
			playerAnimator.setFrames(plyr.getIdleSprites(), 0);
		} else if (!plyr.isDead() && plyr.isAttacking() && plyr.getSpriteDirection() == 0
				&& plyr.getCurrentHorizontalDirection() == 0 && plyr.isDownBlock()
				&& playerAnimator.getAnimationState() != 3) {
			if (plyr.isJumpAttacking()) {
				playerAnimator.setFrames(plyr.getAttackSprites(), 3, playerAnimator.getCurrentFrame());
				plyr.setJumpAttacking(false);
			} else if (!plyr.isJumpAttacking()) {
				playerAnimator.setFrames(plyr.getAttackSprites(), 3);
			}

		}
		// when player is idle facing left
		else if (!plyr.isDead() && !plyr.isAttacking() && plyr.getSpriteDirection() == 1
				&& plyr.getCurrentHorizontalDirection() == 0 && plyr.isDownBlock()
				&& playerAnimator.getAnimationState() != 10) {
			playerAnimator.setFrames(plyr.getIdleLeftSprites(), 10);
		} else if (!plyr.isDead() && plyr.isAttacking() && plyr.getSpriteDirection() == 1
				&& plyr.getCurrentHorizontalDirection() == 0 && plyr.isDownBlock()
				&& playerAnimator.getAnimationState() != 13) {
			if (plyr.isJumpAttacking()) {
				playerAnimator.setFrames(plyr.getAttackLeftSprites(), 13, playerAnimator.getCurrentFrame());
				plyr.setJumpAttacking(false);
			} else if (!plyr.isJumpAttacking()) {
				playerAnimator.setFrames(plyr.getAttackLeftSprites(), 13);
			}
		}
	}

	public void updateUncrouch(Player plyr, ArrayList<Platform> pList) {
		plyr.setMyY(plyr.getMyY() - DD.PLYRHEIGHT / 2);
		plyr.setMyHeight(DD.PLYRHEIGHT);
		for (int a = 0; a < pList.size(); a++) {
			plyr.interact(pList.get(a));

			if (plyr.inUncrouchBoundaries(pList.get(a))) {
				plyr.setIntentToUncrouch(true);
				plyr.setHeldDownKey(true);
				plyr.setMyY(plyr.getMyY() + DD.PLYRHEIGHT / 2);
				plyr.setMyHeight(DD.PLYRHEIGHT / 2);
				return;
			}
		}
		plyr.setIntentToUncrouch(false);
		plyr.setHeldDownKey(false);
	}

	public void updatePlayerBlocks(Player plyr, ArrayList<Platform> pList) {
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;
		if (plyr.isIntentToUncrouch()) {
			updateUncrouch(plyr, pList);
		}
		for (int a = 0; a < pList.size(); a++) {
			plyr.interact(pList.get(a));

			// while the contact of the platform is up, in the perspective of the player,
			// the platform is down
			// therefore, the platform's up contact is the player's down block, and the
			// platform's left contact is the player's right block.
			if (pList.get(a).isUpContact()) {
				down = true;
			}
			if (pList.get(a).isDownContact()) {
				up = true;
			}
			if (pList.get(a).isLeftContact()) {
				right = true;
			}
			if (pList.get(a).isRightContact()) {
				left = true;
			}
		}
		plyr.setUpBlock(up);
		plyr.setDownBlock(down);
		plyr.setLeftBlock(left);
		plyr.setRightBlock(right);
	}

	public void playerMovementKeyPressed(KeyEvent e, Player plyr, ArrayList<ThrowingStar> plyrStars, int codeUp,
			int codeDown, int codeLeft, int codeRight, int codeSpecial, int codeSpecial2, int codePickUp,
			Animator playerAnimator) {
		int key = e.getKeyCode();
		// codeUp
		if (key == codeUp) {
			if (!plyr.isDead()) {
				if (!plyr.isAttacking()) {
					if (!plyr.isUpBlock()) {
						if (plyr.getAvailableJumps() == 1) {
							playerAnimator.jumpReset();
						}
						plyr.jump();

					}
				}
			}
		}

		if (key == codeDown) {
			if (!plyr.isDead()) {
				if (!plyr.isAttacking()) {
					plyr.setAttacking(true);
					plyr.setAttackHit(false);
					plyr.setAttackStartTime(System.currentTimeMillis());
				}
			}
		}

		// codeDown
		/*
		 * if (key == codeDown) { if (!plyr.isHeldDownKey()) { plyr.setMyY(plyr.getMyY()
		 * + DD.PLYRHEIGHT / 2); plyr.setMyHeight(DD.PLYRHEIGHT / 2);
		 * plyr.setHeldDownKey(true); } }
		 */

		// codeLeft
		if (key == codeLeft) {
			if (!plyr.isDead()) {
				if (!plyr.isAttacking()) {
					plyr.setIntentLeftMovement(true);
					plyr.setIntentRightMovement(false);
					plyr.setSpriteDirection(1);
					if (!plyr.isLeftBlock()) {
						plyr.setMyXSpeed(-DD.PLYRXSPD);
						plyr.setCurrentHorizontalDirection(1);
						if (plyr.getMyX() < DD.XBORDER)
							plyr.setMyXSpeed(0);
					}
				}
			}

			// code right
		} else if (key == codeRight) {
			if (!plyr.isDead()) {
				if (!plyr.isAttacking()) {
					plyr.setIntentRightMovement(true);
					plyr.setIntentLeftMovement(false);
					plyr.setSpriteDirection(0);
					if (!plyr.isRightBlock()) {
						plyr.setMyXSpeed(DD.PLYRXSPD);
						plyr.setCurrentHorizontalDirection(2);
						if (plyr.getMyX() > DD.SCREENWIDTH - (DD.XBORDER + DD.PLYRWIDTH))
							plyr.setMyXSpeed(0);
					}
				}
			}
		} else if (key == codeSpecial) {
			if (!plyr.isDead()) {
				if (plyr instanceof Ninja) {
					plyr = (Ninja) plyr;
					plyrStars.add(new ThrowingStar(plyr));
					
				}
				else if(plyr instanceof Knight && !((Knight) plyr).isChargeOnCD()) {
					Knight knight = (Knight) plyr;
					knight.setCharging(true);
					knight.setChargeStartTime(System.currentTimeMillis());
					knight.setChargeOnCD(true);		
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			paused = !paused;
		}

		if (paused) {
			return;
		}
		playerMovementKeyPressed(e, plyr, plyr1Stars, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT,
				KeyEvent.VK_RIGHT, KeyEvent.VK_DELETE, KeyEvent.VK_END, KeyEvent.VK_PAGE_DOWN, player1Animator);
		if (numberOfPlayers >= 2)
			playerMovementKeyPressed(e, plyr2, plyr2Stars, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D,
					KeyEvent.VK_Z, KeyEvent.VK_X, KeyEvent.VK_C, player2Animator);
		if (numberOfPlayers >= 3)
			playerMovementKeyPressed(e, plyr3, plyr3Stars, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L,
					KeyEvent.VK_M, KeyEvent.VK_COMMA, KeyEvent.VK_PERIOD, player3Animator);
		if (numberOfPlayers >= 4)
			playerMovementKeyPressed(e, plyr4, plyr4Stars, 104, 101, 100, 102, 97, 98, 99, player4Animator);
	}

	public void playerMovementKeyReleased(KeyEvent e, Player plyr, int codeUp, int codeDown, int codeLeft,
			int codeRight) {
		// if the player is moving in the other direction, releasing the key of the
		// opposite direction will not make you stop moving
		// Only if you are moving the same way as the released key then the movement
		// will stop.
		int key = e.getKeyCode();

		if (key == codeLeft)
			plyr.setIntentLeftMovement(false);

		if (key == codeLeft && plyr.getCurrentHorizontalDirection() == 2) {
			return;
		} else if (key == codeLeft && plyr.getCurrentHorizontalDirection() == 1) {
			plyr.setCurrentHorizontalDirection(0);
			plyr.setMyXSpeed(0);

		}

		if (key == codeRight)
			plyr.setIntentRightMovement(false);

		if (key == codeRight && plyr.getCurrentHorizontalDirection() == 1) {
			return;
		} else if (key == codeRight && plyr.getCurrentHorizontalDirection() == 2) {
			plyr.setCurrentHorizontalDirection(0);
			plyr.setMyXSpeed(0);
		}

		/*
		 * if (key == codeDown) { if (plyr.isHeldDownKey()) {
		 * plyr.setIntentToUncrouch(true); } }
		 */
	}

	@Override
	public void keyReleased(KeyEvent e) {
		playerMovementKeyReleased(e, plyr, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
		if (numberOfPlayers >= 2)
			playerMovementKeyReleased(e, plyr2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
		if (numberOfPlayers >= 3)
			playerMovementKeyReleased(e, plyr3, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L);
		if (numberOfPlayers >= 4)
			playerMovementKeyReleased(e, plyr4, 104, 101, 100, 102);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public boolean isBackToMenu() {
		return backToMenu;
	}

	public void setBackToMenu(boolean backToMenu) {
		this.backToMenu = backToMenu;
	}
}
