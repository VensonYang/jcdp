package cn.org.awcp.kotlin.auth;

import java.util.Arrays;

/**
 * BubbleSort
 *
 * @author venson
 * @version 20180712
 */
public class BubbleSort {
    static int sum=0;
    public static void main(String[] args) {
        int[] arr = new int[]{3, 523, 32, 34,999, 233, 345, 322,888, 33, 2, 77,1,562,1};
        sort(arr);
        System.out.println(sum);
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                int temp = arr[j];
                if (temp > arr[j+1]) {
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
                System.out.println("第"+(i+1)+"次，第"+(j+1)+"轮:"+Arrays.toString(arr));
                sum++;
            }
            System.out.println("第"+(i+1)+"次:"+Arrays.toString(arr));
        }
    }
}
