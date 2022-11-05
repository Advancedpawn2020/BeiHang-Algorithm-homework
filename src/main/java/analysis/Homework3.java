package analysis;

import data.CustomDataProducer;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.StopWatch;
import sort.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * 第三题:大数据规模，分析算法及其性能
 *
 * @author zhangrikang
 * @date 2022/11/5
 */
public class Homework3 {
    /**
     * 未处理的数组
     */
    static int[] unHandleArr;
    /**
     * 数组最大值
     */
    static int arrayBound = 100;
    /**
     * 多线程个数
     */
    static int threadNum = 10;
    /**
     * 根据区间以及线程个数分发的桶集合
     */
    static HashMap<Integer, List<Integer>> bucketList = new HashMap<>();
    /**
     * 所有子线程运行时间的map
     */
    static HashMap<Integer, Long> threadRunTime = new HashMap<>();
    /**
     * 线程池
     */
    static ExecutorService pool = new ThreadPoolExecutor(threadNum, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            new CustomizableThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {
        // 保证所有算法只针对同一个未排序集合进行排序
        unHandleArr = CustomDataProducer.generate(1, arrayBound, 1000000);
        System.out.printf("unHandleArr:%s%n", Arrays.toString(unHandleArr));
        // 分发桶
        distributeBucket();

        multithreadingAnalysis(SelectionSort.class.getSimpleName(), new SelectionSort()::sort);
        multithreadingAnalysis(MergeSort.class.getSimpleName(), new MergeSort()::sort);
        multithreadingAnalysis(ShellSort.class.getSimpleName(), new ShellSort()::sort);
        multithreadingAnalysis(QuickSort.class.getSimpleName(), new QuickSort()::sort);
        multithreadingAnalysis(RadixSort.class.getSimpleName(), new RadixSort()::sort);
        pool.shutdown();
    }

    private static void multithreadingAnalysis(String sortName, Function<int[], int[]> sorter) {
        HashMap<Integer, List<Integer>> bucketCopy = new HashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        StopWatch mainWatch = new StopWatch();
        mainWatch.start();
        for (int i = 0; i < threadNum; i++) {
            int finalI = i;
            pool.execute(
                    () -> {
                        StopWatch watch = new StopWatch();
                        watch.start();
                        bucketCopy.put(finalI, toIntegerList(sorter.apply(
                                toIntArray(bucketList.get(finalI).toArray(new Integer[1])))));
                        watch.stop();
//                        System.out.printf("%s %s part finish, sortArr:%s%nTotal time：%d ms%n", sortName, finalI, bucketCopy.get(finalI), watch.getTotalTimeMillis());
                        threadRunTime.put(finalI, watch.getTotalTimeMillis());
                        countDownLatch.countDown();
                    });
        }
        try {
            countDownLatch.await();
        } catch (Exception ignore) {
        }
        List<Integer> sortArr = new LinkedList<>();
        // 将排序后的桶合并
        for (Map.Entry<Integer, List<Integer>> entry : bucketCopy.entrySet()) {
            sortArr.addAll(entry.getValue());
        }

        long totalRunTime = 0;
        for (Map.Entry<Integer, Long> entry : threadRunTime.entrySet()) {
            totalRunTime += entry.getValue();
        }

        mainWatch.stop();

//        System.out.printf("%s finish, sortArr:%s%nAll Thread Total time：%d ms%nMain time：%d ms%n", sortName, sortArr, totalRunTime, mainWatch.getTotalTimeMillis());
        System.out.printf("%s finish, Total time：%d ms%nMain time：%d ms%n", sortName,
                totalRunTime, mainWatch.getTotalTimeMillis());
    }

    private static int[] toIntArray(Integer[] integers) {
        return Arrays.stream(integers)
                .mapToInt(Integer::valueOf).toArray();
    }

    private static List<Integer> toIntegerList(int[] ints) {
        return new ArrayList<>() {{
            for (int i : ints) {
                add(i);
            }
        }};
    }

    private static void distributeBucket() {
        // 不同区间放入不同的桶
        for (int data : unHandleArr) {
            final int d = getDigits(data);
            final List<Integer> dList = bucketList.getOrDefault(d, new ArrayList<>());
            dList.add(data);
            bucketList.put(d, dList);
        }
    }

    /**
     * 获得数字所属区间
     *
     * @param data 数字
     * @return 区间
     */
    private static int getDigits(int data) {
        for (int i = 0; i < threadNum; i++) {
            int start = arrayBound / threadNum * i;
            int end = start + arrayBound / threadNum;
            if (data >= start && data < end) {
                return i;
            }
        }
        return 0;
    }
}
