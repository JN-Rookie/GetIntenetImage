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

    public static byte[] getImage(String path) throws IOException {


        URL url = new URL(path);
        ;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");//设置请求方法为get
        connection.setReadTimeout(5 * 1000);//设置请求过时时间为5秒
        connection.disconnect();
        InputStream inputStream = connection.getInputStream();//通过输入流获得图片数据
        byte[] data = StreamTool.readInputStream(inputStream);//获得图片的二进制数据


        return data;
    }
}
