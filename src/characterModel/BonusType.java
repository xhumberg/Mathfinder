package characterModel;

import java.util.LinkedList;
import java.util.List;

public class BonusType {
	private List<EffectWithSource> effects;
	boolean doesStack;

	public BonusType(boolean doesStack) {
		effects = new LinkedList<EffectWithSource>();
		this.doesStack = doesStack;
	}

	public BonusType(String source, Integer value, boolean doesStack) {
		this(doesStack);
		addAdjustment(source, value);
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

	public int getAdjustmentAmount() {
		int value = 0;
		for (EffectWithSource effect : effects)
			if (doesStack)
				value += effect.value;
			else
				value = Math.max(value, effect.value);
		return value;
	}

	private class EffectWithSource {
		protected String source;
		protected Integer value;
		
		public EffectWithSource(String source, Integer value) {
			this.source = source;
			this.value = value;
		}
	}

	public boolean removeSource(String source) {
		int foundIndex = -1;
		for (int i = 0; i < effects.size(); i++) {
			if (effects.get(i).source.equals(source)) {
				foundIndex = i;
				break;
			}
		}
		if (foundIndex >= 0) {
			effects.remove(foundIndex);
			
			//Ensure no more effects from this source exist
			removeSource(source);
			
			return true;
		}
		return false;
	}
	
	public String toString() {
		return String.valueOf(getAdjustmentAmount());
	}
}
