package HTTP;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 输出信息
 */
public class myHttpResponse {
    //1.首行信息【http版本号+状态码+状态描述符】

    //版本号，因为版本号是固定的，不可修改的，那么我们可以设置成静态变量，并且用final修饰,然后返回的是HTTP/1.1
    private static final String version = "HTTP/1.1";

    //状态码
    private Integer state;

    //状态描述符
    private String stateMessage;

    //2.header（响应报文头）其中（Content—Type和Content—Length）很重要,而且它是以key: value的形式存储的
    Map<String,String> responseHeader = new HashMap<>();


    //3.空行

    //4.body
    //这个是服务器自定义返回的一个信息,因为String的性能很低，而且会产生很多的垃圾，所以我们使用StringBuffer
    StringBuffer responseBody = new StringBuffer();

    //这里要严格遵循HTTP协议的格式输出
    public void flush(PrintWriter writer){
        //1.先输出首行,注意：按照空格进行分隔
        writer.println(String.format("%s %d %s",version,this.state,this.stateMessage));

        //2.写入header内容（Content—Type（会自己进行设置）和Content—Length（不会进行设置，所以要自己写））
        for (Map.Entry<String,String> item:responseHeader.entrySet()){
            //key Value,拼接输出时要以“: ”（冒号+空格）进行分隔输出
            writer.println(String.format("%s: %s",item.getKey(),item.getValue()));
        }
        //添加一个Content—Length
        writer.println("Content—Length: "+responseBody.toString().getBytes().length);
        //输出空行
        writer.println();

        //输出body
        writer.println(responseBody.toString());
    }

    //因为它是需要写入的，所以需要提供set方法，需要手动添加一个header方法

    public void setState(Integer state) {
        this.state = state;
    }

    public void setStateMessage(String stateMessage) {
        this.stateMessage = stateMessage;
    }

    //每次可能就只写入一段内容那么就每次把body内容加进去
    public void setResponseBody(String body) {
        this.responseBody.append(body);
    }

    //需要设置两个值，一个key，一个value，然后把这个key和value放入responseHeader中
    public void setResponseHeader(String key,String value){
        this.responseHeader.put(key,value);

    }


}
