package com.local.tacocloud.database.repository;

import com.local.tacocloud.database.entity.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, String> {
   // PagingAndSortingRepository also allows Spring Data Rest to expose an API with page, size and sort params
}
