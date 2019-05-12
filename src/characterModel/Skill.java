package characterModel;

//Array of skills, each of which has a name, a bonus, and two booleans for untrained and armor check.
public class Skill {
	private Bonus myBonus;
	private int ranks;
	private boolean untrained;
	private boolean armorCheck;
	
	public Skill()
	{
		myBonus = new Bonus();
		int ranks = 0;
		untrained = false;
		armorCheck = false;
	}
	
	public int getValue()
	{
		return myBonus.getValue();
	}
	
	public int getRanks()
	{
		return ranks;
	}
	
	public void addRanks(int newRanks)
	{
		ranks += newRanks;
	}
}
