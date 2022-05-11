package com.akrios.practical.joke.Web;


import com.akrios.practical.joke.JsonBodyHandler;
import com.akrios.practical.joke.Service.JokeService;
import com.akrios.practical.joke.Service.JwtUserDetailsService;
import com.akrios.practical.joke.domain.JokeStorage;
import com.akrios.practical.joke.dto.JokesDTO;
import com.akrios.practical.joke.jwt.JwtRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.LinkedHashSet;
import java.util.List;

@RestController
@CrossOrigin
public class NoAuthController {

    private final Logger logger = Logger.getLogger(NoAuthController.class);

    private final int jokeAmount = 30;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JokeService jokeService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<?>> createUser(@RequestBody JwtRequest authenticationRequest) throws Exception {
        DeferredResult<ResponseEntity<?>> response = new DeferredResult<>();

        Mono.fromSupplier(
                        () -> userDetailsService.createUser(authenticationRequest.getUsername(), authenticationRequest.getPassword()))
                .subscribeOn(Schedulers.elastic()).subscribe(
                        body -> response
                                .setResult(new ResponseEntity<>(body, HttpStatus.OK)),
                        throwable -> {
                            logger.error(throwable.getLocalizedMessage(), throwable);
                            response.setResult(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
                        });
        return response;
    }

    @RequestMapping(value = "/populatejokes", method = RequestMethod.GET)
    public LinkedHashSet<String> populateJokes() throws IOException, InterruptedException {
        logger.info("Request to populate jokes DB");
        LinkedHashSet<String> ret = new LinkedHashSet<>();

        while (ret.size() < jokeAmount){
            String url = "https://v2.jokeapi.dev/joke/Any?type=single&amount=10";

            // create a client
            var client = HttpClient.newHttpClient();
            // create a request
            var request = HttpRequest.newBuilder(
                            URI.create(url))
                    .build();

            // use the client to send the request
            logger.info("Sending request");
            var response = client.send(request, new JsonBodyHandler<>(JokesDTO.class));

            logger.info("getting jokes");
            List<JokeStorage> jokes = response.body().get().getJokes();

            if (jokes != null) {
                logger.info("populating jokes");
                jokes.forEach(joke -> {
                    if (ret.size() < jokeAmount) {
                        String jk = joke.getJoke();
                        if(ret.add(jk)) {
                            jokeService.saveJoke(jk);
                        }
                    }
                });
            }
        }
        logger.info("Jokes Size: " + ret.size());
        return ret;
    }
}
