import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player extends Item {
	public static final int KNIGHT_TYPE = 0;
	public static final int NINJA_TYPE = 1;
	
	private int availableJumps;
	private int currentHorizontalDirection; // 0 = not moving, 1 = left, 2 = right
	private int currentVerticalDirection;// 0 = not moving, 1 = up, 2 = down
	private boolean intentRightMovement;
	private boolean intentLeftMovement;
	private boolean intentToUncrouch;
	private boolean heldDownKey;
	private boolean inAir;
	private boolean upBlock; // records how many items are blocking the player in the respective direction
	private boolean downBlock;
	private boolean leftBlock;
	private boolean rightBlock;
	private boolean verticalContact;
	private boolean horizontalContact;
	private ArrayList<Image> idleSprites;
	private ArrayList<Image> runSprites;
	private ArrayList<Image> jumpSprites;
	private ArrayList<Image> idleLeftSprites;
	private ArrayList<Image> runLeftSprites;
	private ArrayList<Image> jumpLeftSprites;
	private ArrayList<Image> attackSprites;
	private ArrayList<Image> attackLeftSprites;
	private ArrayList<Image> jumpAttackSprites;
	private ArrayList<Image> jumpAttackLeftSprites;
	private ArrayList<Image> deadSprites;
	private ArrayList<Image> deadLeftSprites;
	private int spriteDirection; // 0 = right, 1 = left
	private boolean attacking;
	private boolean attackHit;
	private boolean jumpAttacking;
	private long attackStartTime;
	
	boolean charging;

	private int characterType; // 0 = knight, 1 = ninja..

	private double deadX, deadY, deadWidth, deadHeight;
	private long deadUpdateTime;

	private int health;
	private boolean dead;

	private ImageIcon playerImageIcon;
	private Image playerImage;

	public Player(double x, double y, double width, double height, double xSpd, double ySpd, int charType) {
		super(x, y, width, height, xSpd, ySpd);

		characterType = charType;
		
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

		ClassLoader cldr = this.getClass().getClassLoader();

		if (characterType == 0) {
			health = 24;
			
			// https://www.gameart2d.com/the-knight-free-sprites.html
			addImages("Idle (1).png", idleSprites, cldr);
			addImages("Idle (2).png", idleSprites, cldr);
			addImages("Idle (3).png", idleSprites, cldr);
			addImages("Idle (4).png", idleSprites, cldr);
			addImages("Idle (5).png", idleSprites, cldr);
			addImages("Idle (6).png", idleSprites, cldr);
			addImages("Idle (7).png", idleSprites, cldr);
			addImages("Idle (8).png", idleSprites, cldr);
			addImages("Idle (9).png", idleSprites, cldr);
			addImages("Idle (10).png", idleSprites, cldr);

			addImages("IdleLeft (1).png", idleLeftSprites, cldr);
			addImages("IdleLeft (2).png", idleLeftSprites, cldr);
			addImages("IdleLeft (3).png", idleLeftSprites, cldr);
			addImages("IdleLeft (4).png", idleLeftSprites, cldr);
			addImages("IdleLeft (5).png", idleLeftSprites, cldr);
			addImages("IdleLeft (6).png", idleLeftSprites, cldr);
			addImages("IdleLeft (7).png", idleLeftSprites, cldr);
			addImages("IdleLeft (8).png", idleLeftSprites, cldr);
			addImages("IdleLeft (9).png", idleLeftSprites, cldr);
			addImages("IdleLeft (10).png", idleLeftSprites, cldr);

			addImages("Run (1).png", runSprites, cldr);
			addImages("Run (2).png", runSprites, cldr);
			addImages("Run (3).png", runSprites, cldr);
			addImages("Run (4).png", runSprites, cldr);
			addImages("Run (5).png", runSprites, cldr);
			addImages("Run (6).png", runSprites, cldr);
			addImages("Run (7).png", runSprites, cldr);
			addImages("Run (8).png", runSprites, cldr);
			addImages("Run (9).png", runSprites, cldr);
			addImages("Run (10).png", runSprites, cldr);

			addImages("RunLeft (1).png", runLeftSprites, cldr);
			addImages("RunLeft (2).png", runLeftSprites, cldr);
			addImages("RunLeft (3).png", runLeftSprites, cldr);
			addImages("RunLeft (4).png", runLeftSprites, cldr);
			addImages("RunLeft (5).png", runLeftSprites, cldr);
			addImages("RunLeft (6).png", runLeftSprites, cldr);
			addImages("RunLeft (7).png", runLeftSprites, cldr);
			addImages("RunLeft (8).png", runLeftSprites, cldr);
			addImages("RunLeft (9).png", runLeftSprites, cldr);
			addImages("RunLeft (10).png", runLeftSprites, cldr);

			addImages("Jump (1).png", jumpSprites, cldr);
			addImages("Jump (2).png", jumpSprites, cldr);
			addImages("Jump (3).png", jumpSprites, cldr);
			addImages("Jump (4).png", jumpSprites, cldr);
			addImages("Jump (5).png", jumpSprites, cldr);
			addImages("Jump (6).png", jumpSprites, cldr);
			addImages("Jump (7).png", jumpSprites, cldr);
			addImages("Jump (8).png", jumpSprites, cldr);
			addImages("Jump (9).png", jumpSprites, cldr);
			addImages("Jump (10).png", jumpSprites, cldr);

			addImages("JumpLeft (1).png", jumpLeftSprites, cldr);
			addImages("JumpLeft (2).png", jumpLeftSprites, cldr);
			addImages("JumpLeft (3).png", jumpLeftSprites, cldr);
			addImages("JumpLeft (4).png", jumpLeftSprites, cldr);
			addImages("JumpLeft (5).png", jumpLeftSprites, cldr);
			addImages("JumpLeft (6).png", jumpLeftSprites, cldr);
			addImages("JumpLeft (7).png", jumpLeftSprites, cldr);
			addImages("JumpLeft (8).png", jumpLeftSprites, cldr);
			addImages("JumpLeft (9).png", jumpLeftSprites, cldr);
			addImages("JumpLeft (10).png", jumpLeftSprites, cldr);

			addImages("Attack (1).png", attackSprites, cldr);
			addImages("Attack (2).png", attackSprites, cldr);
			addImages("Attack (3).png", attackSprites, cldr);
			addImages("Attack (4).png", attackSprites, cldr);
			addImages("Attack (5).png", attackSprites, cldr);
			addImages("Attack (6).png", attackSprites, cldr);
			addImages("Attack (7).png", attackSprites, cldr);
			addImages("Attack (8).png", attackSprites, cldr);
			addImages("Attack (9).png", attackSprites, cldr);
			addImages("Attack (10).png", attackSprites, cldr);

			addImages("AttackLeft (1).png", attackLeftSprites, cldr);
			addImages("AttackLeft (2).png", attackLeftSprites, cldr);
			addImages("AttackLeft (3).png", attackLeftSprites, cldr);
			addImages("AttackLeft (4).png", attackLeftSprites, cldr);
			addImages("AttackLeft (5).png", attackLeftSprites, cldr);
			addImages("AttackLeft (6).png", attackLeftSprites, cldr);
			addImages("AttackLeft (7).png", attackLeftSprites, cldr);
			addImages("AttackLeft (8).png", attackLeftSprites, cldr);
			addImages("AttackLeft (9).png", attackLeftSprites, cldr);
			addImages("AttackLeft (10).png", attackLeftSprites, cldr);

			addImages("JumpAttack (1).png", jumpAttackSprites, cldr);
			addImages("JumpAttack (2).png", jumpAttackSprites, cldr);
			addImages("JumpAttack (3).png", jumpAttackSprites, cldr);
			addImages("JumpAttack (4).png", jumpAttackSprites, cldr);
			addImages("JumpAttack (5).png", jumpAttackSprites, cldr);
			addImages("JumpAttack (6).png", jumpAttackSprites, cldr);
			addImages("JumpAttack (7).png", jumpAttackSprites, cldr);
			addImages("JumpAttack (8).png", jumpAttackSprites, cldr);
			addImages("JumpAttack (9).png", jumpAttackSprites, cldr);
			addImages("JumpAttack (10).png", jumpAttackSprites, cldr);

			addImages("jumpAttackLeft (1).png", jumpAttackLeftSprites, cldr);
			addImages("jumpAttackLeft (2).png", jumpAttackLeftSprites, cldr);
			addImages("jumpAttackLeft (3).png", jumpAttackLeftSprites, cldr);
			addImages("jumpAttackLeft (4).png", jumpAttackLeftSprites, cldr);
			addImages("jumpAttackLeft (5).png", jumpAttackLeftSprites, cldr);
			addImages("jumpAttackLeft (6).png", jumpAttackLeftSprites, cldr);
			addImages("jumpAttackLeft (7).png", jumpAttackLeftSprites, cldr);
			addImages("jumpAttackLeft (8).png", jumpAttackLeftSprites, cldr);
			addImages("jumpAttackLeft (9).png", jumpAttackLeftSprites, cldr);
			addImages("jumpAttackLeft (10).png", jumpAttackLeftSprites, cldr);

			addImages("Dead (1).png", deadSprites, cldr);
			addImages("Dead (2).png", deadSprites, cldr);
			addImages("Dead (3).png", deadSprites, cldr);
			addImages("Dead (4).png", deadSprites, cldr);
			addImages("Dead (5).png", deadSprites, cldr);
			addImages("Dead (6).png", deadSprites, cldr);
			addImages("Dead (7).png", deadSprites, cldr);
			addImages("Dead (8).png", deadSprites, cldr);
			addImages("Dead (9).png", deadSprites, cldr);
			addImages("Dead (10).png", deadSprites, cldr);

			addImages("DeadLeft (1).png", deadLeftSprites, cldr);
			addImages("DeadLeft (2).png", deadLeftSprites, cldr);
			addImages("DeadLeft (3).png", deadLeftSprites, cldr);
			addImages("DeadLeft (4).png", deadLeftSprites, cldr);
			addImages("DeadLeft (5).png", deadLeftSprites, cldr);
			addImages("DeadLeft (6).png", deadLeftSprites, cldr);
			addImages("DeadLeft (7).png", deadLeftSprites, cldr);
			addImages("DeadLeft (8).png", deadLeftSprites, cldr);
			addImages("DeadLeft (9).png", deadLeftSprites, cldr);
			addImages("DeadLeft (10).png", deadLeftSprites, cldr);
		}

		else if (characterType == 1) {
			
			health = 3;
			
			addImagesCustomDimensions("NinjaIdle (1).png", idleSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdle (2).png", idleSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdle (3).png", idleSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdle (4).png", idleSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdle (5).png", idleSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdle (6).png", idleSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdle (7).png", idleSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdle (8).png", idleSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdle (9).png", idleSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdle (10).png", idleSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);

			addImagesCustomDimensions("NinjaIdleLeft (1).png", idleLeftSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdleLeft (2).png", idleLeftSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdleLeft (3).png", idleLeftSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdleLeft (4).png", idleLeftSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdleLeft (5).png", idleLeftSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdleLeft (6).png", idleLeftSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdleLeft (7).png", idleLeftSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdleLeft (8).png", idleLeftSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdleLeft (9).png", idleLeftSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);
			addImagesCustomDimensions("NinjaIdleLeft (10).png", idleLeftSprites, cldr, (int) (DD.NINJAIDLESPRITEWIDTH ), (int) DD.NINJASPRITEHEIGHT);

			addImages("NinjaRun (1).png", runSprites, cldr);
			addImages("NinjaRun (2).png", runSprites, cldr);
			addImages("NinjaRun (3).png", runSprites, cldr);
			addImages("NinjaRun (4).png", runSprites, cldr);
			addImages("NinjaRun (5).png", runSprites, cldr);
			addImages("NinjaRun (6).png", runSprites, cldr);
			addImages("NinjaRun (7).png", runSprites, cldr);
			addImages("NinjaRun (8).png", runSprites, cldr);
			addImages("NinjaRun (9).png", runSprites, cldr);
			addImages("NinjaRun (10).png", runSprites, cldr);

			addImages("NinjaRunLeft (1).png", runLeftSprites, cldr);
			addImages("NinjaRunLeft (2).png", runLeftSprites, cldr);
			addImages("NinjaRunLeft (3).png", runLeftSprites, cldr);
			addImages("NinjaRunLeft (4).png", runLeftSprites, cldr);
			addImages("NinjaRunLeft (5).png", runLeftSprites, cldr);
			addImages("NinjaRunLeft (6).png", runLeftSprites, cldr);
			addImages("NinjaRunLeft (7).png", runLeftSprites, cldr);
			addImages("NinjaRunLeft (8).png", runLeftSprites, cldr);
			addImages("NinjaRunLeft (9).png", runLeftSprites, cldr);
			addImages("NinjaRunLeft (10).png", runLeftSprites, cldr);

			addImages("NinjaJump (1).png", jumpSprites, cldr);
			addImages("NinjaJump (2).png", jumpSprites, cldr);
			addImages("NinjaJump (3).png", jumpSprites, cldr);
			addImages("NinjaJump (4).png", jumpSprites, cldr);
			addImages("NinjaJump (5).png", jumpSprites, cldr);
			addImages("NinjaJump (6).png", jumpSprites, cldr);
			addImages("NinjaJump (7).png", jumpSprites, cldr);
			addImages("NinjaJump (8).png", jumpSprites, cldr);
			addImages("NinjaJump (9).png", jumpSprites, cldr);
			addImages("NinjaJump (10).png", jumpSprites, cldr);
			
			addImages("NinjaJumpLeft (1).png", jumpLeftSprites, cldr);
			addImages("NinjaJumpLeft (2).png", jumpLeftSprites, cldr);
			addImages("NinjaJumpLeft (3).png", jumpLeftSprites, cldr);
			addImages("NinjaJumpLeft (4).png", jumpLeftSprites, cldr);
			addImages("NinjaJumpLeft (5).png", jumpLeftSprites, cldr);
			addImages("NinjaJumpLeft (6).png", jumpLeftSprites, cldr);
			addImages("NinjaJumpLeft (7).png", jumpLeftSprites, cldr);
			addImages("NinjaJumpLeft (8).png", jumpLeftSprites, cldr);
			addImages("NinjaJumpLeft (9).png", jumpLeftSprites, cldr);
			addImages("NinjaJumpLeft (10).png", jumpLeftSprites, cldr);

			addImagesCustomDimensions("NinjaAttack (1).png", attackSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaAttack (2).png", attackSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttack (3).png", attackSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttack (4).png", attackSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttack (5).png", attackSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttack (6).png", attackSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttack (7).png", attackSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttack (8).png", attackSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttack (9).png", attackSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttack (10).png", attackSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);

			addImagesCustomDimensions("NinjaAttackLeft (1).png", attackLeftSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaAttackLeft (2).png", attackLeftSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttackLeft (3).png", attackLeftSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttackLeft (4).png", attackLeftSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttackLeft (5).png", attackLeftSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttackLeft (6).png", attackLeftSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttackLeft (7).png", attackLeftSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttackLeft (8).png", attackLeftSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttackLeft (9).png", attackLeftSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);
			addImagesCustomDimensions("NinjaAttackLeft (10).png", attackLeftSprites, cldr, (int) (DD.NINJAATTACKSPRITEWIDTH), (int) DD.NINJAATTACKSPRITEHEIGHT);


			addImagesCustomDimensions("NinjaJumpAttack (1).png", jumpAttackSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttack (2).png", jumpAttackSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttack (3).png", jumpAttackSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttack (4).png", jumpAttackSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttack (5).png", jumpAttackSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttack (6).png", jumpAttackSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttack (7).png", jumpAttackSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttack (8).png", jumpAttackSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttack (9).png", jumpAttackSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttack (10).png", jumpAttackSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));

			addImagesCustomDimensions("NinjaJumpAttackLeft (1).png", jumpAttackLeftSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttackLeft (2).png", jumpAttackLeftSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttackLeft (3).png", jumpAttackLeftSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttackLeft (4).png", jumpAttackLeftSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttackLeft (5).png", jumpAttackLeftSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttackLeft (6).png", jumpAttackLeftSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttackLeft (7).png", jumpAttackLeftSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttackLeft (8).png", jumpAttackLeftSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttackLeft (9).png", jumpAttackLeftSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));
			addImagesCustomDimensions("NinjaJumpAttackLeft (10).png", jumpAttackLeftSprites, cldr, (int) (DD.NINJAJUMPATTACKSPRITEWIDTH), (int) (DD.NINJAATTACKSPRITEHEIGHT));


			addImages("NinjaDead (1).png", deadSprites, cldr);
			addImages("NinjaDead (2).png", deadSprites, cldr);
			addImages("NinjaDead (3).png", deadSprites, cldr);
			addImages("NinjaDead (4).png", deadSprites, cldr);
			addImages("NinjaDead (5).png", deadSprites, cldr);
			addImages("NinjaDead (6).png", deadSprites, cldr);
			addImages("NinjaDead (7).png", deadSprites, cldr);
			addImages("NinjaDead (8).png", deadSprites, cldr);
			addImages("NinjaDead (9).png", deadSprites, cldr);
			addImages("NinjaDead (10).png", deadSprites, cldr);

			addImages("NinjaDeadLeft (1).png", deadLeftSprites, cldr);
			addImages("NinjaDeadLeft (2).png", deadLeftSprites, cldr);
			addImages("NinjaDeadLeft (3).png", deadLeftSprites, cldr);
			addImages("NinjaDeadLeft (4).png", deadLeftSprites, cldr);
			addImages("NinjaDeadLeft (5).png", deadLeftSprites, cldr);
			addImages("NinjaDeadLeft (6).png", deadLeftSprites, cldr);
			addImages("NinjaDeadLeft (7).png", deadLeftSprites, cldr);
			addImages("NinjaDeadLeft (8).png", deadLeftSprites, cldr);
			addImages("NinjaDeadLeft (9).png", deadLeftSprites, cldr);
			addImages("NinjaDeadLeft (10).png", deadLeftSprites, cldr);
		}
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

	public int getCharacterType() {
		return characterType;
	}

	public void setCharacterType(int characterType) {
		this.characterType = characterType;
	}

}
