package lsc.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 排序算法
 *
 * @author shichang.liu
 * @date 2017年3月17日上午9:58:07
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class SortUtil {

    private static final int[] ARRAY = new int[10];


    /*
     * 冒泡排序
     */
    public static int[] BubbleSort(int[] a) {
        int j;
        int tmp;
        for (int i = 0; i < a.length; i++) {
            for (j = 1; j < a.length - i; j++) {
                if (a[j] < a[j - 1]) {
                    tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            }
        }
        return a;

    }

    /*
     * 冒泡排序 第二种实现
     */
    public static int[] BubbleSort2(int[] b) {
        int tmp = 0;
        int length = b.length;
        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j > i; j--) {
                if (b[i] > b[j]) {
                    tmp = b[i];
                    b[i] = b[j];
                    b[j] = tmp;
                }
            }
        }
        return b;
    }

    /*
     * 插入排序
     */
    public static int[] insertionSort(int[] a) {
        int length = a.length;
        int temp;
        int j;
        for (int i = 1; i < length; i++) {
            temp = a[i];
            for (j = i; j > 0 && temp < a[j - 1]; j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
        return a;
    }

    /*
     * 选择排序
     */
    public static int [] selectionSort(int[] a) {
        int length = a.length;
        int min;
        int temp;
        for (int i = 0; i < length; i++) {
            min = i;
            for (int j = i + 1; j < length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
        return a;
    }
    
    
    
    /*
     * 堆排序
     */
    public static int[] heapSort(int[] a) {
        int length = a.length;
        int temp;
        for (int k = length / 2; k >= 1; k--) {
            sink(a, k, length);
        }
        while (length > 0) {
            temp = a[0];
            a[0] = a[length - 1];
            a[length - 1] = temp;
            length--;
            sink(a, 1, length);
        }
        return a;
    }

    private static int[] sink(int[] a, int k, int n) {
        int temp;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && a[j - 1] < a[j]) {
                j++;
            }
            if (a[k - 1] < a[j - 1]) {
                break;
            }
            temp = a[k - 1];
            a[k - 1] = a[j - 1];
            a[j - 1] = temp;
            k = j;
        }
        return a;
    }
    
    
    
    //*******************************************测试*****************************************************
    
    
    public static void main(String[] args) {
        // BubbleSortTest();
        // insertionSortTest();
        // selectionSortTest();
        heapSortTest();

    }

    private static void createArray() {
        Set<Integer> set = new HashSet<Integer>();
        Random random = new Random();
        for (int i = 0; i < ARRAY.length; i++) {
            int k = random.nextInt(100);
            if (set.add(k)) {
                ARRAY[i] = k;
            }
        }
    }

    @SuppressWarnings("unused")
    private static void BubbleSortTest() {
        createArray();
        long start2 = System.currentTimeMillis();
        System.out.println(start2);
        int[] a = BubbleSort2(ARRAY);
        System.out.println(System.currentTimeMillis());
        System.out.println("sort2 start*********{" + String.valueOf(System.currentTimeMillis() - start2) + "}");
        System.out.println(Arrays.toString(a));
        // =================================
        createArray();
        long start = System.currentTimeMillis();
        System.out.println("sort start========={" + String.valueOf(System.currentTimeMillis() - start) + "}");
        System.out.println(Arrays.toString(BubbleSort(ARRAY)));

    }

    @SuppressWarnings("unused")
    private static void insertionSortTest() {
        createArray();
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(insertionSort(ARRAY)));
        System.out.println("sort start========={" + String.valueOf(System.currentTimeMillis() - start) + "}");
    }

    @SuppressWarnings("unused")
    private static void selectionSortTest() {
        createArray();
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(selectionSort(ARRAY)));
        System.out.println("sort start========={" + String.valueOf(System.currentTimeMillis() - start) + "}");
    }
    
    private static void heapSortTest() {
        createArray();
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(heapSort(ARRAY)));
        System.out.println("sort start========={" + String.valueOf(System.currentTimeMillis() - start) + "}");
    }
    
    
    
    
}
