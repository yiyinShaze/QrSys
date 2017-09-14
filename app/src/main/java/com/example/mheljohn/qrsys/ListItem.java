package com.example.mheljohn.qrsys;

/**
 * Created by Mhel John on 5/3/2017.
 */

public class ListItem {

    private String itemName;
    private String gender;
    private String size;
    private String age;
    private String height;
    private String weight;
    private String chest;
    private String waist;
    private String fabricType;
    private String price;
    private String qrCode;
    private String noStock;
    private String location;
    private String dressUrlFront;
    private String dressUrlBack;
    private String dressUrlLeft;
    private String dressUrlRight;
    private String dressUrlFabric;


    public ListItem(String itemName, String gender, String size, String age, String height, String weight,
                    String chest, String waist, String fabricType, String price, String qrCode, String noStock, String location,
                    String dressUrlFront, String dressUrlBack, String dressUrlLeft, String dressUrlRight, String dressUrlFabric) {
        this.itemName = itemName;
        this.gender = gender;
        this.size = size;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.chest = chest;
        this.waist = waist;
        this.fabricType = fabricType;
        this.price = price;
        this.qrCode = qrCode;
        this.noStock = noStock;
        this.location = location;
        this.dressUrlFront = dressUrlFront;
        this.dressUrlBack = dressUrlBack;
        this.dressUrlLeft = dressUrlLeft;
        this.dressUrlRight = dressUrlRight;
        this.dressUrlFabric = dressUrlFabric;
    }

    public String getItemName() {
        return itemName;
    }

    public String getGender() {
        return gender;
    }

    public String getSize() {
        return size;
    }

    public String getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getChest() {
        return chest;
    }

    public String getWaist() {
        return waist;
    }

    public String getFabricType() {
        return fabricType;
    }

    public String getPrice() {
        return price;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getNoStock() {
        return noStock;
    }

    public String getLocation() {
        return location;
    }

    public String getDressUrlFront() {
        return dressUrlFront;
    }

    public String getDressUrlBack() {
        return dressUrlBack;
    }

    public String getDressUrlLeft() {
        return dressUrlLeft;
    }

    public String getDressUrlRight() {
        return dressUrlRight;
    }

    public String getDressUrlFabric() {
        return dressUrlFabric;
    }
}