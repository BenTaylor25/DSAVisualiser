package com.mygdx.dsav.Generators;

import java.util.ArrayList;
import java.util.Collections;

public class GBinarySearch {
    public String[] arr;
    public boolean hasNext;

    public GBinarySearch(String[] values) {
        arr = values;
    }

    public boolean isSorted() {
        boolean isSorted = true;
        for (int i = 0; i < arr.length-1; i++) {
            try {
                int ia = Integer.parseInt(arr[i]);
                int ib = Integer.parseInt(arr[i+1]);
                isSorted = isSorted && ia < ib;
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

    public void sort() {
        GBubbleSort gen = new GBubbleSort(arr);
        while (gen.hasNext) {
            arr = gen.next();
        }
    }

}
