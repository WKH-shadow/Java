package Net;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    private Socket socket = null;
    //此处传入的IP和port都是服务器端的IP和port
    public TCPClient(String IP,int port) throws IOException {
        //当实例化这个socket对象的时候，就是在和服务器建立连接
        //第一种写法
        //socket = new Socket(IP,port);
        //第二种写法
        socket = new Socket();
        socket.connect(new InetSocketAddress(IP,port));
    }

    /**
     * 1.从命令行读取用户输入
     * 2.把用户输入的内容发送给服务器
     * 3.读取服务器的响应
     * 4.把响应结果显示在显示器上
     */
    public void start(){
        //用户的输入
        Scanner scanner = new Scanner(System.in);
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            while (true){
                System.out.println("请输入命令->");
                //从命令行读取用户输入
                String request = scanner.nextLine();
                if(request == null){
                    System.out.println("客户端发送数据结束~");
                    break;
                }
                //把用户输入的内容发送给服务器,为什么要加上 \n ，是因为我们的服务器在尝试进行按行读取数据
                //比如要保证客户端发送的数据时 \n  ，服务器才能readLine
                //因为这是带缓冲区的流对象，直接write并不是真的把数据发送出去了，二十把数据放到缓冲区了中，
                // 所以必须使用flush操作才能把数据发送出去
                bufferedWriter.write(request+"\n");
                bufferedWriter.flush();
                //读取服务器的响应,此处的readLine和服务器的 write是对应的
                String response = bufferedReader.readLine();
                if(response == null){
                    System.out.println("连接已经断开~");
                    break;
                }
                //把响应结果显示在显示器上
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        final String IP = "127.0.0.1";
        final int port = 9090;
        TCPClient tcpClient = new TCPClient(IP,port);
        tcpClient.start();
    }
}
