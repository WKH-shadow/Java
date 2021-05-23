package LeetCode;

/**
 * 最长回文串
 */
public class Demo11 {
    public boolean longestPalindromeChild(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // 暴力解法
    public String longestPalindrome(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (longestPalindromeChild(test) && test.length() > max) {
                    ans = s.substring(i, j);
                    max = Math.max(max, ans.length());
                }
            }
        return ans;
    }
    public static void main(String[] args) {
        String str = "babad";
        Demo11 demo11 = new Demo11();
        System.out.println(demo11.longestPalindrome(str));
    }
}
