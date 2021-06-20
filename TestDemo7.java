package TestDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 将十万个数据，统计重复数字出现的次数
 */
public class TestDemo7 {
    public static void main(String[] args) {
        //首先生成十万个数据
        Random random = new Random();
        //然后讲个十万个随机数放入到list当中
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10_0000; i++) {
            arrayList.add(random.nextInt(6_0000));
        }
        //然后使用hashMap,然后开始遍历list中的数据，在放进去之前首先得判断一下是否包含key，如果包含的话，那么就将value+1，如果不包含，那么就接着遍历下一个
        Map<Integer,Integer> map = new HashMap<>();
        for (Integer value: arrayList
             ) {
            if(map.get(value) == null){
                map.put(value,1);
            }else {
                map.put(value,map.get(value)+1);
            }
        }
        //然后遍历map
        for (Map.Entry<Integer,Integer> entry:map.entrySet()
             ) {
            System.out.println("重复的数字"+entry.getKey()+" "+"重复次数"+entry.getValue());
        }
    }
}
