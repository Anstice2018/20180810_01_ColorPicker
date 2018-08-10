package com.example.android.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class ColorPickerActivity extends AppCompatActivity {
    private static final String TAG = "ColorPickerActivity";


    public static final String 顏色key = "key1";
    public static final String 顏色名稱key = "key2";

    private int mColorInt;              // 顏色值
    private CharSequence mColorName;    // 顏色名稱

    private void saveData(){
        //取得偏好設定物件
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        //取得偏好設定編輯物件
        SharedPreferences.Editor editor = prefs.edit();
        //寄放資料到包裹
        editor.putInt(顏色key, mColorInt);
        editor.putString(顏色名稱key, mColorName.toString());
        //送出資料
        editor.commit();
        Log.d(TAG, "saveData() mColorInt =" + mColorInt + "mColorName =" + mColorName);
    }

    private void restoreData(){
        //取得偏好設定物件
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        //從包裹取回寄放的資料
        //如果 key 不存在, 返回 0 當結果(作為預設值)
        mColorInt = prefs.getInt(顏色key, 0);
        mColorName = prefs.getString(顏色名稱key, "holo_red_light");
        Log.d(TAG, "restoreData() mColorInt =" + mColorInt + "mColorName =" + mColorName);

        RadioButton radio = null;
        switch(mColorName.toString()){
            case "holo_red_light" :
                radio = (RadioButton) findViewById(R.id.radio_holo_red_light);
                break;
            case "holo_orange_dark" :
                radio = (RadioButton) findViewById(R.id.radio_holo_orange_dark);
                break;
            case "holo_green_light" :
                radio = (RadioButton) findViewById(R.id.radio_holo_green_light);
                break;
            case "holo_blue_dark" :
                radio = (RadioButton) findViewById(R.id.radio_holo_blue_dark);
                break;
        }
        if (radio != null){
            radio.setChecked(true); //設為已圈選
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume()");
        restoreData();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause()");
        saveData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        Log.d(TAG, "onCreate");
        initColorData();    // 預設選擇紅色選項
    }

    private void initColorData() {
        // 預設選取紅色
        RadioButton radio = (RadioButton) findViewById(R.id.radio_holo_red_light);
        mColorInt = radio.getCurrentTextColor(); // 取得選鈕文字顏色
        mColorName = radio.getText();            // 取得選鈕文字內容
        Log.d(TAG, "clickColor() mColorInt = " + mColorInt + "  mColorName = " + mColorName);
    }

    public void clickColor(View view) {
        RadioButton radio = (RadioButton)view;   // view 為當前所按的選鈕
        mColorInt = radio.getCurrentTextColor(); // 取得選鈕文字顏色
        mColorName = radio.getText();            // 取得選鈕文字內容
    }

    public void ok(View view) {

        // 將回傳資料寄放在 Intent
        Intent intent = new Intent();

        intent.putExtra(顏色key, mColorInt);
        intent.putExtra(顏色名稱key, mColorName);

        setResult(RESULT_OK, intent); // 設定有結果
        finish(); // 工作完成，關閉此畫面，回到前一個畫面
    }


    public void cancel(View view) {
        setResult(RESULT_CANCELED); // 設定取消的結果
        finish();
    }
}




