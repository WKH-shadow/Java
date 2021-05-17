package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角进阶
 */
public class Demo5 {
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
        }
        return cur;
    }

    public static void main(String[] args) {
        List<Integer> list = getRow(5);
        System.out.println(list);
    }
}
