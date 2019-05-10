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
		
		//Apply race
//		Raider.loadRace("Human.race");
		
		//Set abilities
		Raider.str.setBase(15);
		Raider.dex.setBase(12);
		Raider.con.setBase(14);
		Raider.pfint.setBase(10);
		Raider.wis.setBase(13);
		Raider.cha.setBase(8);
		
		//Apply class
//		Raider.loadClass("Barbarian1.class");
		
		//Give feats
//		Raider.loadFeat("PowerAttack.feat");
//		Raider.loadFeat("WeaponFocus.feat");
//		Raider.specifyFeat("WeaponFocus", "Battleaxe");
		
		//Give skill ranks
//		Raider.giveRanks("Intimidate", 1);
//		Raider.giveRanks("Perception", 1);
//		Raider.giveRanks("Profession (Sailor)", 1);
//		Raider.giveRanks("Survival", 1);
//		Raider.giveRanks("Swim", 1);
		
		//Set languages
//		Raider.giveLanguage("Common");
//		Raider.giveLanguage("+1 Additional");
		
		//Give gear
//		Raider.loadGear("Chainmail.armor");
//		Raider.loadGear("HeavyWooden.shield");
//		Raider.loadGear("Battleaxe.weapon");
//		Raider.loadGear("CureLightWounds.potion");
		
		//RAGE
//		Raider.activate("Rage");
		
		String block = Raider.getStatBlock();
		System.out.println(block);
	}
}
