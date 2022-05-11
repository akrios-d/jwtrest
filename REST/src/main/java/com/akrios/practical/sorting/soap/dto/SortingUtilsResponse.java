package com.akrios.practical.sorting.soap.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "sortedList"
})
@XmlRootElement(name = "sortingUtilsResponse")
public class SortingUtilsResponse {

    @XmlElement(required = true)
    protected SortedList[] sortedList;

    public SortedList[] getSortedList() {
        return sortedList;
    }

    public void setSortedList(SortedList[] sortedList) {
        this.sortedList = sortedList;
    }
}
