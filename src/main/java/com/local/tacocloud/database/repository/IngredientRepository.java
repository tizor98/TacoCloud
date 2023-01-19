package com.local.tacocloud.database.repository;

import com.local.tacocloud.domain.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {

   List<Ingredient> findAll();

   Optional<Ingredient> findById(String id);

   Ingredient save(Ingredient ingredient);

}
