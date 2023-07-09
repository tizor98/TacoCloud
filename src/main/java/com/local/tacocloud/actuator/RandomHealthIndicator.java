package com.local.tacocloud.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class RandomHealthIndicator implements HealthIndicator {
   @Override
   public Health health() {
      int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
      if(hour < 9) {
         return Health
            .outOfService()
            .withDetail("reason", "It's too early in the morning")
            .withDetail("hour", hour)
            .build();
      }

      if(Math.random() <= 0.1) {
         return Health
            .down()
            .withDetail("reason", "It is what it is")
            .build();
      }

      return Health.up().withDetail("reason", "All is good!").build();
   }
}
