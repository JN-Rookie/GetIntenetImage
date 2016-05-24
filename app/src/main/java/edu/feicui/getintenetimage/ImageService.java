package edu.feicui.getintenetimage;

import java.io.IOException;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Administrator on 2016/5/23.
 */
public class ImageService {
    public static byte[] getImages(String path) throws IOException {
        URL url=new URL(path);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");//用GET方法获取图片
        connection.setReadTimeout(5*1000);//设置等待超时时间为5秒
        connection.disconnect();
        InputStream inputStream=connection.getInputStream();//用connection通过输入流获取图片
        byte[] data=StreamTool.read(inputStream);//获取图片的二进制数据

        return data;

    }


}
