package characterModel;

import java.util.HashMap;
import java.util.LinkedList;

public class Bonus {
	private LinkedList<Bonus> stats;
	private HashMap<String, BonusEffect> nonStackingBonuses;
	private BonusEffect circumstance;
	private BonusEffect dodge;
	private BonusEffect penalties;
	private LinkedList<Bonus> otherBonuses;

	public Bonus() {
	}

	public Bonus(Integer base) {
		applyBonus("Base", "Base", base);
	}

	/**
	 * Creates a bonus of the specified type of the amount listed so long as a bonus
	 * of equal or higher value doesn't already exist. If "Circumstance" or "Dodge"
	 * is placed in the type field, the values are placed in a special stacking
	 * field, otherwise bonuses of the same type do not stack.
	 * 
	 * @param type   - The name of the bonus, starting with a capital letter
	 * @param amount - An integer amount specifying the bonus.
	 */
	public void applyBonus(String source, String type, int amount) {
		placeBonus(source, type, amount, false);
	}

	public void setBonus(String source, String type, int amount) {
		placeBonus(source, type, amount, true);
	}

	private void placeBonus(String source, String type, int amount, boolean forcePlacement) {
		//TODO: implement forcePlacement
		if (type.equals("Circumstance")) {
			if (circumstance == null)
				circumstance = new BonusEffect(true);
			circumstance.addEffect(source, amount);
			return;
		}
		if (type.equals("Dodge")) {
			if (dodge == null)
				dodge = new BonusEffect(true);
			dodge.addEffect(source, amount);
			return;
		}

		ensureMapExists();

		BonusEffect effect = nonStackingBonuses.get(type);

		// Ensure the effect exists
		if (effect == null) {
			effect = new BonusEffect(false);
			nonStackingBonuses.put(type, effect);
		}

		effect.addEffect(source, amount);
	}

	public void addStat(Bonus stat) {
		if (stats == null)
			stats = new LinkedList<Bonus>();
		stats.add(stat);
	}

	public void addPenalty(String source, Integer amount) {
		if (penalties == null)
			penalties = new BonusEffect(true);
		penalties.addEffect(source, amount);
	}

	public int getValue() {
		int total = 0;
		if (stats != null)
			for (Bonus stat : stats)
				total += stat.getMod();
		if (nonStackingBonuses != null)
			for (BonusEffect e : nonStackingBonuses.values())
				total += e.getValue();
		if (circumstance != null)
			total += circumstance.getValue();
		if (dodge != null)
			total += dodge.getValue();
		if (penalties != null)
			total += penalties.getValue();
		if (otherBonuses != null)
			for (Bonus bonus : otherBonuses)
				total += bonus.getValue();
		return total;
	}

	public int getMod() {
		return (int) Math.floor((getValue() / 2) - 5);
	}

	/**
	 * Removes all bonuses, effectively making the bonus 0.
	 */
	public void reset() {
		nonStackingBonuses = null;
		circumstance = null;
		dodge = null;
		penalties = null;
	}

	private void ensureMapExists() {
		if (nonStackingBonuses == null)
			nonStackingBonuses = new HashMap<String, BonusEffect>();
	}

	public String toString() {
		return String.valueOf(getValue());
	}

	public void setBase(int val) {
		setBonus("Base", "Base", val);
	}

	public void addBonus(Bonus bonus) {
		if (otherBonuses == null)
			otherBonuses = new LinkedList<Bonus>();
		otherBonuses.add(bonus);
	}
	
	/**
	 * Note, this function is incapable of removing bonuses added using the addBonus and addStat functions.
	 * @param source
	 */
	public void removeSource(String source) {
		if (nonStackingBonuses != null)
			for (BonusEffect e : nonStackingBonuses.values())
				e.removeSource(source);
		if (circumstance != null)
			circumstance.removeSource(source);
		if (dodge != null)
			dodge.removeSource(source);
		if (penalties != null)
			penalties.removeSource(source);
	}
}
