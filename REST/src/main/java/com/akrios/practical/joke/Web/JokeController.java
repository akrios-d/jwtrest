package com.akrios.practical.joke.Web;

import com.akrios.practical.joke.Service.JokeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/api/v1")
public class JokeController {

    private final Logger logger = Logger.getLogger(JokeController.class);

    @Autowired
    private JokeService jokeService;

    @RequestMapping(value = "/joke", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<ResponseEntity<?>> getJokes(){
        DeferredResult<ResponseEntity<?>> response = new DeferredResult<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Mono.fromSupplier(
                        () -> jokeService.findJokes(user.getUsername()))
                .subscribeOn(Schedulers.elastic()).subscribe(
                        body -> response
                                .setResult(new ResponseEntity<>(body, HttpStatus.OK)),
                        throwable -> {
                            logger.error(throwable.getLocalizedMessage(), throwable);
                            response.setResult(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
                        });
        return response;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return user.getUsername();
    }
}
