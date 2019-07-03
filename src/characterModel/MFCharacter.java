package characterModel;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

enum skillType {
	ACROBATICS, APPRAISE, BLUFF, CLIMB, CRAFT_A, CRAFT_B, DIPLOMACY, DISABLE_DEVICE, DISGUISE, ESCAPE_ARTIST, FLY,
	HANDLE_ANIMAL, HEAL, INTIMIDATE, KNOWLEDGE_ARCANA, KNOWLEDGE_DUNGEONEERING, KNOWLEDGE_ENGINEERING,
	KNOWLEDGE_GEOGRAPHY, KNOWLEDGE_HISTORY, KNOWLEDGE_LOCAL, KNOWLEDGE_NATURE, KNOWLEDGE_NOBILITY, KNOWLEDGE_PLANES,
	KNOWLEDGE_RELIGION, LINGUISTICS, PERCEPTION, PERFORM_A, PERFORM_B, PROFESSION_A, PROFESSION_B, RIDE, SENSE_MOTIVE,
	SLEIGHT_OF_HAND, SPELLCRAFT, STEALTH, SURVIVAL, SWIM, USE_MAGIC_DEVICE
}

public class MFCharacter {
	
	BasicInfo basic;
	AbilityScoreGroup stats;
	
	Map<String, Numerical> bonusIndex;
	Statistic init;
	Statistic AC;
	Statistic TouchAC;
	Statistic FFAC;
	HP hp;
	Statistic fort;
	Statistic ref;
	Statistic will;
	// Defensive Abilities
	Statistic landSpeed;
	Statistic swimSpeed;
	Statistic climbSpeed;
	Statistic flySpeed;
	// Melee attacks
	AbilityScoreGroup abilityScores;
	Statistic BAB;
	Statistic CMB;
	Statistic CMD;
	// Feats
	Skill skills[];
	Statistic ACP;
	List<String> languages;
	// SQ
	// Combat Gear
	
	
	// Other Gear
	// Other

