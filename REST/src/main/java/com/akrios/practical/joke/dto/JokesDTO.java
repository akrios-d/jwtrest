package com.akrios.practical.joke.dto;


import com.akrios.practical.joke.domain.JokeStorage;

import java.util.ArrayList;

public class JokesDTO {

    private boolean error;
    private Long amount;
    private ArrayList<JokeStorage> jokes = new ArrayList<>();


    // Getter Methods

    public boolean getError() {
        return error;
    }

    public Long getAmount() {
        return amount;
    }

    // Setter Methods

    public void setError(boolean error) {
        this.error = error;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public ArrayList<JokeStorage> getJokes() {
        return jokes;
    }

    public void setJokes(ArrayList<JokeStorage> jokes) {
        this.jokes = jokes;
    }
}

