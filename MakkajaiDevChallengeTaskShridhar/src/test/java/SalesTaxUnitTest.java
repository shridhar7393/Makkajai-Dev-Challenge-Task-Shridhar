
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pojo.Item;
import pojo.ItemType;
import util.FileUtil;

/**
 *
 */

/**
 * @author Shridhar Hegde
 *
 *
 *
 *         File read Exception testing is skipped here
 * 
 */
public class SalesTaxUnitTest {

	FileUtil fileUtil = new FileUtil();

	@Test
	public void testPositiveWhetherInputIsConvertedToItemsListwithNotNull() throws IOException {
		Receipt rc = new Receipt();
		rc.generateReceiptFromFileInput("src/test/resources/data/test1.txt");
		ArrayList<Item> aList = rc.getItemsList();
		for (Item item : aList) {
			assertTrue(item instanceof Item);
			assertNotNull(item.getPrice());
			assertNotNull(item.getQuntity());
			assertNotNull(item.getName());
			assertNotNull(item.getItemType());
		}
	}

	@Test
	public void testPositiveOutputFromInput1() throws IOException {
		Receipt rc = new Receipt();
		rc.generateReceiptFromFileInput("src/test/resources/data/test1.txt");
		ArrayList<Item> items = rc.getItemsList();
		String[] out1 = { "1 book: 12.49", "1 music CD: 16.49", "1 chocolate bar: 0.85" };
		int i = 0;
		for (Item item : items) {
			assertEquals(out1[i++], item.toString());
		}
		assertEquals(new BigDecimal("1.50"), rc.getTotalSalesTax());
		assertEquals(new BigDecimal("29.83"), rc.getTotalCost());

	}

	@Test
	public void testPositiveOutputFromInput2() throws IOException {
		Receipt rc = new Receipt();
		rc.generateReceiptFromFileInput("src/test/resources/data/test2.txt");
		ArrayList<Item> items = rc.getItemsList();
		String[] out2 = { "1 imported box of chocolates: 10.50", "1 imported bottle of perfume: 54.65" };
		int i = 0;
		for (Item item : items) {
			assertEquals(out2[i++], item.toString());
		}
		assertEquals(new BigDecimal("7.65"), rc.getTotalSalesTax());
		assertEquals(new BigDecimal("65.15"), rc.getTotalCost());

	}

	@Test
	public void testPositiveOutputFromInput3() throws IOException {
		Receipt rc = new Receipt();
		rc.generateReceiptFromFileInput("src/test/resources/data/test3.txt");
		ArrayList<Item> items = rc.getItemsList();
		String[] out3 = { "1 imported bottle of perfume: 32.19", "1 bottle of perfume: 20.89",
				"1 packet of headache pills: 9.75", "1 imported box of chocolates: 11.85" };
		int i = 0;
		for (Item item : items) {
			assertEquals(out3[i++], item.toString());
		}
		assertEquals(new BigDecimal("6.70"), rc.getTotalSalesTax());
		assertEquals(new BigDecimal("74.68"), rc.getTotalCost());

	}

	@Test
	public void shouldCalculatePriceWithTax() {
		SalesTaxCalculator stc1 = new SalesTaxCalculator();
		Item testItem = new Item("bottle of perfume", 364.00, 1, ItemType.OTHER, true);
		assertEquals(418.60, stc1.calculatePriceWithTax(testItem), 0.0);
	}

	@Test
	public void shouldRoundUpDoubleValueToTheNearestFiveCents() {
		SalesTaxCalculator stc1 = new SalesTaxCalculator();
		assertEquals(10.45, stc1.roundUpToNearestFiveCents(10.42090), 0.0);
	}

	@Test
	public void testPositiveOutputOfRoundUpToTwoDecimals() {
		SalesTaxCalculator stc1 = new SalesTaxCalculator();
		assertEquals(BigDecimal.valueOf(0.65), stc1.roundUpToTwoDecimals(0.65470000001));
	}

	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void shouldThrowNumberFormatExceptionWhenquantityIsNotInteger() throws Exception {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Quantity is not in a valid format");
		fileUtil.getItemsListFromString("bottle of perfume at 0");
	}

	@Test
	public void shouldThrowRuntimeExceptionWhenAtKeywordIsNotUsedProperly() throws Exception {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Mention item name and price seperated by 'at' keyword");
		fileUtil.getItemsListFromString("1 pill at ");
	}

	@Test
	public void shouldThrowRuntimeExceptionWhenItemNameIsNotPresent() throws Exception {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Item name is not mentioned");
		fileUtil.getItemsListFromString("1 at 23.60");
	}

	@Test
	public void shouldThrowNumberFormatExceptionWhenItemPriceIsNotValid() throws Exception {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Price is not valid");
		fileUtil.getItemsListFromString("1 chocolate at two dollars");
	}

}
