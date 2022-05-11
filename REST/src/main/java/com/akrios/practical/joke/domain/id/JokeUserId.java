package com.akrios.practical.joke.domain.id;

import java.io.Serializable;
import java.util.Objects;

public class JokeUserId implements Serializable {

    private Long jokeId;

    private Long userDBId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JokeUserId)) return false;
        JokeUserId that = (JokeUserId) o;
        return Objects.equals(getUserDBId(), that.getUserDBId()) &&
                Objects.equals(getJokeId(), that.getJokeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJokeId(), getUserDBId());
    }

    public Long getJokeId() {
        return jokeId;
    }

    public void setJokeId(Long jokeId) {
        this.jokeId = jokeId;
    }

    public Long getUserDBId() {
        return userDBId;
    }

    public void setUserDBId(Long userDBId) {
        this.userDBId = userDBId;
    }
}
