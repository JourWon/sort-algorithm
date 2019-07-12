## 史上最全经典排序算法总结(Java实现)

查找和排序算法是算法的入门知识，其经典思想可以用于很多算法当中。因为其实现代码较短，应用较常见。所以在面试中经常会问到排序算法及其相关的问题。但万变不离其宗，只要熟悉了思想，灵活运用也不是难事。一般在面试中最常考的是快速排序和归并排序，并且经常有面试官要求现场写出这两种排序的代码。对这两种排序的代码一定要信手拈来才行。还有插入排序、冒泡排序、堆排序、基数排序、桶排序等。面试官对于这些排序可能会要求比较各自的优劣、各种算法的思想及其使用场景。还有要会分析算法的时间和空间复杂度。通常查找和排序算法的考察是面试的开始，如果这些问题回答不好，估计面试官都没有继续面试下去的兴趣都没了。所以想开个好头就要把常见的排序算法思想及其特点要熟练掌握，有必要时要熟练写出代码。下面主要介绍经典排序算法。

### 0、排序算法说明

#### 0.1 **排序的定义**

对一序列对象根据某个关键字进行排序。

#### 0.2 术语说明

- **稳定**：如果a原本在b前面，而a=b，排序之后a仍然在b的前面；
- **不稳定**：如果a原本在b的前面，而a=b，排序之后a可能会出现在b的后面；
- **内排序**：所有排序操作都在内存中完成；
- **外排序**：由于数据太大，因此把数据放在磁盘中，而排序通过磁盘和内存的数据传输才能进行；
- **时间复杂度：** 一个算法执行所耗费的时间。
- **空间复杂度**：运行完一个程序所需内存的大小。

#### 0.3 算法总结

![算法总结](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/算法总结.jpg)

**图片名词解释：**

- n: 数据规模
- k: “桶”的个数
- In-place: 占用常数内存，不占用额外内存
- Out-place: 占用额外内存

#### 0.4 算法分类

![算法分类](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/算法分类.jpg)

#### 0.5 比较和非比较的区别

常见的**快速排序、归并排序、堆排序、冒泡排序**等属于**比较排序**。**在排序的最终结果里，元素之间的次序依赖于它们之间的比较。每个数都必须和其他数进行比较，才能确定自己的位置。**
在**冒泡排序**之类的排序中，问题规模为n，又因为需要比较n次，所以平均时间复杂度为O(n²)。在**归并排序、快速排序**之类的排序中，问题规模通过**分治法**消减为logN次，所以时间复杂度平均**O(nlogn)**。
比较排序的优势是，适用于各种规模的数据，也不在乎数据的分布，都能进行排序。可以说，**比较排序适用于一切需要排序的情况。**

**计数排序、基数排序、桶排序**则属于**非比较排序**。非比较排序是通过确定每个元素之前，应该有多少个元素来排序。针对数组arr，计算arr[i]之前有多少个元素，则唯一确定了arr[i]在排序后数组中的位置。
非比较排序只要确定每个元素之前的已有的元素个数即可，所有一次遍历即可解决。算法时间复杂度**O(n)**。
**非比较排序时间复杂度底，但由于非比较排序需要占用空间来确定唯一位置。所以对数据规模和数据分布有一定的要求。**

------

 

下面的排序算法统一使用的测试代码如下，[源码GitHub链接](https://github.com/JourWon/sort-algorithm)

```java
public static void main(String[] args) {
    int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
	// 只需要修改成对应的方法名就可以了
    bubbleSort(array);

    System.out.println(Arrays.toString(array));
}
```



### 1、冒泡排序（Bubble Sort）

冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。 

#### 1.1 算法描述

- 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
- 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
- 针对所有的元素重复以上的步骤，除了最后一个；
- 重复步骤1~3，直到排序完成。

**1.2 动图演示**

![冒泡排序](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/冒泡排序.gif)

#### 1.3 代码实现

```java
/**
 * Description:冒泡排序
 *
 * @param array 需要排序的数组
 * @author JourWon
 * @date 2019/7/11 9:54
 */
public static void bubbleSort(int[] array) {
	if (array == null || array.length <= 1) {
		return;
	}

	int length = array.length;

	// 外层循环控制比较轮数i
	for (int i = 0; i < length; i++) {
		// 内层循环控制每一轮比较次数，每进行一轮排序都会找出一个较大值
		// (array.length - 1)防止索引越界，(array.length - 1 - i)减少比较次数
		for (int j = 0; j < length - 1 - i; j++) {
			// 前面的数大于后面的数就进行交换
			if (array[j] > array[j + 1]) {
				int temp = array[j + 1];
				array[j + 1] = array[j];
				array[j] = temp;
			}
		}
	}

}
```

#### 1.4 **算法分析**

**最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)**



