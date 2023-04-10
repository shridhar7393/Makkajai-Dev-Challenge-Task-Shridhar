package salestax.service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import salestax.util.Item;

public class SalesTaxCalculator {
	public SalesTaxCalculator(){}
    public HashMap<String, Object> calculateAndSendReceipt(int orderNum, Item... items) {
    	double subtotal = 0.0;
        double totalTax = 0.0;
        List<Item> itemList = new ArrayList<Item>();
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        for (Item item : items) {
            double itemPriceWithTax = calculatePriceWithTax(item);
            subtotal += item.getPrice();
            totalTax += itemPriceWithTax - item.getPrice();
            item.setItemPriceWithTax(rounding(itemPriceWithTax).toString());
            itemList.add(item);
//            System.out.printf("%s %n", item.toString() );
        }
        resultMap.put("itemList", itemList);

//        System.out.printf("Sales Taxes: %.2f%n", totalTax);
        resultMap.put("totalTax", rounding(totalTax).toString());
//        System.out.printf("Total: %.2f%n%n", subtotal + totalTax);
        resultMap.put("totalAmount", subtotal + totalTax);
		return resultMap;
        
    }
    
    public void calculateAndPrintReceipt(int orderNum, Item... items) {
    	double subtotal = 0.0;
        double totalTax = 0.0;

        for (Item item : items) {
            double itemPriceWithTax = calculatePriceWithTax(item);
            subtotal += item.getPrice();
            totalTax += itemPriceWithTax - item.getPrice();
            item.setItemPriceWithTax(rounding(itemPriceWithTax).toString());
            System.out.printf("%s %n", item.toString() );
        }
        System.out.printf("Sales Taxes: %.2f%n", totalTax);
        System.out.printf("Total: %.2f%n%n", subtotal + totalTax);
        
    }
    
    private double calculatePriceWithTax(Item item) {
        double taxRate = item.isImported() ? 0.15 : 0.10;
        if (item.getItemType().isTaxExempt()) {
            taxRate = taxRate - 0.10;
        }
        double itemTax = roundUpToNearestFiveCents(item.getPrice() * taxRate);
        return item.getPrice() + itemTax;
    }

    private double roundUpToNearestFiveCents(double amount) {
        
        double rounded = rounding(amount).doubleValue();
        double remainder = rounded % 0.05;
        if (remainder != 0) {
            rounded += 0.05 - remainder;
        }
        return rounded;
    }
    
    private BigDecimal rounding(double var) {
    	BigDecimal bd = new BigDecimal(var);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
    	return bd;
    }
}