	/**
	 * Initializes the character to default values.
	 */
	private MFCharacter() {
		basic = BasicInfo.generateNewBasicInfo();
		
		// Hashtable to access bonuses
		bonusIndex = new HashMap<String, Numerical>();
		
		init = Statistic.createFlatStatistic(); bonusIndex.put("Initiative", init);
		AC = Statistic.createStatisticWithBaseValue(10); bonusIndex.put("AC", AC);
		TouchAC = Statistic.createStatisticWithBaseValue(10); bonusIndex.put("Touch AC", TouchAC);
		FFAC = Statistic.createStatisticWithBaseValue(10); bonusIndex.put("Flat-footed AC", FFAC);
		// HP must be initialized after con.
		fort = Statistic.createFlatStatistic(); bonusIndex.put("Fort", fort);
		ref = Statistic.createFlatStatistic(); bonusIndex.put("Ref", ref);
		will = Statistic.createFlatStatistic(); bonusIndex.put("Will", will);
		landSpeed = Statistic.createFlatStatistic(); bonusIndex.put("Speed", landSpeed);
		swimSpeed = Statistic.createFlatStatistic(); bonusIndex.put("Swim Speed", swimSpeed);
		climbSpeed = Statistic.createFlatStatistic(); bonusIndex.put("Climb Speed", climbSpeed);
		flySpeed = Statistic.createFlatStatistic(); bonusIndex.put("Fly Speed", flySpeed);
		// Melee attacks
		// Ranged attacks
		// Special attacks
		
		abilityScores = new AbilityScoreGroup();
		
		putAbilityScoresInNumericalIndex();
		
		BAB = Statistic.createFlatStatistic(); bonusIndex.put("BAB", BAB);
		CMB = Statistic.createFlatStatistic(); bonusIndex.put("CMB", CMB);
		CMD = Statistic.createStatisticWithBaseValue(10); bonusIndex.put("CMD", CMD);
		ACP = Statistic.createFlatStatistic(); bonusIndex.put("ACP", ACP);
		
		// HP is special
		hp = new HP(abilityScores.getConstitution()); bonusIndex.put("HP", hp.otherBonuses);
		
		// Add stats to skills
		fort.addNumerical(abilityScores.getConstitution());
		ref.addNumerical(abilityScores.getDexterity());
		will.addNumerical(abilityScores.getWisdom());
		AC.addNumerical(abilityScores.getDexterity());
		TouchAC.addNumerical(abilityScores.getDexterity());
		init.addNumerical(abilityScores.getDexterity());
		CMB.addNumerical(abilityScores.getStrength());
		CMB.addNumerical(BAB);
		CMD.addNumerical(abilityScores.getStrength());
		CMD.addNumerical(abilityScores.getStrength());
		CMD.addNumerical(BAB);
		// Feats
		
		// Skills
		skills = new Skill[38];
		
		skills[skillType.ACROBATICS.ordinal()] = new Skill(abilityScores.getDexterity(), ACP); bonusIndex.put("Acrobatics", skills[skillType.ACROBATICS.ordinal()].myBonus);
		skills[skillType.APPRAISE.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Appraise", skills[skillType.APPRAISE.ordinal()].myBonus);
		skills[skillType.BLUFF.ordinal()] = new Skill(abilityScores.getCharisma()); bonusIndex.put("Bluff", skills[skillType.BLUFF.ordinal()].myBonus);
		skills[skillType.CLIMB.ordinal()] = new Skill(abilityScores.getStrength(), ACP); bonusIndex.put("Climb", skills[skillType.CLIMB.ordinal()].myBonus);
		skills[skillType.CRAFT_A.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Craft A", skills[skillType.CRAFT_A.ordinal()].myBonus);
		skills[skillType.CRAFT_B.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Craft B", skills[skillType.CRAFT_B.ordinal()].myBonus);
		skills[skillType.DIPLOMACY.ordinal()] = new Skill(abilityScores.getCharisma()); bonusIndex.put("Diplomacy", skills[skillType.DIPLOMACY.ordinal()].myBonus);
		skills[skillType.DISABLE_DEVICE.ordinal()] = new Skill(abilityScores.getDexterity(), ACP); bonusIndex.put("Disable Device", skills[skillType.DISABLE_DEVICE.ordinal()].myBonus);
		skills[skillType.DISGUISE.ordinal()] = new Skill(abilityScores.getCharisma()); bonusIndex.put("Disguise", skills[skillType.DISGUISE.ordinal()].myBonus);
		skills[skillType.ESCAPE_ARTIST.ordinal()] = new Skill(abilityScores.getDexterity(), ACP); bonusIndex.put("Escape Artist", skills[skillType.ESCAPE_ARTIST.ordinal()].myBonus);
		skills[skillType.FLY.ordinal()] = new Skill(abilityScores.getDexterity(), ACP); bonusIndex.put("Fly", skills[skillType.FLY.ordinal()].myBonus);
		skills[skillType.HANDLE_ANIMAL.ordinal()] = new Skill(abilityScores.getCharisma()); bonusIndex.put("Handle Animal", skills[skillType.HANDLE_ANIMAL.ordinal()].myBonus);
		skills[skillType.HEAL.ordinal()] = new Skill(abilityScores.getWisdom()); bonusIndex.put("Heal", skills[skillType.HEAL.ordinal()].myBonus);
		skills[skillType.INTIMIDATE.ordinal()] = new Skill(abilityScores.getCharisma()); bonusIndex.put("Intimidate", skills[skillType.INTIMIDATE.ordinal()].myBonus);
		skills[skillType.KNOWLEDGE_ARCANA.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Knowledge (Arcana)", skills[skillType.KNOWLEDGE_ARCANA.ordinal()].myBonus);
		skills[skillType.KNOWLEDGE_DUNGEONEERING.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Knowledge (Dungeoneering)", skills[skillType.KNOWLEDGE_DUNGEONEERING.ordinal()].myBonus);
		skills[skillType.KNOWLEDGE_ENGINEERING.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Knowledge (Engineering)", skills[skillType.KNOWLEDGE_ENGINEERING.ordinal()].myBonus);
		skills[skillType.KNOWLEDGE_GEOGRAPHY.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Knowledge (Geography)", skills[skillType.KNOWLEDGE_GEOGRAPHY.ordinal()].myBonus);
		skills[skillType.KNOWLEDGE_HISTORY.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Knowledge (History)", skills[skillType.KNOWLEDGE_HISTORY.ordinal()].myBonus);
		skills[skillType.KNOWLEDGE_LOCAL.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Knowledge (Local)", skills[skillType.KNOWLEDGE_LOCAL.ordinal()].myBonus);
		skills[skillType.KNOWLEDGE_NATURE.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Knowledge (Nature)", skills[skillType.KNOWLEDGE_NATURE.ordinal()].myBonus);
		skills[skillType.KNOWLEDGE_NOBILITY.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Knowledge (Nobility)", skills[skillType.KNOWLEDGE_NOBILITY.ordinal()].myBonus);
		skills[skillType.KNOWLEDGE_PLANES.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Knowledge (Planes)", skills[skillType.KNOWLEDGE_PLANES.ordinal()].myBonus);
		skills[skillType.KNOWLEDGE_RELIGION.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Knowledge (Religion)", skills[skillType.KNOWLEDGE_RELIGION.ordinal()].myBonus);
		skills[skillType.LINGUISTICS.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Linguistics", skills[skillType.LINGUISTICS.ordinal()].myBonus);
		skills[skillType.PERCEPTION.ordinal()] = new Skill(abilityScores.getWisdom()); bonusIndex.put("Perception", skills[skillType.PERCEPTION.ordinal()].myBonus);
		skills[skillType.PERFORM_A.ordinal()] = new Skill(abilityScores.getCharisma()); bonusIndex.put("Perform A", skills[skillType.PERFORM_A.ordinal()].myBonus);
		skills[skillType.PERFORM_B.ordinal()] = new Skill(abilityScores.getCharisma()); bonusIndex.put("Perform B", skills[skillType.PERFORM_B.ordinal()].myBonus);
		skills[skillType.PROFESSION_A.ordinal()] = new Skill(abilityScores.getWisdom()); bonusIndex.put("Profession A", skills[skillType.PROFESSION_A.ordinal()].myBonus);
		skills[skillType.PROFESSION_B.ordinal()] = new Skill(abilityScores.getWisdom()); bonusIndex.put("Profession B", skills[skillType.PROFESSION_B.ordinal()].myBonus);
		skills[skillType.RIDE.ordinal()] = new Skill(abilityScores.getDexterity(), ACP); bonusIndex.put("Ride", skills[skillType.RIDE.ordinal()].myBonus);
		skills[skillType.SENSE_MOTIVE.ordinal()] = new Skill(abilityScores.getWisdom()); bonusIndex.put("Sense Motive", skills[skillType.SENSE_MOTIVE.ordinal()].myBonus);
		skills[skillType.SLEIGHT_OF_HAND.ordinal()] = new Skill(abilityScores.getDexterity(), ACP); bonusIndex.put("Sleight of Hand", skills[skillType.SLEIGHT_OF_HAND.ordinal()].myBonus);
		skills[skillType.SPELLCRAFT.ordinal()] = new Skill(abilityScores.getIntelligence()); bonusIndex.put("Spellcraft", skills[skillType.SPELLCRAFT.ordinal()].myBonus);
		skills[skillType.STEALTH.ordinal()] = new Skill(abilityScores.getDexterity(), ACP); bonusIndex.put("Stealth", skills[skillType.STEALTH.ordinal()].myBonus);
		skills[skillType.SURVIVAL.ordinal()] = new Skill(abilityScores.getWisdom()); bonusIndex.put("Survival", skills[skillType.SURVIVAL.ordinal()].myBonus);
		skills[skillType.SWIM.ordinal()] = new Skill(abilityScores.getStrength(), ACP); bonusIndex.put("Swim", skills[skillType.SWIM.ordinal()].myBonus);
		skills[skillType.USE_MAGIC_DEVICE.ordinal()] = new Skill(abilityScores.getCharisma()); bonusIndex.put("Use Magic Device", skills[skillType.USE_MAGIC_DEVICE.ordinal()].myBonus);

		languages = new LinkedList<String>();
		// SQ
		// Combat Gear
		// Other Gear
		// Other
	}

	private void putAbilityScoresInNumericalIndex() {
		bonusIndex.put("Str", abilityScores.getStrength());
		bonusIndex.put("Dex", abilityScores.getDexterity());
		bonusIndex.put("Con", abilityScores.getConstitution());
		bonusIndex.put("Int", abilityScores.getIntelligence());
		bonusIndex.put("Wis", abilityScores.getWisdom());
		bonusIndex.put("Cha", abilityScores.getCharisma());
	}

	/**
	 * Gets the character's name
	 * 
	 * @return A String containing the character's name
	 */
	public String getName() {
		return basic.getName();
	}

	/**
	 * Initializes the character to default values, but sets the name to the one
	 * provided
	 * 
	 * @param name - A String containing the desired name for the character.
	 */
	public MFCharacter(String name) {
		this();
		basic.setName(name);
	}
	
	public void giveRanks(String skillName, int ranks)
	{
		skillType st = skillFromString(skillName);
		skills[st.ordinal()].addRanks(ranks);
	}
	
	public int getRanks(String skillName)
	{
		skillType st = skillFromString(skillName);
		return skills[st.ordinal()].getRanks();
	}
	
	public int getSkillBonus(String skillName)
	{
		skillType st = skillFromString(skillName);
		return skills[st.ordinal()].getValue();
	}
	
	public void setClassSkill(String skillName)
	{
		skillType st = skillFromString(skillName);
		skills[st.ordinal()].setClassSkill();
	}
	
	public void setACP(int acp)
	{
		ACP.addPenalty("Base", acp);
		System.out.println(ACP.getAdjustmentValue());
	}
	
	private skillType skillFromString(String skillName)
	{
		return skillType.valueOf(skillName.toUpperCase().replace(' ', '_'));
	}

	public Numerical getNumericalFromString(String bonusName)
	{
		return bonusIndex.get(bonusName);
	}
	
	/**
	 * Generates and returns a character's NPC stat block.
	 * 
	 * @return - the stat block generated by the character's stats
	 */
	public String getStatBlock() {
		StringBuilder sheet = new StringBuilder();
		sheet.append(basic);
		
		sheet.append("\nInit ");
		sheet.append(init);
		sheet.append("; Senses: Perception ");
		sheet.append(skills[skillType.PERCEPTION.ordinal()].getValue());
		sheet.append("\n----------\nDefense\n----------\nAC ");
		sheet.append(AC);
		sheet.append(", touch ");
		sheet.append(TouchAC);
		sheet.append(", flat-footed ");
		sheet.append(FFAC);
		// Specify types of armor bonus
		sheet.append("\nhp ");
		sheet.append(getEffectiveHP() + "/" + getMaxHP());
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
		sheet.append(landSpeed);
		sheet.append(" ft.\nMelee ");
		// Attacks
		// Ranged attacks
		// Special attacks
		sheet.append("\n----------\nStatistics\n----------\nStr ");
		sheet.append(abilityScores);
		sheet.append("\nBase Atk ");
		sheet.append(BAB);
		sheet.append("; CMB ");
		sheet.append(CMB);
		sheet.append("; CMD ");
		sheet.append(CMD);
		// Feats
		// Skills
		sheet.append("\nLanguages: ");
		sheet.append(languages.toString().replace("[", "").replace("]", ""));
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
			 * Ref - Will - SQ - SAttack - SDefense
			 */

			hp.addHD(DiceType.valueOf(getNodeText(root, "HD")));

			Node skills = getNode(root, "Skills");

			// Get number of skills per level.

			// Get class skills
			NodeList skillList = getNodeList(skills, "classSkill");

			for (int i = 0; i < skillList.getLength(); i++) {
//				setClassSkill(skillList.item(i).getTextContent());
			}

			NodeList proficienciesList = getNodeList(root, "Proficient");

			for (int i = 0; i < proficienciesList.getLength(); i++) {
				String proficiency = proficienciesList.item(i).getTextContent();
				proficiency = proficiency.replace(" ", "_").toUpperCase();
//				addProficiency(proficiency);
			}

			this.BAB.addBonus(className, className, Integer.parseInt(getNodeText(root, "BAB")));
			this.fort.addBonus(className, className, Integer.parseInt(getNodeText(root, "Fort")));
			this.ref.addBonus(className, className, Integer.parseInt(getNodeText(root, "Ref")));
			this.will.addBonus(className, className, Integer.parseInt(getNodeText(root, "Will")));

			NodeList SQList = getNodeList(root, "SQ");
			for (int i = 0; i < SQList.getLength(); i++) {
				Node SQ = SQList.item(i);
				// SQ have a name
				// SQ have a description
				// SQ CAN have bonuses and/or effects
			}

			NodeList SAttackList = getNodeList(root, "SAttack");
			for (int i = 0; i < SAttackList.getLength(); i++) {
				Node SAttack = SAttackList.item(i);
				// SQ have a name
				// SQ have a description
				// SQ CAN have bonuses and/or effects
			}

			NodeList SDefenseList = getNodeList(root, "SDefense");
			for (int i = 0; i < SDefenseList.getLength(); i++) {
				Node SDefense = SDefenseList.item(i);
				// SQ have a name
				// SQ have a description
				// SQ CAN have bonuses and/or effects
			}

			basic.setLevel(basic.getLevel()+1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private NodeList getNodeList(Node parent, String tagName) {
		Element element = (Element) parent;

		return element.getElementsByTagName(tagName);
	}

	private Node getNode(Node parent, String tagName) {
		return getNodeList(parent, tagName).item(0);
	}

	private String getNodeText(Node parent, String tagName) {
		return getNode(parent, tagName).getTextContent();
	}
	// XML Parsers

	public void addHD(DiceType type) {
		hp.addHD(type);
	}

	public Integer getMaxHP() {
		return hp.getMaxHP();
	}

	public void favoredClassHP(int amount) {
		hp.favoredClassBonus = amount;
	}

	public void damage(int amount) {
		hp.damage(amount);
	}

	public void heal(int amount) {
		hp.heal(amount);
	}

	public int getCurrentHP() {
		return hp.getCurrentHP();
	}

	public int getEffectiveHP() {
		return hp.getEffectiveHP();
	}

	public void damage(int amount, boolean nonlethal) {
		hp.damage(amount, nonlethal);
	}

	public void giveLanguage(String language) {
		if (language != null)
			languages.add(language);
	}

	public void setStrength(int base) {
		abilityScores.setStrength(AbilityScore.getAbilityScoreWithBase(base));
	}
	
	public void setDexterity(int base) {
		abilityScores.setDexterity(AbilityScore.getAbilityScoreWithBase(base));
	}
	
	public void setConstitution(int base) {
		abilityScores.setConstitution(AbilityScore.getAbilityScoreWithBase(base));
	}
	
	public void setIntelligence(int base) {
		abilityScores.setIntelligence(AbilityScore.getAbilityScoreWithBase(base));
	}
	
	public void setWisdom(int base) {
		abilityScores.setWisdom(AbilityScore.getAbilityScoreWithBase(base));
	}
	
	public void setCharisma(int base) {
		abilityScores.setCharisma(AbilityScore.getAbilityScoreWithBase(base));
	}

}
