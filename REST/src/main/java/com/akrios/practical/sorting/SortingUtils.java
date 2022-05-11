package com.akrios.practical.sorting;

import java.util.List;

public class SortingUtils {

    public static void quickSort(List<Integer> list, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(list, begin, end);

            quickSort(list, begin, partitionIndex-1);
            quickSort(list, partitionIndex+1, end);
        }
    }

    private static int partition(List<Integer> list, int begin, int end) {
        int pivot = list.get(end);
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (list.get(j) <= pivot) {
                i++;

                swap(list,i,j);
            }
        }
        swap(list,i+1,end);

        return i+1;
    }

    private static void swap(List<Integer> list, int i, int j) {
        int swapTemp = list.get(i);
        list.set(i,list.get(j));
        list.set(j, swapTemp);
    }
}
