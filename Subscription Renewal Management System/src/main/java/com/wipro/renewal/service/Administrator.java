package com.wipro.renewal.service;

import java.util.Date;
import java.util.List;

import com.wipro.renewal.bean.RenewalBean;
import com.wipro.renewal.dao.RenewalDAO;

public class Administrator {

    RenewalDAO dao = new RenewalDAO();

    public String addRecord(RenewalBean bean) {

        if (bean == null || bean.getSubscriberName() == null
                || bean.getSubscriptionId() == null
                || bean.getRenewalDate() == null)
            return "INVALID INPUT";

        if (bean.getSubscriberName().length() < 2)
            return "INVALID SUBSCRIBER NAME";

        if (bean.getSubscriptionId().length() < 2)
            return "INVALID SUBSCRIPTION ID";

        if (bean.getAmount() <= 0)
            return "INVALID AMOUNT";

        if (dao.recordExists(bean.getSubscriptionId(),
                bean.getRenewalDate()))
            return "ALREADY EXISTS";

        String id = dao.generateRenewalID(
                bean.getSubscriptionId(),
                bean.getRenewalDate());

        bean.setRenewalId(id);

        return dao.createRecord(bean);
    }

    public RenewalBean viewRecord(String subscriptionId, Date renewalDate) {

        return dao.fetchRecord(subscriptionId, renewalDate);
    }

    public List<RenewalBean> viewAllRecords() {

        return dao.fetchAllRecords();
    }
}
