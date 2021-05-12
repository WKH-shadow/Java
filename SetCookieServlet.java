import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.创建Cookie对象
        //首先需要一个cookie对象,这里我们需要两个cookie对象，一个来存储用户名（username——java），一个来存储密码（pwd——javas）
        Cookie username = new Cookie("uesrname","java");
        Cookie pwd = new Cookie("pwd","javas");
        //当我们创建好两个cookie对象之后，就可以对这两个对象进行一系列操作
        //比如：设置他的过期时间，这里我们将username的过期时间设置为永久
        username.setMaxAge(-1);
        //密码pwd的过期时间设置为一分钟,注意他的过期时间是以秒为单位
        pwd.setMaxAge(60);
        //2.将 Cookie 对象关联到response上
        resp.addCookie(username);
        resp.addCookie(pwd);

        //显示给用户部分的信息
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        //然后写入响应给客户端的内容
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>Cookie 设置成功</h1>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}