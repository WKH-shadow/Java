package JavaPractice;

public class TestDemo3_5 {
    /**
     * 装箱（装包）：简单类型变为包装类类型
     * 拆箱（拆包）：包装类类型变为简单类型
     * @param args
     */
    //因为底层的Integer有一个取值区间，如果不在这个区间，那么就找不到，返回false
    //区间为[-128,127]
    public static void main(String[] args) {
        Integer a = 100;
        Integer b = 100;
        System.out.println(a == b);//返回true

        Integer c = 200;
        Integer d = 200;
        System.out.println(c == d);//不在区间范围内，返回false
    }
}
