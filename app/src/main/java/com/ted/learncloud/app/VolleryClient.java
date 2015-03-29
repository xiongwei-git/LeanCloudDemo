package com.ted.learncloud.app;

import android.content.Context;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

/**
 * Created by Ted on 2015/3/28.
 */
public class VolleryClient {

    public void getData(Context context){
        MyJsonRequest jsonObjectRequest = new MyJsonRequest(Constants.SERVER_IP, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });

        RequestQueue mQueue = Volley.newRequestQueue(context);
        mQueue.add(jsonObjectRequest);
    }
}
