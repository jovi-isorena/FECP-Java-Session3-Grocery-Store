package models;

public enum Status{
    PRODUCT_ADDED("Product successfully added."),
    PRODUCT_UPDATED("Product successfully updated."),
    PRODUCT_REMOVED("Product successfully removed."),
    PRODUCT_NAME_EMPTY("Product name must not be empty."),
    ADDING_EXISTING_PRODUCT("Product already exists. Previous quantity overwritten."),
    PRODUCT_DOES_NOT_EXISTS("Product not found."),
    INVALID_QUANTITY("Quantity must be numeric."),
    NEGATIVE_QUANTITY("Quantity must not be less than 0.");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
