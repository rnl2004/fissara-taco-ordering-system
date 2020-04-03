package com.fissara.taco.ordering.system;

import com.fissara.taco.ordering.system.model.Ingredient;
import com.fissara.taco.ordering.system.model.Order;
import com.fissara.taco.ordering.system.model.Taco;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FissaraTacoOrderingSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(FissaraTacoOrderingSystemApplication.class, args);

		Ingredient ingredient = new Ingredient();
		ingredient.setId(201L);
		ingredient.setName("test ingredient");

		Set<Ingredient> ingredients = new HashSet<>();
		ingredients.add(ingredient);

		Taco taco = new Taco();
		taco.setId(101L);
		taco.setName("test taco");
		taco.setIngredients(ingredients);

		Taco taco2 = new Taco();
		taco2.setId(102L);
		taco2.setName("test taco2");

		Set<Taco> tacos = new HashSet<>();
		tacos.addAll(Arrays.asList(taco, taco2));

		Order order = new Order();
		order.setId(1L);
		order.setTacos(tacos);

		System.out.println(order.toString());
	}

}
