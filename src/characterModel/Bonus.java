package characterModel;

import java.util.HashMap;
import java.util.LinkedList;

public class Bonus {
	private HashMap<String, Integer> nonStackingBonuses;
	private LinkedList<Integer> circumstance;
	private LinkedList<Integer> dodge;
	private LinkedList<Integer> penalties;

	public Bonus() {
	}

	public Bonus(Integer base) {
		applyBonus("Base", base);
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
	public void applyBonus(String type, int amount) {
		placeBonus(type, amount, false);
	}

	public void setBonus(String type, int amount) {
		placeBonus(type, amount, true);
	}

	private void placeBonus(String type, int amount, boolean forcePlacement) {
		if (type.equals("Circumstance")) {
			return;
		}
		if (type.contentEquals("Dodge")) {
			return;
		}
		ensureMapExists();
		Integer previous = nonStackingBonuses.put(type, amount);
		if (previous == null || !forcePlacement)
			return;
		nonStackingBonuses.put(type, Math.max(amount, previous));
	}

	public void addPenalty(Integer amount) {
		if (penalties == null)
			penalties = new LinkedList<Integer>();
		penalties.push(amount);
	}

	public int getValue() {
		int total = 0;
		if (nonStackingBonuses != null)
			for (Integer i : nonStackingBonuses.values())
				total += i;
		if (circumstance != null)
			for (Integer i : circumstance)
				total += i;
		if (dodge != null)
			for (Integer i : dodge)
				total += i;
		if (penalties != null)
			for (Integer i : penalties)
				total += i;
		return total;
	}

	/**
	 * Removes all bonuses, effectively making the bonus 0.
	 */
	public void reset() {
		nonStackingBonuses = null;
		circumstance = null;
		dodge = null;
	}

	private void ensureMapExists() {
		if (nonStackingBonuses == null)
			nonStackingBonuses = new HashMap<String, Integer>();
	}

	public String toString() {
		return String.valueOf(getValue());
	}

	public void setBase(int val) {
		setBonus("Base", val);
	}
}
