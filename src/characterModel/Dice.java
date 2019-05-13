package characterModel;

public class Dice {

	DiceType type;
	int numberOfDice;

	public Dice(DiceType type) {
		this.type = type;
	}

	public Dice(DiceType type, int num) {
		numberOfDice = num;
		this.type = type;
	}

	public int max() {
		switch (type) {
		case D4:
			return 4;
		case D6:
			return 6;
		case D8:
			return 8;
		case D10:
			return 10;
		case D12:
			return 12;
		case D20:
			return 20;
		case D100:
			return 100;
		default:
			return 0;
		}
	}

	public int avg() {
		switch (type) {
		case D4:
			return 3;
		case D6:
			return 4;
		case D8:
			return 5;
		case D10:
			return 6;
		case D12:
			return 7;
		case D20:
			return 11;
		case D100:
			return 51;
		default:
			return 0;
		}

	}
}
