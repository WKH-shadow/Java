package HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 需求：
 *  1. 如果是/404，则显示没有找到页面
 *  2. 如果是/302，则跳转到指定的页面，如跳转到百度
 *  3. 如果是/200，则显示正常页面
 */
public class myHttpRequest {
    //1.首行信息【方法+URL+版本号】,然后对首行信息进行封装
    //方法
    private String method;
    //url
    private String url;
    //版本号
    private String version;
    //2.获取URL里面的参数，因为url里面的参数可能有多个，而且是以key-value的形式，所以使用map
    Map<String,String> parments = new HashMap<>();

    //3.获取header信息
    Map<String,String> requsetHeader = new HashMap<>();

    //4.空行

    //5.body（暂不考虑）

    //初始化内容，想要读取request的信息，就需要借助BufferedReader
    public void init(BufferedReader reader) throws IOException {
        //首先读取到首行信息
        String firstLine = reader.readLine();
        //因为首行信息是按照“ ”（空格）进行分隔的，所以我们就按照空格进行分隔
        String[] firstLineArr = firstLine.split(" ");
        //然后对首行信息里面的方法进行初始化
        this.method = firstLineArr[0];
        //初始化URL
        this.url = firstLineArr[1];
        //初始化版本号
        this.version = firstLineArr[2];


        //读取URL里面的参数
        //url如：http://182.254.116.116/d?dn=6ea62b48a6cc721df07b8f9b03956ad7&id=2046&ttl=1
        //我们可以看到从？以后他都是以“&”进行分隔的一个个key=value的对象，也就是/s？key1=val1&key2=val2……
        //创建一个方法去读取URL里面的参数,方法里面的参数是把整个URL传进去
        processURL(this.url);

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
            requsetHeader.put(lineArr[0],lineArr[1]);

        }


    }

    //将URL里面的参数存放在parents中
    private void processURL(String url) {
        //首先将？之后的内容截取出来，也就是将
        // /s？key1=val1&key2=val2……变成key1=val1&key2=val2
        //使用String中的substring方法，也就是从？+1的位置开始截取完
        //但是首先得判断URL里面有没有“？”问号
        if(url.startsWith("?")) {
            String newUrl = url.substring(url.indexOf("?") + 1);
            //然后将新的newUrl用“&”进行分隔变成key=value的形式
            String[] newUrlArr = newUrl.split("&");
            //遍历这个新的newUrl
            for (String kvs : newUrlArr) {
                //然后newUrl里面的每一个key=value用“=”进行分隔
                String[] item = kvs.split("=");
                //然后放进map里面也就是parents里面
                parments.put(item[0], item[1]);
            }
        }

    }

    //如果外部类要去使用的话，我们必须提供get方法

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }
    //然后手动实现两个map的get方法
    public String getParents(String key){
        return this.parments.get(key);
    }
    public String getRequestHeader(String key){
        return this.requsetHeader.get(key);
    }
}
