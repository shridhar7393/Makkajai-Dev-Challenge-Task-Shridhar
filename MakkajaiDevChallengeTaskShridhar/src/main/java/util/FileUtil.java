package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import pojo.Item;
import pojo.ItemType;

public class FileUtil {

	public ArrayList<Item> getItemsListFromFile(String fileName) throws IOException{
		ArrayList<String> strList = getListOfStringFromInputFile(fileName);
		ArrayList<Item> itemsList = getItemListFromListOfStrings(strList);
		return itemsList;
	}
	public ArrayList<Item> getItemsListFromString(String str){
		ArrayList<String> lines = new ArrayList<>();
			lines.add(str);
		return getItemListFromListOfStrings(lines);
	}

	private ArrayList<String> getListOfStringFromInputFile(String fileName) throws IOException {
		FileReader fileReader;
		ArrayList<String> res = new ArrayList<>();

		try {
			fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				res.add(line);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("'at' keyword missing in an input line");
		} catch (IOException e) {
			throw new IOException("'at' keyword missing in an input line");
		}
		return res;

	}

	private ArrayList<Item> getItemListFromListOfStrings(ArrayList<String> strList) {
		ArrayList<Item> itemsList = new ArrayList<>();
		try {
			for(String line: strList) {
				// first word is the quantity
				int quantity = Integer.parseInt(line.split(" ")[0]);
				// check for "imported" word
				boolean isImported = line.contains("imported");
				// check for the item type
				ItemType itemType = line.contains("book") ? ItemType.BOOK
						: line.contains("chocolate") ? ItemType.FOOD
								: line.contains("pill") ? ItemType.MEDICAL : ItemType.OTHER;
				// separate name and price
				String[] itemNameAndPrice = line.split(" at ");
				if (isValid(itemNameAndPrice)) {
					itemsList.add(new Item(itemNameAndPrice[0], Double.parseDouble(itemNameAndPrice[1]), quantity,
							itemType, isImported));
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException("Mention item name and price seperated by 'at' keyword");
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Quantity is not in a valid format");
		}

		return itemsList;

	}

	private boolean isValid(String[] itemNameAndPrice) {
		boolean flag = true;

		if(itemNameAndPrice[0].split(" ").length <= 1  ) {
			flag = false;
			throw new RuntimeException("Item name is not mentioned");
		}
		try {
			Double.parseDouble(itemNameAndPrice[1]);
		} catch (NumberFormatException e) {
			flag = false;
			throw new RuntimeException("Price is not valid");
		}
		return flag;
	}
}
