package com.ted.learncloud.app;

/**
 * Created by Ted on 2015/3/27.
 */

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.List;

public class LeancloudClient {

    public static final String API_URL = "https://leancloud.cn";
    public static final String API_VERSION = "/1.1";

    //public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();



    public class Person {
        String createdAt;
        String updatedAt;
    }

    public interface PersonGetter {
        @GET("/classes/{class}")
        List<Person> getPerson(
                @Path("class") String className
        );
    }

    public static void main(String... args) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL+API_VERSION)
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Cache-Control", "public, max-age=" + 60 * 60 * 4);
                        request.addHeader("X-AVOSCloud-Application-Id", "fdsm1bi25mz0fdkmbtg3k2vnc8z105b3wkkmylvuy8pso1t5");
                        request.addHeader("X-AVOSCloud-Application-Key", "6n82f5lljlmamtoxpu4b8jspufqg2lc1c9h7ztmpol176dl1");
                    }
                })
                .build();

        PersonGetter personGetter = restAdapter.create(PersonGetter.class);

        List<Person> persons = personGetter.getPerson("XiongWeiData");
        for (Person person : persons) {
            System.out.println(person.createdAt + " (" + person.updatedAt + ")");
        }
    }
}

