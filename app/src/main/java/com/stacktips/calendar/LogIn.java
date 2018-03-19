package com.stacktips.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

/**
 * Created by DELL on 09-03-2018.
 */

public class LogIn extends AppCompatActivity implements LoopjHttpHandler.OnJsonResponseListener {

    Button btn;
    LoopjHttpHandler json;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = (Button) findViewById(R.id.btn_click);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callService();
            }
        });
    }

    private void callService() {
        json = new LoopjHttpHandler(this);
        String url = "http://dishari.kazmatechnology.in/api/Dishari/Products";
        HashMap<String, String>hashMap = new HashMap<>();
        hashMap.put("AccessToken", "NEc3WCtxamU0a1BRQVhkSmRlMS80b3NuMG1RYUwwNUtCQjJDdU5XSmd5UT06ZGViYW1Aa2F6bWF0ZWNobm9sb2d5LmNvbQ==");
        json.makeHttpRequest(url, "POST", hashMap, 1);
    }

    @Override
    public void getJsonResponseCallBack(String result, int urlNo, int status_code) {
        if (urlNo==1)
        Log.e("status_code", String.valueOf(status_code));
        Log.e("result", result);

    }
}
