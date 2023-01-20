package com.local.tacocloud.database.repository;

import com.local.tacocloud.domain.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

   List<TacoOrder> findByDeliveryZip(String deliveryZip);

   // Read, Find and Get are synonymous for Spring Data JPA
   List<TacoOrder> readOrdersByDeliveryZipAndCreatedAtBetween(String deliveryZip, Date startDate, Date endDate);

}
