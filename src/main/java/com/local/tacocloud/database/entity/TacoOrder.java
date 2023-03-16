package com.local.tacocloud.database.entity;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "taco_orders")
public class TacoOrder implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   private String id;

   private Date createdAt = new Date();

   @NotBlank(message = "Delivery name is required")
   private String deliveryName;

   @NotBlank(message = "Street is required")
   private String deliveryStreet;

   @NotBlank(message = "City is required")
   private String deliveryCity;

   @NotBlank(message = "State is required")
   private String deliveryState;

   @NotBlank(message = "Zip code is required")
   private String deliveryZip;

   @CreditCardNumber(message = "Not a valid credit card number")
   private String ccNumber;

   @Pattern(regexp = "^(0[1-9]|1[0-2])([/])([2-9][0-9])$", message = "Must be formatted as MM/YY")
   private String ccExpiration;

   @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
   private String ccCVV;

   private List<Taco> tacos = new ArrayList<>();

   private String userId;

   public void addTaco(Taco taco) {
      this.tacos.add(taco);
   }

   public void removeTaco(Taco taco) {
      this.tacos.remove(taco);
   }

}
