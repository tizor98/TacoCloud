package com.local.tacocloud.actuator;

import com.local.tacocloud.database.entity.Ingredient;
import com.local.tacocloud.database.entity.Taco;
import com.local.tacocloud.database.entity.TacoOrder;
import io.micrometer.core.instrument.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class TacoMetrics {

   private static final String METRIC_NAME = "taco.cloud";
   private static final String TAG_NAME = "ingredient";
   private static final Logger log = LoggerFactory.getLogger(TacoMetrics.class);
   private final MeterRegistry meterRegistry;

   @Autowired
   public TacoMetrics(MeterRegistry meterRegistry) {
      this.meterRegistry = meterRegistry;
   }

   @Pointcut("execution(* com.local.tacocloud.database.repository.OrderRepository.save*(..))")
   public void repositoryOperations() {}

   @AfterReturning(pointcut = "repositoryOperations()", returning = "order")
   public void onAfterCreate(JoinPoint joinPoint, TacoOrder order) {
      List<Taco> tacos = order.getTacos();
      for(Taco taco: tacos) {
         for (Ingredient ingredient : taco.getIngredients()) {
            meterRegistry.counter(METRIC_NAME, TAG_NAME, ingredient.getId()).increment();
         }
      }
      log.info("Tacos info for this order: {}", order.getTacos());
   }

}
