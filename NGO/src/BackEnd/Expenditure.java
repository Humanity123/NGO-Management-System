package BackEnd;

import java.util.HashMap;
import java.util.Map;

public class Expenditure {
	private Map<Item,Integer> itemCost;
	private int money;
	private ExpenditureType type;
	
	public Expenditure(){
		money=0;
		itemCost= new HashMap<Item,Integer>();
		type=ExpenditureType.BUYING;
	}
}
