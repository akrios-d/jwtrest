package com.akrios.practical.joke.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userdb", schema = "public")
public class UserDB implements Serializable {

    private static final long serialVersionUID = -5935092148306480935L;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="user_id_seq", sequenceName="user_id_seq", schema = "public", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;

    @Column(name = "username", unique=true)
    private String username;

    // Note: Since it's an exercise won't crypt to save/load from DB
    @Column(name = "password")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
