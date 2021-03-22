package WEB;

/**
 * 单例模式——懒汉模式
 */
public class ThreadDemo15 {
    static class Singleton{
        //将构造函数设置为私有
        private Singleton(){}
        //设置一个私有属性，并且不会对它进行赋值
        private static Singleton instance = null;
        public static synchronized Singleton getInstance(){
            //对其进行判断，当为空的时候再去创建一个对象
            if(instance == null){
                instance = new Singleton();
            }
            return instance;
        }
    }
    public static void main(String[] args) {
        //切记不能使用Singleton singleton1 = new Singleton(),这样之后来调用getInstance方法，
        // 因为一旦new就会产生新的对象
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);
    }
}
