package salestax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Receipt{
	private List<Item> items;

	private double totalSalesTax;
	private double totalCost;

	private HashMap<String, Object> resultMap = new HashMap<String, Object>();

	public Receipt(ArrayList<Item> itemsList) {
		items = itemsList;
		totalSalesTax = 0;
		totalCost = 0;
	}

	public HashMap<String, Object> generateReceipt() {
		SalesTaxCalculator stc = new SalesTaxCalculator();
		ArrayList<Item> resItemList = new ArrayList<>();
		for (Item item : items) {
			double itemPriceWithTax = stc.calculatePriceWithTax(item);
			item.setItemPriceWithTax(stc.roundUpToTwoDecimals(itemPriceWithTax).toString());
			totalCost += itemPriceWithTax;
			totalSalesTax += itemPriceWithTax - item.getPrice();
			resItemList.add(item);
		}
		resultMap.put("itemList", resItemList);
		resultMap.put("totalTax", stc.roundUpToTwoDecimals(totalSalesTax).toString());
		resultMap.put("totalAmount", totalCost);
		return resultMap;
	}


}
