package com.akrios.practical.joke.scheduler;

import com.akrios.practical.joke.Service.JokeService;
import com.akrios.practical.joke.domain.JokeUser;
import com.akrios.practical.joke.repository.JokeUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    private JokeUserRepository jokeUserRepository;

    @Autowired
    private JokeService jokeService;

    @Scheduled(cron = "0 * * * * *", zone = TIME_ZONE)
    public void updateJokeUsability() {
        log.info("Starting updating usable jokes");
        List<JokeUser> listJokeUser = jokeUserRepository.findByIsUsableFalse();
        ZonedDateTime now = ZonedDateTime.now();
        for (JokeUser jokeUser : listJokeUser) {
            if (jokeUser.getTimestamp().plusMinutes(5).isBefore(now)) {
                log.info("Cleaning for jid: " + jokeUser.getJokeId() + " uid:" + jokeUser.getUserDBId());
                jokeService.UpdateUsableJokeUser(jokeUser);
            }
        }
        log.info("Done updating usable jokes");
    }
}
