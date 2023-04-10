package salestax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Receipt{
	public HashMap<String, Object> generateReceipt(ArrayList<Item> items) {
		double subtotal = 0.0;
        double totalTax = 0.0;
        List<Item> itemList = new ArrayList<Item>();
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        SalesTaxCalculator stc = new SalesTaxCalculator();
        RoundUpTo round = new RoundUpTo();

        for (Item item : items) {
            double itemPriceWithTax = stc.calculatePriceWithTax(item);
            subtotal += item.getPrice();
            totalTax += itemPriceWithTax - item.getPrice();
            item.setItemPriceWithTax(round.twoDecimals(itemPriceWithTax).toString());
            itemList.add(item);
        }
        resultMap.put("itemList", itemList);
        resultMap.put("totalTax", round.twoDecimals(totalTax).toString());
        resultMap.put("totalAmount", subtotal + totalTax);
		return resultMap;
	}

}
