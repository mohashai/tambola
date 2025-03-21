package com.sahajai.interview.tambola.utils;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class RandomUtilsTest {

    @Test
    void testGetNRandomNumbersInRange_CorrectSize() {
        List<Integer> randomNumbers = RandomUtils.getNRandomNumbersInRange(5, 1, 10);
        assertEquals(5, randomNumbers.size(), "Should return exactly 5 numbers");
    }

    @Test
    void testGetNRandomNumbersInRange_WithinRange() {
        int min = 10, max = 20, n = 5;
        List<Integer> randomNumbers = RandomUtils.getNRandomNumbersInRange(n, min, max);

        for (int num : randomNumbers) {
            assertTrue(num >= min && num <= max, "Numbers should be within the range [" + min + ", " + max + "]");
        }
    }

    @Test
    void testGetNRandomNumbersInRange_UniqueNumbers() {
        List<Integer> randomNumbers = RandomUtils.getNRandomNumbersInRange(7, 1, 10);
        long uniqueCount = randomNumbers.stream().distinct().count();
        assertEquals(7, uniqueCount, "All numbers should be unique");
    }

    @Test
    void testGetNRandomNumbersInRange_NEqualsRangeSize() {
        List<Integer> randomNumbers = RandomUtils.getNRandomNumbersInRange(10, 1, 10);
        assertEquals(10, randomNumbers.size(), "Should return all numbers in the range");
        assertTrue(randomNumbers.containsAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), "Should contain all numbers from 1 to 10");
    }

    @Test
    void testGetNRandomNumbersInRange_NGreaterThanRange_HandlesGracefully() {
        List<Integer> randomNumbers = RandomUtils.getNRandomNumbersInRange(15, 1, 10);
        assertEquals(10, randomNumbers.size(), "When n is greater than range, should return all available numbers");
    }

    @Test
    void testGetNRandomNumbersInRange_SingleElementRange() {
        List<Integer> randomNumbers = RandomUtils.getNRandomNumbersInRange(1, 5, 5);
        assertEquals(1, randomNumbers.size(), "Should return one element");
        assertEquals(5, randomNumbers.get(0), "Should return the only number in range (5)");
    }
}
