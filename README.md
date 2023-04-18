# Makkajai-Dev-Challenge-Task-Shridhar


Open terminal at "executables" folder.  
Run the following command "java -cp Makkajai-salestax-java-jar.jar Main 'test1.txt'".  
or Run the project in your IDE with file name as argument.

##### Assumptions:  
1. quantity and price: In input, price is assumed as price for one item. In code, price is calculated by multiplying it with its quantity.  

#####Note:
If representing monetary amounts, using double is almost certainly a bad idea. We can use following instead,  
return an int (i.e. the rounded value multiplied by 100);  
return a String;  
return a BigDecimal;  
Here I am using BigDecimal, rounding to two decimals.    

##### Approach:  
Real-life scenario :  
1. List down the items, i.e., name, price, quantity, category/type, imported or not.  
2. Apply basic-sales-tax to each item other than medical/book/food type.  
3. Add import-tax to each imported item of any type.  
4. Calculate total tax amount applied and total price to be paid.  
5. Generate/Print receipt.  

Class design:  
1. Raw String input is converted to Object of Item class.
2. Receipt object generates a receipt by taking list of item objects.
3. generateReceipt function utilizes tax calculation logics from SalesTaxCalculator class.
4. Receipt class calculates and stores total tax amount and total price to be paid.


Run the SalesTaxUnitTest.java file in your IDE.
