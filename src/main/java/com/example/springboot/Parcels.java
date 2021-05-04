package com.example.springboot;

public class Parcels {

  private String id;
  private String vouchers; // voucher
  private String voucherValidity;
  private int weight; // weight cost
  private int height; // Height Volume
  private int width; // width volume;
  private int length; // lenght volume;
  private float discount; // parcel discount
  private double cost; // parcel cost


  private String getId() {
    return id;
  }

  private void setId(String id) {
    this.id = id;
  }

  private String getVouchers() {
    return vouchers;
  }

  public void setVouchers(String vouchers) {
    this.vouchers = vouchers;
  }
  
  private String getVoucherValidity() {
    return voucherValidity;
  }
  
  private void setVoucherValidity(String voucherValidity) {
    this.voucherValidity = voucherValidity;
  }  
  
  public int getWeight() {
    return weight;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public int getLength() {
    return length;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public float getDiscount() {
    return discount;
  }

  public void setDiscount(float discount) {
    this.discount = discount;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

}
