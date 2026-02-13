package com.wipro.renewal.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.wipro.renewal.bean.RenewalBean;
import com.wipro.renewal.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    Administrator admin = new Administrator();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        try {

            if (operation.equals("newRecord")) {

                String result = addRecord(request);

                if (result.equals("FAIL") ||
                    result.equals("INVALID INPUT") ||
                    result.equals("ALREADY EXISTS") ||
                    result.equals("INVALID AMOUNT") ||
                    result.equals("INVALID SUBSCRIPTION ID") ||
                    result.equals("INVALID SUBSCRIBER NAME")) {

                    response.sendRedirect("error.html");
                } else {
                    response.sendRedirect("success.html");
                }
            }

            else if (operation.equals("viewRecord")) {

                RenewalBean bean = viewRecord(request);

                if (bean == null) {
                    request.setAttribute("msg",
                            "No matching records exists! Please try again!");
                } else {
                    request.setAttribute("bean", bean);
                }

                RequestDispatcher rd =
                        request.getRequestDispatcher("displayRenewal.jsp");
                rd.forward(request, response);
            }

            else if (operation.equals("viewAllRecords")) {

                List<RenewalBean> list = admin.viewAllRecords();

                if (list == null || list.size() == 0) {
                    request.setAttribute("msg",
                            "No records available!");
                } else {
                    request.setAttribute("list", list);
                }

                RequestDispatcher rd =
                        request.getRequestDispatcher("displayAllRenewals.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            response.sendRedirect("error.html");
        }
    }

    // 🔹 Add Record Method
    public String addRecord(HttpServletRequest request) {

        try {

            RenewalBean bean = new RenewalBean();

            bean.setSubscriberName(
                    request.getParameter("subscriberName"));

            bean.setSubscriptionId(
                    request.getParameter("subscriptionId"));

            String dateStr =
                    request.getParameter("renewalDate");

            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd");

            Date date = sdf.parse(dateStr);

            bean.setRenewalDate(date);

            bean.setAmount(
                    Double.parseDouble(
                            request.getParameter("amount")));

            bean.setStatus(
                    request.getParameter("status"));

            bean.setRemarks(
                    request.getParameter("remarks"));

            return admin.addRecord(bean);

        } catch (Exception e) {
            return "FAIL";
        }
    }

    // 🔹 View Single Record
    public RenewalBean viewRecord(HttpServletRequest request) {

        try {

            String subscriptionId =
                    request.getParameter("subscriptionId");

            String dateStr =
                    request.getParameter("renewalDate");

            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd");

            Date date = sdf.parse(dateStr);

            return admin.viewRecord(subscriptionId, date);

        } catch (Exception e) {
            return null;
        }
    }
}
