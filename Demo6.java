package LeetCode;

/**
 * 最长的连续递增数列
 * count 为当前元素峰值，ans为最大峰值
 * 初始化 count = 1
 * 从 0 位置开始遍历，遍历时根据前后元素状态判断是否递增，递增则 count++，递减则 count=1
 * 如果 count>ans，则更新 ans
 * 直到循环结束
 */
public class Demo6 {
    public static int findLengthOfLCIS(int[] nums) {
        if(nums.length <= 1)
            return nums.length;
        int ans = 1;
        int count = 1;
        for(int i=0;i<nums.length-1;i++) {
            if(nums[i+1] > nums[i]) {
                count++;
            } else {
                count = 1;
            }
            ans = count > ans ? count : ans;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {1,3,5,4,7};
        int ret = findLengthOfLCIS(arr);
        System.out.println(ret);
    }
}
