package JavaPractice;

/**
 * 杨辉三角
 */

import java.util.ArrayList;
import java.util.List;

class TestDemo5 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        if(numRows == 0){
            return ret;
        }
        //第一行的数据都是1，使用我们直接new一个ArrayList，将1放进去
        List<Integer> List1 = new ArrayList<>();
        List1.add(1);
        ret.add(List1);
        //也可以直接写一个匿名对象
//        ret.add(new ArrayList<>());
//        ret.get(0).add(1);

        //接下来就是从第二行开始，也就是数组的第一行
        for(int i = 1;i < numRows; i++){
            //把整个堪称一个二维数组，然后每一行都产生了一个新的数组，所以每一次也要产生新的ArrayList
            List<Integer> list = new ArrayList<>();
            //首先每一行的第一个数据都是1，所以直接add一个1
            list.add(1);
            //在这里记录前一行的数据，因为ret里面存放的是List<Integer>，所以我们可以直接通过ret拿到i-1行的数据
            List<Integer> preRows = ret.get(i-1);

            //然后每一行里面又有每一列，所以接着看每一列数字的变化,因为第一列已经add（1）了，
            //所以直接从第二列开始看，也就是数组中的1号下标,因为只有j<i的时候循环才能走下去，一旦j=i的时候
            //也就是每一行的最后一个数据都为1，所以当j=i的时候，直接add（1）
            for(int j = 1; j < i; j++){
                //我们从图中可以看出从第二行开始，我们可以有规律的发现，
                //每一个数字都是上一行数字加上上一行的前一个数字之和，也就是[i-1,j]+[i-1,j-1]
                //但是我们首先必须知道前一行的数据，所以我们有了记录前一行的数据,然后计算数据
                int nums = preRows.get(j)+preRows.get(j-1);
                //然后再将计算的数据添加到对应行里面，即list里面
                list.add(nums);
            }
            list.add(1);
            //然后再将每一行的数据添加到ret当中，就形成了 List<List<Integer>>，即
            ret.add(list);
        }
        return ret;
    }

    public static void main(String[] args) {
        TestDemo5 testDemo5 = new TestDemo5();
        List<List<Integer>> lists = testDemo5.generate(5);
        System.out.println(lists);
    }
}