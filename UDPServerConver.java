package Net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * UDP通讯实现一个将英语翻译成中文的字典
 * 实现步骤：
 * 1.初始化DatagramSocket ，Map<String,String>
 * 2.进入主循环进行相应的业务处理
 *    2.1 阻塞等待用户的命令输入
 *    2.2 接收到客户端的命令
 *    2.3 将英文解析成中文
 *    2.4 将中文信息返回
 */
public class UDPServerConver {
    private DatagramSocket datagramSocket = null;
    private static Map<String,String> map = new HashMap<String,String >();
    //表示程序启动时就会执行的静态代码
    static {
        map.put("cat","小猫");
        map.put("dog","小狗");
        map.put("pig","小猪");
        map.put("monkey","小猴");

    }
    //初始化初始化DatagramSocket ，Map<String,String>
    public UDPServerConver(int port) throws SocketException {
        datagramSocket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        System.out.println("英译汉服务器端正常启动了~~");
        while (true){
            DatagramPacket requestDatagramPacket = new DatagramPacket(new byte[4096],4096);
            //阻塞等待用户的命令输入
            datagramSocket.receive(requestDatagramPacket);
            //接收到客户端发送的命令
            //trim的作用，因为你定义的字节是4096，那么每次都会发送4096个字节，那么后面的内容就会使空格，而trim就是删除这些空格
            String requestContext = new String(requestDatagramPacket.getData()).trim();
            //写一个方法，实现将英文解析成中文,因为要将结果返回给客户端，所以要用String接收
            String responseContxt = process(requestContext);
            //将中文信息返回给客户端,需要依赖DatagramPacket
            DatagramPacket responseDatagramPacket = new DatagramPacket(
                    responseContxt.getBytes(),
                    responseContxt.getBytes().length,
                    requestDatagramPacket.getAddress(),
                    requestDatagramPacket.getPort());
            datagramSocket.send(responseDatagramPacket);
        }
    }

    private String process(String requestContext) {
        //那么就直接用map里面的getOrDefault方法,如果找到了就返回相应的value，如果找不到就返回默认的命令
        return map.getOrDefault(requestContext,"找不到该英文对应的中文~");
    }

    public static void main(String[] args) throws IOException {
        UDPServerConver udpServerConver = new UDPServerConver(9090);
        udpServerConver.start();
    }
}
