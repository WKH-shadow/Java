package IO;

import java.io.*;

/**
 * 版本UID问题
 */
public class IoDemo12 {
    static class  Person implements Serializable {
        public final static long  serialVersionUID = 1L;
        private int id;
        private int age;
        private String name;
        public static int count = 1;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
    public static void main(String[] args) {
        Person person =  new Person();
        person.setId(111);
        person.setAge(18);
        person.setName("豹子头");
        //需要将二进制存在哪里的路径
        String filePath = "D:\\io_test\\2\\person1";
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
            {
                //进行序列化操作
                objectOutputStream.writeObject(person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
