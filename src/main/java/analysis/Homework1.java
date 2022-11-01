package analysis;

import data.CustomDataProducer;
import org.springframework.util.StopWatch;
import sort.MergeSort;

import java.util.Arrays;

/**
 * 第一题:常规版本, 低数据规模，分析算法及其性能
 *
 * @author zhangrikang
 * @date 2022/11/1
 */
public class Homework1 {
    public static void main(String[] args) {
        StopWatch mergeWatch = new StopWatch();
        int[] unHandleArr = CustomDataProducer.generate(1, 10, 100);
        System.out.printf("unHandleArr:%s%n", Arrays.toString(unHandleArr));
        // 归并排序
        mergeWatch.start();
        int[] sortArr = MergeSort.sort(unHandleArr.clone());
        mergeWatch.stop();
        System.out.printf("mergeSort finish, sortArr:{} :%s%nTotal time：%d ms%n", Arrays.toString(sortArr), mergeWatch.getTotalTimeMillis());
    }
}
