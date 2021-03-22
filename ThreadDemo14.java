package WEB;

/**
 * 单例模式——饿汉模式
 */
public class ThreadDemo14 {
    static class Singleton{
        //将构造函数设置为私有，因为单例对象的类只能允许一个实例存在
        private Singleton(){
        }
        //设置一个私有属性，其实就是它的实例
        private static Singleton instance = new Singleton();
        //提供统一的获取实例的方法
        public static Singleton getSingleton(){
            return instance;
        }
    }
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getSingleton();
        Singleton singleton2 = Singleton.getSingleton();
        //如果返回true说明是同一个实例，如果返回false，则说明有多个对象
        System.out.println(singleton1 == singleton2);
    }
}
