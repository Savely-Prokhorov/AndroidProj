package com.prokhorov.styleruhw4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView goodsRecycler;
    private GoodsAdapter goodsAdapter;

    @BindView(R.id.add_button)
    Button addButton;

    private ArrayList<Product> goodsArr = new ArrayList<Product>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        goodsAdapter = new GoodsAdapter();
        goodsRecycler.setAdapter(goodsAdapter);
        makeRequest();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextScreen();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        makeRequest();
    }

    private void fillAdapter() {
        ArrayList<Product> goodsList = new ArrayList<Product>();
        for (int i = goodsArr.size() - 1; i >= 0; i--) {
            Product product = new Product(  goodsArr.get(i).getTitle(),
                                            goodsArr.get(i).getPrice(),
                                            goodsArr.get(i).getImgUrl());
            goodsList.add(product);
        }
        goodsAdapter.replaceGoods(goodsList);
    }


    private void makeRequest() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://185.17.120.159:8080/goods";

        Request request = new Request.Builder()
                .url(url)
                .build();

        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();
                String stringResponse = response.body().string();
                Gson gson = new Gson();
                Goods goods = gson.fromJson(stringResponse, Goods.class);
                goodsArr = goods.getGoods();//массив всех мемов
                runOnUiThread(() -> fillAdapter());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void goToNextScreen() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}
