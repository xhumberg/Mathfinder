package characterModel;


public class Item {
	private int cost;
	private String name;
	private String description;
	private boolean equipped;
	private TempEffect effect;
	
	public Item(int cost, String name, String description)
	{
		this.cost = cost;
		this.name = name;
		this.description = description;
		this.equipped = false;
	}
	
	public Item(int cost, String name, String description, TempEffect effect)
	{
		this(cost, name, description);
		this.effect = effect;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public boolean isEquipped()
	{
		return equipped;
	}
	
	public void equip()
	{
		equipped = true;
		if(effect != null)
		{
			effect.activate();
		}
	}
	
	public void unequip()
	{
		equipped = false;
		if(effect != null)
		{
			effect.deactivate();
		}
	}
}
