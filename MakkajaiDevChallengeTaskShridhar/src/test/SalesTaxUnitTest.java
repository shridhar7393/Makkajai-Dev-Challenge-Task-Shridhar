package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import salestax.service.SalesTaxCalculator;
import salestax.util.Item;
import salestax.util.ItemType;

/**
 * 
 */

/**
 * @author shridharhegde
 *
 */
public class SalesTaxUnitTest {
	Item[] in1 = {new Item("book", 12.49, 1, ItemType.BOOK),
		      new Item("music CD", 14.99, 1, ItemType.OTHER),
		      new Item("chocolate bar", 0.85, 2, ItemType.FOOD)};
	String[] out1 = {"1 book 12.49","1 music CD 16.49","2 chocolate bar 1.70"};
	
	Item[] in2 = {new Item("book", 12.49, 1, ItemType.BOOK),
		      new Item("music CD", 14.99, 1, ItemType.OTHER),
		      new Item("chocolate bar", 0.85, 2, ItemType.FOOD)};
	String[] out2 = {"1 book 12.49","1 music CD 16.49","2 chocolate bar 1.70"};

	@Test
	public void test1() {
		SalesTaxCalculator stc1 = new SalesTaxCalculator();
		HashMap<String, Object> resultMap1 = new HashMap<String, Object>();
	    resultMap1 = stc1.calculateAndSendReceipt(1,in1);
	    ArrayList<Item> items = (ArrayList<Item>) resultMap1.get("itemList");
	    int i = 0;
	    for (Item item : items) {
	    	
	    	assertEquals(out1[i++], item.toString());
	    	assertEquals("1.50", resultMap1.get("totalTax"));
	    	assertEquals(30.68, resultMap1.get("totalAmount"));
	    	
        }
	    
	}
	
//	@Test
//	public void test2() {
//		SalesTaxCalculator stc1 = new SalesTaxCalculator();
//		HashMap<String, Object> resultMap1 = new HashMap<String, Object>();
//	    resultMap1 = stc1.calculateAndSendReceipt(1,in1);
//	    ArrayList<Item> items = (ArrayList<Item>) resultMap1.get("itemList");
//	    int i = 0;
//	    for (Item item : items) {
//	    	
//	    	assertEquals(out1[i++], item.toString());
//        }
//	    
//	}

}
