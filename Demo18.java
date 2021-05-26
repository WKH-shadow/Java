package LeetCode;

import java.util.Stack;

/**
 * 题目描述：最长的括号子串
 * 给出一个仅包含字符'('和')'的字符串，计算最长的格式正确的括号子串的长度。
 * 对于字符串"(()"来说，最长的格式正确的子串是"()"，长度为2.
 * 再举一个例子：对于字符串")()())",来说，最长的格式正确的子串是"()()"，长度为4
 */
public class Demo18 {
    public static int longestValidParentheses (String s){
    if(s == null || s.length() <= 0)
            return 0;

    Stack<Integer> stack = new Stack<>();
    int max= 0;
    int last = -1;
        for(int i = 0; i < s.length(); i++){
        // 遇到左括弧就压栈
        if(s.charAt(i) == '(')
            stack.push(i);
        else{
            // 栈为空，更新起始点的位置
            if(stack.isEmpty())
                last = i;
            else{
                stack.pop();
                // 更新合法括弧的长度
                if(stack.isEmpty())
                    max = Math.max(max, i - last);
                else
                    max = Math.max(max, i - stack.peek());
            }
        }
    }
        return max;
}

    public static void main(String[] args) {
        String s = "((())))))";
        System.out.println(longestValidParentheses(s));
    }
}
