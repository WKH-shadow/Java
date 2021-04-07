package HTTP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyHttpServer2 {
    private ServerSocket serverSocket = null;
    public MyHttpServer2(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器正常启动了~");
        //为了让一个服务器响应多个客户端，所以我们使用线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,15,100,TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10));
        while(true){
            //首先等待客户端连接，此处的客户端就是浏览器
            Socket clientSocket = serverSocket.accept();
            threadPoolExecutor.submit(()->{
                //然后处理接收到的消息
                try {
                    procescsSocket(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void procescsSocket(Socket clientSocket) throws IOException {
        //从Socket中拿到两个对象，一个是Reader读取对象，一个是响应对象Writer
        try(
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
                ) {

            //然后我们就可以用我们刚刚声明的方法进行封装
            myHttpRequest request = new myHttpRequest();
            //然后给他的值设置内容,然后这里实现了代码的复用，就不用每次写获取响应的代码了
            request.init(bufferedReader);
            //打印首行信息
            System.out.println(String.format("首行信息: %s %s %s",request.getMethod(),request.getUrl(),request.getVersion()));
            //业务处理，不同的访问地址（url）返回不同的页面
            myHttpResponse response = new myHttpResponse();
            //响应的页面类型为html
            response.setResponseHeader("Content-Type","text/html; charset=utf-8");
            //if(requesr.getUrl.indexOf("/404") != -1)等同于if(request.getUrl().startsWith("/404"))
            if(request.getUrl().startsWith("/404")){
                //然后404页面
                response.setState(404);
                response.setStateMessage("not found");
                //如果是404body可以为空
            }else if(request.getUrl().startsWith("/302")){
                //跳转到指定的页面,如跳转到百度，如果是跳转，一定要设置一个header Location-> https://www.baidu.com;
                response.setState(302);
                response.setResponseHeader("Location","https://www.baidu.com");
                response.setStateMessage("Location");
                //此时跳转到另一个页面了，所以body也可以为空
            }else {
                //正常返回页面
                response.setState(200);
                response.setStateMessage("OK");
                //此时正常返回页面，那么显示一个body内容,此时因为有中文，所以一定要注意设置编码格式
                response.setResponseBody("<h1>欢迎来到Java~</h1>");
            }
            //返回响应内容
            response.flush(printWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //因为http是短链接，所以要进行关闭
            clientSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        MyHttpServer2 myHttpServer2 = new MyHttpServer2(9090);
        myHttpServer2.start();
    }
}
