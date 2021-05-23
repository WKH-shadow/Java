package LeetCode;

/**
 * 买股票的最佳时机
 */
public class Demo10 {
    /**
     * 暴力解法：直接循环遍历
     */
    public int maxProfit(int[] prices) {
        //定义一个max来存放最大值
        int max = 0;
        for (int i = 0; i < prices.length-1; i++) {
            for (int j = i+1; j < prices.length-1; j++) {
                int ret = prices[j] - prices[i];
                if(ret > max){
                   max = ret;
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        Demo10 demo10 = new Demo10();
        System.out.println(demo10.maxProfit(arr));
    }
}
