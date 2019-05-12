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
		strength.applyBonus("Base", "Base", 14);
		strength.applyBonus("Rage", "Morale", 4);
		assertEquals(18, strength.getValue());
	}

	@Test
	void basicTest2() {
		Bonus strength = new Bonus();
		strength.applyBonus("Base", "Base", 14);
		strength.applyBonus("Rage", "Morale", 4);
		strength.applyBonus("Mutagen", "Alchemical", 4);
		assertEquals(22, strength.getValue());
	}
	
	@Test
	void basicTest3() {
		Bonus counter = new Bonus();
		for (int i = 0; i < 50000; i++) {
			counter.applyBonus(String.valueOf(i), String.valueOf(i), 1);
		}
		assertEquals(50000, counter.getValue());
	}
	
	@Test
	void penaltyTest1() {
		Bonus strength = new Bonus();
		strength.applyBonus("Base", "Base", 25);
		strength.addPenalty("Reduce Person", -2);
		assertEquals(23, strength.getValue());
	}
	
	@Test
	void superNegative() {
		Bonus counter = new Bonus();
		for (int i = 0; i < 50000; i++) {
			counter.addPenalty(String.valueOf(i), -1);
		}
		assertEquals(-50000, counter.getValue());
	}
	
	@Test
	void modTest() {
		Bonus dex = new Bonus(14);
		assertEquals(2, dex.getMod());
	}
	
	@Test
	void pretendAcrobatics() {
		Bonus acrobatics = new Bonus();
		acrobatics.applyBonus("Ranks", "Ranks", 3);
		acrobatics.applyBonus("Class Skill", "Class Skill", 3);
		acrobatics.addPenalty("ACP", -2);
		Bonus dex = new Bonus(16);
		acrobatics.addStat(dex);
		assertEquals(7, acrobatics.getValue());
		dex.applyBonus("Belt", "Enhancement", 2);
		assertEquals(8,  acrobatics.getValue());
	}
	
}
