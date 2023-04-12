package salestax;

import java.util.ArrayList;

public class Receipt{
	private ArrayList<Item> itemsList;

	private double totalSalesTax;
	private double totalCost;

	public ArrayList<Item> getItemsList() {
		return itemsList;
	}

	public double getTotalSalesTax() {
		return totalSalesTax;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public Receipt() {
		itemsList = new ArrayList<Item>();
		totalSalesTax = 0.00;
		totalCost = 0.00;
	}

	public void generateReceipt(ArrayList<Item> items) {
		SalesTaxCalculator stc = new SalesTaxCalculator();
		for (Item item : items) {
			double itemPriceWithTax = stc.calculatePriceWithTax(item);
			item.setItemPriceWithTax(stc.roundUpToTwoDecimals(itemPriceWithTax).toString());
			totalCost += itemPriceWithTax;
			totalSalesTax += stc.roundUpToTwoDecimals(itemPriceWithTax - item.getPrice()).doubleValue();
			itemsList.add(item);
		}


	}

}
