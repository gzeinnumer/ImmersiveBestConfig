package com.intishaka.immersivebestconfig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_main);
        setPaddingToTopParentView();
    }

    private void setPaddingToTopParentView() {
        int statusBarHeight = GblFunction.getStatusBarHeight(this);
        Log.d(TAG, "onCreate: " + statusBarHeight);

        LinearLayout content = findViewById(R.id.content);
        LinearLayout.LayoutParams layoutParams;
        //Width Height
        layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                GblFunction.getValueInDP(getApplicationContext(), 100)
        );
//        layoutParams = new LinearLayout.LayoutParams(100, statusBarHeight);
//        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        content.setLayoutParams(layoutParams);

        //set padding pada button, agar tidak memaka view
        LinearLayout parent = findViewById(R.id.parent);
        parent.setPadding(0, GblFunction.getStatusBarHeight(this), 0, 0);
    }

    private void setFullScreen() {
        //SYSTEM_UI_FLAG_IMMERSIVE_STICKY -> AUTO HIDE
        //SYSTEM_UI_FLAG_IMMERSIVE -> NOT AUTO HIDE
        int decore =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //enable this tho maker icon status bar become black
            decore += View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }

        getWindow().getDecorView().setSystemUiVisibility(decore);
    }
}