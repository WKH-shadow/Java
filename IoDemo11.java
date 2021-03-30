package IO;

import java.io.*;

class Person implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class IoDemo11 {
    public static void main(String[] args) {
        //序列化操作
        //serialization();
        //反序列化操作
        //Person person = deserialization();
        //System.out.println("id: "+person.getId());
        //System.out.println("name:" + person.getName());
    }

    //序列化
    private static void serialization() {
        //创建一个Person对象
        Person person = new Person();
        person.setId(100);
        person.setName("Java");
        String filePath = "D:\\io_test\\2\\person.txt";
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
            //序列化要调用writeObject()方法，然后将对象传进去
            objectOutputStream.writeObject(person);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //反序列化,因为反序列化会把二进制转换成一个对象，所以返回值就使用定义的Person
//    private static Person deserialization() {
//        //创建一个对象用来接收
//        Person person = null;
//        //反序列化需要操作的文件地址,也就是我们刚刚序列化的文件
//        String filePath = "D:\\io_test\\2\\person.txt";
//        try{
//            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
//            {
//                //这里需要将强转成Person类
//                person = (Person) objectInputStream.readObject();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return person;
//    }

}
