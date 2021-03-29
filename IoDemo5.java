package IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 带有缓冲区的字节流操作:图片复制
 *
 */
public class IoDemo5 {
    public static void main(String[] args) {
        //输入文件目录
        String srcFilePath = "D:\\io_test\\加菲猫.jpg";
        //输出文件目录（拷贝的路径）
        String destFilePath = "D:\\io_test\\加菲猫3.jpg";
        try {
            //因为带有缓存区的是基于原始的类进行操作的
            BufferedInputStream bufferedInputStream =
                    new BufferedInputStream(new FileInputStream(srcFilePath));
            BufferedOutputStream bufferedOutputStream =
                    new BufferedOutputStream(new FileOutputStream(destFilePath));
            {
                        byte[] bytes = new byte[1024];
                        int count = 0;
                        while ((count = bufferedInputStream.read(bytes)) != -1){
                            bufferedOutputStream.write(bytes,0,count);
                        }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
