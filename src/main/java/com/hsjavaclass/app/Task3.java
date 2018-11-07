package com.hsjavaclass.app;

import java.util.Comparator;

public class Task3 {
    public static Integer maxElement(Integer[] a) {
        if(a != null && a.length > 0) {
            int max = Integer.MIN_VALUE;
            for (int num : a) {
                if(num > max)
                    max = num;
            }
            return max;
        }
        return null;
    }

    public static <T extends Comparable<T>> T maxNumber(T[] a) {
        if(a != null && a.length > 0) {
            T max = a[0];
            for(int i = 1; i < a.length; i ++) {
                if(a[i].compareTo(max) > 0)
                    max = a[i];
            }
            return max;
        }
        return null;
    }

    public static void main(String[] args) {
        Integer[] a = {1,2,3,4,5};
        System.out.println(maxNumber(a));
    }
}