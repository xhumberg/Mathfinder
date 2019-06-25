package characterModel;

import java.util.HashMap;
import java.util.LinkedList;

public class Statistic implements Numerical {
	public final boolean STACKING = true;
	public final boolean NON_STACKING = false;

	private LinkedList<Numerical> numericalAdjustments;
	private HashMap<String, NumericalEffect> nonStackingNumericalEffects;
	private NumericalEffect circumstanceBonus; // circumstance bonuses always stack
	private NumericalEffect dodgeBonus; // dodge bonuses always stack
	private NumericalEffect penalties; // penalties always stack

	int total; 
	
	public static Statistic createFlatStatistic() {
		return new Statistic();
	}

	public static Statistic createStatisticWithBaseValue(int base) {
		return new Statistic(base);
	}

	private Statistic() {
	}

	private Statistic(int base) {
		addBonus("Base", "Base", base);
	}

	@Override
	public void addBonus(String sourceName, String typeName, int bonus) {
		if (!tryToAddToStackingTypes(sourceName, typeName, bonus)) {
			ensureNonStackingEffectExists(typeName);
			NumericalEffect effect = nonStackingNumericalEffects.get(typeName);
			effect.addAdjustment(sourceName, bonus);
		}
	}

	private boolean tryToAddToStackingTypes(String sourceName, String typeName, int bonus) {
		if (typeName.equals("Circumstance")) {
			addCircumstanceBonus(sourceName, bonus);
			return true;
		} else if (typeName.contentEquals("Dodge")) {
			addDodgeBonus(sourceName, bonus);
			return true;
		}
		return false;
	}

	private void addCircumstanceBonus(String sourceName, int bonus) {
		if (circumstanceBonus == null)
			circumstanceBonus = new NumericalEffect(STACKING);
		circumstanceBonus.addAdjustment(sourceName, bonus);
	}

	private void addDodgeBonus(String sourceName, int bonus) {
		if (dodgeBonus == null)
			dodgeBonus = new NumericalEffect(STACKING);
		dodgeBonus.addAdjustment(sourceName, bonus);
	}

	private void ensureNonStackingEffectExists(String typeName) {
		if (!nonStackingNumericalEffects.containsKey(typeName)) {
			NumericalEffect effect = new NumericalEffect(NON_STACKING);
			nonStackingNumericalEffects.put(typeName, effect);
		}
	}

	@Override
	public void addNumerical(Numerical adjustment) {
		if (numericalAdjustments == null)
			numericalAdjustments = new LinkedList<Numerical>();
		numericalAdjustments.add(adjustment);
	}

	@Override
	public void addPenalty(String sourceName, int penalty) {
		if (penalties == null)
			penalties = new NumericalEffect(STACKING);
		penalties.addAdjustment(sourceName, penalty);
	}

	@Override
	public int getAdjustmentValue() {
		total = 0;
		addNumericalAdjustmentsToTotal();
		addNonStackingNumericalEffectsToTotal();
		addStackingBonusToTotal();
		addPenaltiesToTotal();
		return total;
	}

	private void addNumericalAdjustmentsToTotal() {
		if (numericalAdjustments != null)
			for (Numerical num : numericalAdjustments)
				total += num.getAdjustmentValue();
	}
	
	private void addNonStackingNumericalEffectsToTotal() {
		if (nonStackingNumericalEffects != null)
			for (NumericalEffect e : nonStackingNumericalEffects.values())
				total += e.getValue();
	}
	
	private void addStackingBonusToTotal() {
		if (circumstanceBonus != null)
			total += circumstanceBonus.getValue();
		if (dodgeBonus != null)
			total += dodgeBonus.getValue();
	}
	
	private void addPenaltiesToTotal() {
		if (penalties != null)
			total += penalties.getValue();
	}

	@Override
	public void reset() {
		numericalAdjustments = null;
		nonStackingNumericalEffects = null;
		circumstanceBonus = null;
		dodgeBonus = null;
		penalties = null;
	}

	public String toString() {
		return String.valueOf(getAdjustmentValue());
	}

	public void setBase(int val) {
		addBonus("Base", "Base", val);
	}

	@Override
	public void removeSource(String source) {
		if (nonStackingNumericalEffects != null)
			for (NumericalEffect e : nonStackingNumericalEffects.values())
				e.removeSource(source);
		if (circumstanceBonus != null)
			circumstanceBonus.removeSource(source);
		if (dodgeBonus != null)
			dodgeBonus.removeSource(source);
		if (penalties != null)
			penalties.removeSource(source);
	}
}
