package LeetCode;

/**
 * 题目描述：
 * 最后一个单词的长度
 */
public class Demo9 {
    /**
     * 解题思路：
     * 从字符串末尾开始向前遍历，其中主要有两种情况
     * 第一种情况，以字符串"Hello World"为例，从后向前遍历直到遍历到头或者遇到空格为止，即为最后一个单词"World"的长度5
     * 第二种情况，以字符串"Hello World "为例，需要先将末尾的空格过滤掉，再进行第一种情况的操作，即认为最后一个单词为"World"，长度为5
     * 所以完整过程为先从后过滤掉空格找到单词尾部，再从尾部向前遍历，找到单词头部，最后两者相减，即为单词的长度
     * 时间复杂度：O(n)，n为结尾空格和结尾单词总体长度
     * @return
     */
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while(end >= 0 && s.charAt(end) == ' ')
            end--;
        if(end < 0)
            return 0;
        int start = end;
        while(start >= 0 && s.charAt(start) != ' ')
            start--;
        return end - start;
    }
    public static void main(String[] args) {
        String str = "Hello World";
        Demo9 demo9 = new Demo9();
        System.out.println(demo9.lengthOfLastWord(str));
    }
}
