package salestax;

public class SalesTaxCalculator {
	private final double importTax = 0.05;
	private final double basicSalesTax = 0.10;
    
    public double calculatePriceWithTax(Item item) {
    	double res = 0.00;
        try {
			double taxRate = item.isImported() ? basicSalesTax + importTax : basicSalesTax;
			if (item.getItemType().isTaxExempt()) {
			    taxRate = taxRate - basicSalesTax;
			}
			double itemTax = new RoundUpTo().nearestFiveCents(item.getPrice() * taxRate);
			res = item.getPrice() + itemTax;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return res;
    }

    
}
