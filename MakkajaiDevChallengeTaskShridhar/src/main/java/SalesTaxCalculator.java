

import java.math.BigDecimal;
import java.math.RoundingMode;

import pojo.Item;

public class SalesTaxCalculator {
	private final double importTax = 0.05;
	private final double basicSalesTax = 0.10;

    public double calculatePriceWithTax(Item item) {
    	double res = 0.00;
			try {
				double itemTax = calculateTax(item);
				res = item.getItemTotalPrice() + itemTax;
			} catch (ArithmeticException e) {
				throw new ArithmeticException(e.getMessage());
			}

        return roundUpToTwoDecimals(res).doubleValue();
    }
    public double calculateTax(Item item) {
    	double itemTax = 0.00;
		try {
			double taxRate = item.isTaxExempt() ? 0.00: basicSalesTax;
			taxRate += item.isImported() ? importTax : 0.00;
			itemTax = roundUpToNearestFiveCents(item.getItemTotalPrice() * taxRate);
		} catch (ArithmeticException e) {
			throw new ArithmeticException(e.getMessage());
		}
    	return itemTax;
    }
    public double roundUpToNearestFiveCents(double amount) {

		double rounded = 0.00;
		try {
			rounded = roundUpToTwoDecimals(amount).doubleValue();
			double remainder = rounded % 0.05;
			if (remainder != 0) {
				rounded += 0.05 - remainder;
			}
		} catch (ArithmeticException e) {
			throw new ArithmeticException(e.getMessage());
		}
		return roundUpToTwoDecimals(rounded).doubleValue();
	}
    public BigDecimal roundUpToTwoDecimals(BigDecimal var) {
    	return roundUpToTwoDecimals(var.doubleValue());
	}

	public BigDecimal roundUpToTwoDecimals(double var) {
		BigDecimal bd = new BigDecimal(var);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd;
	}


}
