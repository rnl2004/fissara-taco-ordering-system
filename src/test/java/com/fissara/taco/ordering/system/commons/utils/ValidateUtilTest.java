package com.fissara.taco.ordering.system.commons.utils;

import com.fissara.taco.ordering.system.commons.exception.IngredientException;
import com.fissara.taco.ordering.system.commons.exception.TacoException;
import com.fissara.taco.ordering.system.model.Ingredient;
import com.fissara.taco.ordering.system.model.Taco;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.LinkedList;
import java.util.List;

public class ValidateUtilTest {

    @Test
    public void tacoNameValidationTest() {

        // Assuming one of the taco name is less than 5 characters
        Taco taco = new Taco();
        taco.setId(1L);
        taco.setName("Test");

        Taco taco2 = new Taco();
        taco2.setId(1L);
        taco2.setName("Test taco 2");

        List<Taco> tacoList = new LinkedList<>();
        tacoList.add(taco);
        tacoList.add(taco2);

        Validate validate = new Validate();

        Assertions.assertThrows(TacoException.class, () -> { validate.validateTaco(tacoList); });
    }

    @Test
    public void tacoSizeValidationTest() {

        // Assuming tacoList is empty
        List<Taco> tacoList = new LinkedList<>();

        Validate validate = new Validate();

        Assertions.assertThrows(TacoException.class, () -> { validate.validateTaco(tacoList); });

    }

    @Test
    public void ingredientNameValidationTest() {

        // Assuming one of the ingredient name is less than 5 characters
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Test");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("Test Ingredient 2");

        List<Ingredient> ingredientList = new LinkedList<>();
        ingredientList.add(ingredient);
        ingredientList.add(ingredient2);

        Validate validate = new Validate();

        Assertions.assertThrows(IngredientException.class, () -> { validate.validateIngredient(ingredientList); });
    }

    @Test
    public void ingredientSizeValidationTest() {

        // Assuming ingredientList is empty
        List<Ingredient> ingredientList = new LinkedList<>();

        Validate validate = new Validate();

        Assertions.assertThrows(IngredientException.class, () -> { validate.validateIngredient(ingredientList); });
    }
}
