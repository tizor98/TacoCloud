package com.local.tacocloud.database.repository;

import com.local.tacocloud.database.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

   User findByUsername(String username);

}