### 2、选择排序（Selection Sort）

表现**最稳定的排序算法之一**，因为**无论什么数据进去都是O(n2)的时间复杂度**，所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。

选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。 

#### 2.1 算法描述

n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：

- 初始状态：无序区为R[1..n]，有序区为空；
- 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
- n-1趟结束，数组有序化了。

#### **2.2 动图演示**

![选择排序](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/选择排序.gif)　　

#### 2.3 代码实现

```java
/**
 * Description: 选择排序
 *
 * @param array
 * @return void
 * @author JourWon
 * @date 2019/7/11 23:31
 */
public static void selectionSort(int[] array) {
	if (array == null || array.length <= 1) {
		return;
	}

	int length = array.length;

	for (int i = 0; i < length - 1; i++) {
		// 保存最小数的索引
		int minIndex = i;

		for (int j = i + 1; j < length; j++) {
			// 找到最小的数
			if (array[j] < array[minIndex]) {
				minIndex = j;
			}
		}

		// 交换元素位置
		if (i != minIndex) {
			swap(array, minIndex, i);
		}
	}

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
```

#### 2.4 **算法分析**

**最佳情况：T(n) = O(n2)  最差情况：T(n) = O(n2)  平均情况：T(n) = O(n2)**



### 3、插入排序（Insertion Sort）

插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。

#### 3.1 算法描述

一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：

- 从第一个元素开始，该元素可以认为已经被排序；
- 取出下一个元素，在已经排序的元素序列中从后向前扫描；
- 如果该元素（已排序）大于新元素，将该元素移到下一位置；
- 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
- 将新元素插入到该位置后；
- 重复步骤2~5。

#### 3.2 动图演示

![插入排序](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/插入排序.gif)

#### 3.2 代码实现

```java
/**
 * Description: 插入排序
 *
 * @param array
 * @return void
 * @author JourWon
 * @date 2019/7/11 23:32
 */
public static void insertionSort(int[] array) {
	if (array == null || array.length <= 1) {
		return;
	}

	int length = array.length;

	// 要插入的数
	int insertNum;

	for (int i = 1; i < length; i++) {
		insertNum = array[i];
		// 已经排序好的元素个数
		int j = i - 1;
		while (j >= 0 && array[j] > insertNum) {
			// 从后到前循环，将大于insertNum的数向后移动一格
			array[j + 1] = array[j];
			j--;
		}

		// 将需要插入的数放在要插入的位置
		array[j + 1] = insertNum;
	}
}
```

#### 3.4 **算法分析**

**最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)**



### 4、希尔排序（Shell Sort）

