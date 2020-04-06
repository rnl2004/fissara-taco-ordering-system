package com.fissara.taco.ordering.system.commons.utils;

import com.fissara.taco.ordering.system.commons.exception.CustomerException;
import com.fissara.taco.ordering.system.commons.exception.IngredientException;
import com.fissara.taco.ordering.system.commons.exception.TacoException;
import com.fissara.taco.ordering.system.commons.messages.ErrorMessage;
import com.fissara.taco.ordering.system.model.Customer;
import com.fissara.taco.ordering.system.model.Ingredient;
import com.fissara.taco.ordering.system.model.Taco;

import java.util.List;

public class Validate {

    public void validateCustomer(Customer customer) throws CustomerException {
        if (customer == null || customer.getId() == null) {
            throw new CustomerException(ErrorMessage.CUSTOMER_IS_REQUIRED_ERROR);
        }
    }

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
