package characterModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CharacterTests {

	@Test
	void basicCharacterCreation() {
		MFCharacter Jo;
		//This test fails if it throws any kind of error
	}
	
	@Test
	void getNameTest() {
		MFCharacter Jo = new MFCharacter("Jo");
		assertEquals(Jo.name, "Jo");
	}

	
	
}
