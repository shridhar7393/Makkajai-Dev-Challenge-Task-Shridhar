package salestax;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundUpTo {

	public double nearestFiveCents(double amount) {

		double rounded = twoDecimals(amount).doubleValue();
		double remainder = rounded % 0.05;
		if (remainder != 0) {
			rounded += 0.05 - remainder;
		}
		return rounded;
	}

	public BigDecimal twoDecimals(double var) {
		BigDecimal bd = new BigDecimal(var);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd;
	}
}
