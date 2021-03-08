package JavaPractice;
/*
注：
静态代码块不能访问实例成员变量，静态的东西属于类，不属于对象，属于类不属于对象的都在方法区
对象的产生：
（1）.为对象分配内存
（2）.调用合适的构造方法
对象属性的初始化：
（1）.提供一系列的get set方法
（2）.构造方法进行初始化
（3）.代码块
 */

//<T>只是一个占位符，代表当前类是一个泛型，这里的T也可以是K,V,E等
//如果后面有指定类型，那么这里的T就是什么类型
class Person{
    String name;
    int age;

    public Person(String name) {
        this.name = name;
    }
}

public class MyArrayList<T> {
    public T[] elem;
    public int UsedSize;
    //使用构造方法对其赋值
    public MyArrayList(){
        //这里有一个坑，那就是泛型类的数组是不可以new的，如T[] t = new T[]; ERROR
        this.elem = (T[]) new Object[10];
        this.UsedSize = 0;
    }
    //插入：每次放在最后
public void Insert(T value){
        this.elem[this.UsedSize] = value;
        UsedSize++;
}
//得到顺序表index的值
public Object getIndex(int index){
        return this.elem[index];
}

    public static void main(String[] args) {
      Person person = new Person("物理");
        System.out.println(person);
        MyArrayList<String> myArrayList = new MyArrayList<>();
        System.out.println(myArrayList);
        MyArrayList<Integer> myArrayList1 = new MyArrayList<>();
        System.out.println(myArrayList1);
    }
}
