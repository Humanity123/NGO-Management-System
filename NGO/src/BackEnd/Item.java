package BackEnd;

public class Item {
	private ItemType itemType;
	private int Class;
	
	Item(ItemType itemType, int Class){
		this.itemType = itemType;
		this.Class = Class;
	}
	
	public int hashCode(){
		String ret = Integer.toString(Class) + Integer.toString(itemType.ordinal());
		return ret.hashCode();
	}
	
	public boolean equals(Object obj){
		return (obj instanceof Item) ? (((Item)obj).getclass()==Class && ((Item)obj).getItemType()==itemType) : false; 
	}
	
	public int getclass(){
		return Class;
	}
	
	public ItemType getItemType(){
		return itemType;
	}
}
