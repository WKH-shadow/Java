package LeetCode;

/**
 * 寻找第k大
 * 思路分析：
 * 快速排序思想寻找第k大元素
 */
public class Demo16 {
    public static int findKth(int[] a, int n, int K) {
        return find(a, 0, n-1, K);
    }

    //递归寻找数组中第K大的元素
    private static int find(int[] a, int low, int high, int K) {
        int pivot = partition(a, low, high);

        if(pivot + 1 < K)
            return find(a, pivot+1, high, K);
        else if(pivot + 1 > K)
            return find(a, low, pivot-1, K);
        else
            return a[pivot];
    }

    //将数组划分为两部分，左边较大，右边较小
    private static int partition(int[] a, int low, int high) {
        // 将数组首元素作为每一轮比较的基准
        int pivotValue = a[low];

        while (low < high) {
            // 从右往左扫描，直到遇到比基准元素小的元素
            while (low < high && a[high] <= pivotValue)
                --high;


            a[low] = a[high];

            // 从左往右扫描，直到遇到比基准元素大的元素
            while (low < high && a[low] >= pivotValue)
                ++low;


            a[high] = a[low];
        }

        // 将基准元素放到中间位置
        a[low] = pivotValue;

        // 返回数组的中轴位置
        return low;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,2,2};
        int ret = findKth(arr,5,3);
        System.out.println(ret);
    }
}
