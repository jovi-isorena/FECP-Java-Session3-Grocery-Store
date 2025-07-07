import models.Status;
import services.ProductService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GroceryStoreInventorySystem {
    static HashMap<String, Integer> products = new HashMap<String, Integer>();
    static Scanner sc = new Scanner(System.in);
    static ProductService productService = new ProductService();
    public static void main(String[] args) {
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
                    addProduct(name, quantity);
                    break;
                }
                case "3":{
                    // check
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    checkProduct(name);
                    break;
                }
                case "4":{
                    //update
                    System.out.print("Enter product to update: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new stock quantity: ");
                    String quantity = sc.nextLine();
                    updateProduct(name, quantity);
                    break;
                }
                case "5": {
                    //remove
                    System.out.print("Enter product to remove: ");
                    String name = sc.nextLine();
                    removeProduct(name);
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
    public static boolean addProduct(String name, String quantity){
        int dQuantity = 0;
        Status result = null;
        try{
            dQuantity = Integer.parseInt(quantity);
            result = productService.addProduct(name, dQuantity);
            System.out.println(result.getDescription());
            return (result == Status.PRODUCT_ADDED);
        }catch(NumberFormatException e){
            result = Status.INVALID_QUANTITY;
            System.out.println(result.getDescription());
            return false;
        }
    }
    public static int checkProduct(String name){
        int quantity = productService.checkProduct(name);
        if(quantity == -1){
            System.out.println("Item not in inventory.");
        }else if(quantity == 0){
            System.out.println("Item does not have stock.");
        }else{
            System.out.println(name + " is in stock: " + quantity);
        }
        return quantity;
    }
    public static boolean updateProduct(String name, String quantity){
        int dQuantity = 0;
        Status result = null;
        try{
            dQuantity = Integer.parseInt(quantity);
            result = productService.updateProduct(name, dQuantity);
            System.out.println(result.getDescription());
            return (result == Status.PRODUCT_UPDATED);
        }catch(NumberFormatException e){
            result = Status.INVALID_QUANTITY;
            System.out.println(result.getDescription());
            return false;
        }
    }
    public static boolean removeProduct(String name){
        Status result = productService.removeProduct(name);
        System.out.println(result.getDescription());
        return result == Status.PRODUCT_REMOVED;
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


}
