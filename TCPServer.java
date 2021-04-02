package Net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    //ServerSocket是让服务器端和端口号建立联系
    private ServerSocket serverSocket = null;
    //指定一个要关联的端口号,而且一个端口号只能被一个进程绑定
    //所以这个端口号可能被其他服务器端占用，当我们再去访问的时候，就可能会报错
    public TCPServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    //进入主主程序
    public void start() throws IOException {
        System.out.println("服务器正常启动了~");
        while(true){
            //这里的accept类似于UDP中的receive，但是却有差距，比如：
            //如果有人在拨号（connect），此时调用accept就会立即返回一个Socket对象
            //但是更多的情况是，当你调用accept的时候，并没有任何客户端建立连接，此时的accept只能等待（阻塞）
            //一直等到真的有客户来连接才返回
            Socket clientSocket = serverSocket.accept();
            //处理连接clientSocket
            processConnect(clientSocket);
        }
    }

    private void processConnect(Socket clientSocket) {
        //此时和客户端已经建立上连接了，然后打印出客户端的IP(得到IP的字符串表示形式)和端口号
        System.out.printf("[%S:%d]客户端上线!\n", clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        //为了后面的读写做准备，主要准备好对应的流对象，带缓冲区的操作
        //目的是构造bufferedReader和bufferedWriter对象，但是构造时需要依赖clientSocket的getInputStream()和getOutputStream()来完成
        //但是不能直接构造，需要借助InputStreamReader和OutputStreamWriter来转换一下
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            while (true){
            //1.读取请求并解析，直接读取一行,然后进行判断，如果读取结果位null说明客户端已经下线
            String request = bufferedReader.readLine();
            if (request == null) {
                //此时客户端已经下线，此时打印客户端端的IP和端口号，然后直接结束
                System.out.printf("[%s:%d] 客户端已经下线~\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
                return;
            }
            //2.根据请求计算响应,此时说明读取到了数据，还是写一个方法
            String response = process(request);
            //3.把响应结果返回给客户端
            bufferedWriter.write(response + "\n");//【注意】此处的 \n 很重要
            bufferedWriter.flush();//【注意】此处的flush很重要

            //打印一个日志,也就是客户端的IP和端口号，以及客户端发送的命令和服务器端相应的命令
            String log = String.format("[%s:%d] 客户端发送的命令；%s 服务器端相应的命令：%s",
                    clientSocket.getInetAddress().toString(), clientSocket.getPort(), request, response);
            System.out.println(log);
        }
        } catch (IOException e) {
            //e.printStackTrace();
            //这里捕捉到异常之后也任务时客户端下线了，所以可以这样写
            System.out.printf("[%s:%d] 客户端已经下线~\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
            return;
        }
    }

    //根据请求计算响应，请求什么就响应什么，也就是读取到了什么就直接返回什么结果
    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TCPServer tcpServer = new TCPServer(9090);
        tcpServer.start();
    }
}
