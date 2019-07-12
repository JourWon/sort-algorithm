package com.jourwon.sort;

import java.util.Arrays;

/**
 * Description:计数排序
 * 计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
 * 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。
 * 计数排序(Counting sort)是一种稳定的排序算法。计数排序使用一个额外的数组C，
 * 其中第i个元素是待排序数组A中值等于i的元素的个数。然后根据数组C来将A中的元素排到正确的位置。
 * 它只能对整数进行排序。
 * <p>
 * 算法描述
 * 找出待排序的数组中最大和最小的元素；
 * 统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
 * 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
 * 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
 * <p>
 * 算法分析
 * 当输入的元素是n 个0到k之间的整数时，它的运行时间是 O(n + k)。
 * 计数排序不是比较排序，排序的速度快于任何比较排序算法。
 * 由于用来计数的数组C的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），
 * 这使得计数排序对于数据范围很大的数组，需要大量时间和内存。
 * 最佳情况：T(n) = O(n+k)  最差情况：T(n) = O(n+k)  平均情况：T(n) = O(n+k)
 *
 * @author JourWon
 * @date 2019/7/11 22:59
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};

        countingSort(array);

        System.out.println(Arrays.toString(array));
    }

    /**
     * Description: 计数排序
     *
     * @param array
     * @return void
     * @author JourWon
     * @date 2019/7/11 23:42
     */
    public static void countingSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int length = array.length;

        int max = array[0];
        int min = array[0];
        for (int i = 0; i < length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
            if (min > array[i]) {
                min = array[i];
            }
        }
        // 最大最小元素之间范围[min, max]的长度
        int offset = max - min + 1;
        // 1. 计算频率，在需要的数组长度上额外加1
        int[] count = new int[offset + 1];
        for (int i = 0; i < length; i++) {
            // 使用加1后的索引，有重复的该位置就自增
            count[array[i] - min + 1]++;
        }
        // 2. 频率 -> 元素的开始索引
        for (int i = 0; i < offset; i++) {
            count[i + 1] += count[i];
        }

        // 3. 元素按照开始索引分类，用到一个和待排数组一样大临时数组存放数据
        int[] aux = new int[length];
        for (int i = 0; i < length; i++) {
            // 填充一个数据后，自增，以便相同的数据可以填到下一个空位
            aux[count[array[i] - min]++] = array[i];
        }
        // 4. 数据回写
        for (int i = 0; i < length; i++) {
            array[i] = aux[i];
        }
    }

}
