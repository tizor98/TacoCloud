package com.local.tacocloud.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
// @RequiredArgsConstructor
// Can be useful when JPA uses the NoArgsConstructor the @Data annotation removes the ArgsConstructor unless this label is here
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

   @Id
   private final String id;
   private final String name;
   private final Type type;

   @Override
   public boolean isNew() {
      return true;
   }

   public enum Type {
      WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
   }

}
