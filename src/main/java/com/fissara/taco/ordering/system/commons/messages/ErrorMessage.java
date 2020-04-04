package com.fissara.taco.ordering.system.commons.messages;

public class ErrorMessage {

    public static final String INVALID_CUSTOMER_ERROR = "Customer is required";
    public static final String INVALID_TRANSACTION_ERROR = "Invalid transaction";
    public static final String INGREDIENT_IS_REQUIRED_ERROR = "Ingredient(s) is required at least one";
    public static final String TACO_IS_REQUIRED_ERROR = "Taco is required at least one";
    public static final String INVALID_TACO_NAME_ERROR = "Taco name is required at least 5 characters";

    private ErrorMessage() {}
}
