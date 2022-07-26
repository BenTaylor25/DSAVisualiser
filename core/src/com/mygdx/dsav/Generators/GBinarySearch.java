package com.mygdx.dsav.Generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GBinarySearch {
    public String[] arr;
    public boolean hasNext;

    public GBinarySearch(String[] values) {
        arr = values;
    }

    public boolean isSorted() {
        ArrayList<String> x = new ArrayList<>(Arrays.asList(arr));
        ArrayList<String> y = new ArrayList<>(x);
        
        Collections.sort(y);
        
        return x.equals(y);
    }

    public void sort() {
        GBubbleSort gen = new GBubbleSort(arr);
        while (gen.hasNext) {
            arr = gen.next();
        }
    }

}
