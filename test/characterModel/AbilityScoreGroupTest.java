package characterModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbilityScoreGroupTest {
	
	@Test
	public void initializesToFlatScoreTest() {
		AbilityScoreGroup scores = new AbilityScoreGroup();
		assertEquals(10, scores.getStrength().getScore());
		assertEquals(10, scores.getDexterity().getScore());
		assertEquals(10, scores.getConstitution().getScore());
		assertEquals(10, scores.getIntelligence().getScore());
		assertEquals(10, scores.getWisdom().getScore());
		assertEquals(10, scores.getCharisma().getScore());
	}
	
	@Test
	public void referencesUpdateCorrectlyTest() {
		AbilityScoreGroup scores = new AbilityScoreGroup();
		
		AbilityScore strength = AbilityScore.getAbilityScoreWithBase(12);
		AbilityScore dexterity = AbilityScore.getAbilityScoreWithBase(14);
		AbilityScore constitution = AbilityScore.getAbilityScoreWithBase(8);
		AbilityScore intelligence = AbilityScore.getAbilityScoreWithBase(13);
		AbilityScore wisdom = AbilityScore.getAbilityScoreWithBase(19);
		AbilityScore charisma = AbilityScore.getAbilityScoreWithBase(120);
		
		scores.setStrength(strength);
		scores.setDexterity(dexterity);
		scores.setConstitution(constitution);
		scores.setIntelligence(intelligence);
		scores.setWisdom(wisdom);
		scores.setCharisma(charisma);
		
		assertEquals(12, scores.getStrength().getScore());
		assertEquals(14, scores.getDexterity().getScore());
		assertEquals(8, scores.getConstitution().getScore());
		assertEquals(13, scores.getIntelligence().getScore());
		assertEquals(19, scores.getWisdom().getScore());
		assertEquals(120, scores.getCharisma().getScore());
	}

}
