package characterModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BonusTest {

	@Test
	void StartsAtZero() {
		Statistic hehe = Statistic.createFlatStatistic();
		assertEquals(0, hehe.getValue());
	}
	
	@Test
	void basicTest1() {
		Statistic strength = Statistic.createFlatStatistic();
		strength.applyBonus("Base", "Base", 14);
		strength.applyBonus("Rage", "Morale", 4);
		assertEquals(18, strength.getValue());
	}

	@Test
	void basicTest2() {
		Statistic strength = Statistic.createFlatStatistic();
		strength.applyBonus("Base", "Base", 14);
		strength.applyBonus("Rage", "Morale", 4);
		strength.applyBonus("Mutagen", "Alchemical", 4);
		assertEquals(22, strength.getValue());
	}
	
	@Test
	void basicTest3() {
		Statistic counter = Statistic.createFlatStatistic();
		for (int i = 0; i < 50000; i++) {
			counter.applyBonus(String.valueOf(i), String.valueOf(i), 1);
		}
		assertEquals(50000, counter.getValue());
	}
	
	@Test
	void penaltyTest1() {
		Statistic strength = Statistic.createFlatStatistic();
		strength.applyBonus("Base", "Base", 25);
		strength.addPenalty("Reduce Person", -2);
		assertEquals(23, strength.getValue());
	}
	
	//@Test
	void superNegative() {
		//TODO: figure out why this takes so long
		Statistic counter = Statistic.createFlatStatistic();
		for (int i = 0; i < 50000; i++) {
			counter.addPenalty(String.valueOf(i), -1);
		}
		assertEquals(-50000, counter.getValue());
	}
	
	@Test
	void modTest() {
		Statistic dex = Statistic.createFlatStatistic();
		assertEquals(2, dex.getMod());
	}
	
	@Test
	void pretendAcrobatics() {
		Statistic acrobatics = Statistic.createFlatStatistic();
		acrobatics.applyBonus("Ranks", "Ranks", 3);
		acrobatics.applyBonus("Class Skill", "Class Skill", 3);
		acrobatics.addPenalty("ACP", -2);
		Statistic dex = Statistic.createStatisticWithBaseValue(16);
		acrobatics.addStat(dex);
		assertEquals(7, acrobatics.getValue());
		dex.applyBonus("Belt", "Enhancement", 2);
		assertEquals(8,  acrobatics.getValue());
	}
	
	@Test
	void removeEffectTesting() {
		Statistic Strength = Statistic.createFlatStatistic();
		Strength.setBase(14);
		Strength.applyBonus("Rage", "Morale", 4);
		assertEquals(18, Strength.getValue());
		Strength.removeSource("Rage");
		assertEquals(14, Strength.getValue());
	}
	
	@Test
	void stackTest1() {
		Statistic Attack = Statistic.createFlatStatistic();
		Statistic Strength = Statistic.createStatisticWithBaseValue(14);
		Attack.addStat(Strength);
		Statistic BAB = Statistic.createStatisticWithBaseValue(4);
		Attack.addBonus(BAB);
		assertEquals(6, Attack.getValue());
		Attack.applyBonus("Flanking", "Circumstance", 2);
		assertEquals(8, Attack.getValue());
		Attack.applyBonus("High Ground", "Circumstance", 1);
		assertEquals(9, Attack.getValue());
		Attack.removeSource("High Ground");
		assertEquals(8, Attack.getValue());
	}
	
	@Test
	void stackTest2() {
		Statistic AC = Statistic.createStatisticWithBaseValue(10);
		Statistic Dex = Statistic.createStatisticWithBaseValue(18);
		AC.addStat(Dex);
		AC.applyBonus("Mithral Chain Shirt", "Armor", 9);
		AC.applyBonus("Heavy Steel Shield", "Shield", 2);
		AC.applyBonus("Fighting Defensively", "Dodge", 2);
		AC.applyBonus("Dodge", "Dodge", 1);
		assertEquals(28, AC.getValue());
		AC.applyBonus("Fighting Defensively", "Shield", 3);
		assertEquals(29, AC.getValue());
		AC.removeSource("Heavy Steel Shield");
		assertEquals(29, AC.getValue());
		AC.applyBonus("Heavy Wooden Shield", "Shield", 2);
		assertEquals(29, AC.getValue());
		AC.removeSource("Fighting Defensively"); //This should remove the greater shield bonus and the dodge bonus
		assertEquals(26, AC.getValue());
	}
	
	@Test
	void rageTest() {
		Statistic Str = Statistic.createStatisticWithBaseValue(18);
		Str.applyBonus("Belt", "Enhancement", 2);
		Statistic Con = Statistic.createStatisticWithBaseValue(14);
		
		TempEffect rage = new TempEffect("Rage");
		rage.addBonus(Str, "Morale", 4);
		rage.addBonus(Con, "Morale", 4);
		
		assertEquals(20, Str.getValue());
		assertEquals(14, Con.getValue());
		
		rage.activate();
		
		assertEquals(24, Str.getValue());
		assertEquals(18, Con.getValue());
		
		rage.deactivate();
		
		assertEquals(20, Str.getValue());
		assertEquals(14, Con.getValue());
		
		Statistic AC = Statistic.createStatisticWithBaseValue(13);
		
		rage.addPenalty(AC, -2);
		
		rage.activate();
		
		assertEquals(11, AC.getValue());
		
		rage.deactivate();
		
		assertEquals(13, AC.getValue());
	}
	
	@Test
	void effectRemoval() {
		Statistic Str = Statistic.createStatisticWithBaseValue(18);
		Str.applyBonus("Belt", "Morale", 2);
		
		TempEffect rage = new TempEffect("Rage");
		rage.addBonus(Str, "Morale", 4);
		
		assertEquals(20, Str.getValue());
		
		rage.activate();
		
		assertEquals(22, Str.getValue());
		
		rage.deactivate();
		
		assertEquals(20, Str.getValue());
	}
	
}
