import java.io.IOException;
import pojo.Item;

public class Main {

  public static void main(String[] args) throws IOException {
    Receipt receipt = new Receipt();
    receipt.generateReceiptFromFileInput(args[0]);
    
    System.out.println("Output");
    for (Item item : receipt.getItemsList()) {
      System.out.printf("%s %n", item.toString());
    }

    System.out.printf("Sales Taxes: %s%n", receipt.getTotalSalesTax());
    System.out.printf("Total: %s", receipt.getTotalCost());
  }
}
