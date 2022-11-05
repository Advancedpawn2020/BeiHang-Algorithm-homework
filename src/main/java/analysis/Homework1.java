package analysis;

import data.CustomDataProducer;
import org.springframework.util.StopWatch;
import sort.*;

import java.util.Arrays;
import java.util.function.Function;

/**
 * 第一题:常规版本, 低数据规模，分析算法及其性能
 *
 * @author zhangrikang
 * @date 2022/11/1
 */
public class Homework1 {
    static int[] unHandleArr;

    public static void main(String[] args) {
        // 保证所有算法只针对同一个未排序集合进行排序
        unHandleArr = CustomDataProducer.generate(1, 10000, 1000000);
        System.out.printf("unHandleArr:%s%n", Arrays.toString(unHandleArr));

        analysis(SelectionSort.class.getSimpleName(), new SelectionSort()::sort);
        analysis(MergeSort.class.getSimpleName(), new MergeSort()::sort);
        analysis(ShellSort.class.getSimpleName(), new ShellSort()::sort);
        analysis(QuickSort.class.getSimpleName(), new QuickSort()::sort);
        analysis(RadixSort.class.getSimpleName(), new RadixSort()::sort);
    }

    private static void analysis(String sortName, Function<int[], int[]> sorter) {
        StopWatch watch = new StopWatch();
        watch.start();
        int[] sortArr = sorter.apply(unHandleArr.clone());
        watch.stop();
//      System.out.printf("%s finish, sortArr:%s%nTotal time：%d ms%n", sortName, Arrays.toString(sortArr), watch.getTotalTimeMillis());
        System.out.printf("%s finish, Total time：%d ms%n", sortName, watch.getTotalTimeMillis());
    }
}
