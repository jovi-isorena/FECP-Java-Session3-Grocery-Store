import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ProductService;

import static org.junit.jupiter.api.Assertions.*;

class GroceryStoreInventorySystemTest {
//    private GroceryStoreInventorySystem inventorySystem;
//    @BeforeEach
//    void setup(){
//        inventorySystem = new ProductService();
//    }
    @BeforeAll
    static void setup(){
        GroceryStoreInventorySystem.addProduct("Bread","5");
    }
    @Test
    void shouldReturnTrueAddingValidProduct() {
        assertTrue(GroceryStoreInventorySystem.addProduct("Cheese","10"));
    }
    @Test
    void shouldReturnFalseAddingInvalidProductEmptyName() {
        assertFalse(GroceryStoreInventorySystem.addProduct("","10"));
    }
    @Test
    void shouldReturnFalseAddingInvalidProductEmptyQuantity() {
        assertFalse(GroceryStoreInventorySystem.addProduct("Cheese",""));
    }
    @Test
    void shouldReturnFalseAddingInvalidProductNegativeQuantity() {
        assertFalse(GroceryStoreInventorySystem.addProduct("Cheese","-10"));
    }
    @Test
    void shouldReturnFalseAddingInvalidProductNonNumericQuantity() {
        assertFalse(GroceryStoreInventorySystem.addProduct("Cheese","ten"));
    }

    @Test
    void shouldReturnNonNegativeProductIsInStock() {
        assertTrue(GroceryStoreInventorySystem.checkProduct("Bread") >= 0);
    }
    @Test
    void shouldReturnNegativeProductIsNotInStock() {
        assertEquals(-1,GroceryStoreInventorySystem.checkProduct("Pizza"));
    }

    @Test
    void shouldReturnTrueUpdatingValidProduct() {
        assertTrue(GroceryStoreInventorySystem.updateProduct("Bread", "40"));
    }
    @Test
    void shouldReturnFalseUpdatingNonExistingProduct() {
        assertFalse(GroceryStoreInventorySystem.updateProduct("Flour", "40"));
    }
    @Test
    void shouldReturnFalseUpdatingProductWithNegativeQuantity() {
        assertFalse(GroceryStoreInventorySystem.updateProduct("Cheese", "-40"));
    }
    @Test
    void shouldReturnFalseUpdatingProductWithNonNumericQuantity() {
        assertFalse(GroceryStoreInventorySystem.updateProduct("Cheese", "forty"));
    }

    @Test
    void shouldReturnTrueRemovingValidProduct() {
        assertTrue(GroceryStoreInventorySystem.removeProduct("Cheese"));
    }
    @Test
    void shouldReturnFalseRemovingNonExistingProduct() {
        assertFalse(GroceryStoreInventorySystem.removeProduct("Flour"));
    }

    @Test
    void viewInventory() {
        assertEquals(1, GroceryStoreInventorySystem.viewInventory());
    }
}