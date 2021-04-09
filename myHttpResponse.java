package myHttp;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class myHttpResponse {
    //1.首行信息【版本号 状态码 状态描述符】

    //1.版本号,因为其是固定的不可变的
    private  static  final String version = "HTTP/1.1";

    //状态码
    private  Integer state;

    //状态描述符
    private String stateMessage;

    //2.header(最重要的Content-Type(Java会自动生成)   Content-Length)
    Map<String,String> responseHeader = new HashMap<>();

    //3.换行

    //4.body
    StringBuffer responseBody = new StringBuffer();

    //这里要严格遵循HTTP协议的格式输出
    public void flush(PrintWriter writer){
        //然后先输出首行
        writer.println(String.format("%s %d %s",version,this.state,this.stateMessage));
        //然后写入header的内容，遍历responseHeader中的内容
        for (Map.Entry<String,String> item:responseHeader.entrySet()
             ) {
            //然后没读取一行数据，让其用“： ”（冒号+空格）进行拼接
            writer.println(String.format("%s: %s",item.getKey(),item.getValue()));
        }
        //然后要加上一个Content-length,这个长度就是body的长度
        writer.println("Content-length: "+responseBody.toString().getBytes().length);
        //输出空行
        writer.println();
        //返回body信息
        writer.println(responseBody.toString());
        //最后进行flush操作
        writer.flush();
    }
    //提供set方法

    public void setState(Integer state) {
        this.state = state;
    }

    public void setStateMessage(String stateMessage) {
        this.stateMessage = stateMessage;
    }

    public void setResponseBody(String body) {
        this.responseBody.append(body);
    }
    //手动写一个map的set方法
    public void setResponseHeader(String key,String value){
     this.responseHeader.put(key,value);
    }
}
