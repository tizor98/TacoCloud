package com.local.tacocloud.web.api;

import com.local.tacocloud.database.repository.OrderRepository;
import com.local.tacocloud.domain.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "https://127.0.0.1:8443") // For development purpose
public class OrderApiController {

   @Autowired
   public OrderRepository orderRepo;

   @PostMapping(consumes = "application/json")
   public TacoOrder postOrder(@RequestBody TacoOrder order) {
      return orderRepo.save(order);
   }

   @PutMapping(path = "/{orderId}", consumes = "application/json")
   public TacoOrder putOrder(@PathVariable("orderId") String orderId, @RequestBody TacoOrder order) {
      order.setId(orderId);
      return orderRepo.save(order);
   }

   @PatchMapping(path = "/{orderId}", consumes = "application/json")
   public TacoOrder patchOrder(@PathVariable("orderId") String orderId, @RequestBody TacoOrder patch) {
      TacoOrder order = orderRepo.findById(orderId).get();
      if (patch.getDeliveryName() != null) {
         order.setDeliveryName(patch.getDeliveryName());
      }
      if (patch.getDeliveryStreet() != null) {
         order.setDeliveryStreet(patch.getDeliveryStreet());
      }
      if (patch.getDeliveryCity() != null) {
         order.setDeliveryCity(patch.getDeliveryCity());
      }
      if (patch.getDeliveryState() != null) {
         order.setDeliveryState(patch.getDeliveryState());
      }
      if (patch.getDeliveryZip() != null) {
         order.setDeliveryZip(patch.getDeliveryZip());
      }
      if (patch.getCcNumber() != null) {
         order.setCcNumber(patch.getCcNumber());
      }
      if (patch.getCcExpiration() != null) {
         order.setCcExpiration(patch.getCcExpiration());
      }
      if (patch.getCcCVV() != null) {
         order.setCcCVV(patch.getCcCVV());
      }
      return orderRepo.save(order);
   }

   @DeleteMapping(path = "/{orderId}", consumes = "application/json")
   public ResponseEntity<Boolean> deleteOrder(@PathVariable("orderId") String orderId) {
      try {
         orderRepo.deleteById(orderId);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (EmptyResultDataAccessException e) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
   }

}
