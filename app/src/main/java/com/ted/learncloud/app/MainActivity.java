package com.ted.learncloud.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            AVObject testObject = new AVObject("XiongWeiData");
            testObject.put("name", "xiongwei");
            testObject.put("age", "27");
            testObject.saveInBackground();
        }else {
            AVQuery<AVObject> query = new AVQuery<AVObject>("XiongWeiData");
            query.getInBackground("5513f5dde4b0e3088fa9f316", new GetCallback<AVObject>() {
                public void done(AVObject xiongwei, AVException e) {
                    if (e == null) {
                        Log.d("获取姓名", "获取姓名" + xiongwei.getString("name"));
                    } else {
                        Log.e("获取姓名", "获取姓名: " + e.getMessage());
                    }
                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        findViewById(R.id.get_button).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
