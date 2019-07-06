package characterModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MFCharacterTest {
	
	@Test
	void testGetName() {
		MFCharacter wizardBoi = new MFCharacter("Wizard Boi");
		assertEquals("Wizard Boi", wizardBoi.getName());
	}
	
	@Test
	void testBlankStatBlock() {
		MFCharacter dave = new MFCharacter("Dave");
		String generatedStatBlock = dave.getStatBlock();
		String expectedStatBlock = getExpectedBlankStatBlock();
		assertEquals(expectedStatBlock, generatedStatBlock);
	}

	private String getExpectedBlankStatBlock() {
		return "Dave\n" + 
				"Human Barbarian 0\n" + 
				"N Medium Humanoid (Human)\n" + 
				"Init 0; Senses: Perception 0\n" + 
				"----------\n" + 
				"Defense\n" + 
				"----------\n" + 
				"AC 10, touch 10, flat-footed 10\n" + 
				"hp 0/0\n" + 
				"Fort 0, Ref 0, Will 0\n" + 
				"Defensive Abilities \n" + 
				"----------\n" + 
				"Offense\n" + 
				"----------\n" + 
				"Speed 0 ft.\n" + 
				"Melee \n" + 
				"----------\n" + 
				"Statistics\n" + 
				"----------\n" + 
				"Str Str 10(0), Dex 10(0), Con 10(0), Int 10(0), Wis 10(0), Cha 10(0)\n" + 
				"Base Atk 0; CMB 0; CMD 10\n" + 
				"Languages: ";
	}

}
