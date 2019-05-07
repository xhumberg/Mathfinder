package characterModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MFCharacterTest {
	@Test
	void getNameTest() {
		MFCharacter Jo = new MFCharacter("Jo");
		assertEquals(Jo.getName(), "Jo");
	}

	@Test
	void level6BarbSheet() {
		
	}
}
