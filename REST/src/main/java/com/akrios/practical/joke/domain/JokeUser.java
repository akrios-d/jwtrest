package com.akrios.practical.joke.domain;

import com.akrios.practical.joke.domain.id.JokeUserId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "joke_user", schema = "public")
@IdClass(JokeUserId.class)
public class JokeUser implements Serializable {

    private static final long serialVersionUID = -464934002092837925L;

    @Id
    @Column(name = "jokeId")
    private Long jokeId;

    @Id
    @Column(name = "userDBId")
    private Long userDBId;

    @Column(name = "jokeCount")
    private Long jokeCount;

    @Column(name = "isUsable")
    private Boolean isUsable;

    @Column(name = "timestamp", columnDefinition = "timestamp with time zone")
    private ZonedDateTime timestamp;

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

    public Long getJokeCount() {
        return jokeCount;
    }

    public void setJokeCount(Long jokeCount) {
        this.jokeCount = jokeCount;
    }

    public Boolean getUsable() {
        return isUsable;
    }

    public void setUsable(Boolean usable) {
        isUsable = usable;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
