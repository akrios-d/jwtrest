package com.akrios.practical.joke.Service;

import com.akrios.practical.joke.domain.UserDB;
import com.akrios.practical.joke.repository.UserRepository;
import com.akrios.practical.sorting.rest.Service.ApplicationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final Logger logger = Logger.getLogger(JwtUserDetailsService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDB result = userRepository.findByUsername(username);
        if (result == null) {
            logger.info("User not found with username: " + username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(result.getUsername(), passwordEncoder.encode(result.getPassword()),
                new ArrayList<>());
    }

    // Used to create a new user by endpoint
    public UserDB createUser(String username, String password) {
        UserDB userDB = new UserDB();

        userDB.setId(null);
        userDB.setUsername(username);
        userDB.setPassword(password);

        UserDB result = this.userRepository.save(userDB);

        if (result == null)
        {
            throw new RuntimeException("Error trying to create user");
        }

        return result;
    }
}
