# Makkajai-Dev-Challenge-Task-Shridhar

Pass filename as parameter for main method like "test1.txt".__

Assumptions:
1. Inputs are given as object of Item class like:
       new Item(String name, double price, int quantity, itemType, boolean imported);
       Here "imported" can be set false or left empty if item is not imported, but should be true if imported.
2. quantity and price:
      In input, price is assumed as price for one item. In code, price is calculated by multiplying it with its quantity.__

If representing monetary amounts, using double is almost certainly a bad idea. We can use following instead,__
  return an int (i.e. the rounded value multiplied by 100);__
  return a String;__
  return a BigDecimal;__
 Here I am returning a itemPriceWithTax as String.
