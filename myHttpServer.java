package HTTP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TCP的服务器，按照HTTP协议的规则进行数据的获取和返回=HTTP服务器
 */
public class myHttpServer {
    private ServerSocket serverSocket = null;
    public myHttpServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动成功~");
        while (true){
            //这里用线程池来服务多个客户端
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,15,1000,
                    TimeUnit.SECONDS,new LinkedBlockingQueue<>(10));
            //接收客户端发来的消息
            Socket clientSocket = serverSocket.accept();
            threadPoolExecutor.submit(()->{
                try {
                    processSocket(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void processSocket(Socket clientSocket) throws IOException {
        try(
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
                ){
            //1.获取的首行信息，首行信息里面由（请求方法+URL+HTTP版本号）
            String firstLine = bufferedReader.readLine();
            //然后将首行信息，用空格进行分隔,然后就可以拿到三个信息
            String[] firstArr = firstLine.split(" ");
            //获取请求方法
            String method = firstArr[0];
            //获取URL
            String url = firstArr[1];
            //获取HTTP版本号
            String version = firstArr[2];
            //然后打印这三个信息
            System.out.println(String.format("请求方法:%s,URL:%s,HTTP版本号:%s",method,url,version));
            //2.获取请求头，因为它是key-value所以申请一个Map存放请求头
            Map<String,String> requestHead = new HashMap<>();
            //每次读取的时候都放在item里面
            String item;
            //读取操作，因为不止一行，使用使用while循环
            while ((item = bufferedReader.readLine()) != null && item.length() > 0){
                //它是以“： ”（冒号+空格）进行区分
                String[] itemArr = item.split(": ");
                requestHead.put(itemArr[0],itemArr[1]);
        }

        //4.body 暂不考虑

            //响应信息
            //1.首行信息，返回给客户端一个首行【HTTP/1.1，200  OK】,中间以空格分隔
            printWriter.println("HTTP/1.1 200 OK");
            //2.响应头  Content-Type   Content-Length
            printWriter.println("Content-Type: text/html;charset=utf-8");
            //然后写入body内容
            String body = "<h1>你好，世界！</h1>";
            //这里注意，是字节长度，不是字符长度
            printWriter.println("Content-Length: "+body.getBytes().length);
            //3.空行
            printWriter.println();
            //4.body
            printWriter.println(body);
            //缓冲区的刷新
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭链接
            clientSocket.close();
        }
    }


    public static void main(String[] args) throws IOException {
        myHttpServer myHttpServer = new myHttpServer(9090);
        myHttpServer.start();
    }
}
