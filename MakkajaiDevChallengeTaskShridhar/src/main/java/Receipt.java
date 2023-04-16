
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import pojo.Item;
import util.FileUtil;

public class Receipt {
	private ArrayList<Item> itemsList;
	private BigDecimal totalSalesTax;
	private BigDecimal totalCost;

	public ArrayList<Item> getItemsList() {
		return itemsList;
	}

	public BigDecimal getTotalSalesTax() {
		return totalSalesTax;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	private void setItemsList(ArrayList<Item> itemsList) {
		this.itemsList = itemsList;
	}

	public Receipt() {
		itemsList = new ArrayList<>();
		totalSalesTax = new BigDecimal(0.00);
		totalCost = new BigDecimal(0.00);
	}

	public void generateReceiptFromFileInput(String fileName) throws IOException {
		FileUtil fileUtil = new FileUtil();
		generateReceiptFromItemsList(fileUtil.getItemsListFromFile(fileName));
	}

	public void generateReceiptFromItemsList(ArrayList<Item> items) {
		if (items != null) {
			generateReceipt(items);
		} else
			setItemsList(null);
	}

	private void generateReceipt(ArrayList<Item> items) {
		BigDecimal subTotal = new BigDecimal(0);
		SalesTaxCalculator stc = new SalesTaxCalculator();
		for (Item item : items) {
			BigDecimal itemPriceWithTax = BigDecimal.valueOf(stc.calculatePriceWithTax(item));
			item.setItemPriceWithTax(stc.roundUpToTwoDecimals(itemPriceWithTax).toString());
			totalCost = totalCost.add(itemPriceWithTax);
			subTotal = subTotal.add(BigDecimal.valueOf(item.getItemTotalPrice()));
			totalSalesTax = totalSalesTax.add(itemPriceWithTax);
			itemsList.add(item);
		}
		totalSalesTax = totalSalesTax.subtract(subTotal);
	}



}
