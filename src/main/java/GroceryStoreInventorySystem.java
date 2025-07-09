import models.Status;
import services.ProductService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GroceryStoreInventorySystem {
    static Scanner sc = new Scanner(System.in);
    static ProductService productService;
    public static void main(String[] args) {
        setProductService(new ProductService());
        String selection;
        do{
            displayMenu();
            selection = sc.nextLine();
            switch (selection){
                case "1":{ //view
                    viewInventory();
                    break;
                }
                case "2":{
                    //add
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    String quantity = sc.nextLine();
                    System.out.println(addProduct(name, quantity));
                    break;
                }
                case "3":{
                    // check
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.println(checkProduct(name));
                    break;
                }
                case "4":{
                    //update
                    System.out.print("Enter product to update: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new stock quantity: ");
                    String quantity = sc.nextLine();
                    System.out.println(updateProduct(name, quantity));
                    break;
                }
                case "5": {
                    //remove
                    System.out.print("Enter product to remove: ");
                    String name = sc.nextLine();
                    System.out.println(removeProduct(name));
                    break;
                }
                case "6":
                    System.out.println("Thank you for using my app. \nExiting.");
                    break;
            }
        }while(!selection.equals("6"));

    }
    public static void displayMenu(){
        System.out.println("--- Grocery Inventory Menu ---");
        System.out.println("1. View Inventory");
        System.out.println("2. Add Product");
        System.out.println("3. Check Product");
        System.out.println("4. Update Stock");
        System.out.println("5. Remove Product");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }
    public static String addProduct(String name, String quantity){
        int dQuantity = 0;
        Status result = null;
        try{
            dQuantity = Integer.parseInt(quantity);
            result = productService.addProduct(name, dQuantity);
            return result.toString();
        }catch(NumberFormatException e){
            result = Status.INVALID_QUANTITY;
            return result.toString();
        }
    }
    public static String checkProduct(String name){
        int quantity = productService.checkProduct(name);
        String result;
        if(quantity == -1){
            result = "Product not found.";
        }else if(quantity == 0){
            result = "Product does not have stock.";
        }else{
            result = name + " is in stock: " + quantity;
        }
        return result;
    }
    public static String updateProduct(String name, String quantity){
        int dQuantity = 0;
        Status result = null;
        try{
            dQuantity = Integer.parseInt(quantity);
            result = productService.updateProduct(name, dQuantity);
            return result.toString();
        }catch(NumberFormatException e){
            result = Status.INVALID_QUANTITY;
            return result.toString();
        }
    }
    public static String removeProduct(String name){
        Status result = productService.removeProduct(name);
        return result.toString();
    }
    public static int viewInventory(){
        HashMap<String, Integer> products = productService.getProducts();
        int size = products.size();
        if(size == 0){
            System.out.println("Inventory is empty.");
        }else{
            System.out.println("Current Inventory:");
            for(Map.Entry<String, Integer> entry: products.entrySet()) {
                System.out.printf("%s - %d\n", entry.getKey(), entry.getValue());
            }
        }
        return size;
    }
    public static void setProductService(ProductService productService) {
        GroceryStoreInventorySystem.productService = productService;
    }
}
