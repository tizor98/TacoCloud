package com.local.tacocloud.web.controllers;

import com.local.tacocloud.database.repository.UserRepository;
import com.local.tacocloud.domain.RegistrationForm;
import com.local.tacocloud.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@Slf4j // Create log info
public class RegisterController {

   private final UserRepository userRepository;

   private final PasswordEncoder passwordEncoder;

   @Autowired
   public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
   }

   @GetMapping
   public String registerForm() {
      return "registerForm";
   }

   @PostMapping
   public String processRegistration(RegistrationForm form) {
      User user = userRepository.save(form.toUser(passwordEncoder));
      log.info("User registred: {}", user);
      return "redirect:/login";
   }

}
