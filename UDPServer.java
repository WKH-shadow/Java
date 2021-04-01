package Net;

import java.io.IOException;
        import java.net.DatagramPacket;
        import java.net.DatagramSocket;
        import java.net.SocketException;

/**
 * UDP的服务器端，使用对象 DatagramSocket / DatagramPacket
 */
public class UDPServer {
    private DatagramSocket datagramSocket = null;
    public UDPServer(int port) throws SocketException {
        datagramSocket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器正常启动了~");
        //写一个while循环，表示一直读取客户端发送的消息
        while (true){
            //阻塞等待客户端的请求
            DatagramPacket requestDatagramPacket = new DatagramPacket(new byte[4096],4096);
            datagramSocket.receive(requestDatagramPacket);
            //接收客户端发送的命令
            String requestContext = new String(requestDatagramPacket.getData());
            //客户端信息的加工和处理
            String responseContext = process(requestContext);
            //将信息返回给客户端
            DatagramPacket responseDatagramPacket = new DatagramPacket(
                    requestContext.getBytes(),
                    requestContext.getBytes().length,
                    requestDatagramPacket.getAddress(),
                    requestDatagramPacket.getPort());
            //最后调用sent方法，将信息发送给客户端
            datagramSocket.send(requestDatagramPacket);
            //结果打印
            System.out.printf("客户端IP： %s,客户端端口：%d,客户端的内容：%s,服务器响应的内容：%s\n",
                    requestDatagramPacket.getAddress(),
                    requestDatagramPacket.getPort(),
                    requestContext, responseContext);
        }
    }

    //客户端信息的加工和处理
    private String process(String requestContext) {
        return requestContext;
    }

    public static void main(String[] args) throws IOException {
        UDPServer udpServer = new UDPServer(9090);
        udpServer.start();
    }
}
