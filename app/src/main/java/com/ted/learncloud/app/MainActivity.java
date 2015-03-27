package com.ted.learncloud.app;

import android.os.Bundle;
import android.os.Handler;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.util.List;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Handler mHandler = new Handler();

    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            AVObject testObject = new AVObject("XiongWeiData");
            testObject.put("name", "xiongwei");
            testObject.put("age", "27");
            testObject.saveInBackground();
        }else if(view.getId() == R.id.get_button_rest){
            new Thread(mGetDataRunnable).start();
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

    private Runnable mGetDataRunnable = new Runnable() {
        @Override
        public void run() {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setConverter(new GsonConverter(gson))
                    .setEndpoint(LeancloudClient.API_URL + LeancloudClient.API_VERSION)
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade request) {
                            request.addHeader("X-AVOSCloud-Application-Id", "fdsm1bi25mz0fdkmbtg3k2vnc8z105b3wkkmylvuy8pso1t5");
                            request.addHeader("X-AVOSCloud-Application-Key", "6n82f5lljlmamtoxpu4b8jspufqg2lc1c9h7ztmpol176dl1");
                        }
                    })
                    .build();

            LeancloudClient.PersonGetter personGetter = restAdapter.create(LeancloudClient.PersonGetter.class);

            List<LeancloudClient.Person> persons = personGetter.getPerson("XiongWeiData");
            for (LeancloudClient.Person person : persons) {
                System.out.println(person.createdAt + " (" + person.updatedAt + ")");
            }
        }
    };


    //private void getAllData

    private GetCallback mGetCallback = new GetCallback() {
        @Override
        public void done(AVObject avObject, AVException e) {

        }

        @Override
        protected void internalDone0(Object o, AVException e) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        findViewById(R.id.get_button).setOnClickListener(this);
        findViewById(R.id.get_button_rest).setOnClickListener(this);
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
