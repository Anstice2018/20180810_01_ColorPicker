package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int 顏色請求 = 0;
    private static final int 圖片請求 = 1;

    private TextView m_tv_color;
    private ImageView m_iv_logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();// 初始設定，將所有 View 先找好
    }

    private void init() {
        m_tv_color = findViewById(R.id.tv_color);
        m_iv_logo  = findViewById(R.id.iv_logo);
    }

    // 按下選擇顏色按鈕
    public void selectColor(View view) {
        // 傳令兵 Intent( fromActivity , toActivity )
        Intent intent = new Intent(this, ColorPickerActivity.class);
        // 開始傳令 startActivity(intent);
        startActivityForResult(intent, 顏色請求);
    }

    // 按下選擇圖片按鈕
    public void selectImage(View view) {
        // Intent( fromActivity , toActivity )
        Intent intent = new Intent(this, ImagePickerActivity.class);
        startActivityForResult(intent, 圖片請求);
    }


    // 傳令兵送結果回來
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode != RESULT_OK) { // 回傳結果不是 OK
            return; // 結束
        }

        // 取得傳令兵背包
        Bundle 包 = intent.getExtras();

        switch (requestCode) {
            case 顏色請求:
                // 從背包取值
                int colorInt = 包.getInt(ColorPickerActivity.顏色key);
                CharSequence colorName = 包.getCharSequence(ColorPickerActivity.顏色名稱key);

                // 設定 TextView 文字內容 與 顏色
                m_tv_color = findViewById(R.id.tv_color);
                m_tv_color.setText(colorName);
                m_tv_color.setBackgroundColor(colorInt);
                break;
            case 圖片請求:
                // 從背包取值
                int drawableId = 包.getInt(ImagePickerActivity.BUNDLE_KEY_DRAWABLE_ID_INT);
                m_iv_logo.setImageResource(drawableId);// 透過 drawableId 設定 ImageView 所使用的圖
                break;
        }
    }
}

