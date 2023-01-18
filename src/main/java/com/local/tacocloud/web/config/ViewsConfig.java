package com.local.tacocloud.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// This configuration class replaces the need for a HomeController class
@Configuration
public class ViewsConfig implements WebMvcConfigurer {

   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/").setViewName("home");
   }

}
