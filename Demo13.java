package LeetCode;

/**
 *反转字符串
 */
public class Demo13 {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length;) {
            if(chars.length - i < k) {
                for (int j = chars.length - 1; j > i ; j--,i++) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
                break;
            }else {
                for (int j = i + k - 1; j > i; j--,i++) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
                i = i - (k / 2) + 2 * k;
            }
        }
        return new String(chars);
    }
    public static void main(String[] args) {
        String s = "hello world";
        Demo13 demo13 = new Demo13();
        System.out.println(demo13.reverseStr(s, 3));
    }
}
