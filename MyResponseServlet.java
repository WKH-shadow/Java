import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class MyResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //刷新操作，设置成1s刷新一次
        resp.setIntHeader("Refresh",1);
        //设置响应类型
        resp.setContentType("text/html");
        //设置编码格式,如果不设置会出现乱码
        resp.setCharacterEncoding("utf-8");
        //写入body信息
        PrintWriter writer = resp.getWriter();
        writer.println(String.format("<h1>当前时间: %s</h1>",new Date()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
