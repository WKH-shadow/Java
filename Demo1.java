package NK;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述：两数之和
 * 给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
 * 你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
 * 假设给出的数组中只存在唯一解
 * 例如：
 * 给出的数组为 {20, 70, 110, 150},目标值为90
 * 输出 index1=1, index2=2
 */
public class Demo1 {
    //暴力破解法，首先从数组中按照顺序拿到一个元素，然后遍历它之后的元素，如果想加之和等于目标值，那么就返回下标
    public int[] twoSum (int[] numbers, int target){
        //申请一个数组存放两个值的下标
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                if(numbers[i]+numbers[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    //倒推法
    public int[] twoSum1 (int[] numbers, int target){
        //先遍历一遍，将数组中的数字存放再map里面，然后value对一个存放它的下标
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i],i);
        }
        //然后再次遍历数组，如果出现等于target-numbers[i]的值，就
        // 返回它的对应的key（因为get（key）方法是根据key值返回value）
        for (int i = 0; i < numbers.length; i++) {
            if(map.containsKey(target-numbers[i]) && map.get(numbers[i]) != i){
                return new int[]{map.get(numbers[i]),i};
            }
        }
        //如果找不到还是一样返回-1.-1
        return new int[]{-1,-1};
    }

    //一次hash法
    public int[] twoSum2 (int[] numbers, int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int needNumber = target-numbers[i];
            if(map.containsKey(needNumber)){
                return new int[]{map.get(numbers[i]),i};
            }
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        int[] arr = {5,7,1,4,3,8};
        Demo1 demo1 = new Demo1();
        int[] ret = demo1.twoSum(arr,4);
        System.out.println(Arrays.toString(ret));
        System.out.println("==============================");
        int[] ret1 = demo1.twoSum(arr,4);
        System.out.println(Arrays.toString(ret1));
        System.out.println("==============================");
        int[] ret2 = demo1.twoSum(arr,4);
        System.out.println(Arrays.toString(ret2));
    }
}
