package characterModel;

import java.util.LinkedList;
import java.util.List;

public class TempEffect {
	private class BonusTriple{
		Statistic target;
		String type;
		int value;
	}
	
	private class PenaltyTuple{
		Statistic target;
		int value;
	}
	
	String effectName;
	List<BonusTriple> Bonuses;
	List<PenaltyTuple> Penalties;
	Statistic perDay;
	int used;
	
	public TempEffect(String name) {
		effectName = name;
		used = 0;
	}
	
	public void addBonus(Statistic target, String type, int value) {
		BonusTriple bonus = new BonusTriple();
		bonus.target = target;
		bonus.type = type;
		bonus.value = value;
		
		if (Bonuses == null)
			Bonuses = new LinkedList<BonusTriple>();
		
		Bonuses.add(bonus);
	}
	
	public void addPenalty(Statistic target, int value) {
		PenaltyTuple penalty = new PenaltyTuple();
		penalty.target = target;
		penalty.value = value;
		
		if (Penalties == null)
			Penalties = new LinkedList<PenaltyTuple>();
		
		Penalties.add(penalty);
	}
	
	public void activate() {
		if (Bonuses != null)
		for (BonusTriple triple : Bonuses) {
			triple.target.addBonus(effectName, triple.type, triple.value);
		}
		if (Penalties != null)
		for (PenaltyTuple tuple : Penalties) {
			tuple.target.addPenalty(effectName, tuple.value);
		}
	}
	
	public void deactivate() {
		if (Bonuses != null)
		for (BonusTriple triple : Bonuses) {
			triple.target.removeSource(effectName);
		}
		if (Penalties != null)
		for (PenaltyTuple tuple : Penalties) {
			tuple.target.removeSource(effectName);
		}
	}
}
