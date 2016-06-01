package com.excilys.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.excilys.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("FROM User user  WHERE user.name = (:lastName)")
    public User findUserByName(@Param("lastName") String name);
}
