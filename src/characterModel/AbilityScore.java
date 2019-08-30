package characterModel;

public class AbilityScore extends Numerical {
	public static AbilityScore getFlatAbilityScore() {
		return new AbilityScore(10);
	}
	
	public static AbilityScore getAbilityScoreWithBase(int base) {
		return new AbilityScore(base);
	}

	private AbilityScore(int base) {
		super(base);
	}
	
	@Override
	public int getAdjustmentValue() {
		int score = getScore();
		return (int)Math.floor(score/2)-5;
		
	}
	
	public int getScore() {
		return super.getAdjustmentValue();
	}
	
	public String toString() {
		return String.valueOf(getScore()) + "(" + 
				(getAdjustmentValue() < 0 ? "-" : "") +
				getAdjustmentValue() + ")";
	}
}
