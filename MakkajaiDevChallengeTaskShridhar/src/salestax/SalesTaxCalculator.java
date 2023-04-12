package salestax;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalesTaxCalculator {
	private final double importTax = 0.05;
	private final double basicSalesTax = 0.10;
    
    public double calculatePriceWithTax(Item item) {
    	double res = 0.00;
        try {
			double taxRate = item.getItemType().isTaxExempt() ? 0.00: basicSalesTax;
			taxRate += item.isImported() ? importTax : 0.00;
			double itemTax = roundUpToNearestFiveCents(item.getPrice() * taxRate);
			res = item.getPrice() + itemTax;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return res;
    }
    public double roundUpToNearestFiveCents(double amount) {

		double rounded = roundUpToTwoDecimals(amount).doubleValue();
		double remainder = rounded % 0.05;
		if (remainder != 0) {
			rounded += 0.05 - remainder;
		}
		return rounded;
	}

	public BigDecimal roundUpToTwoDecimals(double var) {
		BigDecimal bd = new BigDecimal(var);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd;
	}

    
}
