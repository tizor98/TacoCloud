package com.local.tacocloud.web.api;

import com.local.tacocloud.database.repository.IngredientRepository;
import com.local.tacocloud.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "https://127.0.0.1:8443") // For development purpose
public class IngridientApiController {

   @Autowired
   public IngredientRepository ingredientRepo;

   @GetMapping
   public List<Ingredient> ingredientList() {
      return (List<Ingredient>) ingredientRepo.findAll();
   }

}
