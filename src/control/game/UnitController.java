package control.game;

import control.scenes.MainMenuController;
import model.Attack;
import model.Unit;
import resources.constants.*;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UnitController {
	private static volatile UnitController instance = null;

	private UnitController()
	{
	}
	public static synchronized void initialize ()
	{
		if (instance == null)
		{
			instance = new UnitController();
		} else
		{
			throw new IllegalStateException(Constants_ExceptionMessages.ALREADY_INITIALIZED);
		}
	}


	public List<Unit> UnitCreator() {//WIP
		List<Unit> units = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(Constants_Player_Units.FILE_READ_UNITS))) {

			String line;
			while ((line = br.readLine())!=null) {
				String[] values = line.split(Constants_DefaultValues.SPLIT);
				String Name = values[Constants_IndexPropertyUnit.INDEX_NAME];
				int health = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_HEALTH]);
				int shield = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_SHIELD]);
				int mana = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_MANA]);
				int meeleDamage = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_MEELE_DAMAGE]);
				int rangedDamage = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_RANGE_DAMAGE]);
				int ammo = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_AMMO]);
				int dodge = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_DODGE]);
				int magicResistance = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_MAGIC_RESISTENCE]);
				int movementPoints = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_MOVEMENT_POINTS]);
				int initiative = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_INITIATIVE]);
				int magicDamage = Integer.valueOf(values[Constants_IndexPropertyUnit.MAGIC_DAMAGE]);
				int myAttack = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_MY_ATTACK]);
				int positionX = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_POSITION_X]);
				int positionY = Integer.valueOf(values[Constants_IndexPropertyUnit.INDEX_POSITION_Y]);


				units.add(new Unit(Name, health, shield, mana, meeleDamage, rangedDamage, ammo, dodge,
						magicResistance, movementPoints, initiative, magicDamage, myAttack, positionX, positionY));

			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return units;
	}

	public List<Attack> AttackCreator()
	{//WIP
		List<Attack> attacks = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(Constants_Combat.ATTACK_LIST)))
		{

			String line;
			while ((line = br.readLine())!=null)
			{
				String[] values = line.split(Constants_DefaultValues.SPLIT);
				int attackRange = Integer.valueOf(values[Constants_IndexPropertyEnemy.INDEX_ATTACK_RANGE]);
				boolean isMagic = Boolean.valueOf(values[Constants_IndexPropertyEnemy.INDEX_IS_MAGIC]);
				boolean isRanged = Boolean.valueOf(values[Constants_IndexPropertyEnemy.INDEX_IS_RANGED]);

				attacks.add(new Attack(attackRange,isMagic,isRanged));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return attacks;
	}


	public static UnitController getInstance()
	{
		if (instance == null)
		{
			throw new IllegalStateException(Constants_ExceptionMessages.SINGLETON_NOT_INITIALIZED);
		}
		return instance;
	}

}