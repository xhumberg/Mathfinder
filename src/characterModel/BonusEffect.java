package characterModel;

import java.util.LinkedList;
import java.util.List;

public class BonusEffect {
	private List<BonusTuple> effects;
	boolean stacking;

	public BonusEffect(boolean stack) {
		effects = new LinkedList<BonusTuple>();
		stacking = stack;
	}

	public BonusEffect(String source, Integer value, boolean stack) {
		this(stack);
		addEffect(source, value);
	}

	public void addEffect(String source, Integer value) {
		if (sourceExists(source)) {
			BonusTuple changeMe = getSource(source);
			changeMe.value = value;
			return;
		}
		BonusTuple effect = new BonusTuple();
		effect.source = source;
		effect.value = value;
		effects.add(effect);
	}

	private boolean sourceExists(String source) {
		return (getSource(source) != null);
	}

	private BonusTuple getSource(String source) {
		for (BonusTuple t : effects) {
			if (t.source.contentEquals(source))
				return t;
		}
		return null;
	}

	public int getValue() {
		int value = 0;
		for (BonusTuple effect : effects)
			if (stacking)
				value += effect.value;
			else
				value = Math.max(value, effect.value);
		return value;
	}

	private class BonusTuple {
		String source;
		Integer value;
	}
}
