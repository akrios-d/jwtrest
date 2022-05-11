package com.akrios.practical.sorting.rest.Service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import static com.akrios.practical.sorting.SortingUtils.quickSort;

@Service
public class ApplicationService {

    private final Logger logger = Logger.getLogger(ApplicationService.class);

    public HashMap<Integer,String> sortUtils(List<Integer> list, List<Integer> list2) {
        // Add list 2 to list 1
        list.addAll(list2);

        // Sort the list
        quickSort(list,0,list.size()-1);

        return  prepareResponse(list);
    }

    private HashMap<Integer, String> prepareResponse(List<Integer> list) {
        // Create HashMap to return as dictionary
        HashMap<Integer,String> response = new HashMap<>();

        // iterate over sorted list
        list.forEach( n -> {
            if (n%2 == 0) {
                logger.info("I'm even " + n);
                response.put(n,"Even");
            }
            else {
                logger.info("I'm odd " + n);
                response.put(n,"Odd");
            }
        });

        return response;
    }
}
