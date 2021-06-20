package TestDemo;

import java.util.*;

/**
 * 只出现一次的数字
 */
public class TestDemo8 {

    //方法一：使用异或来解决，0异或任何数字都是任何数字，相同的数字异或出来都是0
    //那么当把所有的数字都异或完毕之后，只剩下出现一次的那个数字就出来了
    public int singleNumber1(int[] nums){
        //定义一个ret来接受结果
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            ret = ret ^ nums[i];
        }
        return ret;
    }

    /**
     *  方法二：使用set集合，遍历数组，然后当拿到一个数字的时候就在set里面判断
     *  1.如果不在set集合里面的话，就直接将这数字放进set里面
     *  2.如果在set集合的话，那么就直接使用move将set集合里面的这数字一出去
     *  结果：到最后，set集合剩下来的那个数字就是只出现一次的数字
     */

    public int singleNumber(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])){
                set.remove(nums[i]);
            }else {
                set.add(nums[i]);
            }
        }
        int result = 0;
        //set的一个特性，就是他里面存放的数字是无序的，所以要遍历来查找元素
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])){
                result = nums[i];
                break;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] arr = {2,2,1};
        TestDemo8 testDemo8 = new TestDemo8();
        System.out.println(testDemo8.singleNumber(arr));
        System.out.println("==================");
        System.out.println(testDemo8.singleNumber1(arr));
    }
}
