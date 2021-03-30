package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class IoDemo13 {
    public static void main(String[] args) {
        IoDemo12.Person person = null;
        String filePath = "D:\\io_test\\2\\person1";
        person.count = 2;
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
            {
                person = (IoDemo12.Person) objectInputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println("person id :"+person.getId());
        //System.out.println("person name :"+person.getName());
        System.out.println("count = "+ person.count);
    }
}
