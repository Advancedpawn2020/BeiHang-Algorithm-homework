package task;

import sort.*;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * Created by shihaichao
 * Date: 2022/11/7
 */
public class MultiThreadSortTask extends RecursiveTask<int []> {

    /**
     * 未处理的数组
     */
    int[] unHandleArr;

    static Sorter sorter;

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    /**
     * 多线程排序临界值
     */
    static int MIN_ARRAY_SORT_GRAN = 1 << 13;

    public MultiThreadSortTask(int[] unHandleArr) {
        this.unHandleArr = unHandleArr;
    }

    @Override
    protected int[] compute() {
        //参照Arrays.parallelSort值，一定条件下直接排序
        if (unHandleArr.length <= MIN_ARRAY_SORT_GRAN || (ForkJoinPool.getCommonPoolParallelism() == 1)) {
            return sorter.sort(unHandleArr);
        } else {
            //如果任务大于阀值，就分裂成两个子任务计算
            int mid = unHandleArr.length / 2;
            MultiThreadSortTask leftTask = new MultiThreadSortTask(Arrays.copyOfRange(unHandleArr, 0, mid));
            MultiThreadSortTask rightTask = new MultiThreadSortTask(Arrays.copyOfRange(unHandleArr, mid, unHandleArr.length));

            //并行执行子任务
            invokeAll(leftTask, rightTask);

            //等待子任务执行完，合并得到结果
            int[] leftResult = leftTask.join();
            int[] rightResult = rightTask.join();
            return joinInts(leftResult, rightResult);
        }
    }


    private static int[] joinInts(int array1[], int array2[]) {
        int destInts[] = new int[array1.length + array2.length];
        int array1Len = array1.length;
        int array2Len = array2.length;
        int destLen = destInts.length;

        // 只需要以新的集合destInts的长度为标准，遍历一次即可
        for (int index = 0, array1Index = 0, array2Index = 0; index < destLen; index++) {
            int value1 = array1Index >= array1Len ? Integer.MAX_VALUE : array1[array1Index];
            int value2 = array2Index >= array2Len ? Integer.MAX_VALUE : array2[array2Index];
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
