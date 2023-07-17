package com.example.myfurnitureapp1.models;

import java.io.Serializable;

public class MyCartModel implements Serializable {

    String productName;
    String productPrice;
    String currentDate;
    String currentTime;
    String totalQuantity;
    int totalPrice;
    String documentId;

    public MyCartModel() {
    }

    public MyCartModel(String productName, String productPrice, String currentDate, String currentTime, String totalQuantity, int totalPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
