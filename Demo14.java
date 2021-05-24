package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中的第一个唯一字符
 */
public class Demo14 {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap();
        char[] chars = s.toCharArray();
        //先统计每个字符的数量
        for (char ch : chars) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        //然后在遍历字符串s中的字符，如果出现次数是1就直接返回
        for (int i = 0; i < s.length(); i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;

    }
    public static void main(String[] args) {
        String s = "loveleetcode";
        Demo14 demo14 = new Demo14();
        int ret = demo14.firstUniqChar(s);
        System.out.println(ret);
    }
}
