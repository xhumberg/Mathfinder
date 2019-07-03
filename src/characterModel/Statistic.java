package characterModel;

public class Statistic extends Numerical {
	
	public static Statistic createFlatStatistic() {
		return new Statistic();
	}

	public static Statistic createStatisticWithBaseValue(int base) {
		return new Statistic(base);
	}
	
	private Statistic() {
		super();
	}
	
	private Statistic(int base) {
		super(base);
	}
}
