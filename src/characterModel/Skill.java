package characterModel;

public class Skill{
	Statistic myBonus;
	private int ranks;
	// private boolean untrained;
	private Statistic armorCheck;
	private boolean classSkill;

	public Skill(AbilityScore stat) {
		myBonus = Statistic.createFlatStatistic();
		myBonus.addNumerical(stat);
		ranks = 0;
		// untrained = false;
		armorCheck = Statistic.createFlatStatistic();
		classSkill = false;
	}

	public Skill(AbilityScore stat, Statistic ACP) {
		this(stat);
		armorCheck = ACP;
	}
	
	public int getValue() {
		int bonus = myBonus.getAdjustmentValue() + ranks;
		if (classSkill && ranks > 0) {
			bonus += 3;
		}
		if(armorCheck != null)
		{
			 bonus += armorCheck.getAdjustmentValue();
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
