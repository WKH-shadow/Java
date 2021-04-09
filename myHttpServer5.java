package myHttp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class myHttpServer5 {
    ServerSocket serverSocket = null;
    public myHttpServer5(int port) throws IOException {
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
                    processSocket(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void processSocket(Socket clientSocket) throws IOException {
        //首先获取一个流
        try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
                ) {
            //然后用刚刚声明的方法来进行封装
            myHttpRequest request = new myHttpRequest();
            //然后调用它的方法来对信息进行处理
            request.init(reader);
            //然后打印首行信息
            System.out.println(String.format("%s %s %s",request.getMethod(),request.getUrl(),request.getVersion()));
            //然后根据url不同，返回不同的页面
            myHttpResponse response = new myHttpResponse();
            //响应的页面类型为html
            response.setResponseHeader("Content-Type","text/html; charset=utf-8");
            if(request.getUrl().startsWith("/404")){
                //然后404页面
                response.setState(404);
                response.setStateMessage("not found");
                //如果是404body可以为空
            }else if(request.getUrl().startsWith("/302")) {
                //跳转到指定的页面,如跳转到百度，如果是跳转，一定要设置一个header Location-> https://www.baidu.com;
                response.setState(302);
                response.setResponseHeader("Location", "https://www.baidu.com");
                response.setStateMessage("Location");
                //此时跳转到另一个页面了，所以body也可以为空
            }else if(request.getUrl().startsWith("/myhtml.html")){
                //设置首行信息
                response.setState(200);
                response.setStateMessage("OK");
                //首先需要一个html的路径
                String filePath = "D:\\javahtml\\jQuery.html";
                //获取一个读取固定文件的流
                try(
                        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))){
                    String line;
                    while ((line = fileReader.readLine()) != null){
                        response.setResponseBody(line+"\n");
                    }
                }

                //如果是/login表示要进行登录操作了
            }else if(request.getUrl().startsWith("/login")){
                //登录业务
                //1.拿到POST提交方式传递过来的username和pwd，要从request的body中获取
                System.out.println(String.format("用户名：%s,密码：%s",request.getRequestBody("username"),request.getRequestBody("pwd")));
                //验证用户名和密码是否正确（通常是连接数据可以进行信息核对）
                //这里写一个简易版的：如果传递过来的用户名等于root密码等于root，我就认为是正确的用户和密码

            }else {
                //正常返回页面
                response.setState(200);
                response.setStateMessage("OK");
                //此时正常返回页面，那么显示一个body内容,此时因为有中文，所以一定要注意设置编码格式
                response.setResponseBody("<h1>欢迎来到Java~</h1>");
            }
            //返回响应内容
            response.flush(writer);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //因为http是短链接，所以要进行关闭
            clientSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        myHttpServer5 myHttpServer5 = new myHttpServer5(9090);
        myHttpServer5.start();
    }
}
