import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
//首先必须声明这个类是用来处理(文件)流的【不可获取】
@MultipartConfig
public class FileServlet extends HttpServlet {
    @Override
    //因为提交表单只需要post，那么这里就不需要get方法了
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //首先拿到流信息，他返回的是一个part对象
        Part part = req.getPart("img");
        //手下声明一个保存路径，这里保存在D盘的File文件下
        String filePath = "D:\\File\\";
        //保存文件的名称这里就和原文件的名称一样，通过part里面的方法得到原文件名称
        String fileName = part.getSubmittedFileName();
        //通过write方法，可以将这个png文件保存在任意路径下，write里面的参数，就是要保存的路径
        part.write(filePath+fileName);

        //然后给前端返回响应的结果
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>上传成功</h1>");
    }
}
