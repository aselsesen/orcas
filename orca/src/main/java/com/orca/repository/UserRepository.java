package com.orca.repository;

import com.orca.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {


User findByEmail(String email);


}
