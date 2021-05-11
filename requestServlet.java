import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//必须继承HttpServlet
public class requestServlet extends HttpServlet {
    //重写doGet()方法和doPost()方法

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //拿到参数的值
        String uname = req.getParameter("uname");
        //拿到request的方法
        String methed = req.getMethod();
        //得到url
        String url = req.getContextPath();
        //获取协议的名称
        String pocl = req.getProtocol();
        //获取编码
        String encoding = req.getCharacterEncoding();

        //然后把这些内容写回给客户端,首先设置编码格式和类型，然后再是返回的内容
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(String.format("<h1>请求参数: %s</h1>",uname));
        writer.println(String.format("<h2>method: %s</h2>",methed));
        writer.println(String.format("<h3>url: %s</h3>",url));
        writer.println(String.format("<h4>协议名称pocl: %s</h4>",pocl));
        writer.println(String.format("<h5>编码encoding: %s</h5>",encoding));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //我们让doPost方法直接调用doGet方法
        this.doGet(req,resp);
    }
}
