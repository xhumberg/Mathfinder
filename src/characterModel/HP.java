package characterModel;

import java.util.LinkedList;
import java.util.List;

public class HP {
	List<Dice> hitDice;
	Bonus con;
	int favoredClassBonus;
	Bonus otherBonuses;
	int lethalDamage;
	int nonlethalDamage;

	public HP(Bonus con) {
		hitDice = new LinkedList<Dice>();
		this.con = con;
		favoredClassBonus = 0;
		otherBonuses = new Bonus();
	}

	public int getMaxHP() {
		if (hitDice.size() < 1)
			return 0;
		int numHD = getHDNum();
		int totalHP = con.getMod() * numHD + favoredClassBonus + otherBonuses.getValue();

		// Now for the dice
		totalHP += hitDice.get(0).max();
		int diceIndex = 1;
		while (diceIndex < numHD) {
			totalHP += hitDice.get(diceIndex++).avg();
		}
		return totalHP;
	}

	public int getHDNum() {
		return hitDice.size();
	}

	public void addHD(DiceType type) {
		hitDice.add(new Dice(type));
	}

	public void damage(int amount) {
		damage(amount, false);
	}

	public void damage(int amount, boolean nonlethal) {
		if (nonlethal)
			nonlethalDamage += amount;
		else
			lethalDamage += amount;

	}

	public void heal(int amount) {
		nonlethalDamage = Math.max(0, nonlethalDamage - amount);
		lethalDamage = Math.max(0, lethalDamage - amount);
	}

	public int getCurrentHP() {
		return getMaxHP() - lethalDamage;
	}

	public int getEffectiveHP() {
		return getMaxHP() - lethalDamage - nonlethalDamage;
	}
}
