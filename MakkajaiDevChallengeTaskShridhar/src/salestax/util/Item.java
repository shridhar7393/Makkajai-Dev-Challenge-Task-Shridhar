package salestax.util;

import java.math.BigDecimal;

public class Item {

	private final String name;
	private final double price;
	private final int quntity;
	private final ItemType itemType;
	private final boolean imported;
	public String itemPriceWithTax;



	public Item(String name, double price, int quntity, ItemType itemType) {
		this(name, price, quntity, itemType, false);
	}

	public Item(String name, double price, int quntity, ItemType itemType, boolean imported) {
		this.name = name;
		this.price = price;
		this.quntity = quntity;
		this.itemType = itemType;
		this.imported = imported;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price * quntity;
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
	public String toString() {
		return quntity + " " + (imported ? "imported " : "") + name + " " + getItemPriceWithTax();
	}
}

