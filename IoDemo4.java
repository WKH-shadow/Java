package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 复制D:\io_test下的加菲猫文件到D:\io_test这个目录下
 */

public class IoDemo4 {

    public static void main(String[] args) throws IOException {
        //输入文件目录
        String srcFilePath = "D:\\io_test\\加菲猫.jpg";
        //输出文件目录（拷贝的路径）
        String destFilePath = "D:\\io_test\\加菲猫2.jpg";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            //输入流,传入路径
            fileInputStream = new FileInputStream(srcFilePath);
            //输出流
             fileOutputStream = new FileOutputStream(destFilePath);
            byte[] bytes = new byte[1024];
            try {
                int count = 0;
                //如果不等于-1说明还没有读取完成，要继续读取
                while((count = fileInputStream.read(bytes)) != -1){
                    //还有内容要读取,然后从0开始读取，上面count有读了多少个，那么就写多少个
                    fileOutputStream.write(bytes,0,count);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            //用完之后一定要关闭流,但是得判断不是空的时候才需要去关闭流
            if(fileInputStream != null){
                fileInputStream.close();
            }
            if(fileOutputStream != null){
                fileOutputStream.close();
            }
        }
    }
}
