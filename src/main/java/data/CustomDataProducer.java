package data;

import java.util.Random;

/**
 * 通用数据集生成
 * @author zhangrikang
 * @date 2022/11/1
 */
public class CustomDataProducer {

    /**
     * 产生待排序数据集(待优化)
     *
     * @param seed   随机种子
     * @param num    数据bound
     * @param length 数据集长度
     * @return 待排序数据集
     */
    public static int[] generate(int seed, int num, int length) {
        int[] arrays = new int[length];
        for (int i = 0; i < length; i++) {
            arrays[i] = new Random().nextInt(num);
        }
        return arrays;
    }
}
