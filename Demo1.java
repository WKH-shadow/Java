package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断句子是否为全字母句
 */
public class Demo1 {
    public boolean checkIfPangram(String sentence) {
        List<Character> list = new ArrayList<>();
        char[] chars = sentence.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            while (!list.contains(chars[i])){
                list.add(chars[i]);
            }
        }
        //此时在遍历list，看他是否包含所有字母
        if(list.size() == 26){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "leetcode";
        Demo1 demo1 = new Demo1();
        boolean ret = demo1.checkIfPangram(str);
        System.out.println(ret);
    }
}
