package com.akrios.practical.joke.repository;

import com.akrios.practical.joke.domain.JokeStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeStorageRepository extends JpaRepository<JokeStorage, Long> {
}
