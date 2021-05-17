package LeetCode;

/**
 * 最大子序和：
 * 1.首先对数组进行遍历，当前最大连续子序列和为 sum，结果为 ans
 * 2.如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
 * 3.如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
 * 4.每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果
 * 时间复杂度：O(n)O(n)
 *
 */
public class Demo4 {
    public static int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        int ret = maxSubArray(arr);
        System.out.println(ret);
    }
}
