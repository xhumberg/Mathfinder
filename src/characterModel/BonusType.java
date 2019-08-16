package characterModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BonusType {
	
	private String type;
	private List<EffectWithSource> effects;
	private boolean doesStack;

	public BonusType(String type, boolean doesStack) {
		effects = new LinkedList<EffectWithSource>();
		this.doesStack = doesStack;
		this.type = type;
	}

	public void addAdjustment(String source, Integer value) {
		if (hasSource(source)) {
			EffectWithSource fetchedEffect = getEffectFromSource(source);
			fetchedEffect.value = value;
			return;
		}
		EffectWithSource newEffect = new EffectWithSource(source, value);
		effects.add(newEffect);
	}

	private boolean hasSource(String source) {
		return (getEffectFromSource(source) != null);
	}

	private EffectWithSource getEffectFromSource(String source) {
		for (EffectWithSource t : effects) {
			if (t.source.contentEquals(source))
				return t;
		}
		return null;
	}
	
	public String toString() {
		return type + ": " + String.valueOf(getAdjustmentAmount());
	}

	public int getAdjustmentAmount() {
		int value = 0;
		for (EffectWithSource effect : effects)
			if (doesStack)
				value += effect.value;
			else
				value = Math.max(value, effect.value);
		return value;
	}

	public void removeSource(String source) {
		List<Integer> foundIndexes = findIndexesOfEffectsWithSource(source);
		removeEffectsWithIndexes(foundIndexes);
	}
	
	private List<Integer> findIndexesOfEffectsWithSource(String source) {
		ArrayList<Integer> foundIndexes = new ArrayList<Integer>();
		for (int i = 0; i < effects.size(); i++) {
			if (effectAtIndexHasSource(i, source)) {
				foundIndexes.add(i);
			}
		}
		return foundIndexes;
	}
	
	private boolean effectAtIndexHasSource(int index, String source) {
			return effects.get(index).source.equals(source);
	}
	
	private void removeEffectsWithIndexes(List<Integer> indexes) {
		//Remove from the back so the indexes don't change.
		for (int i = indexes.size() - 1; i >= 0; i--) {
			effects.remove(i);
		}
	}

	public String toVerboseString() {
		StringBuilder sb = new StringBuilder(toString() + " [");
		for (EffectWithSource effect : effects) {
			sb.append(effect.toString()).append(", ");
		}
		sb.replace(sb.length()-2, sb.length(), "]");
		return sb.toString();
	}
}
