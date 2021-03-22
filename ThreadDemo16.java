package WEB;

/**
 * 单例模式——懒汉模式
 * 使用synchronized锁同步代码块
 */
public class ThreadDemo16 {
    static class Singleton {
        private Singleton() {
        }

        private static Singleton instance = null;

        public static Singleton getInstance() {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);
    }
}
