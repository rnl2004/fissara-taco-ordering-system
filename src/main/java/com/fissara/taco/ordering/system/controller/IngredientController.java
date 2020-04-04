package com.fissara.taco.ordering.system.controller;

import com.fissara.taco.ordering.system.model.Ingredient;
import com.fissara.taco.ordering.system.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/create")
    public Ingredient create(@RequestBody String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        return ingredientService.save(ingredient);
    }
}
