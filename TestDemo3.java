package JavaPractice;
import java.util.ArrayList;
import java.util.List;

public class TestDemo3 {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(13);
//        list.add(15);
//        list.add(3);
//        list.add(5);
//        System.out.println(list);
//        Collections.sort(list);
//        System.out.println(list);


        /**
         * 判断字符串1中是否含有字符串s的字符，如果有则删除，并返回字符串1
         */
        String str1 = "welcome to bit";
        String str2 = "come";
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < str1.length() ; i++) {
            char tmp = str1.charAt(i);
            //虽然str2.contains()拿到了str1中的字符，但是因为str1已经转成了char类型，
            // 所以tmp+""表示拿到的为字符串String类型的
            if(!str2.contains(tmp+"")){
                list.add(tmp);
            }
        }
        //打印list
        for (int i = 0; i <list.size() ; i++) {
            System.out.print(list.get(i));
        }
    }

}
