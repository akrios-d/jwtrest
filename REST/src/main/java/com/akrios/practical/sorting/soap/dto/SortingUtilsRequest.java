package com.akrios.practical.sorting.soap.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "listOne",
        "listTwo"
})
@XmlRootElement(namespace="http://spring.io/guides/gs-producing-web-service", name="sortingUtilsRequest")
public class SortingUtilsRequest {

    @XmlElement(namespace="http://spring.io/guides/gs-producing-web-service", required = true)
    private Integer[] listOne;

    @XmlElement(namespace="http://spring.io/guides/gs-producing-web-service", required = true)
    private Integer[] listTwo;

    public Integer[] getListOne() {
        return listOne;
    }

    public void setListOne(Integer[] listOne) {
        this.listOne = listOne;
    }

    public Integer[] getListTwo() {
        return listTwo;
    }

    public void setListTwo(Integer[] listTwo) {
        this.listTwo = listTwo;
    }
}
