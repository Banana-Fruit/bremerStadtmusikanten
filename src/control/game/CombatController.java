package control.game;
import model.Attack;
import model.Unit;
import resources.constants.Constants_Sorting;
import java.util.*;

public class CombatController
{
	public void Combat(List<Unit> participants)
	{
		Collections.sort(participants, new Comparator<Unit>()
		{
			@Override
			public int compare(Unit o1, Unit o2)
			{
				if (Integer.compare(o2.getInit(),o1.getInit()) > 0)
				{
					return(Constants_Sorting.POSITIVE);
				}
				else if (Integer.compare(o2.getInit(),o1.getInit()) == 0)
				{
					return(Constants_Sorting.ZERO);
				}
				else
				{
					return(Constants_Sorting.NEGATIVE) ;
				}
				
			}
		});
		
		for(int n = 0; n<= participants.size(); n++)
		{//WIP
			//AttackUnit(participants.get(n), choiceOfFoe(participants,n),Attackused);
		}
		
		
	}
	
	private static Unit choiceOfFoe(List<Unit> ListofFoes,int n)
	{
		int m = n;//IndexofClickedFoe;
		Unit choice = ListofFoes.get(m);
		return choice;
	}
	
	
	public void AttackUnit(Unit attacker, Unit defender,Attack attackused)
	{
		
		boolean isMagic = attackused.getMagic();
		boolean isRanged = attackused.getRanged();
		int atkRange = attackused.getAtkRange();
		
		
		int shield = defender.getShield();
		int magicResist = defender.getMagicresist();
		int dodge = defender.getDodge();
		
		int meleeDamage = attacker.getMeele();
		int rangedDamage = attacker.getRanged();
		int magicDamage = attacker.getMagicDmg();
		int currentMana = attacker.getMana();
		int currentAmmo = attacker.getAmmo();
		
		
		float damageBlocked;
		int rawDamage;
		boolean isCloseEnough;
		int newMana;
		int newAmmo;
		
		
		if(isMagic)
		{
			if (currentMana > 0)
			{
				damageBlocked = magicDamage * magicResist / 100;
				rawDamage = magicDamage;
				newMana = currentMana - 1;
				attacker.setMana(newMana);
				
				if (RollDice(dodge) == true)
				{
					DoDamage(damageBlocked, defender,rawDamage);
					System.out.print(defender.getHp() + " sind die neuen Hp von: " + defender.getName());
				}
				else
				{
					System.out.println("DODGED");
				}
				
			}
		}
		else if(isRanged)
		{
			
			if (currentAmmo > 0)
			{
				damageBlocked = rangedDamage * shield/100;
				rawDamage = rangedDamage;
				newAmmo = currentAmmo -1;
				attacker.setAmmo(newAmmo);
				if (RollDice(dodge) == true)
				{
					DoDamage(damageBlocked, defender,rawDamage);
					System.out.print(defender.getHp() + " sind die neuen Hp von: " + defender.getName());
				}
				else
				{
					System.out.println("DODGED");
				}
			}
			
		}
		else
		{
			
			damageBlocked = meleeDamage * shield/100;
			rawDamage = meleeDamage;
			if (RollDice(dodge) == true)
			{
				DoDamage(damageBlocked, defender,rawDamage);
				System.out.print(defender.getHp() + " sind die neuen Hp von: " + defender.getName());
			}
			else
			{
				System.out.println("DODGED");
			}
		}
	}
	
	
	
	public void DoDamage(float DmgBlocked, Unit Defender,int rawDamage)
	{
		int curHP = Defender.getHp();
		int roundDamageDealt;
		int newHP;
		
		roundDamageDealt = Math.round(rawDamage - DmgBlocked);
		newHP = curHP - roundDamageDealt;
		Defender.setHp(newHP);
		
	}
	
	public boolean RollDice(int chance)
	{
		Random random = new Random();
		boolean didDodge;
		int roll = random.nextInt();
		if (roll <= chance)
		{
			didDodge = true;
			return didDodge;
		} else
		{
			didDodge = false;
			return didDodge;
			
			
			
		}
	}
	
	
	
}