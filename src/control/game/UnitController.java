package control.game;

import model.Attack;
import model.Unit;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UnitController {
	public List<Unit> UnitCreator() {//WIP
		List<Unit> units = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader("Einheiten"))) {

			String line;
			while ((line = br.readLine())!=null) {
				String[] values = line.split(",");
				String Name = values[0];
				int health = Integer.valueOf(values[1]);
				int shield = Integer.valueOf(values[2]);
				int mana = Integer.valueOf(values[3]);
				int meeleDamage = Integer.valueOf(values[4]);
				int rangedDamage = Integer.valueOf(values[5]);
				int ammo = Integer.valueOf(values[6]);
				int dodge = Integer.valueOf(values[7]);
				int magicResistance = Integer.valueOf(values[8]);
				int movementPoints = Integer.valueOf(values[9]);
				int initiative = Integer.valueOf(values[10]);
				int magicDamage = Integer.valueOf(values[11]);
				int myAttack = Integer.valueOf(values[12]);
				int positionX = Integer.valueOf(values[13]);
				int positionY = Integer.valueOf(values[14]);


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
				new FileReader("Angriffe")))
		{

			String line;
			while ((line = br.readLine())!=null)
			{
				String[] values = line.split(",");
				int attackRange = Integer.valueOf(values[0]);
				boolean isMagic = Boolean.valueOf(values[1]);
				boolean isRanged = Boolean.valueOf(values[2]);

				attacks.add(new Attack(attackRange,isMagic,isRanged));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return attacks;
	}

}