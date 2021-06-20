package TestDemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 在list当中找到第一个出现的重复数字
 */
public class TestDemo6 {
    public static int ret = 10_0000;
    public static void main(String[] args) {
        //生成十万个数据
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        //首先将十万个数据放到list当中
        for (int i = 0; i < 10_0000; i++) {
            arrayList.add(random.nextInt(6_0000));
        }
        //然后将这十万个数据放入到,但是放入之前首先都判断set里面是否包含这个数字，如果包含，那么直接记录这个数字，然后结束，
        // 如果不包含，那么就接着往set里面放入数据
        Set<Integer> set = new HashSet<>();
        int ret = 0;
        for (Integer value: arrayList
             ) {
            if(set.contains(value)){
                System.out.println(value);
                break;
            }else {
                set.add(value);
            }
        }
        }
}
