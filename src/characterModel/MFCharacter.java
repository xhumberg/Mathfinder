package characterModel;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

enum skillType
{
	ACROBATICS,
	APPRAISE,
	BLUFF,
	CLIMB,
	CRAFT_A,
	CRAFT_B,
	DIPLOMACY,
	DISABLE_DEVICE,
	DISGUISE,
	ESCAPE_ARTIST,
	FLY,
	HANDLE_ANIMAL,
	HEAL,
	INTIMIDATE,
	KNOWLEDGE_ARCANA,
	KNOWLEDGE_DUNGEONEERING,
	KNOWLEDGE_ENGINEERING,
	KNOWLEDGE_GEOGRAPHY,
	KNOWLEDGE_HISTORY,
	KNOWLEDGE_LOCAL,
	KNOWLEDGE_NATURE,
	KNOWLEDGE_NOBILITY,
	KNOWLEDGE_PLANES,
	KNOWLEDGE_RELIGION,
	LINGUISTICS,
	PERCEPTION,
	PERFORM_A,
	PERFORM_B,
	PROFESSION_A,
	PROFESSION_B,
	RIDE,
	SENSE_MOTIVE,
	SLEIGHT_OF_HAND,
	SPELLCRAFT,
	STEALTH,
	SURVIVAL,
	SWIM,
	USE_MAGIC_DEVICE
}

public class MFCharacter {
	String name;
	String race;
	String pfclass;
	int level;
	String alignment;
	String size;
	String type;
	Bonus init;
	Bonus AC;
	Bonus TouchAC;
	Bonus FFAC;
	// HP
	Bonus fort;
	Bonus ref;
	Bonus will;
	// Defensive Abilities
	Bonus speed;
	// Melee attacks
	// Ranged attacks
	// Special attacks
	Bonus str;
	Bonus dex;
	Bonus con;
	Bonus pfint;
	Bonus wis;
	Bonus cha;
	int BAB;
	Bonus CMB;
	Bonus CMD;
	// Feats
	Skill skills[];
	Bonus ACP;
	// Languages
	// SQ
	// Combat Gear
	// Other Gear
	// Other

	/**
	 * Initializes the character to default values.
	 */
	private MFCharacter() {
		name = "Dave";
		race = "Human";
		pfclass = "Barbarian";
		level = 0;
		alignment = "N";
		size = "Medium";
		type = "Humanoid (Human)";
		init = new Bonus();
		AC = new Bonus(10);
		TouchAC = new Bonus(10);
		FFAC = new Bonus(10);
		// HP
		fort = new Bonus();
		ref = new Bonus();
		will = new Bonus();
		speed = new Bonus();
		// Melee attacks
		// Ranged attacks
		// Special attacks
		str = new Bonus();
		dex = new Bonus();
		con = new Bonus();
		pfint = new Bonus();
		wis = new Bonus();
		cha = new Bonus();
		BAB = 0;
		CMB = new Bonus();
		CMD = new Bonus(10);
		// Feats
		skills = new Skill[38];
		ACP = new Bonus();
		//ACP.setBase(num);
		
		skills[skillType.ACROBATICS.ordinal()] = new Skill(dex, ACP);
		skills[skillType.APPRAISE.ordinal()] = new Skill(pfint);
		skills[skillType.BLUFF.ordinal()] = new Skill(cha);
		skills[skillType.CLIMB.ordinal()] = new Skill(str, ACP);
		skills[skillType.CRAFT_A.ordinal()] = new Skill(pfint);
		skills[skillType.CRAFT_B.ordinal()] = new Skill(pfint);
		skills[skillType.DIPLOMACY.ordinal()] = new Skill(cha);
		skills[skillType.DISABLE_DEVICE.ordinal()] = new Skill(dex, ACP);
		skills[skillType.DISGUISE.ordinal()] = new Skill(cha);
		skills[skillType.ESCAPE_ARTIST.ordinal()] = new Skill(dex, ACP);
		skills[skillType.FLY.ordinal()] = new Skill(dex, ACP);
		skills[skillType.HANDLE_ANIMAL.ordinal()] = new Skill(cha);
		skills[skillType.HEAL.ordinal()] = new Skill(wis);
		skills[skillType.INTIMIDATE.ordinal()] = new Skill(cha);
		skills[skillType.KNOWLEDGE_ARCANA.ordinal()] = new Skill(pfint);
		skills[skillType.KNOWLEDGE_DUNGEONEERING.ordinal()] = new Skill(pfint);
		skills[skillType.KNOWLEDGE_ENGINEERING.ordinal()] = new Skill(pfint);
		skills[skillType.KNOWLEDGE_GEOGRAPHY.ordinal()] = new Skill(pfint);
		skills[skillType.KNOWLEDGE_HISTORY.ordinal()] = new Skill(pfint);
		skills[skillType.KNOWLEDGE_LOCAL.ordinal()] = new Skill(pfint);
		skills[skillType.KNOWLEDGE_NATURE.ordinal()] = new Skill(pfint);
		skills[skillType.KNOWLEDGE_NOBILITY.ordinal()] = new Skill(pfint);
		skills[skillType.KNOWLEDGE_PLANES.ordinal()] = new Skill(pfint);
		skills[skillType.KNOWLEDGE_RELIGION.ordinal()] = new Skill(pfint);
		skills[skillType.LINGUISTICS.ordinal()] = new Skill(pfint);
		skills[skillType.PERCEPTION.ordinal()] = new Skill(wis);
		skills[skillType.PERFORM_A.ordinal()] = new Skill(cha);
		skills[skillType.PERFORM_B.ordinal()] = new Skill(cha);
		skills[skillType.PROFESSION_A.ordinal()] = new Skill(wis);
		skills[skillType.PROFESSION_B.ordinal()] = new Skill(wis);
		skills[skillType.RIDE.ordinal()] = new Skill(dex, ACP);
		skills[skillType.SENSE_MOTIVE.ordinal()] = new Skill(wis);
		skills[skillType.SLEIGHT_OF_HAND.ordinal()] = new Skill(dex, ACP);
		skills[skillType.SPELLCRAFT.ordinal()] = new Skill(pfint);
		skills[skillType.STEALTH.ordinal()] = new Skill(dex, ACP);
		skills[skillType.SURVIVAL.ordinal()] = new Skill(wis);
		skills[skillType.SWIM.ordinal()] = new Skill(str, ACP);
		skills[skillType.USE_MAGIC_DEVICE.ordinal()] = new Skill(cha);
			
		
		// Languages
		// SQ
		// Combat Gear
		// Other Gear
		// Other
	}

