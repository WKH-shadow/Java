package TestDemo;

import java.util.HashSet;
import java.util.Set;

/**
 * 石头与宝石：
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 *
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 *
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 */
public class TestDemo10 {

    /**
     * 思路方法，
     * 既然说了J中的数据不重复，那么我们就可以将其放入到set集合当中，然后在遍历S，定义一个count，如果存在就++，最后返回count
     * @param jewels
     * @param stones
     * @return
     */
    public int numJewelsInStones(String jewels, String stones) {
        //因为set集合存放的数据是不重复的，所以用set集合
        Set<Character> set = new HashSet<>();
        //首先将jewls转换成字符数组
        char[] chars = jewels.toCharArray();
        //然后遍历将其放入到set中
        for (char s: chars
             ) {
            set.add(s);
        }
        //然后定义一个count，然后开始遍历S
        int count = 0;
        for (char s: stones.toCharArray()
             ) {
            if(set.contains(s)){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        String jewls = "aA";
        String stones = "aAAbbb";
        TestDemo10 testDemo10 = new TestDemo10();
        int ret = testDemo10.numJewelsInStones(jewls,stones);
        System.out.println(ret);
    }
}
