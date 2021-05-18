package dao;

import models.UserInfo;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据持久层（用于数据库的交互）
 * 处理 userInfo 这张表的相关数据操作
 */
public class UserInfoDao {

    /**
     * 注册方法（添加方法）
     * 返回值为int，当为 1 的时候说明添加成功，否则添加失败
     */
    public int addUser(UserInfo userInfo) throws SQLException {
        //首先定义一个返回结果，根据这个返回结果的值来判断是否添加成功
        int result = 0;

        //1.首先拿到连接对象connection
        Connection connection = DBUtils.getConnection();

        //2.构建sql
        String sql = "insert into userinfo(username,password) values(?,?)";


        //3.执行sql
        PreparedStatement statement = connection.prepareStatement(sql);
        //执行之前，需要对着两个占位符进行赋值
        statement.setString(1,userInfo.getUsername());
        statement.setString(2,userInfo.getPassword());
        //执行完成之后会有一个结果，我们就直接用result接收
        result = statement.executeUpdate();


        //4.关闭连接
        DBUtils.close(connection,statement,null);


        //5.返回结果
        return result;
    }
}
