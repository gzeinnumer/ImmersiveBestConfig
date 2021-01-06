package com.intishaka.immersivebestconfig;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;

public class GblFunction {

    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getValueInDP(Context context, int value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    public static float getValueInDP(Context context, float value){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    public static int getValueInPixel(Context context, int value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, value, context.getResources().getDisplayMetrics());
    }

    public static float getValueInPixel(Context context, float value){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, value, context.getResources().getDisplayMetrics());
    }
}
