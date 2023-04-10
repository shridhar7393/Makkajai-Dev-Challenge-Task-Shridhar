package salestax;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		ReadInputFromFile rd = new ReadInputFromFile(args[0]);
	    HashMap<String, Object> resultMap = new Receipt().generateReceipt(rd.getItemsList()); // Item(name, price, quantity, itemType, imported) if imported boolean is not mentioned then false is considered
	    
		@SuppressWarnings("unchecked")
		ArrayList<Item> items = (ArrayList<Item>) resultMap.get("itemList");
	    for (Item item : items) {
            System.out.printf("%s %n", item.toString() );
        }
	    System.out.printf("Sales Taxes: %s%n", resultMap.get("totalTax"));
	    System.out.printf("Total: %.2f%n%n", resultMap.get("totalAmount"));
	  }
}
