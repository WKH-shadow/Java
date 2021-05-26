package LeetCode;

/**
 * 题目：判断回文
 * 给定一个字符串，请编写一个函数判断该字符串是否回文。如果回文请返回true，否则返回false
 */
public class Demo17 {
    public boolean judge (String str) {
        for(int i = 0,j = str.length() -1; i<=j ; i++,j--){
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String s = "absba";
        Demo17 demo17 = new Demo17();
        System.out.println(demo17.judge(s));
    }
}
