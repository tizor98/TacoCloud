package com.local.tacocloud.domain;

import lombok.Data;

@Data
// @Table would be optional here, as always
public class IngredientRef {

   private final String ingredient;

}
