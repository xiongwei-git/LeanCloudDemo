package com.ted.learncloud.app;

import android.util.Log;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

/**
 * Created by Ted on 2015/3/29.
 */
public class LeanCloudNorClient {

    private void getData(){
        AVQuery<AVObject> query = new AVQuery<AVObject>(Constants.SERVER_DATA_NAME);
        query.getInBackground(Constants.SERVER_DATA_ID, new GetCallback<AVObject>() {
            public void done(AVObject data, AVException e) {
                if (e == null) {
                    Log.d("获取姓名", "获取姓名" + data.getString("name"));
                } else {
                    Log.e("获取姓名", "获取姓名: " + e.getMessage());
                }
            }
        });
    }

    public void addData(){
        AVObject testObject = new AVObject("XiongWeiData");
        testObject.put("name", "xiongwei");
        testObject.put("age", "27");
        testObject.saveInBackground();
    }
}
