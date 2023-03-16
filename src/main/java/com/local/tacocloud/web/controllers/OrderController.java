package com.local.tacocloud.web.controllers;

import com.local.tacocloud.database.entity.TacoOrder;
import com.local.tacocloud.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@Slf4j // Create log info
@SessionAttributes("tacoOrder") // Mantain tacoOrder along the whole session
public class OrderController {

   @Autowired
   private RestTemplate res;

   @GetMapping("/current")
   public String showCurrentOrder() {
      return "orderForm";
   }

   @PostMapping
   public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {

      if(errors.hasErrors()) {
         return "orderForm";
      }

      order.setUserId(user.getId());

      log.info("Order received: {}", order);

      // It functions whether we use /api/orders (generate in controllers of folder web.api) or /data-api/tacoOrders (by spring data rest)
      TacoOrder taco = res.postForObject("https://localhost:8443/api/orders", order, TacoOrder.class);
      sessionStatus.setComplete(); // Close tacoOrder

      log.info("Order submitted: {}", taco);

      return "redirect:/";
   }

}
