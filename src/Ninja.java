
public class Ninja extends Player {

	public Ninja(double x, double y, double width, double height, double xSpd, double ySpd) {
		super(x, y, width, height, xSpd, ySpd);
		health = 6;
		
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
