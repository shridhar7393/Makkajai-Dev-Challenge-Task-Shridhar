package salestax;

public enum ItemType {
	  BOOK,
	  FOOD,
	  MEDICAL,
	  OTHER;

	  public boolean isTaxExempt() {
	    return this == BOOK || this == FOOD || this == MEDICAL;
	  }
}
