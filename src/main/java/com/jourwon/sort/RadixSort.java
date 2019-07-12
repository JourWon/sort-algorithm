package com.jourwon.sort;

import java.util.Arrays;

/**
 * Description:基数排序
 * 基数排序也是非比较的排序算法，对每一位进行排序，从最低位开始排序，复杂度为O(kn),
 * 为数组长度，k为数组中的数的最大的位数；
 * 基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。
 * 有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。
 * 最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。基数排序基于分别排序，分别收集，所以是稳定的。
 * <p>
 * 算法描述
 * 取得数组中的最大数，并取得位数；
 * arr为原始数组，从最低位开始取每个位组成radix数组；
 * 对radix进行计数排序（利用计数排序适用于小范围数的特点）；
 * <p>
 * 算法分析
 * 最佳情况：T(n) = O(n * k)   最差情况：T(n) = O(n * k)   平均情况：T(n) = O(n * k)
 * <p>
 * 基数排序有两种方法：
 * MSD 从高位开始进行排序 LSD 从低位开始进行排序
 * <p>
 * 基数排序 vs 计数排序 vs 桶排序
 * 这三种排序算法都利用了桶的概念，但对桶的使用方法上有明显差异：
 * 基数排序：根据键值的每位数字来分配桶
 * 计数排序：每个桶只存储单一键值
 * 桶排序：每个桶存储一定范围的数值
 *
 * @author JourWon
 * @date 2019/7/11 23:14
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};

        radixSort(array);

        System.out.println(Arrays.toString(array));
    }

    /**
     * Description: 基数排序
     *
     * @param array
     * @return void
     * @author JourWon
     * @date 2019/7/11 23:45
     */
    public static void radixSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int length = array.length;

        // 每位数字范围0~9，基为10
        int radix = 10;
        int[] aux = new int[length];
        int[] count = new int[radix + 1];
        // 以关键字来排序的轮数，由位数最多的数字决定，其余位数少的数字在比较高位时，自动用0进行比较
        // 将数字转换成字符串，字符串的长度就是数字的位数，字符串最长的那个数字也拥有最多的位数
        int x = Arrays.stream(array).map(s -> String.valueOf(s).length()).max().getAsInt();

        // 共需要d轮计数排序, 从d = 0开始，说明是从个位开始比较，符合从右到左的顺序
        for (int d = 0; d < x; d++) {
            // 1. 计算频率，在需要的数组长度上额外加1
            for (int i = 0; i < length; i++) {
                // 使用加1后的索引，有重复的该位置就自增
                count[digitAt(array[i], d) + 1]++;
            }
            // 2. 频率 -> 元素的开始索引
            for (int i = 0; i < radix; i++) {
                count[i + 1] += count[i];
            }

            // 3. 元素按照开始索引分类，用到一个和待排数组一样大临时数组存放数据
            for (int i = 0; i < length; i++) {
                // 填充一个数据后，自增，以便相同的数据可以填到下一个空位
                aux[count[digitAt(array[i], d)]++] = array[i];
            }
            // 4. 数据回写
            for (int i = 0; i < length; i++) {
                array[i] = aux[i];
            }
            // 重置count[]，以便下一轮统计使用
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }

        }
    }

    /**
     * Description: 根据d，获取某个值的个位、十位、百位等，d = 0取出个位，d = 1取出十位，以此类推。对于不存在的高位，用0补
     *
     * @param value
     * @param d
     * @return int
     * @author JourWon
     * @date 2019/7/11 23:46
     */
    private static int digitAt(int value, int d) {
        return (value / (int) Math.pow(10, d)) % 10;
    }

}
