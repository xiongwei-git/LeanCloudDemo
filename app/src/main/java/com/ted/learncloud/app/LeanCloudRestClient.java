package com.ted.learncloud.app;

import android.content.Context;

/**
 * Created by Ted on 2015/3/28.
 */
public class LeanCloudRestClient {
    private VolleryClient volleryClient;
    private Context context;

    private LeanCloudRestClient(Context context){
        volleryClient = new VolleryClient();
        this.context = context;
    }

    private void getDataByVolley(){
        volleryClient.getData(context);
    }
}
