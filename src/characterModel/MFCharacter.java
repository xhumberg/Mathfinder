package characterModel;

public class MFCharacter {
	private String name;
	private String race;
	private String pfclass;
	private int level;
	private String alignment;
	private String size;
	private String type;
	private Bonus init;
	private Bonus AC;
	private Bonus TouchAC;
	private Bonus FFAC;
	//HP
	private Bonus fort;
	private Bonus ref;
	private Bonus will;
	//Defensive Abilities
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
	//Other Gear
	//Other
	
	private MFCharacter() {
		name = "Dave";
		race = "Human";
		pfclass = "Barbarian";
		level = 1;
		alignment = "N";
		size = "Medium";
		type = "Humanoid (Human)";
		init = new Bonus();
		AC = new Bonus(10);
		TouchAC = new Bonus(10);
		FFAC = new Bonus(10);
		//HP
		fort = new Bonus();
		ref = new Bonus();
		will = new Bonus();
		speed = new Bonus();
		//Melee attacks
		//Ranged attacks
		//Special attacks
		str=new Bonus();
		dex = new Bonus();
		con = new Bonus();
		pfint = new Bonus();
		wis = new Bonus();
		cha = new Bonus();
		BAB = 1;
		CMB = new Bonus();
		CMD = new Bonus();
		//Feats
		//Skills
		//Languages
		//SQ
		//Combat Gear
		//Other Gear
		//Other
	}
	
	public String getName() {
		return new String(name);
	}
	
	public MFCharacter(String name) {
		this();
		this.name = name;
	}
	
	public String getCharacterSheet() {
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
		//Perception
		sheet.append("\n----------\nDefense\n----------\nAC ");
		sheet.append(AC);
		sheet.append(", touch ");
		sheet.append(TouchAC);
		sheet.append(", flat-footed ");
		sheet.append(FFAC);
		//Specify types of armor bonus
		sheet.append("\nhp ");
		//HP
		//Hit dice specs
		sheet.append("\nFort ");
		sheet.append(fort);
		sheet.append(", Ref ");
		sheet.append(ref);
		sheet.append(", Will ");
		sheet.append(will);
		sheet.append("\nDefensive Abilities ");
		//Defensive Abilities
		sheet.append("\n----------\nOffense\n----------\nSpeed ");
		sheet.append(speed);
		sheet.append(" ft.\nMelee ");
		//Attacks
		//Ranged attacks
		//Special attacks
		sheet.append("\n----------\nStatistics\n----------\nStr ");
		sheet.append(str);
		sheet.append(", Dex");
		sheet.append(dex);
		sheet.append(", Con");
		sheet.append(con);
		sheet.append(", Int");
		sheet.append(pfint);
		sheet.append(", Wis");
		sheet.append(wis);
		sheet.append(", Cha");
		sheet.append(cha);
		sheet.append("\nBase Atk ");
		sheet.append(BAB);
		sheet.append("; CMB ");
		sheet.append(CMB);
		sheet.append("; CMD ");
		sheet.append(CMD);
		//Feats
		//Skills
		//Languages
		//SQ
		//Combat Gear
		//Other Gear
		//Other
		System.out.println(sheet);
		
		return name;
	}
}