希尔排序是希尔（Donald Shell）于1959年提出的一种排序算法。希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序，同时该算法是冲破O(n2）的第一批算法之一。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。

**希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。**

#### 4.1 算法描述

我们来看下希尔排序的基本步骤，在此我们选择增量gap=length/2，缩小增量继续以gap = gap/2的方式，这种增量选择我们可以用一个序列来表示，**{n/2,(n/2)/2...1}**，称为**增量序列**。希尔排序的增量序列的选择与证明是个数学难题，我们选择的这个增量序列是比较常用的，也是希尔建议的增量，称为希尔增量，但其实这个增量序列不是最优的。此处我们做示例使用希尔增量。

先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：

- 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
- 按增量序列个数k，对序列进行k 趟排序；
- 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。

#### 4.2 过程演示

![希尔排序](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/希尔排序.jpg)

 

#### 4.3 代码实现

```java
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
```

#### 4.4 算法分析

**最佳情况：T(n) = O(nlog2 n)  最坏情况：T(n) = O(nlog2 n)  平均情况：T(n) =O(nlog2n)**　



### 5、归并排序（Merge Sort）

和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是O(n log n）的时间复杂度。代价是需要额外的内存空间。

归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。 

#### 5.1 算法描述

- 把长度为n的输入序列分成两个长度为n/2的子序列；
- 对这两个子序列分别采用归并排序；
- 将两个排序好的子序列合并成一个最终的排序序列。

#### 5.2 动图演示

![归并排序](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/归并排序.gif)

#### 5.3 代码实现

```java
/**
 * Description: 归并排序
 *
 * @param array
 * @return void
 * @author JourWon
 * @date 2019/7/11 23:37
 */
public static void mergeSort(int[] array) {
	if (array == null || array.length <= 1) {
		return;
	}

	sort(array, 0, array.length - 1);
}

private static void sort(int[] array, int left, int right) {
	if (left == right) {
		return;
	}
	int mid = left + ((right - left) >> 1);
	// 对左侧子序列进行递归排序
	sort(array, left, mid);
	// 对右侧子序列进行递归排序
	sort(array, mid + 1, right);
	// 合并
	merge(array, left, mid, right);
}

private static void merge(int[] array, int left, int mid, int right) {
	int[] temp = new int[right - left + 1];
	int i = 0;
	int p1 = left;
	int p2 = mid + 1;
	// 比较左右两部分的元素，哪个小，把那个元素填入temp中
	while (p1 <= mid && p2 <= right) {
		temp[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
	}
	// 上面的循环退出后，把剩余的元素依次填入到temp中
	// 以下两个while只有一个会执行
	while (p1 <= mid) {
		temp[i++] = array[p1++];
	}
	while (p2 <= right) {
		temp[i++] = array[p2++];
	}
	// 把最终的排序的结果复制给原数组
	for (i = 0; i < temp.length; i++) {
		array[left + i] = temp[i];
	}
}
```

#### 5.4 算法分析

**最佳情况：T(n) = O(n)  最差情况：T(n) = O(nlogn)  平均情况：T(n) = O(nlogn)**



### 6、快速排序（Quick Sort）

快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。

#### 6.1 算法描述

快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：

- 从数列中挑出一个元素，称为 “基准”（**pivot**）；
- 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
- 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。

#### 6.2 动图演示

![快速排序](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/快速排序.gif)

#### 6.3 代码实现

```java
/**
 * Description: 快速排序
 *
 * @param array
 * @return void
 * @author JourWon
 * @date 2019/7/11 23:39
 */
public static void quickSort(int[] array) {
	quickSort(array, 0, array.length - 1);
}


private static void quickSort(int[] array, int left, int right) {
	if (array == null || left >= right || array.length <= 1) {
		return;
	}
	int mid = partition(array, left, right);
	quickSort(array, left, mid);
	quickSort(array, mid + 1, right);
}


private static int partition(int[] array, int left, int right) {
	int temp = array[left];
	while (right > left) {
		// 先判断基准数和后面的数依次比较
		while (temp <= array[right] && left < right) {
			--right;
		}
		// 当基准数大于了 arr[left]，则填坑
		if (left < right) {
			array[left] = array[right];
			++left;
		}
		// 现在是 arr[right] 需要填坑了
		while (temp >= array[left] && left < right) {
			++left;
		}
		if (left < right) {
			array[right] = array[left];
			--right;
		}
	}
	array[left] = temp;
	return left;
}
```

#### 6.4 算法分析

**最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(nlogn)**　



### 7、堆排序（Heap Sort）

堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。

#### 7.1 算法描述

- 将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；
- 将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]；
- 由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，然后再次将R[1]与无序区最后一个元素交换，得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。

#### 7.2 动图演示

![堆排序](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/堆排序.gif)

#### 7.3 代码实现

注意：这里用到了完全二叉树的部分性质

```java
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
```

#### 7.4 算法分析

**最佳情况：T(n) = O(nlogn) 最差情况：T(n) = O(nlogn) 平均情况：T(n) = O(nlogn)**



### 8、计数排序（Counting Sort）

计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。

计数排序(Counting sort)是一种稳定的排序算法。计数排序使用一个额外的数组C，其中第i个元素是待排序数组A中值等于i的元素的个数。然后根据数组C来将A中的元素排到正确的位置。它只能对整数进行排序。

#### 8.1 算法描述

- 找出待排序的数组中最大和最小的元素；
- 统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
- 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
- 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。

#### 8.2 动图演示

![计数排序](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/计数排序.gif)

#### 8.3 代码实现

```java
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
```

#### 8.4 算法分析

当输入的元素是n 个0到k之间的整数时，它的运行时间是 O(n + k)。计数排序不是比较排序，排序的速度快于任何比较排序算法。由于用来计数的数组C的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），这使得计数排序对于数据范围很大的数组，需要大量时间和内存。

**最佳情况：T(n) = O(n+k)  最差情况：T(n) = O(n+k)  平均情况：T(n) = O(n+k)**



### 9、桶排序（Bucket Sort）

桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。

桶排序 (Bucket sort)的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排

#### 9.1 算法描述

- 人为设置一个BucketSize，作为每个桶所能放置多少个不同数值（例如当BucketSize==5时，该桶可以存放｛1,2,3,4,5｝这几种数字，但是容量不限，即可以存放100个3）；
- 遍历输入数据，并且把数据一个一个放到对应的桶里去；
- 对每个不是空的桶进行排序，可以使用其它排序方法，也可以递归使用桶排序；
- 从不是空的桶里把排好序的数据拼接起来。 

**注意，如果递归使用桶排序为各个桶排序，则当桶数量为1时要手动减小BucketSize增加下一循环桶的数量，否则会陷入死循环，导致内存溢出。**

#### 9.2 图片演示

![桶排序](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/桶排序.jpg)

#### 9.3 代码实现

```java
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
```

#### 9.4 算法分析

桶排序最好情况下使用线性时间O(n)，桶排序的时间复杂度，取决与对各个桶之间数据进行排序的时间复杂度，因为其它部分的时间复杂度都为O(n)。很显然，桶划分的越小，各个桶之间的数据越少，排序所用的时间也会越少。但相应的空间消耗就会增大。 

**最佳情况：T(n) = O(n+k)   最差情况：T(n) = O(n+k)   平均情况：T(n) = O(n2)**　　



### 10、基数排序（Radix Sort）

基数排序也是非比较的排序算法，对每一位进行排序，从最低位开始排序，复杂度为O(kn),为数组长度，k为数组中的数的最大的位数；

基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。基数排序基于分别排序，分别收集，所以是稳定的。

#### 10.1 算法描述

- 取得数组中的最大数，并取得位数；
- arr为原始数组，从最低位开始取每个位组成radix数组；
- 对radix进行计数排序（利用计数排序适用于小范围数的特点）；

#### 10.2 动图演示

![基数排序](https://raw.githubusercontent.com/JourWon/image/master/史上最全经典排序算法总结(Java实现)/基数排序.gif) 

#### 10.3 代码实现

```java
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
```

#### 10.4 算法分析

**最佳情况：T(n) = O(n \* k)   最差情况：T(n) = O(n \* k)   平均情况：T(n) = O(n \* k)**

基数排序有两种方法：

MSD 从高位开始进行排序 LSD 从低位开始进行排序 

 

**基数排序 vs 计数排序 vs 桶排序**

这三种排序算法都利用了桶的概念，但对桶的使用方法上有明显差异：

- 基数排序：根据键值的每位数字来分配桶
- 计数排序：每个桶只存储单一键值
- 桶排序：每个桶存储一定范围的数值