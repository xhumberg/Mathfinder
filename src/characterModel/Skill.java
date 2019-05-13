package characterModel;

public class Skill {
	private Bonus myBonus;
	private int ranks;
	//private boolean untrained;
	private Bonus armorCheck;
	private boolean classSkill;
	
	public Skill(Bonus stat)
	{
		myBonus = new Bonus();
		myBonus.addStat(stat);
		ranks = 0;
		//untrained = false;
		armorCheck = new Bonus();
		classSkill = false;
	}
	
	public Skill(Bonus stat, Bonus ACP)
	{
		this(stat);
		armorCheck = ACP;
	}
	
	public int getValue()
	{
		int bonus = myBonus.getValue() + ranks + armorCheck.getValue();
		if(classSkill && ranks > 0)
		{
			bonus += 3;
		}
		return bonus;
	}
	
	public void setRanks(int newRanks)
	{
		ranks = newRanks;
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
