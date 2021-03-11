package JavaPractice;

import java.util.Stack;

/**
 * 括号匹配问题
 * 方法：
 * 1.遍历当前字符串，如果是左括号就入栈
 * 2.当 当前的字符串的字符是右括号，那么和当前栈顶的元素的括号进行批匹配，如果匹配成功，那么直接将栈顶出栈。
 * 3.当i下标对应的字符串和栈顶的字符串不能够匹配的时候，那么说明，右括号匹配错位
 * 4.当遍历结束后，如果i下标已经没有元素，此时栈内为空，那么字符串就完成匹配
 * （4.1）如果i下标还有元素，但是栈内为空，说明此时右括号多，此时匹配失败
 * （4.2）如果i下标没有元素，但是栈内还有元素，说明此时左括号多，此时也匹配失败
 */
public class TestDemo7 {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        //首先遍历字符串
        for (int i = 0; i < s.length(); i++) {
            //然后遇见左括号“（”，“[”，“{”的时候入栈
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                stack.push(s.charAt(i));
            }else if(stack.empty()){
                System.out.println("说明右括号多");
            }else {
                char ch = stack.peek();
                if(ch == '('&&s.charAt(i) == ')'||ch == '['&&s.charAt(i) == ']'||ch == '{'&&s.charAt(i) == '}'){
                    System.out.println("字符串匹配成功");
                    stack.pop();
                }else {
                    System.out.println("匹配失败");
                    return false;
                }
            }
        }
        if(!stack.empty()){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        String str =  "([)]";
        isValid(str);
    }
}
