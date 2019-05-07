package characterModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BonusTest {

	@Test
	void StartsAtZero() {
		Bonus hehe = new Bonus();
		assertEquals(0, hehe.getValue());
	}
	
	@Test
	void basicTest1() {
		Bonus strength = new Bonus();
		strength.addBonus("Base", 14);
		strength.addBonus("Morale", 4);
		assertEquals(18, strength.getValue());
	}

	@Test
	void basicTest2() {
		Bonus strength = new Bonus();
		strength.addBonus("Base", 14);
		strength.addBonus("Morale", 4);
		strength.addBonus("Alchemical", 4);
		assertEquals(22, strength.getValue());
	}
	
	@Test
	void basicTest3() {
		Bonus counter = new Bonus();
		for (int i = 0; i < 50000; i++) {
			counter.addBonus(String.valueOf(i), 1);
		}
		assertEquals(50000, counter.getValue());
	}
	
	@Test
	void penaltyTest1() {
		Bonus strength = new Bonus();
		strength.addBonus("Base", 25);
		strength.addPenalty(-2);
		assertEquals(23, strength.getValue());
	}
	
	@Test
	void superNegative() {
		Bonus counter = new Bonus();
		for (int i = 0; i < 50000; i++) {
			counter.addPenalty(-1);
		}
		assertEquals(-50000, counter.getValue());
	}
	
}
