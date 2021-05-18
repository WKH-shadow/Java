package services;

import dao.UserInfoDao;
import models.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class regServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //注册的状态
        int succ = 0;//默认情况下一定得是假

        //错误信息描述
        String msg = "未知错误";

        //1.拿到前端的参数（一个是username，一个是password）
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //注意：无论前端做了多少判断，后端是永远不相信前端的（因为前端是可以伪造的），所以这里还要对这两个参数进行判断，如果是空的话，那么我们就什么也不做，如果不是空的
        if(username != null && !username.equals("")
                && password != null && !password.equals("")) {
            //此刻表示数据正常，那么我们就可以进入接下来的业务处理

            //2.将参数保存到数据库(注意：通用方法，我们可以写一个类（DBUtils），然后直接调用)
            //首先得有一个userinfo对象
            UserInfo userInfo = new UserInfo();
            //然后设置用户名和密码
            userInfo.setUsername(username);
            userInfo.setPassword(password);
            //然后在创建一个userinfoDao对象，调用里面的addUser方法
            UserInfoDao userInfoDao = new UserInfoDao();
            try {
                //使用succ来接收结果
                succ = userInfoDao.addUser(userInfo);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



        }else {
            //此时说明数据不正常，那么我们返回给前端一个msg
            msg = "非法请求，前端传递的数据为空!";
        }

        //3.将结果返回给用户

        //返回给前端一个json信息,此时contentType就必须是json格式的
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        //标准的json格式，succ和msg就是要返回给前端的信息，这里的succ就对应前端页面reg里面的succ  {"succ":"","msg":""}
        writer.println("{\"succ\":\""+succ+"\",\"msg\":\""+msg+"\"}");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
