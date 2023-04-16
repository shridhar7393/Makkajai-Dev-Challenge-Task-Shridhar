package pojo;

public class Item {

	private final String name;
	private final double price;
	private final int quntity;
	private final ItemType itemType;
	private final boolean imported;
	public String itemPriceWithTax;
	private final boolean isTaxExempt;

	public Item(String name, double price, int quntity, ItemType itemType) {
		this(name, price, quntity, itemType, false);
	}

	public Item(String name, double price, int quntity, ItemType itemType, boolean imported) {
		this.name = name;
		this.price = price;
		this.quntity = quntity;
		this.itemType = itemType;
		this.imported = imported;
		this.isTaxExempt = this.itemType == ItemType.BOOK || this.itemType == ItemType.FOOD
				|| this.itemType == ItemType.MEDICAL;

	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public boolean isImported() {
		return imported;
	}

	public int getQuntity() {
		return quntity;
	}

	public String getItemPriceWithTax() {
		return itemPriceWithTax;
	}

	public void setItemPriceWithTax(String itemPriceWithTax) {
		this.itemPriceWithTax = itemPriceWithTax;
	}

	public boolean isTaxExempt() {
		return isTaxExempt;
	}

	public double getItemTotalPrice() {
		return price * quntity;
	}

	@Override
	public String toString() {
		return name + ":" + " " + getItemPriceWithTax();
	}
}
