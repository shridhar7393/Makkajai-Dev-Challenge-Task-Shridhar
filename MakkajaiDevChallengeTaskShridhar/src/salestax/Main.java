package salestax;

import java.util.ArrayList;
import java.util.HashMap;

import salestax.service.SalesTaxCalculator;
import salestax.util.Item;
import salestax.util.ItemType;

public class Main {
	public static void main(String[] args) {
	    SalesTaxCalculator stc = new SalesTaxCalculator();
	    HashMap<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap = stc.calculateAndSendReceipt(1,
	  	      new Item("book", 12.49, 1, ItemType.BOOK),
		      new Item("music CD", 14.99, 1, ItemType.OTHER, false),
		      new Item("chocolate bar", 0.85, 3, ItemType.FOOD, false)
	    ); // Item(name, price, quantity, itemType, imported) if imported boolean is not mentioned then false is considered
	    
		@SuppressWarnings("unchecked")
		ArrayList<Item> items = (ArrayList<Item>) resultMap.get("itemList");
	    for (Item item : items) {
            System.out.printf("%s %n", item.toString() );
        }
	    System.out.printf("Sales Taxes: %s%n", resultMap.get("totalTax"));
	    System.out.printf("Total: %.2f%n%n", resultMap.get("totalAmount"));
	  }
}
