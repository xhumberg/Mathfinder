package characterModel;

public class AbilityScoreGroup {
	
	private AbilityScore strength;
	private AbilityScore dexterity;
	private AbilityScore constitution;
	private AbilityScore intelligence;
	private AbilityScore wisdom;
	private AbilityScore charisma;
	
	public AbilityScoreGroup() {
		strength = AbilityScore.getFlatAbilityScore();
		dexterity = AbilityScore.getFlatAbilityScore();
		constitution = AbilityScore.getFlatAbilityScore();
		intelligence = AbilityScore.getFlatAbilityScore();
		wisdom = AbilityScore.getFlatAbilityScore();
		charisma = AbilityScore.getFlatAbilityScore();
	}

	public AbilityScore getStrength() {
		return strength;
	}

	public void setStrength(AbilityScore strength) {
		this.strength = strength;
	}

	public AbilityScore getDexterity() {
		return dexterity;
	}

	public void setDexterity(AbilityScore dexterity) {
		this.dexterity = dexterity;
	}

	public AbilityScore getConstitution() {
		return constitution;
	}

	public void setConstitution(AbilityScore constitution) {
		this.constitution = constitution;
	}

	public AbilityScore getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(AbilityScore intelligence) {
		this.intelligence = intelligence;
	}

	public AbilityScore getWisdom() {
		return wisdom;
	}

	public void setWisdom(AbilityScore wisdom) {
		this.wisdom = wisdom;
	}

	public AbilityScore getCharisma() {
		return charisma;
	}

	public void setCharisma(AbilityScore charisma) {
		this.charisma = charisma;
	}
	
	public String toString() {
		return "Str " + strength.toString() + ", "
				+ "Dex " + dexterity.toString() + ", "
				+ "Con " + constitution.toString() + ", "
				+ "Int " + intelligence.toString() + ", "
				+ "Wis " + wisdom.toString() + ", "
				+ "Cha " + charisma.toString();
	}
}
