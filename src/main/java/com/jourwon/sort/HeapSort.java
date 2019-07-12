package com.jourwon.sort;

        import java.util.Arrays;

/**
 * Description:堆排序
 * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。
 * 堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：
 * 即子结点的键值或索引总是小于（或者大于）它的父节点。
 * <p>
 * 算法描述
 * 将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；
 * 将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),
 * 且满足R[1,2…n-1]<=R[n]；
 * 由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，
 * 然后再次将R[1]与无序区最后一个元素交换，得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。
 * 不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。
 * <p>
 * 算法分析
 * 最佳情况：T(n) = O(nlogn) 最差情况：T(n) = O(nlogn) 平均情况：T(n) = O(nlogn)
 *
 * @author JourWon
 * @date 2019/7/11 17:13
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};

        heapSort(array);

        System.out.println(Arrays.toString(array));
    }

    /**
     * Description: 堆排序
     *
     * @param array
     * @return void
     * @author JourWon
     * @date 2019/7/11 23:40
     */
    public static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int length = array.length;

        //1.构建大顶堆
        for (int i = length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(array, i, length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = length - 1; j > 0; j--) {
            //将堆顶元素与末尾元素进行交换
            swap(array, 0, j);
            //重新对堆进行调整
            adjustHeap(array, 0, j);
        }

    }

    /**
     * Description: 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param array
     * @param i
     * @param length
     * @return void
     * @author JourWon
     * @date 2019/7/11 17:58
     */
    private static void adjustHeap(int[] array, int i, int length) {
        //先取出当前元素i
        int temp = array[i];
        //从i结点的左子结点开始，也就是2i+1处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //如果左子结点小于右子结点，k指向右子结点
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (array[k] > temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        //将temp值放到最终的位置
        array[i] = temp;
    }

    /**
     * Description: 交换元素位置
     *
     * @param array
     * @param a
     * @param b
     * @return void
     * @author JourWon
     * @date 2019/7/11 17:57
     */
    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}
