package com.local.tacocloud.database.repository;

import com.local.tacocloud.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, String> {

}
