package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目描述：
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 */
public class Demo8 {
    /**
     * 解题思路：
     * 遍历数组，数字放到 set 中。如果数字已经存在于 set 中，
     * 直接返回 true。如果成功遍历完数组，则表示没有重复元素，返回 false。
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
    public static void main(String[] args) {
        int[] arr = {1,1,1,3,3,4,3,2,4,2};
        Demo8 demo8 = new Demo8();
        System.out.println(demo8.containsDuplicate(arr));
    }
}
