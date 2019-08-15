package characterModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StatisticTest {

	@Test
	void testStartsAtZero() {
		Numerical hehe = Statistic.createFlatStatistic();
		assertEquals(0, hehe.getAdjustmentValue());
	}
	
	@Test
	void testSingleBonusApplies() {
		Numerical testStat = Statistic.createFlatStatistic();
		testStat.addBonus("testSource", "testType", 4);
		assertEquals(4, testStat.getAdjustmentValue());
	}

	@Test
	void testMultipleNonStackingBonusesApply() {
		Numerical strength = Statistic.createFlatStatistic();
		strength.addBonus("Rage", "Morale", 4);
		strength.addBonus("Mutagen", "Alchemical", 4);
		assertEquals(8, strength.getAdjustmentValue());
	}
	
	@Test
	void testBaseApplies() {
		Numerical strength = Statistic.createStatisticWithBaseValue(14);
		strength.addBonus("Rage", "Morale", 4);
		assertEquals(18, strength.getAdjustmentValue());
	}
	
	@Test
	void testThousandsOfNonStackingBonusesApply() {
		Numerical counter = Statistic.createFlatStatistic();
		for (int i = 0; i < 50000; i++) {
			counter.addBonus(String.valueOf(i), String.valueOf(i), 1);
		}
		assertEquals(50000, counter.getAdjustmentValue());
	}
	
	@Test
	void testSinglePenaltyApplies() {
		Numerical strength = Statistic.createFlatStatistic();
		strength.addBonus("Base", "Base", 25);
		strength.addPenalty("Reduce Person", -2);
		assertEquals(23, strength.getAdjustmentValue());
	}
	
	@Test
	void testThousandsOfPenaltiesApply() {
		//TODO: figure out why this takes so long
		Numerical counter = Statistic.createFlatStatistic();
		for (int i = 0; i < 5000; i++) {
			counter.addPenalty(String.valueOf(i), -1);
		}
		assertEquals(-5000, counter.getAdjustmentValue());
	}
	
	@Test
	void testStackingBonusesApply() {
		Numerical AC = Statistic.createFlatStatistic();
		AC.addBonus("Dodge", "Dodge", 1);
		AC.addBonus("Fighting Defensively", "Dodge", 3);
		assertEquals(4, AC.getAdjustmentValue());
		AC.addBonus("Circumstance", "Circumstance", 4);
		AC.addBonus("Antiflank", "Circumstance", 3);
		assertEquals(11, AC.getAdjustmentValue());
	}
	
	@Test
	void testOnlyHighestOfNonStackingBonusesApplies() {
		Numerical AC = Statistic.createFlatStatistic();
		AC.addBonus("Armor", "Armor", 3);
		AC.addBonus("Mage Armor", "Armor", 4);
		assertEquals(4, AC.getAdjustmentValue());
	}
	
	@Test
	void testAddNumerical() {
		Numerical Strength = Statistic.createFlatStatistic();
		Strength.addBonus("Base", "Base", 4);
		Numerical climb = Statistic.createFlatStatistic();
		climb.addNumerical(Strength);
		assertEquals(4, climb.getAdjustmentValue());
		climb.addBonus("Rank", "rank", 1);
		assertEquals(5, climb.getAdjustmentValue());
	}
	
	@Test
	void testAddMultipleNumericals() {
		Numerical CMD = Statistic.createFlatStatistic();
		Numerical Strength = Statistic.createStatisticWithBaseValue(4);
		Numerical Dexterity = Statistic.createStatisticWithBaseValue(2);
		CMD.addNumerical(Strength);
		CMD.addNumerical(Dexterity);
		assertEquals(6, CMD.getAdjustmentValue());
	}
	
	@Test
	void testResetWithNumericalsAndValues() {
		Numerical CMD = Statistic.createFlatStatistic();
		Numerical Strength = Statistic.createStatisticWithBaseValue(4);
		Numerical Dexterity = Statistic.createStatisticWithBaseValue(2);
		CMD.addNumerical(Strength);
		CMD.addNumerical(Dexterity);
		CMD.addBonus("Circumstance", "Circumstance", 2);
		CMD.addPenalty("Entangled", -2);
		assertEquals(6, CMD.getAdjustmentValue());
		CMD.reset();
		assertEquals(0, CMD.getAdjustmentValue());
	}
	
	@Test
	void testSetBaseForStatistic() {
		Numerical CMD = Statistic.createFlatStatistic();
		CMD.setBase(10);
		assertEquals(10, CMD.getAdjustmentValue());
	}
	
	@Test
	void testRemoveSource() {
		Statistic attack = Statistic.createFlatStatistic();
		attack.addNumerical(Statistic.createStatisticWithBaseValue(4));
		attack.addBonus("Rage", "Morale", 4);
		attack.addBonus("Not Rage", "Morale", 3);
		attack.addBonus("Rage", "Circumstance", 2);
		attack.addBonus("Not Rage", "Circumstance", 2);
		attack.addBonus("Rage", "Dodge", 2);
		attack.addBonus("Not Rage", "Dodge", 2);
		attack.addPenalty("Rage", -2);
		assertEquals(14, attack.getAdjustmentValue());
		
		attack.removeSource("Rage");
		assertEquals(11, attack.getAdjustmentValue());
	}
	
	@Test
	void testAttemptToRemoveNonExistantSource() {
		Statistic attack = Statistic.createFlatStatistic();
		attack.addNumerical(Statistic.createStatisticWithBaseValue(4));
		attack.addBonus("Rage", "Morale", 4);
		attack.addBonus("Not Rage", "Morale", 3);
		attack.addBonus("Rage", "Circumstance", 2);
		attack.addBonus("Not Rage", "Circumstance", 2);
		attack.addBonus("Rage", "Dodge", 2);
		attack.addBonus("Not Rage", "Dodge", 2);
		assertEquals(16, attack.getAdjustmentValue());
		
		attack.removeSource("mage Armor");
		assertEquals(16, attack.getAdjustmentValue());
	}
	
	@Test
	void testRemoveSourceBeforeAddingAnyBonuses() {
		Statistic attack = Statistic.createFlatStatistic();
		assertEquals(0, attack.getAdjustmentValue());
		attack.removeSource("Dragon");
		assertEquals(0, attack.getAdjustmentValue());
	}
	
	@Test
	void testToString() {
		Statistic attack = Statistic.createFlatStatistic();
		assertEquals("0 =", attack.toString());
		attack.addBonus("Morale", "Morale", 2);
		assertEquals("2 = [Morale: 2]", attack.toString());
	}
	
	@Test
	void testOverrideValue() {
		Statistic attack = Statistic.createStatisticWithBaseValue(10);
		assertEquals(10, attack.getAdjustmentValue());
		attack.addBonus("Base", "Base", 7);
		assertEquals(7, attack.getAdjustmentValue());
		attack.addBonus("Base", "Base", 1);
		assertEquals(1, attack.getAdjustmentValue());
	}
}
