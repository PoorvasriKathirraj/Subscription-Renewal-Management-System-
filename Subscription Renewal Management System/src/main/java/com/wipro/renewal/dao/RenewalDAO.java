package com.wipro.renewal.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.renewal.bean.RenewalBean;
import com.wipro.renewal.util.DBUtil;

public class RenewalDAO {

    public String createRecord(RenewalBean bean) {

        try {
            Connection con = DBUtil.getDBConnection();

            String sql = "INSERT INTO RENEWAL_TB VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bean.getRenewalId());
            ps.setString(2, bean.getSubscriberName());
            ps.setString(3, bean.getSubscriptionId());
            ps.setDate(4, new java.sql.Date(bean.getRenewalDate().getTime()));
            ps.setDouble(5, bean.getAmount());
            ps.setString(6, bean.getStatus());
            ps.setString(7, bean.getRemarks());

            int result = ps.executeUpdate();

            con.close();

            if (result > 0)
                return bean.getRenewalId();
            else
                return "FAIL";

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    public boolean recordExists(String subscriptionId, Date renewalDate) {

        try {
            Connection con = DBUtil.getDBConnection();

            // ✅ FIXED DATE COMPARISON
            String sql = "SELECT * FROM RENEWAL_TB WHERE SUBSCRIPTIONID=? AND TRUNC(RENEWALDATE)=TRUNC(?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, subscriptionId);
            ps.setDate(2, new java.sql.Date(renewalDate.getTime()));

            ResultSet rs = ps.executeQuery();

            boolean exists = rs.next();

            con.close();

            return exists;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String generateRenewalID(String subscriptionId, Date renewalDate) {

        try {
            Connection con = DBUtil.getDBConnection();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT RENEWAL_SEQ.NEXTVAL FROM DUAL");

            int seq = 0;
            if (rs.next())
                seq = rs.getInt(1);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String datePart = sdf.format(renewalDate);

            String subPart = subscriptionId.substring(0, 2).toUpperCase();

            con.close();

            return datePart + subPart + seq;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RenewalBean fetchRecord(String subscriptionId, Date renewalDate) {

        RenewalBean bean = null;

        try {
            Connection con = DBUtil.getDBConnection();

            // ✅ FIXED SPELLING + DATE ISSUE
            String sql = "SELECT * FROM RENEWAL_TB WHERE SUBSCRIPTIONID=? AND TRUNC(RENEWALDATE)=TRUNC(?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, subscriptionId);
            ps.setDate(2, new java.sql.Date(renewalDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                bean = new RenewalBean();
                bean.setRenewalId(rs.getString(1));
                bean.setSubscriberName(rs.getString(2));
                bean.setSubscriptionId(rs.getString(3));
                bean.setRenewalDate(rs.getDate(4));
                bean.setAmount(rs.getDouble(5));
                bean.setStatus(rs.getString(6));
                bean.setRemarks(rs.getString(7));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    public List<RenewalBean> fetchAllRecords() {

        List<RenewalBean> list = new ArrayList<RenewalBean>();

        try {
            Connection con = DBUtil.getDBConnection();

            String sql = "SELECT * FROM RENEWAL_TB";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                RenewalBean bean = new RenewalBean();

                bean.setRenewalId(rs.getString(1));
                bean.setSubscriberName(rs.getString(2));
                bean.setSubscriptionId(rs.getString(3));
                bean.setRenewalDate(rs.getDate(4));
                bean.setAmount(rs.getDouble(5));
                bean.setStatus(rs.getString(6));
                bean.setRemarks(rs.getString(7));

                list.add(bean);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
