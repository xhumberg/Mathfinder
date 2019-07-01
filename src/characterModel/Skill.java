package characterModel;

public class Skill{
	Statistic myBonus;
	private int ranks;
	// private boolean untrained;
	private Statistic armorCheck;
	private boolean classSkill;

	public Skill(AbilityScore stat) {
		myBonus = new Statistic();
		myBonus.addStat(stat);
		ranks = 0;
		// untrained = false;
		armorCheck = new Statistic();
		classSkill = false;
	}

	public Skill(AbilityScore stat, Statistic ACP) {
		this(stat);
		armorCheck = ACP;
	}
	
	public int getValue() {
		int bonus = myBonus.getValue() + ranks;
		if (classSkill && ranks > 0) {
			bonus += 3;
		}
		if(armorCheck != null)
		{
			 bonus += armorCheck.getValue();
		}
		return bonus;
	}

	public void setRanks(int newRanks) {
		ranks = newRanks;
	}

	public int getRanks() {
		return ranks;
	}

	public void addRanks(int newRanks) {
		ranks += newRanks;
	}
	
	public void setClassSkill()
	{
		classSkill = true;
	}
	
	public void setACP(Statistic newACP)
	{
		// Placeholder
	}
}
