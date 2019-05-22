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
		//TODO: figure out why this takes so long
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
	
	@Test
	void removeEffectTesting() {
		Bonus Strength = new Bonus();
		Strength.setBase(14);
		Strength.applyBonus("Rage", "Morale", 4);
		assertEquals(18, Strength.getValue());
		Strength.removeBonus("Rage");
		assertEquals(14, Strength.getValue());
	}
	
	@Test
	void stackTest1() {
		Bonus Attack = new Bonus();
		Bonus Strength = new Bonus(14);
		Attack.addStat(Strength);
		Bonus BAB = new Bonus(4);
		Attack.addBonus(BAB);
		assertEquals(6, Attack.getValue());
		Attack.applyBonus("Flanking", "Circumstance", 2);
		assertEquals(8, Attack.getValue());
		Attack.applyBonus("High Ground", "Circumstance", 1);
		assertEquals(9, Attack.getValue());
		Attack.removeBonus("High Ground");
		assertEquals(8, Attack.getValue());
	}
	
	@Test
	void stackTest2() {
		Bonus AC = new Bonus(10);
		Bonus Dex = new Bonus(18);
		AC.addStat(Dex);
		AC.applyBonus("Mithral Chain Shirt", "Armor", 9);
		AC.applyBonus("Heavy Steel Shield", "Shield", 2);
		AC.applyBonus("Fighting Defensively", "Dodge", 2);
		AC.applyBonus("Dodge", "Dodge", 1);
		assertEquals(28, AC.getValue());
		AC.applyBonus("Fighting Defensively", "Shield", 3);
		assertEquals(29, AC.getValue());
		AC.removeBonus("Heavy Steel Shield");
		assertEquals(29, AC.getValue());
		AC.applyBonus("Heavy Wooden Shield", "Shield", 2);
		assertEquals(29, AC.getValue());
		AC.removeBonus("Fighting Defensively"); //This should remove the greater shield bonus and the dodge bonus
		assertEquals(26, AC.getValue());
		
	}
	
}
