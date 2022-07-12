package com.mygdx.dsav.Generators;

public class GBubbleSort {
    private int ptrA, ptrB;
    public String[] arr;
    public boolean hasNext;

    public GBubbleSort(String[] values) {
        arr = values;
        ptrA = 0;
        ptrB = 1;
        hasNext = values.length > 1;
    }

    public String[] next() {
        assert hasNext;

        if (shouldSwap(arr[ptrA], arr[ptrB])) {
            String temp = arr[ptrA];
            arr[ptrA] = arr[ptrB];
            arr[ptrB] = temp;
        }

        ptrB++;
        if (ptrB == arr.length) {
            ptrA++;
            ptrB = ptrA + 1;
        }

        if (ptrB == arr.length) {
            hasNext = false;
        }
        
        return arr;
    }
    
    private boolean shouldSwap(String a, String b) {
        return true;
    }

}
