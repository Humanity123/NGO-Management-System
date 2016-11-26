package BackEnd;

import java.util.HashMap;
import java.util.Map;

public class Help {
	private Map<Item,Integer> itemFreq;
	private int money;
	
	public Help(){
		itemFreq = new HashMap<Item,Integer>();
	}
	
	public int getItemCount(Item item){
		int f = 0;
		if(itemFreq.containsKey(item))
			f=itemFreq.get(item).intValue();
		return f;
	}
	
	public int getMoney(){
		return money;
	}
}
