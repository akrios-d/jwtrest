package com.akrios.practical.sorting.soap.endpoint;

import com.akrios.practical.sorting.soap.dto.SortingUtilsRequest;
import com.akrios.practical.sorting.soap.dto.SortingUtilsResponse;
import com.akrios.practical.sorting.soap.service.SortUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Endpoint
public class SortEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private SortUtilsService sortUtilsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sortingUtilsRequest")
    @ResponsePayload
    public SortingUtilsResponse getOrderUtils(@RequestPayload SortingUtilsRequest request) {
        // Create as LinkedList then pass to service layer
        // https://stackoverflow.com/questions/2965747/why-do-i-get-an-unsupportedoperationexception-when-trying-to-remove-an-element-f
        List<Integer> listOne = new LinkedList<>(Arrays.asList(request.getListOne()));
        List<Integer> listTwo = new LinkedList<>(Arrays.asList(request.getListTwo()));

        SortingUtilsResponse response = new SortingUtilsResponse();
        response.setSortedList(sortUtilsService.sortUtils(listOne,listTwo));

        return response;
    }
}