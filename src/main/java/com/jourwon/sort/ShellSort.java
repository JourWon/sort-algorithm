package com.jourwon.sort;

import java.util.Arrays;

/**
 * Description:希尔排序
 * 希尔排序是希尔（Donald Shell）于1959年提出的一种排序算法。
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，
 * 也称为缩小增量排序，同时该算法是冲破O(n2）的第一批算法之一。
 * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
 * 希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 * <p>
 * 算法描述
 * 我们来看下希尔排序的基本步骤，在此我们选择增量gap=length/2，缩小增量继续以gap = gap/2的方式，
 * 这种增量选择我们可以用一个序列来表示，{n/2,(n/2)/2...1}，称为增量序列。
 * 希尔排序的增量序列的选择与证明是个数学难题，我们选择的这个增量序列是比较常用的，
 * 也是希尔建议的增量，称为希尔增量，但其实这个增量序列不是最优的。此处我们做示例使用希尔增量。
 * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
 * 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
 * 按增量序列个数k，对序列进行k 趟排序；
 * 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
 * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 * <p>
 * 算法分析
 * 最佳情况：T(n) = O(nlog2 n)  最坏情况：T(n) = O(nlog2 n)  平均情况：T(n) =O(nlog2n)
 *
 * @author JourWon
 * @date 2019/7/11 15:28
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};

        shellSort(array);

        System.out.println(Arrays.toString(array));
    }

    /**
     * Description: 希尔排序
     *
     * @param array
     * @return void
     * @author JourWon
     * @date 2019/7/11 23:34
     */
    public static void shellSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int length = array.length;

        // temp为临时变量，gap增量默认是长度的一半，每次变为之前的一半，直到最终数组有序
        int temp, gap = length / 2;

        while (gap > 0) {
            for (int i = gap; i < length; i++) {
                // 将当前的数与减去增量之后位置的数进行比较，如果大于当前数，将它后移
                temp = array[i];
                int preIndex = i - gap;

                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }

                // 将当前数放到空出来的位置
                array[preIndex + gap] = temp;

            }
            gap /= 2;
        }
    }

}
