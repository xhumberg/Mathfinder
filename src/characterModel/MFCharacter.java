package characterModel;

public class MFCharacter {
	private String name;
	private String race;
	private String pfclass;
	private int level;
	private String alignment;
	private String size;
	private String type;
	private Bonus AC;
	private Bonus TouchAC;
	private Bonus FFAC;
	//HP
	private Bonus fort;
	private Bonus ref;
	private Bonus will;
	private Bonus speed;
	//Melee attacks
	//Ranged attacks
	//Special attacks
	private Bonus str;
	private Bonus dex;
	private Bonus con;
	private Bonus pfint;
	private Bonus wis;
	private Bonus cha;
	private int BAB;
	private Bonus CMB;
	private Bonus CMD;
	//Feats
	//Skills
	//Languages
	//SQ
	//Combat Gear
	//Other Geat
	//Other
	
	private MFCharacter() {
		name = "Dave";
		race = "Human";
		pfclass = "Barbarian";
		level = 1;
		alignment = "N";
		size = "Medium";
		type = "Humanoid (Human)";
		AC = new Bonus(10);
		TouchAC = new Bonus(10);
		FFAC = new Bonus(10);
		//TODO: Continue test.
	}
	
	public String getName() {
		return new String(name);
	}
	
	public MFCharacter(String name) {
		this();
		this.name = name;
	}
}
