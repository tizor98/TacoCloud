package com.local.tacocloud.database.repository;

import com.local.tacocloud.domain.TacoOrder;

public interface OrderRepository {

   TacoOrder save(TacoOrder tacoOrder);

}
