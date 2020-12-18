package com.prokhorov.styleruhw4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddActivity extends AppCompatActivity {
    @BindView(R.id.title)
    EditText titleET;
    @BindView(R.id.price)
    EditText priceET;
    @BindView(R.id.url)
    EditText urlET;
    @BindView(R.id.post_button)
    Button postButton;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
                finish();
            }
        });
    }

    private void makeRequest() {
        String title = titleET.getText().toString();
        int price = Integer.parseInt(priceET.getText().toString());
        String img_url = urlET.getText().toString();

        Product product = new Product(title, price, img_url);


        String url = "http://185.17.120.159:8080/goods/add/";

        Gson gson = new Gson();
        String json = gson.toJson(product);
        try {
            post(url, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();
                String res = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}