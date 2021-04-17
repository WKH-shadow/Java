package Sort;

import java.util.Arrays;

public class TestSort {
    /**
     *    直接插入排序
     *
     * 特点：就是越有序的一组数据用直接插入排序更快
     * 时间复杂度：       最坏情况下(数据逆序):O(n^2)            最好情况下(数据有序):O(n)
     * 空间复杂度：O(1)  没有申请额外的空间
     * 稳定性：
     * 注意：1.一个稳定的排序可以实现为一个不稳定的排序
     *      2.一个不稳定的排序不可以实现成为一个稳定的排序
     *      总结：也就是没有发生跳跃式的交换数据，那么就是稳定的排序
     *
     */
    public void insertSort(int[] arr){
        if(arr == null){
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j;
            for (j = i-1; j >=0; j--) {
                if(arr[j] >= tmp){
                    arr[j+1] = arr[j];
                }else {
                    break;
                }
            }
            arr[j+1] = tmp;
        }
    }

    /**
     * 希尔排序
     * 思路分析:  分组的思想
     * 在希尔排序里我们首先会设置一个增量值 ，然后把数据按照增量分为几组（分组只是逻辑上的分组并非真的分组）
     * 然后对分组内的数据进行排序。排序完成后 增量变小，然后再根据增量进行排序。
     * 依次类推 当增量为1时，再进行排序的时候，此时的数据经过前几次的处理，变得相对很规律
     *
     * 时间复杂度：最坏的情况下O(n^2) , 最好的情况下(有序)：O(n)
     * 空间复杂度：O(1)
     * 稳定性: 不稳定
     *
     * @param array
     */
    public void shellSort(int[] array){
        //分几次，每次分几组
        int[] drr = {5,3,1};
        for (int i = 0; i < drr.length; i++) {
            shell(array,drr[i]);
        }
    }
    public void shell(int[] arr,int gap){
        for (int i = gap; i < arr.length; i++) {
            int ret = arr[i];
            int j;
            for (j= i-gap; j >= 0 ; j-=gap) {
                if(arr[j] > ret){
                    arr[j+gap] = arr[j];
                }else {
                    break;
                }
            }
            arr[j+gap] = ret;
        }
    }
    public static void main(String[] args) {
        int[] arr = {8,1,4,3,4,6,7};
        TestSort testSort =new TestSort();
//        testSort.insertSort(arr);
//        System.out.println(Arrays.toString(arr));
        testSort.shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
