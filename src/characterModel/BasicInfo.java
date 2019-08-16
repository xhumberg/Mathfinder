package characterModel;

public class BasicInfo {
	private String name;
	private String race;
	private String characterClass;
	private int level;
	private String alignment;
	private String size;
	private String creatureType;

	private BasicInfo() {
		name = "Dave";
		race = "Human";
		characterClass = "Barbarian";
		level = 0;
		alignment = "N";
		size = "Medium";
		creatureType = "Humanoid (Human)";
	}
	
	public static BasicInfo generateNewBasicInfo() {
		return new BasicInfo();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getCharacterClass() {
		return characterClass;
	}

	public void setCharacterClass(String characterClass) {
		this.characterClass = characterClass;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCreatureType() {
		return creatureType;
	}

	public void setCreatureType(String creatureType) {
		this.creatureType = creatureType;
	}
	
	public String toString() {
		StringBuilder stringRepresentation = new StringBuilder();
		stringRepresentation.append(name).append("\n")
			.append(race).append(" ")
			.append(characterClass).append(" ")
			.append(level).append("\n")
			.append(alignment).append(" ")
			.append(size).append(" ")
			.append(creatureType);
		return stringRepresentation.toString();
	}
}
