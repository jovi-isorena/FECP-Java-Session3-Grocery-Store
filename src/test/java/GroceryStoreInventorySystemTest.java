import models.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ProductService;

import static org.junit.jupiter.api.Assertions.*;

class GroceryStoreInventorySystemTest {
    @BeforeEach
    void setup(){
        GroceryStoreInventorySystem.setProductService(new ProductService());


    }
    // Unit testing for lab
    @Test
    void testAddingValidProduct() {
        assertEquals(Status.PRODUCT_ADDED.toString(), GroceryStoreInventorySystem.addProduct("Banana","30"));
    }

    @Test
    void testAddingProductWithZeroQuantity(){
        // The product should be added even with 0 quantity
        assertEquals(Status.PRODUCT_ADDED.toString(), GroceryStoreInventorySystem.addProduct("Mango","0"));
    }

    @Test
    void testAddingExistingProduct(){
        // creates the first item on the inventory
        GroceryStoreInventorySystem.addProduct("Milk","20");
        assertEquals(Status.ADDING_EXISTING_PRODUCT.toString(), GroceryStoreInventorySystem.addProduct("Milk","50"));
        assertEquals("Milk is in stock: 50",GroceryStoreInventorySystem.checkProduct("Milk"));
    }

    @Test
    void testCheckingExistingProduct(){
        // creates the first item on the inventory
        GroceryStoreInventorySystem.addProduct("Milk","20");
        assertEquals("Milk is in stock: 20", GroceryStoreInventorySystem.checkProduct("Milk"));
    }

    @Test
    void testCheckingNonExistingProduct(){
        assertEquals(Status.PRODUCT_DOES_NOT_EXISTS.toString(), GroceryStoreInventorySystem.checkProduct("Ice Cream"));
    }

    @Test
    void testUpdatingValidProduct() {
        // creates the first item on the inventory
        GroceryStoreInventorySystem.addProduct("Bread","10");
        assertEquals(Status.PRODUCT_UPDATED.toString(), GroceryStoreInventorySystem.updateProduct("Bread", "25"));
        assertEquals("Bread is in stock: 25", GroceryStoreInventorySystem.checkProduct("Bread"));
    }

    @Test
    void testUpdatingNonExistingProduct() {
        assertEquals(Status.PRODUCT_DOES_NOT_EXISTS.toString(), GroceryStoreInventorySystem.updateProduct("Tofu", "40"));
    }

    @Test
    void testRemovingValidProduct() {
        GroceryStoreInventorySystem.addProduct("Eggs","10");
        assertEquals(Status.PRODUCT_REMOVED.toString(), GroceryStoreInventorySystem.removeProduct("Eggs"));
    }

    @Test
    void testRemovingNonExistingProduct() {
        assertEquals(Status.PRODUCT_DOES_NOT_EXISTS.toString(), GroceryStoreInventorySystem.removeProduct("Pizza"));
    }

    // Additional testing for coverage

    @Test
    void testAddingInvalidProductEmptyName() {
        assertEquals(Status.PRODUCT_NAME_EMPTY.toString(), GroceryStoreInventorySystem.addProduct("","10"));
    }
    @Test
    void testAddingInvalidProductEmptyQuantity() {
        assertEquals(Status.INVALID_QUANTITY.toString(), GroceryStoreInventorySystem.addProduct("Cheese",""));
    }
    @Test
    void testAddingInvalidProductNegativeQuantity() {
        assertEquals(Status.NEGATIVE_QUANTITY.toString(), GroceryStoreInventorySystem.addProduct("Cheese","-10"));
    }
    @Test
    void testAddingInvalidProductNonNumericQuantity() {
        assertEquals(Status.INVALID_QUANTITY.toString(), GroceryStoreInventorySystem.addProduct("Cheese","ten"));
    }

    @Test
    void testUpdatingProductWithNegativeQuantity() {
        GroceryStoreInventorySystem.addProduct("Cheese","10");
        assertEquals(Status.NEGATIVE_QUANTITY.toString(), GroceryStoreInventorySystem.updateProduct("Cheese", "-40"));
    }
    @Test
    void testUpdatingProductWithNonNumericQuantity() {
        assertEquals(Status.INVALID_QUANTITY.toString(), GroceryStoreInventorySystem.updateProduct("Cheese", "forty"));
    }

    @Test
    void testCheckingEmptyProductName(){
        assertEquals("Product not found.", GroceryStoreInventorySystem.checkProduct(""));
    }

    @Test
    void viewInventory() {
        assertEquals(0, GroceryStoreInventorySystem.viewInventory());
        GroceryStoreInventorySystem.addProduct("Bread","1");
        assertEquals(1, GroceryStoreInventorySystem.viewInventory());
    }
}