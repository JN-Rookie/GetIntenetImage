package edu.feicui.getintenetimage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/5/23.
 */
public class StreamTool {
    /*
    从数据流中获得数据
     */
    public static byte[]read(InputStream inputStream){
        byte[] buff=new byte[1024];//定义缓冲区
        int len=0;
        ByteArrayOutputStream bos=new ByteArrayOutputStream();//定义输出流
        try {
            while ((len=inputStream.read(buff))!=-1){
                bos.write(buff,0,len);
            }
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

}
