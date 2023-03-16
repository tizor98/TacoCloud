package com.local.tacocloud.domain.service;

import com.local.tacocloud.database.entity.TacoOrder;

public interface OrderMessagingService {

   void sendOrder(TacoOrder order);

}
