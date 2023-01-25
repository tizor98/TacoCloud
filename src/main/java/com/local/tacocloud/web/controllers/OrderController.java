package com.local.tacocloud.web.controllers;

import com.local.tacocloud.database.repository.OrderRepository;
import com.local.tacocloud.database.repository.UserRepository;
import com.local.tacocloud.domain.TacoOrder;
import com.local.tacocloud.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@Slf4j // Create log info
@SessionAttributes("tacoOrder") // Mantain tacoOrder along the whole session
public class OrderController {

   @Autowired
   OrderRepository orderRepository;

   @Autowired
   UserRepository userRepository;

   @GetMapping("/current")
   public String showCurrentOrder() {
      return "orderForm";
   }

   @PostMapping
   public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {

      if(errors.hasErrors()) {
         return "orderForm";
      }

      order.setUser(user);

      log.info("Order received: {}", order);

      orderRepository.save(order);
      sessionStatus.setComplete(); // Close tacoOrder

      log.info("Order submitted: {}", order);

      return "redirect:/";
   }

}
