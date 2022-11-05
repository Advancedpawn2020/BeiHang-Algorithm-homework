package sort;

/**
 * @author zhangrikang
 * @date 2022/11/1
 */
public class ShellSort implements Sorter {

    @Override
    public int[] sort(int[] arr) {
        int gap = 1;
        while (gap < arr.length / 3) {
            gap = gap * 3 + 1;
        }

        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3F);
        }

        return arr;
    }
}