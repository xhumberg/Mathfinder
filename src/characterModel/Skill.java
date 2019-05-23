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
		armorCheck = null;
		classSkill = false;
	}
	
	public Skill(Bonus stat, Bonus ACP)
	{
		this(stat);
		armorCheck = ACP;
	}
	
	public int getValue()
	{
		int bonus = myBonus.getValue() + ranks;
		if(classSkill && ranks > 0)
		{
			bonus += 3;
		}
		if(armorCheck != null)
		{
			 bonus += armorCheck.getValue();
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
	
	public void setClassSkill()
	{
		classSkill = true;
	}
	
	public void setACP(Bonus newACP)
	{
		// Placeholder
	}
}
