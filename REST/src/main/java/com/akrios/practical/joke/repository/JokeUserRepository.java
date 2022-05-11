package com.akrios.practical.joke.repository;

import com.akrios.practical.joke.domain.JokeUser;
import com.akrios.practical.joke.domain.id.JokeUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeUserRepository extends JpaRepository<JokeUser, JokeUserId> {
    JokeUser findByUserDBIdAndJokeId(Long userDBId, Long jokeId);
    List<JokeUser> findByIsUsableFalse();
}
