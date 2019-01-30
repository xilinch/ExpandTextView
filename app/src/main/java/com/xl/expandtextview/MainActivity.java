package com.xl.expandtextview;

import android.app.Activity;
import android.os.Bundle;

import com.xl.expandtext.ExpandTextView;

public class MainActivity extends Activity {

    private ExpandTextView rl_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl_info = findViewById(R.id.rl_info);

        rl_info.setTextContent("你的老家在东北啊哪里有大豆和棉花啊还有那松花江松花蛋冰天雪地冰凌挂在屋檐下物产丰富气候伊人啊");
    }
}
