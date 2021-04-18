package Sort;


/**
 * 堆排序    冒泡排序    直接选择排序
 */

import java.util.Arrays;
import java.util.Random;

public class TestSort1 {
    public void swap(int[] array,int x,int y){
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }
    /**
     * 直接选择排序
     * 思想：每次从待排序数字的后面，选取一个比当前数字小的进行交换，直到把当前的序列遍历完成
     * 时间复杂度(不分好坏)：O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：不稳定的排序
     */
    public  void selectSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if(array[i] > array[j]){
                    swap(array,i,j);
                }
            }
        }
    }

    /**
     * 堆排序
     * 思路：首先创建一个大根堆，然后每次最后一个元素和第一个元素下标进行交换，然后再将其变成一个大根堆，以此类推
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     * @param array
     */
    public void headSort(int[] array){
        //首先创建大根堆,时间复杂度为O(n)
        createHeap(array);
        //创建完大根堆之后，然后每次交换0号下标和最后一个下标的值
        int end = array.length-1;
        while (end > 0){
            int tmp = array[0];
            array[0] = array[end];
            array[end] = tmp;
            //然后再对其进行调整
            adjustDown(array,0,end);
            end--;
        }
    }

    private int usedSize = 0;
    private int[] createHeap(int[] array) {
        //i 代表要调整的节点
        for (int i = (array.length-1-1)/2; i >= 0 ; i--) {
            //对这个节点进行向下调整,传入要调整的节点的位置，和结束的位置
            adjustDown(array,i,array.length);
        }
        return array;
    }

    private void adjustDown(int[] array,int root, int length) {
        int parent = root;//要调整的根节点
        int child = 2*parent+1;//左孩子
        //首先左孩子节点的下表必须小于结束位置的下表
        while (child < length){
            //然后判断是否有右孩子，并且找出左右孩子的最大值存放再child++下
            if((child+1) < length && array[child] < array[child+1]){
                child++;
            }
            //此时判断左右孩子的最大值是不是大于父亲节点
            if(array[child] > array[parent]){
                int tmp = array[child];
                array[child] = array[parent];
                array[parent] = tmp;
                //然后再让父亲节点指向孩子节点，孩子节点指向当前父亲的孩子节点
                parent = child;
                child = 2*parent+1;
            }else {
                break;
            }
        }
    }

    /**
     * 冒泡排序：
     * 思想：在无序区间，通过相邻数的比较，将最大的数冒泡到无序区间的最后，持续这个过程，直到数组整体有序
     * 时间复杂度：  在没有优化的情况下最好和最坏都是O(n^2)  优化版本，也就是本来就是一个有序数组，那么时间复杂度就是O(n)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     * @param array
     */
    public void bubbleSort(int[] array){
        //外部循环需要多少趟，内部循环进行比较和交换操作
        for (int i = 0; i < array.length-1; i++) {
            //优化版本
            boolean flag = false;
            for (int j = 0; j < array.length-1-i; j++) {
                if(array[j] > array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    flag = true;
                }
            }
            //一趟下来之后如果没有发生任何的交换
            if(flag == false){
                break;
            }
        }
    }

    public static void main(String[] args) {
//        int[] arr = new int[10_0000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = new Random(10_0000).nextInt();
//        }
//        TestSort1 testSort1 = new TestSort1();
//        long begin = System.currentTimeMillis();
//        testSort1.selectSort(arr);
//        long end = System.currentTimeMillis();
//        System.out.println("bengin-end:"+(end-begin));
//        System.out.println(Arrays.toString(arr));
//
//        int[] arr = {4,6,7,51,1,3,4};
//        TestSort1 testSort1 = new TestSort1();
//        testSort1.headSort(arr);
//        System.out.println(Arrays.toString(arr));
//
        int[] arr = {4,5,46,1,43,2};
        TestSort1 testSort1 = new TestSort1();
        testSort1.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
