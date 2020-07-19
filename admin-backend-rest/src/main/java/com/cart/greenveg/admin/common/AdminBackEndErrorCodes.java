package com.cart.greenveg.admin.common;

public enum AdminBackEndErrorCodes {

    GENERIC_FRAMEWORK_ERROR("GVC_1001", "A database error has occured."),
    BAD_REQUEST("GVC_1002", "BAD Request"),
    INVALID_CATEGORY_ID("GVC_1004", "Category ID Not Found"),
    INVALID_SUB_CATEGORY_ID("GVC_1005", "SUB Category ID Not Found"),
    INVALID_PRODUCT_CATEGORY_ID("GVC_1006", "Product Category ID Not Found")




    ;

    private final String code;
    private final String description;

    private AdminBackEndErrorCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

}
