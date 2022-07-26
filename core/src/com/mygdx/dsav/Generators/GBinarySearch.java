package com.mygdx.dsav.Generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GBinarySearch {
    private int lowerBound;
    private int upperBound;
    public int selected;
    public String[] arr;
    public boolean hasNext;

    public GBinarySearch(String[] values) {
        arr = values;
        reset();
    }

    public void reset() {
        selected = -1;
        lowerBound = 0;
        upperBound = arr.length - 1;
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

    public void next(String target) {
        assert hasNext;

        selected = lowerBound + (upperBound-lowerBound)/2;

        int cmp = arr[selected].compareTo(target);
        if (cmp == 0) {
            // found
            hasNext = false;
        } else {
            if (cmp < 0) {
                // current leftOf target
                lowerBound = selected;
            } else {
                // current rightOf target
                upperBound = selected;
            }
            hasNext = (upperBound - lowerBound) > 0;
        }

    }

}
