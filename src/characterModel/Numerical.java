package characterModel;

public interface Numerical {
	public int getAdjustmentValue();
	public void addBonus(String sourceName, String typeName, int bonus);
	public void addPenalty(String sourceName, int penalty);
	public void addNumerical(Numerical bonus);
	public void removeSource(String sourceName);
	public void reset();
}
