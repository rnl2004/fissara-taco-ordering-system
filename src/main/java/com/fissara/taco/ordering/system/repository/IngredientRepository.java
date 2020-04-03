package com.fissara.taco.ordering.system.repository;

import com.fissara.taco.ordering.system.model.Ingredient;
import com.fissara.taco.ordering.system.model.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
