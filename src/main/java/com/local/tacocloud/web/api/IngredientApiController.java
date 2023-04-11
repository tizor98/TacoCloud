package com.local.tacocloud.web.api;

import com.local.tacocloud.database.repository.IngredientRepository;
import com.local.tacocloud.database.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "https://127.0.0.1:8443") // For development purpose
public class IngredientApiController {

   private final IngredientRepository ingredientRepo;

   @Autowired
   public IngredientApiController(IngredientRepository ingredientRepo) {
      this.ingredientRepo = ingredientRepo;
   }

   @GetMapping
   public List<Ingredient> ingredientList() {
      return (List<Ingredient>) ingredientRepo.findAll();
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
      return ingredientRepo.save(ingredient);
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteIngredient(@PathVariable("id") String ingredientId) {
      ingredientRepo.deleteById(ingredientId);
   }

}
