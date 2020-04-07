package com.fissara.taco.ordering.system.commons.utils;

import com.fissara.taco.ordering.system.commons.exceptions.CustomerException;
import com.fissara.taco.ordering.system.commons.exceptions.IngredientException;
import com.fissara.taco.ordering.system.commons.exceptions.TacoException;
import com.fissara.taco.ordering.system.commons.messages.ErrorMessage;
import com.fissara.taco.ordering.system.model.Customer;
import com.fissara.taco.ordering.system.model.Ingredient;
import com.fissara.taco.ordering.system.model.Taco;

import java.util.List;

public class Validate {

    /**
     * Validation method to validate the customer if null
     * @param customer object to validate
     * @throws CustomerException
     */
    public void validateCustomer(Customer customer) throws CustomerException {
        if (customer == null || customer.getId() == null) {
            throw new CustomerException(ErrorMessage.INVALID_CUSTOMER_ERROR);
        }
    }

    /**
     * Validation method to validate taco name and the number of items
     * @param tacoList list to validate
     * @throws TacoException
     */
    public void validateTaco(List<Taco> tacoList) throws TacoException {
        if (tacoList.size() < 1) {
            throw new TacoException(ErrorMessage.TACO_IS_REQUIRED_ERROR);
        }
        for (Taco taco : tacoList) {
            if (taco.getName().length() < 5) {
                throw new TacoException(ErrorMessage.INVALID_TACO_NAME_ERROR);
            }
        }
    }

    /**
     * Validation method to validate ingredients name and the number of items
     * @param ingredientList list to validate
     * @throws IngredientException
     */
    public void validateIngredient(List<Ingredient> ingredientList) throws IngredientException {
        if (ingredientList.size() < 1) {
            throw new IngredientException(ErrorMessage.INGREDIENT_IS_REQUIRED_ERROR);
        }
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getName().length() < 5) {
                throw new IngredientException(ErrorMessage.INVALID_INGREDIENT_NAME_ERROR);
            }
        }
    }
}
