package com.practice.my_jupiter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;

// 有些field没有用到，所以需要这么说去忽略掉它
@JsonIgnoreProperties(ignoreUnknown = true)
// 如果没有加入keywords和favorite的信息，那么就不用展示出这两个field
@JsonInclude(JsonInclude.Include.NON_NULL)
// annotation

public class Order {
    private String orderId;
    private String senderName;
    private String receiverName;
    private String shippingAddress;
    private String billingAddress;
    private String contactNumber;
    private int packageNumber;
    // extracted from monkeyLearn
    private String description;
    private Set<String> keywords;
    private boolean favorite;

    // constructor for item
    // EMPTY CONSTRUCTOR
    public Order() {
    }

    public Order(String orderId, String senderName, String receiverName, String shippingAddress, String billingAddress, String contactNumber, int packageNumber) {
        this.orderId = orderId;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.contactNumber = contactNumber;
        this.packageNumber = packageNumber;
    }

    @JsonProperty("id")
    public String getId() {
        return orderId;
    }

    @JsonProperty("sender_name")
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @JsonProperty("receiver_name")
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    // 写出我们想要的是怎么样的变量名/改default
    @JsonProperty("shipping_address")
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @JsonProperty("billing_address")
    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    @JsonProperty("contact_number")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @JsonProperty("number_of_package")
    public int getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(int packageNumber) {
        this.packageNumber = packageNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(senderName, order.senderName) && Objects.equals(receiverName, order.receiverName) && Objects.equals(shippingAddress, order.shippingAddress) && Objects.equals(billingAddress, order.billingAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, senderName, receiverName, shippingAddress, billingAddress, contactNumber, packageNumber);
    }

    @Override
    public String toString() {
        return "Item{" +
                "orderId='" + orderId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", packageNumber=" + packageNumber +
                '}';
    }
}
