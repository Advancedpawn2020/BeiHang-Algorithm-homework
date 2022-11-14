package task;

import sort.MergeSort;
import sort.Sorter;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by shihaichao
 * Date: 2022/11/7
 */
public class MultiThreadSortTask extends RecursiveTask<int[]> {

    /**
     * 未处理的数组
     */
    int[] unHandleArr;

    static Sorter sorter;

    public void setSorter(Sorter sorter) {
        MultiThreadSortTask.sorter = sorter;
    }

    /**
     * 多线程排序临界值
     */
    static int MIN_ARRAY_SORT_GRAN = 1 << 13;

    static MergeSort mergeSort = new MergeSort();

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
            return mergeSort.merge(leftResult, rightResult);
        }
    }
}
