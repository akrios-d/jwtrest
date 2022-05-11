package com.akrios.practical.sorting.soap.service;

import com.akrios.practical.sorting.rest.Service.ApplicationService;
import com.akrios.practical.sorting.soap.dto.SortedList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

import static com.akrios.practical.sorting.SortingUtils.quickSort;

@Component
public class SortUtilsService {

    private final Logger logger = Logger.getLogger(ApplicationService.class);

    public SortedList[] sortUtils(List<Integer> list, List<Integer> list2){
        // Add list 2 to list 1
        list.addAll(list2);

        // Sort the list
        quickSort(list,0,list.size()-1);

        return  prepareResponse(list);
    }

    private SortedList[] prepareResponse(List<Integer> list) {
        // Create SortedList[] to return
        SortedList[] response = new SortedList[list.size()];
        // Using hash map as helper so will avoid duplicates
        HashMap<Integer,String> helper = new HashMap<>();

        // iterate over ordered list
        for(int i = 0; i < list.size(); i++) {
            Integer value = list.get(i);
            if (!helper.containsKey(value)) {
                if (value%2 == 0) {
                    logger.info("I'm even " + value);

                    helper.put(value,"Even");

                    response[i] = new SortedList();
                    response[i].setValue(value);
                    response[i].setText("Even");
                }
                else {
                    logger.info("I'm odd " + value);

                    helper.put(value,"Odd");

                    response[i] = new SortedList();
                    response[i].setValue(value);
                    response[i].setText("Odd");
                }
            }
        }

        return response;
    }
}
