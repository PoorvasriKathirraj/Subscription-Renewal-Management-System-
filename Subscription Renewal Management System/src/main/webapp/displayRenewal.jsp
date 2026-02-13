<%@ page import="com.wipro.renewal.bean.RenewalBean" %>
<html>
<head>
    <title>Display Renewal</title>
</head>
<body>

<h2>Renewal Details</h2>

<%
RenewalBean bean =
    (RenewalBean) request.getAttribute("bean");

String msg =
    (String) request.getAttribute("msg");

if(bean != null) {
%>

Renewal ID: <%= bean.getRenewalId() %><br><br>
Subscriber Name: <%= bean.getSubscriberName() %><br><br>
Subscription ID: <%= bean.getSubscriptionId() %><br><br>
Renewal Date: <%= bean.getRenewalDate() %><br><br>
Amount: <%= bean.getAmount() %><br><br>
Status: <%= bean.getStatus() %><br><br>
Remarks: <%= bean.getRemarks() %><br><br>

<%
} else {
%>

<%= msg %>

<%
}
%>

<br>
<a href="menu.html">Back</a>

</body>
</html>
