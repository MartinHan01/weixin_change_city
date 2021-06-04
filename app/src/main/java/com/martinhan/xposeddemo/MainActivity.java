package com.martinhan.xposeddemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.martinhan.regionwx.R;

import java.lang.reflect.Field;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) this.findViewById(R.id.text);
        btn = (Button) this.findViewById(R.id.commit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = et.getText().toString();
                SharedPreferences sp = MainActivity.this.getSharedPreferences("default", Context.MODE_PRIVATE);
                sp.edit().putString("region", text).apply();
            }
        });
        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                String uri = "content://martinhanxposedwx/query";
                Bundle bundle = context.getContentResolver().call(
                        Uri.parse(uri), "query", null, null);
                String region = bundle.getString("region");
                System.out.println("调用结果：" + region);

            }
        });
    }
}
