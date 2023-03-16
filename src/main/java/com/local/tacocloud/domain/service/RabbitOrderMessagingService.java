package com.local.tacocloud.domain.service;

import com.local.tacocloud.database.entity.TacoOrder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

   private final RabbitTemplate rabbitMq;

   @Autowired
   RabbitOrderMessagingService(RabbitTemplate rabbitMq) {
      this.rabbitMq = rabbitMq;
   }

   /* Alternative implementation for sendOrder with send method
   @Override
   public void sendOrder(TacoOrder order) {
      MessageConverter converter = rabbitMq.getMessageConverter();
      MessageProperties properties = new MessageProperties();
      Message message = converter.toMessage(order, properties);
      rabbitMq.send("taco-cloud.order", message);
   }
   */

   @Override
   public void sendOrder(TacoOrder order) {
      rabbitMq.convertAndSend("taco-cloud.order", order,
         message -> {
            MessageProperties props = message.getMessageProperties();
            props.setHeader("X_ORDER_SOURCE", "web");
            return message;
      });
   }

}
