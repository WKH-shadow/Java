package JavaPractice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestDemo34 {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("黑旋风","李逵");
        map.put("豹子头","林冲");
        map.put("青面兽","杨志");
        map.put("及时雨","宋江");
        System.out.println(map);
        String s = map.get("黑旋风");
        System.out.println(s);
        String s1 = map.getOrDefault("黑旋风1","李逵");
        System.out.println(s1);
        System.out.println(map.containsKey("黑旋风"));
        System.out.println("===========================");
        System.out.println(map.isEmpty());
        int a = map.size();
        System.out.println(a);
        Set<Map.Entry<String,String>> mapSet = map.entrySet();
        System.out.println(mapSet);
        map.remove("黑旋风");
        for (Map.Entry<String,String> mapset:map.entrySet()
             ) {
            System.out.println(mapset.getKey()+" "+mapset.getValue());
        }
    }
}
