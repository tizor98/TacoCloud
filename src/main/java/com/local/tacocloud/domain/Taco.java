package com.local.tacocloud.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "tacos")
@RestResource(rel = "tacos", path = "tacos")
public class Taco {

   @Id
   private String id;

   @NotNull
   @Size(min = 5, message = "Name must be at least 5 characters long")
   private String name;

   private Date createdAt = new Date();

   @Size(min = 1, message = "You must choose at least one ingredient")
   private List<Ingredient> ingredients = new ArrayList<>();

   public void addIngredient(Ingredient ingredient) {
      this.ingredients.add(ingredient);
   }

}
