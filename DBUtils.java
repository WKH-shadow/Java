package utils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据连接工具类：
 * 1.提供connection连接
 * 2.提供公共的关闭方法
 */
public class DBUtils {
    //1.首先得要一个datasource对象,而且此对象不需要关闭，只需要一个那么就需要使用单例模式
    //懒汉方式（支持多线程，双重校验），饿汉方式（优点：支持多线程，缺点：无论应用系统用或不用这个对象，都会创建），所以这里使用懒汉模式

    //懒汉模式的关键步骤（3步）
    //1.1，首先创建一个私有的方法，防止其他方法对其进行new实例操作
    private DBUtils() {
    }

    //1.2使用static volatile，防止指令重排
    private static volatile MysqlDataSource dataSource = null;

    //URL  setname   setpwd
    private static final String DBURL = "jdbc:mysql://127.0.0.1/java31blog?characterEncoding=utf-8&useSSL=true";
    private static final String DBUSERNAME = "root";
    private static final String DBPASSWORD = "954579";

    //3.1,需要提供一个公共的方法，返回一个MysqlDataSource对象
    private static MysqlDataSource getInstance() {
        //第一重判断，判断其是否为空,如果没有，那么我们需要进行创建，如果有了，那么我们就直接返回datasource
        if (dataSource == null) {
            //第二重判断,直接对这个类进行加锁
            synchronized (DBUtils.class) {
                if (dataSource == null) {
                    //此时说明没有MysqlDataSource对象，所以此时就需要创建
                    dataSource = new MysqlDataSource();
                    //当创建完成之后，里面就要设置三个值（URL，setname，setpwd）
                    dataSource.setURL(DBURL);
                    dataSource.setUser(DBUSERNAME);
                    dataSource.setPassword(DBPASSWORD);
                }
            }
        }
            //此时说明有datasource对象，那么我们就直接返回
            return dataSource;
        }

        //2.connection对象
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DBUtils.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
        //业务处理都放在一个里面
        //close方法,里面的参数是需要关闭的对象，从大到小写
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet){
        //从小往大进行关闭，首先进行判断是否存在，然后在对其进行关闭

            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

    }

}

