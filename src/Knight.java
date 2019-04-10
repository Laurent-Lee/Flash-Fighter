
public class Knight extends Player {

	public static final int CHARGE_DURATION = 2000;
	public static final int CHARGE_CD = 5000;
	
	private long chargeStartTime;
	private boolean charging;
	private boolean chargeOnCD;
	
	
	public Knight(double x, double y, double width, double height, double xSpd, double ySpd) {
		super(x, y, width, height, xSpd, ySpd);
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

	public boolean isCharging() {
		return charging;
	}

	public void setCharging(boolean charging) {
		this.charging = charging;
	}

	public long getChargeStartTime() {
		return chargeStartTime;
	}

	public void setChargeStartTime(long chargeStartTime) {
		this.chargeStartTime = chargeStartTime;
	}

	public boolean isChargeOnCD() {
		return chargeOnCD;
	}

	public void setChargeOnCD(boolean chargeOnCD) {
		this.chargeOnCD = chargeOnCD;
	}

	
}
