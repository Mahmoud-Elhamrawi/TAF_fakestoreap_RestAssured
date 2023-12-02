package seialization;

import java.util.List;

public class addToCart {
/*
      {
                    userId:5,
                    date:2020-02-03,
                    products:[{productId:5,quantity:1},{productId:1,quantity:5}]
                }

* */
    private int userId ;
    private  String date;
    Object[] productsDeatils ;

    public Object[] getProductsDeatils() {
        return productsDeatils;
    }

    public void setProductsDeatils(Object[] productsDeatils) {
        this.productsDeatils = productsDeatils;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }





}
