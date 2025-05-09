package com.sahajai.interview.tambola.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomUtils {

    public static List<Integer> getNRandomNumbersInRange(int n, int min, int max) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        int _n = Math.min(n, max - min + 1);
        return numbers.subList(0, _n);
    }
}
