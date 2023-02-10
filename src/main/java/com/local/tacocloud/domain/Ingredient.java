package com.local.tacocloud.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "ingredients")
// @RequiredArgsConstructor
// Can be useful when data maneger uses the NoArgsConstructor Lombok can remove the ArgsConstructor unless this label is here
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

   @Id
   private String id;
   private String name;
   private Type type;

   public enum Type {
      WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
   }

}
