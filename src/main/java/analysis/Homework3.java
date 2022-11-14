package analysis;

import data.CustomDataProducer;
import org.springframework.util.StopWatch;
import sort.MergeSort;
import task.MultiThreadSortTask;

import java.util.concurrent.ForkJoinPool;

/**
 * 第三题:大数据规模，分析算法及其性能
 *
 * @author zhangrikang
 * @date 2022/11/5
 */
public class Homework3 {
    /**
     * 待排序数
     */
    static int unHandleArrLength = 1000000;

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] arrayToSort = CustomDataProducer.generate(1, unHandleArrLength, unHandleArrLength);
        MultiThreadSortTask multiThreadSort = new MultiThreadSortTask(arrayToSort);
        multiThreadSort.setSorter(new MergeSort());
//        multiThreadSort.setSorter(new QuickSort());
//        multiThreadSort.setSorter(new RadixSort());
//        multiThreadSort.setSorter(new SelectionSort());
//        multiThreadSort.setSorter(new ShellSort());
        StopWatch watch = new StopWatch();
        watch.start();
        int[] result = forkJoinPool.invoke(multiThreadSort);
        watch.stop();
        System.out.printf("Total time：%dms ", watch.getTotalTimeMillis());
    }
}
