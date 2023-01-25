package com.local.tacocloud.web.controllers;

import com.local.tacocloud.database.repository.IngredientRepository;
import com.local.tacocloud.domain.Ingredient;
import com.local.tacocloud.domain.Ingredient.Type;
import com.local.tacocloud.domain.Taco;
import com.local.tacocloud.domain.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@Slf4j // Create log info
@SessionAttributes("tacoOrder") // Mantain tacoOrder along the whole session
public class DesignTacoController {

   @Autowired
   private IngredientRepository ingredientRepository;

   @ModelAttribute
   public void addIngredientsToModel (Model model) {
      List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();

      Type[] types = Type.values();

      for (Type type : types) {
         model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
      }
      log.info("Model info: {}", model);

   }

   @ModelAttribute(name = "tacoOrder")
   public TacoOrder order() {
      return new TacoOrder();
   }

   @ModelAttribute(name = "taco")
   public Taco taco() {
      return new Taco();
   }

   @GetMapping
   public String showDesignForm() {
      return "design";
   }

   private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
      return ingredients
         .stream().
         filter( ingredient -> ingredient.getType().equals(type))
         .collect(Collectors.toList());
   }

   @PostMapping
   public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {

      if(errors.hasErrors()) {
         return "design";
      }

      tacoOrder.addTaco(taco);
      log.info("Processing taco: {}", taco);

      return "redirect:/orders/current";
   }

}
