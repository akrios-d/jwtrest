package com.akrios.practical.joke.repository;

import com.akrios.practical.joke.domain.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDB, Long> {

    UserDB findByUsername(String username);
}
