package com.mygdx.dsav.Generators;

import java.util.Collections;
import java.util.ArrayList;

public class GBubbleSort {
    private int ptrA, ptrB;
    private boolean swappedThisCycle;
    public String[] arr;
    public boolean hasNext;

    public GBubbleSort(String[] values) {
        arr = values;
        ptrA = 0;
        ptrB = 1;
        swappedThisCycle = false;
        hasNext = values.length > 1;
    }

    public String[] next() {
        assert hasNext;

        if (shouldSwap(arr[ptrA], arr[ptrB])) {
            String temp = arr[ptrA];
            arr[ptrA] = arr[ptrB];
            arr[ptrB] = temp;
            swappedThisCycle = true;
        }

        ptrA++;
        ptrB++;
        if (ptrB == arr.length) {
            ptrA = 0;
            ptrB = 1;
            hasNext = swappedThisCycle;
            swappedThisCycle = false;
        }
        
        return arr;
    }
    
    private boolean shouldSwap(String a, String b) {
        try {
            int ia = Integer.parseInt(a);
            int ib = Integer.parseInt(b);
            return ia > ib;
        } catch (NumberFormatException e) {
            ArrayList<String> x = new ArrayList<>();
            x.add(a);
            x.add(b);
            
            ArrayList<String> y = new ArrayList<>(x);
            Collections.sort(y);

            return x.equals(y);
        }
    }

}
