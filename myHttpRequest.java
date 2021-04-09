package myHttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class myHttpRequest {
    //首行[方法+URL+版本号]
    //方法
    private  String method;
    //URL
    private  String url;
    //版本号
    private String version;

    //获取URL里面的参数
    Map<String,String> parents = new HashMap<>();
    //header
    Map<String,String> requestHeader = new HashMap<>();
    //空行
    //body其实也是key1=value1&key2=value2的键值对形式，所以也需要借助Map,然后提供它的构造方法
    Map<String,String> requestBody = new HashMap<>();

    //初始化方法,要读取request的内容必须借助BufferedRead
    public void init(BufferedReader reader) throws IOException {
        //读取第一行
        String firstLine = reader.readLine();
        //然后将读取的第一行数据按照空格进行分隔
        String[] firsrLineArr = firstLine.split(" ");
        this.method = firsrLineArr[0];
        this.url = firsrLineArr[1];
        this.version = firsrLineArr[2];
        //然后对拿到的url进行处理
        processUrl(this.url);
        //读取请求头
        //因为它的内容不止一条，所以要使用while循环
        //定义一个字符串，来存放每次读取的每一行数据
        String line;
        //因为header的结束是一个空行，所以当读取的数据不为空，并且它的长度大于0，说明数据没有读取完，那么就可以一直读取header的数据
        while ((line = reader.readLine()) != null && line.length() > 0){
            //每一个的数据都是key: val(冒号+空格)的形式分隔
            //然后header的每一行数据都是按照“: ”(冒号+空格的方式)，那么我们就按照（冒号+空格）的方式分隔读取到的每一行
            String[] lineArr = line.split(": ");
            //然后将获取到的数据存储在我们申请的map中，也就是requestHeader中
            requestHeader.put(lineArr[0],lineArr[1]);
        }

        //4.读取body的值，首先得判断是post请求还是get请求，因为如果是get请求的话，body是空的,而且是在method中进行判断
        //equalsIgnoreCase是忽略大小写的意思
        if(this.getMethod().equalsIgnoreCase("post")){//表示body中有内容，进行赋值
            //如果想要拿到body，首先得拿到body的长度，也就是Content-Length,也就是得知道body中有多少个字节
            int contentLength = Integer.parseInt(this.getRequestHeader("Content-length"));
            //然后将读取的字节放在一个char数组里面
            char[] bodys = new char[contentLength];
            //表示读取body信息
            reader.read(bodys,0,contentLength);
            //然后拿到body的字符串，如何将字符转换成字符串，使用new String()
            //而拿到的字符串是这样的key=val1&key2=val2
            String bodyStr = new String(bodys);
            //那么就需要将其用“&”进行分隔，然后再用“=”进行分隔 然后放进body字典中
            //首先遍历字符串用&进行分隔
            for (String item:bodyStr.split("&")
                 ) {
                //然后在讲拿到的key=val形式按照“="进行分隔
                String[] kvs = item.split("=");
                //然后再将其放入body字典中
                requestBody.put(kvs[0],kvs[1]);


            }
        }
    }

    private void processUrl(String url) {
        //http://119.29.29.29/d?dn=baidu.com,
        //首先得判断有没有问号
        if (this.url.startsWith("?")) {
            //然后将这个从？之后截取
            String newUrl = this.url.substring(this.url.indexOf("?"));
            //然后拿到新的url按照“&”进行分隔
            String[] newUrlArr = newUrl.split("&");
            //然后遍历newUrlArr中的数据，然后将其按照”=“进行分隔
            for (String item:newUrlArr
                    ) {
                String[] items = item.split("=");
                //然后将其放入map中
                parents.put(items[0],items[1]);
            }
        }
    }
    //提供get方法


    public String getMethod() {

        return method;
    }

    public String getUrl() {
        return url;
    }


    public String getVersion() {

        return version;
    }
    //手动构造两个map的get方法
    public String getParents(String key){

        return this.parents.get(key);
    }
    public String getRequestHeader(String key){

        return this.requestHeader.get(key);
    }
    //提供requestBody的get方法
    public String getRequestBody(String key){
        return this.requestBody.get(key);
    }
}
