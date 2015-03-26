package com.ted.learncloud.app;

import android.app.Application;
import com.avos.avoscloud.AVOSCloud;

/**
 * Created by Ted on 2015/3/26.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this,"fdsm1bi25mz0fdkmbtg3k2vnc8z105b3wkkmylvuy8pso1t5","6n82f5lljlmamtoxpu4b8jspufqg2lc1c9h7ztmpol176dl1");
    }
}
