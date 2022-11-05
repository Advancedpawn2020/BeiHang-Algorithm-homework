package sort;

/**
 * 排序器
 *
 * @author zhangrikang
 */
public interface Sorter {
    /**
     * 排序
     *
     * @param sourceArray 待排序数组
     * @return 已排序数组
     */
    int[] sort(int[] sourceArray);
}
