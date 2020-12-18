package com.prokhorov.styleruhw4;

import com.google.gson.annotations.SerializedName;

public class Product {
    private String goods;
    private String title;
    private int price;
    @SerializedName("image")
    private String imgUrl;

    public Product(String title, int price, String imgUrl) { //TODO: add img param
        this.title = title;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public int getPrice() {
        return 1;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

