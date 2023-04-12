package salestax;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	private static ArrayList<Item> itemsList = new ArrayList<Item>();
	public static void main(String[] args) {

		File file = new File("src/"+args[0]);
		Scanner sc;

		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				//read the sentence
				String sentence = sc.nextLine(); 
				//change the sentence into words
				String[] words = sentence.split(" "); 
				//first word is the quantity
				int quantity = Integer.parseInt(words[0]); 
				//check for "imported" word
				boolean isImported = sentence.contains("imported"); 
				//check for the item type 
				ItemType itemType = sentence.contains("book") ? 
						ItemType.BOOK : sentence.contains("chocolate") ? 
								ItemType.FOOD : sentence.contains("pill") ? 
										ItemType.MEDICAL : ItemType.OTHER;
				//separate name and price
				String[] temp = sentence.split(" at ");
				itemsList.add(new Item(temp[0], Double.parseDouble(temp[1]), quantity, itemType, isImported));
			}
		} catch (NumberFormatException | FileNotFoundException e) {
			e.printStackTrace();
		}
		Receipt rc = new Receipt(itemsList);
		HashMap<String, Object> resultMap = rc.generateReceipt(); // Item(name, price, quantity, itemType, imported) if imported boolean is not mentioned then false is considered

		@SuppressWarnings("unchecked")
		ArrayList<Item> items = (ArrayList<Item>) resultMap.get("itemList");
		for (Item item : items) {
			System.out.printf("%s %n", item.toString() );
		}
		System.out.printf("Sales Taxes: %s%n", resultMap.get("totalTax"));
		System.out.printf("Total: %.2f%n%n", resultMap.get("totalAmount"));
	}
}
