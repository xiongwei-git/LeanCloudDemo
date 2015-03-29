package com.ted.learncloud.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.GetCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.util.List;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    LeanCloudNorClient mLeanCloudNorClient;

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            mLeanCloudNorClient.addData();
        }else if(view.getId() == R.id.get_button_rest){
            getDataByRest();
        }else {

        }

    }


    private void getDataByRest(){


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.get_button).setOnClickListener(this);
        findViewById(R.id.get_button_rest).setOnClickListener(this);
        mLeanCloudNorClient = new LeanCloudNorClient();
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