	/**
	 * Gets the character's name
	 * 
	 * @return A String containing the character's name
	 */
	public String getName() {
		return new String(name);
	}

	/**
	 * Initializes the character to default values, but sets the name to the one
	 * provided
	 * 
	 * @param name - A String containing the desired name for the character.
	 */
	public MFCharacter(String name) {
		this();
		this.name = name;
	}
	
	public void giveRanks(String skillName, int ranks)
	{
		skillType st = skillType.valueOf(skillName.toUpperCase().replace(' ', '_'));
		skills[st.ordinal()].addRanks(ranks);
	}
	
	public int getRanks(String skillName)
	{
		skillType st = skillType.valueOf(skillName.toUpperCase().replace(' ', '_'));
		return skills[st.ordinal()].getRanks();
	}

	/**
	 * Generates and returns a character's NPC stat block.
	 * 
	 * @return - the stat block generated by the character's stats
	 */
	public String getStatBlock() {
		StringBuilder sheet = new StringBuilder();

		sheet.append(name);
		sheet.append("\n");
		sheet.append(race);
		sheet.append(" ");
		sheet.append(pfclass);
		sheet.append(" ");
		sheet.append(level);
		sheet.append("\n");
		sheet.append(alignment);
		sheet.append(" ");
		sheet.append(size);
		sheet.append(" ");
		sheet.append(type);
		sheet.append("\nInit ");
		sheet.append(init);
		sheet.append("; Senses: Perception ");
		// Perception
		sheet.append("\n----------\nDefense\n----------\nAC ");
		sheet.append(AC);
		sheet.append(", touch ");
		sheet.append(TouchAC);
		sheet.append(", flat-footed ");
		sheet.append(FFAC);
		// Specify types of armor bonus
		sheet.append("\nhp ");
		// HP
		// Hit dice specs
		sheet.append("\nFort ");
		sheet.append(fort);
		sheet.append(", Ref ");
		sheet.append(ref);
		sheet.append(", Will ");
		sheet.append(will);
		sheet.append("\nDefensive Abilities ");
		// Defensive Abilities
		sheet.append("\n----------\nOffense\n----------\nSpeed ");
		sheet.append(speed);
		sheet.append(" ft.\nMelee ");
		// Attacks
		// Ranged attacks
		// Special attacks
		sheet.append("\n----------\nStatistics\n----------\nStr ");
		sheet.append(str);
		sheet.append(", Dex ");
		sheet.append(dex);
		sheet.append(", Con ");
		sheet.append(con);
		sheet.append(", Int ");
		sheet.append(pfint);
		sheet.append(", Wis ");
		sheet.append(wis);
		sheet.append(", Cha ");
		sheet.append(cha);
		sheet.append("\nBase Atk ");
		sheet.append(BAB);
		sheet.append("; CMB ");
		sheet.append(CMB);
		sheet.append("; CMD ");
		sheet.append(CMD);
		// Feats
		// Skills
		// Languages
		// SQ
		// Combat Gear
		// Other Gear
		// Other

		return sheet.toString();
	}

	public void loadClass(String filename) {
		// Code taken and modified from
		// https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
		try {
			File XMLFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document classXML = dBuilder.parse(XMLFile);
			classXML.getDocumentElement().normalize();
			Node root = classXML.getDocumentElement();

			if (root.getNodeName() != "class")
				throw new Exception("File isn't a class");

			String className = ((Element) root).getAttribute("name");

			/*
			 * Base level allows parsing of: - HD - Skills - Proficiencies - BAB - Fort -
			 * Ref - Will - SQ - SAttack - And more later
			 */

			// Add specified hit dice
			// Add classSkills first
			// add Skills per level here. This will allow us to put ranks in.
			// read all proficiencies.
			this.BAB = Integer.parseInt(getNodeText(root, "BAB"));
			this.fort.applyBonus(className, className, Integer.parseInt(getNodeText(root, "Fort")));
			this.ref.applyBonus(className, className, Integer.parseInt(getNodeText(root, "Ref")));
			this.will.applyBonus(className, className, Integer.parseInt(getNodeText(root, "Will")));
			// Add special qualities
			// Apply special quality bonuses
			// Add special quality effects
			// Add SAttacks
			// Add SAttack bonuses
			// Add SAttack effects
			// Add SDefense
			// Add SDefense bonuses
			// Add SDefense effects

			level++;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getNodeText(Node parent, String tagName) {
		Element element = (Element) parent;

		return element.getElementsByTagName(tagName).item(0).getTextContent();
	}
	// XML Parsers

}
