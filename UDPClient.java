package Net;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;


/**
 *客户端  使用对象 DatagramSocket / DatagramPacket
 */
public class UDPClient {
    //初始化DatagramSocket,客户端构建
    private DatagramSocket datagramSocket = null;
    public UDPClient() throws SocketException {
        datagramSocket = new DatagramSocket();
    }

    public void start(String serverIp,int serverPort) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            //构建客户端的请求命令
            System.out.println("请输入你要发送的指令"+"\n"+"->");
            String requestContext = scanner.nextLine();
            //给服务器端发送命令
            DatagramPacket resquestDatagramPacket = new DatagramPacket(
                    requestContext.getBytes(),
                    requestContext.getBytes().length,
                    InetAddress.getByName(serverIp),serverPort);
            datagramSocket.send(resquestDatagramPacket);
            //接收服务器端的命令
            DatagramPacket responseDatagramPacket = new DatagramPacket(new byte[4096],4096);
            //阻塞等待服务器响应的结果
            datagramSocket.receive(responseDatagramPacket);
            //拿到服务器端响应的结果
            String responseContext = new String(responseDatagramPacket.getData());
            //打印服务器端响应的信息
            System.out.println("服务器端响应的信息："+responseContext);
        }
    }

    public static void main(String[] args) throws IOException {
        //服务器端的IP,127.0.0.1表示本机服务器地址
        final String serverIP = "127.0.0.1";
        //服务器端的端口号，服务器端的端口号我们设置的时9090，这里就传9090
        final  int serverPort = 9090;
        UDPClient udpClient = new UDPClient();
        udpClient.start(serverIP,serverPort);
    }
}
