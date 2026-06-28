package com.belle.springsecurityjwt.repository;

import com.belle.springsecurityjwt.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, String> {
    CustomUser findByUsername(String username);
}
