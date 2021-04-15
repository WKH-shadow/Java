package Practice;


import java.util.Arrays;
import java.util.PriorityQueue;

public class Demo5 {

    public static int[] smallestK(int[] arr, int k){
        //申请一个大小为k的数组，来存放弹出的k个元素
        int[] array = new int[k];
        //首先判断数组是否为空，或者k是否为有效值
        if(arr == null ||k <= 0){
            return null;
        }

        //然后将数组里面的所有数全部都放在优先级队列里面
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.offer(arr[i]);
        }

        //然后根据PriorityQueue的特性，将队顶的k个元素弹出，并将其放入一个新的数组里面

        for (int i = 0; i < k; i++) {
            array[i] = priorityQueue.poll();
        }
        return array;
    }
    public static void main(String[] args) {
        int[] arr = {3,2,1};
        int[] ret = smallestK(arr,2);
        System.out.println(Arrays.toString(ret));
    }
}
