package model;

public class Unit
{
	private String name;
	private int hp,shield,mana,meele,ranged,ammo,dodge,magicresist,mvmspeed,init, magicDmg,myAttack,positionX,positionY;




	public Unit(String name, int hp, int shield, int mana, int meele, int ranged, int ammo, int dodge, int magicresist,
				int mvmspeed, int init, int magicDmg, int myAttack, int positionX, int positionY)
	{
		this.name = name;
		this.hp = hp;
		this.shield = shield;
		this.mana = mana;
		this.meele = meele;
		this.ranged = ranged;
		this.ammo = ammo;
		this.dodge = dodge;
		this.magicresist = magicresist;
		this.mvmspeed = mvmspeed;
		this.init = init;
		this.magicDmg = magicDmg;
		this.myAttack = myAttack;
		this.positionX = positionX;
		this.positionY = positionY;
		
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public int getMyAttack() {
		return myAttack;
	}

	public void setMyAttack(int myAttack) {
		this.myAttack = myAttack;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getMagicDmg()
	{
		return magicDmg;
	}
	
	public void setMagicDmg(int magicDmg)
	{
		this.magicDmg = magicDmg;
	}
	
	public void setHp(int hp)
	{
		this.hp = hp;
	}
	
	public void setShield(int shield)
	{
		this.shield = shield;
	}
	
	public void setMana(int mana)
	{
		this.mana = mana;
	}
	
	public void setMeele(int meele)
	{
		this.meele = meele;
	}
	
	public void setRanged(int ranged)
	{
		this.ranged = ranged;
	}
	
	public void setAmmo(int ammo)
	{
		this.ammo = ammo;
	}
	
	public void setDodge(int dodge)
	{
		this.dodge = dodge;
	}
	
	public void setMagicresist(int magicresist)
	{
		this.magicresist = magicresist;
	}
	
	public void setMvmspeed(int mvmspeed)
	{
		this.mvmspeed = mvmspeed;
	}
	
	public void setInit(int init)
	{
		this.init = init;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getHp()
	{
		return hp;
	}
	
	public int getShield()
	{
		return shield;
	}
	
	public int getMana()
	{
		return mana;
	}
	
	public int getMeele()
	{
		return meele;
	}
	
	public int getRanged()
	{
		return ranged;
	}
	
	public int getAmmo()
	{
		return ammo;
	}
	
	public int getDodge()
	{
		return dodge;
	}
	
	public int getMagicresist()
	{
		return magicresist;
	}
	
	public int getMvmspeed()
	{
		return mvmspeed;
	}
	
	public int getInit()
	{
		return init;
	}
}
