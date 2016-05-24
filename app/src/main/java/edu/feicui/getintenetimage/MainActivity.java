package edu.feicui.getintenetimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button    mButton;
    private EditText  mEditText;
    private ImageView mImageView;
    private Bitmap    mBitmap;
    private static final int    LOAD_PIC = 1;
    private static final int    LOAD_PIC_fail = 2;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LOAD_PIC:
                    mImageView.setImageBitmap(mBitmap);
                    Toast.makeText(getApplicationContext(),"图片获取成功",Toast.LENGTH_SHORT).show();
                    break;
                case LOAD_PIC_fail:
                    mImageView.setImageResource(R.drawable.downloadfail);
                    Toast.makeText(getApplicationContext(),"图片连接超时,请检查地址是否正确",Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_getimage);
        mImageView = (ImageView) findViewById(R.id.iv_image);
        mEditText = (EditText) findViewById(R.id.edt_input);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        String path1 = mEditText.getText().toString();

                        try {
                            byte[] data = ImageService.getImages(path1);
                            mBitmap=BitmapFactory.decodeByteArray(data,0,data.length);
                            handler.sendEmptyMessage(LOAD_PIC);
                        } catch (IOException e) {
                            handler.sendEmptyMessage(LOAD_PIC_fail);
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

//
//    private static final String TAG      = "ma";
//    private static final int    LOAD_PIC = 1;
//    private Button    mButton;
//    private ImageView mImageView;
//    private Bitmap    mBitmap;
//    public final String urlPathContent = "http://c.cotton.netease.com/buckets/1BquJi/files/02FrwTHV3h.jpeg";
//
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case LOAD_PIC:
//                    mImageView.setImageBitmap(mBitmap);
//                    Toast.makeText(getApplicationContext(), "显示成功", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mButton = (Button) findViewById(R.id.btn_getimage);
//        mImageView = (ImageView) findViewById(R.id.iv_image);
//
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread() {
//                    @Override
//                    public void run() {
//                        try {
//                            byte[] data = ImageService.getImages(urlPathContent);
//                            mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                            handler.sendEmptyMessage(LOAD_PIC);
//                        } catch (IOException e) {
//                            Toast.makeText(getApplicationContext(), "连接超时", Toast.LENGTH_SHORT).show();
//                            e.printStackTrace();
//                        }
//
//                    }
//                }.start();
//            }
//        });
//    }
}
