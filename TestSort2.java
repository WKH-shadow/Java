package Sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 */
public class TestSort2 {
    /**
     * 方法一：基准法(挖坑法)
     * 思路：(分治法)将拿到的第一个数作为基准，然后将小于它的数据放在左边，大于它的放在右边
     * 时间复杂度：nlogn
     * 空间复杂度：O(logn)
     * 稳定性：不稳定
     */
    //首先写一个方法找到这个区间的基准
    public  int partition(int[] array,int left,int right){
        int tmp = array[left];
        //然后从最后一个下标开始进行查找，当遇到小于tmp的数据的时候就放在见面的空位置上
        //当放进去了之后，此时再从left的位置向右走，当遇到第一个大于tmp的时候，就将其放在后面的那个空位置上
       while (left < right){
           //此时从右边开始查找，直到找到第一个小于tmp的值
           while (left < right && array[right] >= tmp){
               right--;
           }
           if(left >= right){
               break;
           }else {
               array[left] = array[right];
           }

           while (left < right && array[left] < tmp){
               left++;
           }
           if(left >= right ){
               break;
           }else {
               array[right] = array[left];
           }
       }
       array[left] = tmp;
       return left;
    }
    public void quick(int[] array,int left,int right){
        //递归的结束条件
        if(left > right) {
            return;
        }
            int pivot = partition1(array, left, right);
            quick(array, left, pivot - 1);
            quick(array, pivot + 1, right);
        }

    public void quickSort(int[] array){
        quick(array,0,array.length-1);
    }

    /**
     * 快速排序的优化版本(hoare法)
     * 思路：也就是从左边->右边寻找第一个大于tmp的下标，从右边->左边寻找第一个小于tmp的值，然后进行交换
     * 也就是上面的partition方法1不一样，其他都一样
     * @param
     */
    public  int partition1(int[] array,int left,int right){
        int i = left;
        int j = right;
        int tmp = array[left];
        while (i < j){
            //在右边找到第一个大于tmp的下标
            while (i < j && array[j] >= tmp){
                j--;
            }
            //在左边找到第一个小于tmp的下标
            while (i < j && array[j] <= tmp){
                i++;
            }
            //然后将这两个下标对一个的值进行交换
            int ret = array[i];
            array[i] = array[j];
            array[j] = ret;
        }
        //此时当i >= j的时候交换i下标和left下标的值
        int ret = array[i];
        array[i] = array[left];
        array[left] = ret;
        return i;
    }

    public static void main(String[] args) {
        int[] array = {1,5,4,3,1,5,89,14};
        TestSort2 testSort2 = new TestSort2();
        testSort2.quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
