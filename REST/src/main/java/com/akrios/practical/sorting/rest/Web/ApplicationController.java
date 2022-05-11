package com.akrios.practical.sorting.rest.Web;

import com.akrios.practical.sorting.rest.Service.ApplicationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApplicationController {

    private final Logger logger = Logger.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "/sortUtils", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<ResponseEntity<?>> orderUtils(
            @RequestParam List<Integer> list1,
            @RequestParam List<Integer> list2
    ){
        DeferredResult<ResponseEntity<?>> response = new DeferredResult<>();

        Mono.fromSupplier(
                        () -> applicationService.sortUtils(list1,list2))
                .subscribeOn(Schedulers.elastic()).subscribe(
                        body -> response
                                .setResult(new ResponseEntity<>(body, HttpStatus.OK)),
                        throwable -> {
                            logger.error(throwable.getLocalizedMessage(), throwable);
                            response.setResult(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
                        });
        return response;
    }
}
