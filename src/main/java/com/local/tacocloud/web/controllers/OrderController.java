package com.local.tacocloud.web.controllers;

import com.local.tacocloud.database.repository.OrderRepository;
import com.local.tacocloud.domain.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@Slf4j // Create log info
@SessionAttributes("tacoOrder") // Mantain tacoOrder along the whole session
public class OrderController {

   @Autowired
   OrderRepository orderRepository;

   @GetMapping("/current")
   public String showCurrentOrder() {
      return "orderForm";
   }

   @PostMapping
   public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {

      if(errors.hasErrors()) {
         return "orderForm";
      }

      log.info("Order submitted: {}", order);
      orderRepository.save(order);
      sessionStatus.setComplete(); // Close tacoOrder

      return "redirect:/";
   }

}
