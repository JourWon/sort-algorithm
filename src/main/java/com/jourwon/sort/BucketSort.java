package com.jourwon.sort;

import java.util.*;

/**
 * Description:桶排序
 * 桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。
 * 桶排序 (Bucket sort)的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，
 * 每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排
 * <p>
 * 算法描述
 * 人为设置一个BucketSize，作为每个桶所能放置多少个不同数值（例如当BucketSize==5时，
 * 该桶可以存放｛1,2,3,4,5｝这几种数字，但是容量不限，即可以存放100个3）；
 * 遍历输入数据，并且把数据一个一个放到对应的桶里去；
 * 对每个不是空的桶进行排序，可以使用其它排序方法，也可以递归使用桶排序；
 * 从不是空的桶里把排好序的数据拼接起来。
 * 注意，如果递归使用桶排序为各个桶排序，则当桶数量为1时要手动减小BucketSize增加下一循环桶的数量，
 * 否则会陷入死循环，导致内存溢出。
 * <p>
 * 算法分析
 * 桶排序最好情况下使用线性时间O(n)，桶排序的时间复杂度，取决与对各个桶之间数据进行排序的时间复杂度，
 * 因为其它部分的时间复杂度都为O(n)。很显然，桶划分的越小，各个桶之间的数据越少，排序所用的时间也会越少。
 * 但相应的空间消耗就会增大。
 * 最佳情况：T(n) = O(n+k)   最差情况：T(n) = O(n+k)   平均情况：T(n) = O(n2)
 *
 * @author JourWon
 * @date 2019/7/11 23:08
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};

        bucketSort(array);

        System.out.println(Arrays.toString(array));
    }

    /**
     * Description: 桶排序
     *
     * @param array
     * @return void
     * @author JourWon
     * @date 2019/7/11 23:43
     */
    public static void bucketSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        // 建立桶，个数和待排序数组长度一样
        int length = array.length;
        
        LinkedList<Integer>[] bucket = (LinkedList<Integer>[]) new LinkedList[length];

        // 待排序数组中的最大值
        int maxValue = Arrays.stream(array).max().getAsInt();
        // 根据每个元素的值，分配到对应范围的桶中
        for (int i = 0; i < array.length; i++) {
            int index = toBucketIndex(array[i], maxValue, length);
            // 没有桶才建立桶(延时)
            if (bucket[index] == null) {
                bucket[index] = new LinkedList<>();
            }
            // 有桶直接使用
            bucket[index].add(array[i]);
        }

        // 对每个非空的桶排序，排序后顺便存入临时的List，则list中已经有序）
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (bucket[i] != null) {
                Collections.sort(bucket[i]);
                temp.addAll(bucket[i]);
            }
        }

        // 将temp中的数据写入原数组
        for (int i = 0; i < length; i++) {
            array[i] = temp.get(i);
        }
    }

    /**
     * Description: 映射函数，将值转换为应存放到的桶数组的索引
     *
     * @param value
     * @param maxValue
     * @param length
     * @return int
     * @author JourWon
     * @date 2019/7/11 23:44
     */
    private static int toBucketIndex(int value, int maxValue, int length) {
        return (value * length) / (maxValue + 1);
    }

}
