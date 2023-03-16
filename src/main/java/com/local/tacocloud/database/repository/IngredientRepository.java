package com.local.tacocloud.database.repository;

import com.local.tacocloud.database.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
