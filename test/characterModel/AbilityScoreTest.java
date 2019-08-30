package characterModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbilityScoreTest {

	@Test
	public void testFlatAbilityScoreIs10() {
		AbilityScore score = AbilityScore.getFlatAbilityScore();
		assertEquals(10, score.getScore());
	}
	
	@Test
	public void testBaseAbiltyScoreUpdatesProperly() {
		AbilityScore score = AbilityScore.getAbilityScoreWithBase(14);
		assertEquals(14, score.getScore());
	}
	
	@Test
	public void testAbilityScoreAdjustmentValueIsMod() {
		Numerical strength = AbilityScore.getAbilityScoreWithBase(7);
		Numerical dexterity = AbilityScore.getAbilityScoreWithBase(17);
		Numerical constitution = AbilityScore.getAbilityScoreWithBase(12);
		Numerical intelligence = AbilityScore.getAbilityScoreWithBase(16);
		Numerical wisdom = AbilityScore.getAbilityScoreWithBase(8);
		Numerical charisma = AbilityScore.getAbilityScoreWithBase(11);
		
		assertEquals(-2, strength.getAdjustmentValue());
		assertEquals(3, dexterity.getAdjustmentValue());
		assertEquals(1, constitution.getAdjustmentValue());
		assertEquals(3, intelligence.getAdjustmentValue());
		assertEquals(-1, wisdom.getAdjustmentValue());
		assertEquals(0, charisma.getAdjustmentValue());
	}

}
