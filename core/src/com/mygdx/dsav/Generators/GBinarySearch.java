package com.mygdx.dsav.Generators;

import java.util.ArrayList;
import java.util.Collections;

public class GBinarySearch {
    private int lowerBound;
    private int upperBound;
    public int selected;
    public String[] arr;
    public boolean hasNext;
    public boolean found;

    public GBinarySearch(String[] values) {
        arr = values;
        reset();
    }

    public void reset() {
        selected = -1;
        lowerBound = 0;
        upperBound = arr.length - 1;
        hasNext = true;
        found = false;
    }

    public boolean isSorted() {
        boolean isSorted = true;
        for (int i = 0; i < arr.length-1; i++) {
            try {
                int ia = Integer.parseInt(arr[i]);
                int ib = Integer.parseInt(arr[i+1]);
                isSorted = isSorted && ia <= ib;
            } catch (NumberFormatException e) {
                ArrayList<String> x = new ArrayList<>();
                x.add(arr[i]);
                x.add(arr[i+1]);

                ArrayList<String> y = new ArrayList<>(x);
                Collections.sort(y);

                isSorted = isSorted && x.equals(y);
            }
        }
        return isSorted;
    }

    public boolean isFull() {
        boolean full = true;
        for (String x : arr) {
            if (x.equals("")) {
                full = false;
            }
        }
        return full;
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
            found = true;
        } else {
            if (cmp < 0) {
                // current leftOf target
                lowerBound = selected + 1;
            } else {
                // current rightOf target
                upperBound = selected - 1;
            }
            hasNext = (upperBound - lowerBound) >= 0;
        }

    }

}
