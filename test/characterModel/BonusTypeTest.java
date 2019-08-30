package characterModel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BonusTypeTest {

	@Test
	public void initTest() {
		@SuppressWarnings("unused")
		BonusType bonus = new BonusType("Morale", false);
	}
	
	@Test
	public void stackingDoesStackTest() {
		BonusType dodge = new BonusType("Dodge", true);
		dodge.addAdjustment("Fighting Defensively", 3);
		dodge.addAdjustment("Crane Style", 3);
		dodge.addAdjustment("Dodge", 1);
		
		assertEquals(7, dodge.getAdjustmentAmount());
	}

	@Test
	public void noStackingDoesntStackTest() {
		BonusType enhancement = new BonusType("Enhancement", false);
		enhancement.addAdjustment("Belt of Giant's Strength", 6);
		enhancement.addAdjustment("Bull's Strength", 4);
		
		assertEquals(6, enhancement.getAdjustmentAmount());
	}
	
	@Test
	public void sameSourceOverwritesTest() {
		BonusType enhancement = new BonusType("Enhancement", false);
		enhancement.addAdjustment("Belt of Giant's Strength", 4);
		enhancement.addAdjustment("Belt of Giant's Strength", 6);
		
		assertEquals(6, enhancement.getAdjustmentAmount());

		enhancement.addAdjustment("Belt of Giant's Strength", 4);
		
		assertEquals(4, enhancement.getAdjustmentAmount());
	}
	
	@Test
	public void removeSourceTest() {
		BonusType dodge = new BonusType("Dodge", true);
		dodge.addAdjustment("Fighting Defensively", 3);
		dodge.addAdjustment("Crane Style", 3);
		
		assertEquals(6, dodge.getAdjustmentAmount());
		
		dodge.removeSource("Fighting Defensively");
		
		assertEquals(3, dodge.getAdjustmentAmount());
		
		dodge.removeSource("This one doesn't exist");
		
		assertEquals(3, dodge.getAdjustmentAmount());
	}
	
	@Test
	public void testToString() {
		BonusType dodge = new BonusType("Dodge", true);
		dodge.addAdjustment("Fighting Defensively", 3);
		dodge.addAdjustment("Crane Style", 2);
		
		assertEquals("Dodge: 5", dodge.toString());
		assertEquals("Dodge: 5 [Fighting Defensively: 3, Crane Style: 2]", dodge.toVerboseString());
	}
}
