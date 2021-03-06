# ImmersiveBestConfig
 set padding tu top parent

- `activity_main.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/parent"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="@color/teal_200"
    tools:context=".MainActivity">

    <LinearLayout
        android:id="@+id/content"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:background="@color/purple_700"
        android:orientation="vertical"
        android:text="Hello World!" />

</LinearLayout>
```

- `MainActivity.java`
```java
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setForceImmersiveScreen();

        setContentView(R.layout.activity_main);

        setPaddingToTopParentView();
    }

    private void setFullScreen() {
        //SYSTEM_UI_FLAG_IMMERSIVE_STICKY -> AUTO HIDE
        //SYSTEM_UI_FLAG_IMMERSIVE -> NOT AUTO HIDE | CAN SET BACK HOME COLOR
        int decore =
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //enable this tho maker icon status bar become black
            decore += View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //enable this tho maker icon Navigation bar become black
            decore +=  View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        }

        getWindow().getDecorView().setSystemUiVisibility(decore);
    }

    private void setForceImmersiveScreen() {
        final Handler forceImmersive = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                setFullScreen();

                forceImmersive.postDelayed(this, 1000);
            }
        };

        forceImmersive.postDelayed(runnable, 1000);
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
}
```

#### GblFunction
- `GblFunction.java`
```java
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
```

- `Manifest.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest>

    <application >
        <activity android:name=".MainActivity"
            android:theme="@style/Theme.VeryFullScreen.Splash">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

- `style.xml`
```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <style name="Theme.VeryFullScreen.Splash" parent="Theme.MaterialComponents.Light.NoActionBar">
        <item name="android:statusBarColor">@android:color/holo_red_light</item>
        <item name="colorPrimary">@android:color/holo_red_light</item>
        <item name="colorPrimaryVariant">@android:color/holo_green_light</item>
        <item name="colorOnPrimary">@android:color/holo_blue_light</item>
        <item name="colorSecondary">@android:color/holo_red_light</item>
        <item name="colorSecondaryVariant">@android:color/holo_green_light</item>
        <item name="colorOnSecondary">@android:color/holo_blue_light</item>
        <item name="android:fitsSystemWindows">false</item>
        <item name="android:navigationBarColor">@color/purple_700</item>
    </style>
</resources>
```

---

```
Copyright 2021 M. Fadli Zein
```
