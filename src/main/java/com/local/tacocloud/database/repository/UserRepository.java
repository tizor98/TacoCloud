package com.local.tacocloud.database.repository;

import com.local.tacocloud.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

   User findByUsername(String username);

}
