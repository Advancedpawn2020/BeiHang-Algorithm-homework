package sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author zhangrikang
 * @date 2022/11/1
 */

public class MergeSort implements Sorter {

    @Override
    public int[] sort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2F);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort(left), sort(right));
    }

    public int[] merge(int[] left, int[] right) {
        int destInts[] = new int[left.length + right.length];
        int array1Len = left.length;
        int array2Len = right.length;
        int destLen = destInts.length;

        // 只需要以新的集合destInts的长度为标准，遍历一次即可
        for (int index = 0, array1Index = 0, array2Index = 0; index < destLen; index++) {
            int value1 = array1Index >= array1Len ? Integer.MAX_VALUE : left[array1Index];
            int value2 = array2Index >= array2Len ? Integer.MAX_VALUE : right[array2Index];
            // 如果条件成立，说明应该取数组array1中的值
            if (value1 < value2) {
                array1Index++;
                destInts[index] = value1;
            }
            // 否则取数组array2中的值
            else {
                array2Index++;
                destInts[index] = value2;
            }
        }

        return destInts;
    }
}