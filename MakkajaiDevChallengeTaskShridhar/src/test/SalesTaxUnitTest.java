package test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import salestax.Item;
import salestax.ItemType;
import salestax.Receipt;
import salestax.SalesTaxCalculator;

/**
 * 
 */

/**
 * @author shridharhegde
 *
 */
public class SalesTaxUnitTest {
	
	String in1 = "src/test1.txt";
	String[] out1 = {"1 book: 12.49","1 music CD: 16.49","1 chocolate bar: 0.85"};

	

	
	public ArrayList<Item> createListFromInputFile(String fileName){
		ArrayList<Item> itemsList = new ArrayList<Item>();
		File file = new File(fileName);
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String itemSentence = sc.nextLine(); 
				//first word is the quantity
				int quantity = Integer.parseInt(itemSentence.split(" ")[0]); 
				//check for "imported" word
				boolean isImported = itemSentence.contains("imported"); 
				//check for the item type 
				ItemType itemType = itemSentence.contains("book") ? 
						ItemType.BOOK : itemSentence.contains("chocolate") ? 
								ItemType.FOOD : itemSentence.contains("pill") ? 
										ItemType.MEDICAL : ItemType.OTHER;
				//separate name and price
				String[] itemNameandPrice = itemSentence.split(" at ");
				itemsList.add(new Item(itemNameandPrice[0], Double.parseDouble(itemNameandPrice[1]), quantity, itemType, isImported));
			}
		} catch (NullPointerException | NumberFormatException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return itemsList;

	}
	public ArrayList<Item> createListFromInputString(String line){
		ArrayList<Item> itemsList = new ArrayList<Item>();
		//first word is the quantity
		int quantity = Integer.parseInt(line.split(" ")[0]); 
		//check for "imported" word
		boolean isImported = line.contains("imported"); 
		//check for the item type 
		ItemType itemType = line.contains("book") ? 
				ItemType.BOOK : line.contains("chocolate") ? 
						ItemType.FOOD : line.contains("pill") ? 
								ItemType.MEDICAL : ItemType.OTHER;
		//separate name and price
		String[] itemNameandPrice = line.split(" at ");
		itemsList.add(new Item(itemNameandPrice[0], Double.parseDouble(itemNameandPrice[1]), quantity, itemType, isImported));

		return itemsList;

	}

	@Test
	public void testPositiveWhetherInputStringIsConvertedToListOfItems() {
		String inputString = "1 imported bottle of perfume at 47.50";
		ArrayList<Item> aList = createListFromInputString(inputString);
		for(Item item: aList) {
			assertTrue(item instanceof Item);// string is read and converted to Item object
			assertEquals(1, item.getQuntity());
			assertEquals(47.50, item.getPrice(), 0.00);
			assertEquals(true, item.isImported());
			assertEquals(ItemType.OTHER, item.getItemType());
		}
	}

	@Test
	public void testPositiveWhetherInputFileIsConvertedToItemsListwithNotNull() {
		ArrayList<Item> aList = createListFromInputFile(in1);
		for(Item item: aList) {
			assertTrue(item instanceof Item);
			assertNotNull(item.getPrice());
			assertNotNull(item.getQuntity());
			assertNotNull(item.getName());
			assertNotNull(item.getItemType());
		}
	}

	@Test
	public void testPositiveOutputFromInput1() {
		Receipt rc = new Receipt();
		rc.generateReceipt(createListFromInputFile(in1));
		ArrayList<Item> items = (ArrayList<Item>) rc.getItemsList();
		int i = 0;
		for (Item item : items) {
			assertEquals(out1[i++], item.toString());
		}
		assertEquals(1.50, rc.getTotalSalesTax(), 0.2);
		assertEquals(29.83, rc.getTotalCost(), 0.2);

	}

	@Test
	public void testPositiveOutputFromInput2() {

		String in2 = "src/test2.txt";
		String[] out2 = {"1 imported box of chocolates: 10.50","1 imported bottle of perfume: 54.65"};
		Receipt rc = new Receipt();
		rc.generateReceipt(createListFromInputFile(in2));
		ArrayList<Item> items = (ArrayList<Item>) rc.getItemsList();
		int i = 0;
		for (Item item : items) {
			assertEquals(out2[i++], item.toString());
		}
		assertEquals(7.65, rc.getTotalSalesTax(), 0.2);
		assertEquals(65.15, rc.getTotalCost(), 0.2);

	}

	@Test
	public void testPositiveOutputFromInput3() {
		String in3 = "src/test3.txt";
		String[] out3 = {"1 imported bottle of perfume: 32.19","1 bottle of perfume: 20.89","1 packet of headache pills: 9.75","1 imported box of chocolates: 11.85"};

		Receipt rc = new Receipt();
		rc.generateReceipt(createListFromInputFile(in3));
		ArrayList<Item> items = (ArrayList<Item>) rc.getItemsList();
		int i = 0;
		for (Item item : items) {
			assertEquals(out3[i++], item.toString());
		}
		assertEquals(6.70, rc.getTotalSalesTax(), 0.2);
		assertEquals(74.68, rc.getTotalCost(), 0.2);

	}

	@Test
	public void testPositiveOutputOfCalculatePriceWithTaxFunctionFromSalesTaxCalculator() {
		SalesTaxCalculator stc1 = new SalesTaxCalculator();
		Item testItem = new Item("bottle of perfume", 364.00,1,ItemType.OTHER,true);
		assertEquals(418.60, stc1.calculatePriceWithTax(testItem), 0.2);
	}

}
