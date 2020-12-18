package com.prokhorov.styleruhw4;

import java.util.ArrayList;

//Этот класс используется для парсинга JSON
public class Goods {
    private ArrayList<Product> goods;

    public ArrayList<Product> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Product> goods) {
        this.goods = goods;
    }
}
