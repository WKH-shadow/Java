package LeetCode;


import java.util.Arrays;

/**
 * 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 */
public class Demo3 {

    //char类型的英文字母那么是可以直接像数字一样进行排序的，因为它底层采用的是unicode编码存储，实际上也是数字.
    //那么我们就将字符串转换成数组进行排序，这里使用直接插入排序
    public void insertSort(char[] array){
        if(array == null){
            return;
        }
        for (int i = 1; i < array.length; i++) {
            char tmp = array[i];
            int j = 0;
            for (j= i-1; j >=0 ; j--) {
                if(array[j] >= tmp){
                    array[j+1] = array[j];
                }else {
                    break;
                }
            }
            array[j+1] = tmp;
        }
    }
    //这里是将字符数组在转换成字符串，使用拼接的方法
    public String transform(char[] chars){
        String str = "";
        for (int i = 0; i < chars.length; i++) {
            str += chars[i];
        }
        return str;
    }
    public boolean isAnagram(String s, String t) {
        if(s == null && t != null){
            return false;
        }
        if(s != null && t == null){
            return false;
        }
        //转换成字符数组
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        //对两个数组进行排序
        insertSort(chars1);
        insertSort(chars2);
        //在将其转换成字符串
        String s1 = transform(chars1);
        String s2 = transform(chars2);
        if(s1.equals(s2)){
            return true;
        }
        return false;
    }


    //方法二：直接对其进行排序然后比较
    public boolean isAnagram1(String s, String t){
        if(s.length() != t.length()){
            return false;
        }
        char[] chars1 = s.toCharArray();
        Arrays.sort(chars1);
        char[] chars2 = t.toCharArray();
        Arrays.sort(chars2);
        for (int i = 0; i < chars1.length; i++) {
            if(chars1[i] != chars2[i]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String str1 = "va3";
        String str2 = "va";
        Demo3 demo3 = new Demo3();
        System.out.println(demo3.isAnagram(str1, str2));
    }
}
