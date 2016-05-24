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
    public static byte[]readInputStream(InputStream mInputStream){
        byte[] buff=new byte[1024];
        int len=0;
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        try {
            while((len=mInputStream.read(buff))!=-1){
                bos.write(buff,0,len);
            }
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
}
