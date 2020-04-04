package com.fissara.taco.ordering.system.repository;

import com.fissara.taco.ordering.system.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findIngredientsByTacoId(Long id);
}
