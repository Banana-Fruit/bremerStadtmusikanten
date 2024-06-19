package control.game;

import model.Unit;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UnitController
{
	public List<Unit> UnitCreator()
	{//WIP
		List<Unit> units = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader("Einheiten")))
		{
			
			String line;
			while ((line = br.readLine()) != null)
			{
				String[] values = line.split(",");
				String Name = values[0];
				int hp = Integer.valueOf(values[1]);
				int shield = Integer.valueOf(values[2]);
				int mana = Integer.valueOf(values[3]);
				int meele = Integer.valueOf(values[4]);
				int ranged = Integer.valueOf(values[5]);
				int ammo = Integer.valueOf(values[6]);
				int dodge = Integer.valueOf(values[7]);
				int mr = Integer.valueOf(values[8]);
				int mvmt = Integer.valueOf(values[9]);
				int init = Integer.valueOf(values[10]);
				int magcdmg = Integer.valueOf(values[11]);
				
				
				units.add(new Unit(Name, hp, shield, mana, meele, ranged, ammo, dodge, mr, mvmt, init, magcdmg));
				
			}
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		return units;
	}
}
