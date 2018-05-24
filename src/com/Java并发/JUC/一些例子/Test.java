package com.Java并发.JUC.一些例子;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Jay on 4/10/17.
 */
public class Test {

    public static void main(String[] args) {
        test2();

    }

    private static void test_1() {
        int[] array = {-10, 1, 3, 5, 7, 9, 10, 12, 14, 16, 19, 20, 25, 27, 29, 30, 40, 45, 49, 50, 60};
        int sum = 50;
        HashMap<Integer, Integer> result = (HashMap<Integer, Integer>) findSum(array, sum);
        System.out.println(result);
    }

    private static Map<Integer, Integer> findSum(int[] array, int sum) {
        int length = array.length;
        if (sum < array[0] || (sum >= 2 * array[length - 1])) {
            return new HashMap<>(0);
        }

        HashMap<Integer, Integer> result = new HashMap<>(length/2 + 1);
        HashSet<Integer> set = new HashSet<>(length);

        for (int i=0; i<length; i++) {
            set.add(array[i]);
        }

        for (int i=0; i<length; i++) {
            if (array[i] * 2 >= sum) {
                break;
            }
            if ((array[i] != sum - array[i]) && set.contains(sum - array[i])) {
                result.put(array[i], sum - array[i]);
            }
        }

        return result;
    }

    private static void test2() {
        int[] array = {3, 4, 3, 2, 1};
        int[] result = sort(array);
        for (int i=0; i<result.length; i++) {
            System.out.println(result[i]);
        }
    }

    private static int[] sort(int[] array) {
        int[] bitmap = new int[6]; //模拟
        for (int i=0; i<array.length; i++) {
            int value = array[i];
            bitmap[value] = 1;
        }

        int[] result = new int[array.length];
        int index = 0;
        for (int i=0; i<bitmap.length; i++) {
            if (bitmap[i] == 1) {
                result[index++] = i;
            }
        }

        return result;
    }

}
