package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ImagePickerActivity extends AppCompatActivity {

    // 圖片 id 的 key
    public static final String BUNDLE_KEY_DRAWABLE_ID_INT = "com.teacheryin.android.drawable.id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);
    }

    public void clickImage(View view) {

        // 建立傳令兵
        Intent intent = new Intent();

        // 判斷按下的是個圖片按鈕，將 圖片id 放到傳令兵背包
        switch (view.getId()) {
            case R.id.ib_hornets:
                // intent.putExtra( key , value )
                intent.putExtra(BUNDLE_KEY_DRAWABLE_ID_INT, R.drawable.hornets);
                break;
            case R.id.ib_rockets:
                // intent.putExtra( key , value )
                intent.putExtra(BUNDLE_KEY_DRAWABLE_ID_INT, R.drawable.rockets);
                break;
        }

        setResult(RESULT_OK, intent);   // 設定結果是成功的
        finish();                       // 結束畫面
    }



}
