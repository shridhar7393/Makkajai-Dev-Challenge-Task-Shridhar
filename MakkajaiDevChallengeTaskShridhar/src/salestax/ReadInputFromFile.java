package salestax;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadInputFromFile {
	private ArrayList<Item> itemsList = new ArrayList<Item>();


	public ArrayList<Item> getItemsList() {
		return itemsList;
	}

	public ReadInputFromFile(String fileName){

		File file = new File("src/"+fileName);
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
	}
}
