package com.local.tacocloud.database.config;

import com.local.tacocloud.database.repository.IngredientRepository;
import com.local.tacocloud.domain.Ingredient;
import com.local.tacocloud.domain.Ingredient.Type;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialLoadData {

   @Bean
   public ApplicationRunner loadData(IngredientRepository repo) {
      return args -> {
         repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
         repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
         repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
         repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
         repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
         repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
         repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
         repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
         repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
         repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
      };
   }

}
