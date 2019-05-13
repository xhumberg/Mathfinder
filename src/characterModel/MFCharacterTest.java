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
	void level1BarbSheet() {
		MFCharacter Raider = new MFCharacter("Barbarian Raider");

		// Apply race
//		Raider.loadRace("Human.race");

		// Set abilities
		Raider.str.setBase(15);
		Raider.dex.setBase(12);
		Raider.con.setBase(14);
		Raider.pfint.setBase(10);
		Raider.wis.setBase(13);
		Raider.cha.setBase(8);

		// Apply class
		Raider.loadClass("Barbarian1.class");

		// Give feats
//		Raider.loadFeat("PowerAttack.feat");
//		Raider.loadFeat("WeaponFocus.feat");
//		Raider.specifyFeat("Weapon Focus", "Battleaxe");

		// Give skill ranks
		Raider.giveRanks("Intimidate", 1);
		Raider.giveRanks("Perception", 1);
		Raider.giveRanks("Profession A", 1);
		Raider.giveRanks("Survival", 1);

		// Set languages
//		Raider.giveLanguage("Common");
//		Raider.giveLanguage("+1 Additional");

		// Give gear
//		Raider.loadGear("Chainmail.armor");
//		Raider.loadGear("HeavyWooden.shield");
//		Raider.loadGear("Battleaxe.weapon");
//		Raider.loadGear("CureLightWounds.potion");

		// RAGE
//		Raider.activate("Rage");

		String block = Raider.getStatBlock();
		System.out.println(block);
	}

	@Test
	void testSkillRanks() {
		MFCharacter Skillbob = new MFCharacter("Skillbob");

		assertEquals(0, Skillbob.getRanks("Perform A"));

		Skillbob.giveRanks("Climb", 2);
		assertEquals(2, Skillbob.getRanks("Climb"));

		Skillbob.giveRanks("Climb", 1);
		assertEquals(3, Skillbob.getRanks("Climb"));
	}

	@Test
	void heavySkillsTest() {
		MFCharacter skillbob = new MFCharacter("Skillbob");
		
		skillbob.str.setBase(9);
		skillbob.dex.setBase(12);
		skillbob.con.setBase(10);
		skillbob.pfint.setBase(14);
		skillbob.wis.setBase(18);
		skillbob.cha.setBase(20);
		
//		skillbob.setClassSkill("Acrobatics");
//		skillbob.setClassSkill("Perform A");
//		skillbob.setClassSkill("Diplomacy");
//		skillbob.setClassSkill("Climb");
		
//		skillbob.setACP(-3);
		
		skillbob.giveRanks("Acrobatics", 4);
		skillbob.giveRanks("Perception", 10);
		skillbob.giveRanks("Swim", 2);
		
//		assertEquals(1+3+4-3, skillbob.getSkillBonus("Acrobatics"));
//		assertEquals(4+10, skillbob.getSkillBonus("Perception"));
//		assertEquals(-1+2-3, skillbob.getSkillBonus("Swim"));
//		assertEquals(1-3, skillbob.getSkillBonus("Disable Device"));
	}
	
	@Test
	void maxHPTest() {
		MFCharacter dragonDisciple = new MFCharacter("DD");
		
		dragonDisciple.con.setBase(8);
		
		dragonDisciple.addHD(DiceType.D6);
		
		assertEquals(5, dragonDisciple.getMaxHP());
		
		dragonDisciple.favoredClassHP(1);
		
		assertEquals(6, dragonDisciple.getMaxHP());
		
		dragonDisciple.addHD(DiceType.D6);
		dragonDisciple.addHD(DiceType.D6);
		dragonDisciple.addHD(DiceType.D6);
		dragonDisciple.addHD(DiceType.D12);
		dragonDisciple.addHD(DiceType.D12);
		dragonDisciple.favoredClassHP(4);
		dragonDisciple.con.setBonus("Rage", "Morale", 4);
		
		assertEquals(42, dragonDisciple.getMaxHP());
	}
	
	void lethalDamagetest() {
		MFCharacter derek = new MFCharacter("Derek");
		
		derek.con.setBase(16);
		
		for (int i = 0; i < 5; i++)
			derek.addHD(DiceType.D10);
		
		derek.favoredClassHP(5);
		
		assertEquals(54, derek.getMaxHP());
		assertEquals(54, derek.getCurrentHP());
		assertEquals(54, derek.getEffectiveHP());
		
		derek.damage(17);
		
		assertEquals(54, derek.getMaxHP());
		assertEquals(37, derek.getCurrentHP());
		assertEquals(37, derek.getEffectiveHP());
		
		derek.heal(9);

		assertEquals(54, derek.getMaxHP());
		assertEquals(46, derek.getCurrentHP());
		assertEquals(46, derek.getEffectiveHP());
		
		derek.damage(4);
	
		assertEquals(42, derek.getCurrentHP());
		assertEquals(42, derek.getEffectiveHP());
	}
	
	@Test
	void nonlethalDamagetest() {
		MFCharacter derek = new MFCharacter("Derek");
		
		derek.con.setBase(16);
		
		for (int i = 0; i < 5; i++)
			derek.addHD(DiceType.D10);
		
		derek.favoredClassHP(5);
		
		assertEquals(54, derek.getMaxHP());
		assertEquals(54, derek.getCurrentHP());
		assertEquals(54, derek.getEffectiveHP());
		
		derek.damage(17, true);
		
		assertEquals(54, derek.getMaxHP());
		assertEquals(54, derek.getCurrentHP());
		assertEquals(37, derek.getEffectiveHP());
		
		derek.heal(9);

		assertEquals(54, derek.getMaxHP());
		assertEquals(54, derek.getCurrentHP());
		assertEquals(46, derek.getEffectiveHP());
		
		derek.damage(4, true);
	
		assertEquals(54, derek.getCurrentHP());
		assertEquals(42, derek.getEffectiveHP());
	}
	
	@Test
	void doublelDamagetest() {
		MFCharacter derek = new MFCharacter("Derek");
		
		derek.con.setBase(16);
		
		for (int i = 0; i < 5; i++)
			derek.addHD(DiceType.D10);
		
		derek.favoredClassHP(5);
		
		assertEquals(54, derek.getMaxHP());
		assertEquals(54, derek.getCurrentHP());
		assertEquals(54, derek.getEffectiveHP());
		
		derek.damage(17);
		
		assertEquals(54, derek.getMaxHP());
		assertEquals(37, derek.getCurrentHP());
		assertEquals(37, derek.getEffectiveHP());
		
		derek.heal(9);

		assertEquals(54, derek.getMaxHP());
		assertEquals(46, derek.getCurrentHP());
		assertEquals(46, derek.getEffectiveHP());
		
		derek.damage(4, true);
	
		assertEquals(46, derek.getCurrentHP());
		assertEquals(42, derek.getEffectiveHP());
		
		derek.heal(2);
		
		assertEquals(48, derek.getCurrentHP());
		assertEquals(46, derek.getEffectiveHP());
	}
}
