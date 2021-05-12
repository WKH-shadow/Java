import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //读取Cookie的信息
        //因为客户端的cookie可能会有多个，所以我们使用数组来接收,注意：cookie是从request获取的
        Cookie[] cookies = req.getCookies();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        //然后读取cookie的内容
        for (Cookie item: cookies
             ) {
            writer.println(String.format("<h1>Cookie key: %s,Cookie value: %s</h1>",item.getName(),item.getValue()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
