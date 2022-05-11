package com.akrios.practical.joke.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "joke_storage", schema = "public")
public class JokeStorage implements Serializable {

    private static final long serialVersionUID = 6055948772125435722L;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="joke_id_seq", sequenceName="joke_id_seq", schema = "public", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "joke_id_seq")
    private Long id;

    @Column(name = "joke", unique=true, length=512)
    private String joke;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
