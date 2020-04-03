package com.fissara.taco.ordering.system.service;


import com.fissara.taco.ordering.system.model.Ingredient;
import com.fissara.taco.ordering.system.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
}
