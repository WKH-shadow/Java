package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拥有最多糖果的孩子
 */
public class Demo2 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> list = new ArrayList<>();
        //找出这组数据的最大值
        int max = candies[0];
        for (int i = 1; i < candies.length; i++) {
            if(candies[i] > max){
                max = candies[i];
            }
        }

        for (int i = 0; i < candies.length; i++) {
            if((candies[i]+extraCandies) >= max){
                list.add(true);
            }else {
                list.add(false);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,5,1,3};
        Demo2 demo2 = new Demo2();
        System.out.println(demo2.kidsWithCandies(arr, 3));
    }
}
