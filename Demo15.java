package LeetCode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 数组中出现次数超过一半的数字
 */
public class Demo15 {
    /**
     * 方法：使用HashMap，key值对应数组里面不同的数字，val值对应不同数字出现的次数
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int [] array){
    HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<array.length;i++){

            if(!map.containsKey(array[i])){
                map.put(array[i],1);
            }else{
                int count = map.get(array[i]);
                map.put(array[i],++count);
            }
        }
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            Integer key =(Integer)entry.getKey();
            Integer val = (Integer)entry.getValue();
            if(val>array.length/2){
                return key;
            }
        }
        return 0;
}
    public static void main(String[] args) {
        int[] arr = {1,2,3,2,2,2,5,4,2};
        Demo15 demo15 = new Demo15();
        System.out.println(demo15.MoreThanHalfNum_Solution(arr));
    }
}
