package services;

import models.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private ProductService productService;
    @BeforeEach
    void setup(){
        productService = new ProductService();
        productService.addProduct("Bread", 15);
    }
    @Test
    void shouldReturnProductAddedAddingValidProduct() {
        assertEquals(Status.PRODUCT_ADDED, productService.addProduct("Cheese",10));
    }
    @Test
    void shouldReturnProductExistsAddingValidProduct() {
        assertEquals(Status.ADDING_EXISTING_PRODUCT, productService.addProduct("Bread",10));
    }
    @Test
    void shouldReturnNameEmptyAddingInvalidProductEmptyName() {
        assertEquals(Status.PRODUCT_NAME_EMPTY, productService.addProduct("",10));
    }
    @Test
    void shouldReturnNegativeQuantityAddingInvalidProductNegativeQuantity() {
        assertEquals(Status.NEGATIVE_QUANTITY, productService.addProduct("Cheese",-10));
    }

    @Test
    void shouldReturnQuantityOfExistingProduct() {
        assertEquals(15, productService.checkProduct("Bread"));
    }
    @Test
    void shouldReturnNegativeOfNonExistingProduct() {
        assertEquals(-1, productService.checkProduct("Flour"));
    }
    @Test
    void shouldReturnNegativeWithEmptyProductName() {
        assertEquals(-1, productService.checkProduct(""));
    }

    @Test
    void shouldReturnProductUpdatedUpdatingValidProduct() {
        assertEquals(Status.PRODUCT_UPDATED, productService.updateProduct("Bread", 100));
    }
    @Test
    void shouldReturnProductDoesNotExistsUpdatingNonExistingProduct() {
        assertEquals(Status.PRODUCT_DOES_NOT_EXISTS, productService.updateProduct("Flour", 40));
    }
    @Test
    void shouldReturnNegativeQuantityUpdatingProductWithNegativeQuantity() {
        assertEquals(Status.NEGATIVE_QUANTITY, productService.updateProduct("Bread", -40));
    }

    @Test
    void shouldReturnProductRemovedRemovingValidProduct() {
        assertEquals(Status.PRODUCT_REMOVED, productService.removeProduct("Bread"));
    }
    @Test
    void shouldReturnProductDoesNotExistsRemovingNonExistingProduct() {
        assertEquals(Status.PRODUCT_DOES_NOT_EXISTS, productService.removeProduct("Flour"));
    }

    @Test
    void getProducts() {
        assertEquals(1, productService.getProducts().size());
    }
}