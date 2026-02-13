package com.wipro.renewal.bean;

import java.util.Date;

public class RenewalBean {

    private String renewalId;
    private String subscriberName;
    private String subscriptionId;
    private Date renewalDate;
    private double amount;
    private String status;
    private String remarks;

    public String getRenewalId() {
        return renewalId;
    }

    public void setRenewalId(String renewalId) {
        this.renewalId = renewalId;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
