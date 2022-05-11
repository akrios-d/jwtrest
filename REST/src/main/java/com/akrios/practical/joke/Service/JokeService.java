package com.akrios.practical.joke.Service;

import com.akrios.practical.joke.domain.JokeStorage;
import com.akrios.practical.joke.domain.JokeUser;
import com.akrios.practical.joke.domain.UserDB;
import com.akrios.practical.joke.repository.JokeStorageRepository;
import com.akrios.practical.joke.repository.JokeUserRepository;
import com.akrios.practical.joke.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JokeService {

    private final Logger logger = Logger.getLogger(JokeService.class);

    private final int maxJokesValue = 5;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JokeUserRepository jokeUserRepository;

    @Autowired
    JokeStorageRepository jokeStorageRepository;

    public List<JokeStorage> findJokes(String username){
        // Find the user using username from jwt to retrieve userId
        UserDB user = userRepository.findByUsername(username);

        // Recover all jokes
        List<JokeStorage> allJokes = jokeStorageRepository.findAll();

        // List selectedJokes
        List<JokeStorage> selectedJokes = new ArrayList<>();

        for (JokeStorage jk : allJokes) {
            if (selectedJokes.size() < maxJokesValue) {

                JokeUser jkUser = jokeUserRepository.findByUserDBIdAndJokeId(user.getId(),jk.getId());

                if (jkUser == null) {
                    saveJokeUser(jk.getId(), user.getId(), 0L);
                    selectedJokes.add(jk);
                } else {
                    if (jkUser.getUsable()) {
                        saveJokeUser(jk.getId(), user.getId(), jkUser.getJokeCount());
                        selectedJokes.add(jk);
                    }
                }
            }
        }

        return selectedJokes;
    }

    public JokeStorage saveJoke(String joke) {
        JokeStorage jokeStorage = new JokeStorage();
        jokeStorage.setId(null);
        jokeStorage.setJoke(joke);

        JokeStorage result = this.jokeStorageRepository.save(jokeStorage);

        if (result == null)
        {
            throw new RuntimeException("Error trying to save joke");
        }

        return result;
    }

    private JokeUser saveJokeUser(Long jokeId, Long userId, Long count) {
        JokeUser jokeUser = new JokeUser();
        jokeUser.setJokeId(jokeId);
        jokeUser.setUserDBId(userId);
        jokeUser.setUsable(false);
        jokeUser.setTimestamp(ZonedDateTime.now());
        jokeUser.setJokeCount(count+1);

        JokeUser result = this.jokeUserRepository.save(jokeUser);

        if (result == null)
        {
            throw new RuntimeException("Error trying to save jokeUser");
        }

        return result;
    }

    public JokeUser UpdateUsableJokeUser(JokeUser jokeUser) {
        JokeUser jokeUserUpd = new JokeUser();
        jokeUserUpd.setJokeId(jokeUser.getJokeId());
        jokeUserUpd.setUserDBId(jokeUser.getUserDBId());
        jokeUserUpd.setTimestamp(jokeUser.getTimestamp());
        jokeUserUpd.setJokeCount(jokeUser.getJokeCount());
        jokeUserUpd.setUsable(true);

        JokeUser result = this.jokeUserRepository.save(jokeUserUpd);

        if (result == null)
        {
            throw new RuntimeException("Error trying to update jokeUser");
        }

        return result;
    }
}
