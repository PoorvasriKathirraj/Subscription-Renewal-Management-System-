<%@ page import="java.util.List" %>
<%@ page import="com.wipro.renewal.bean.RenewalBean" %>

<html>
<head>
    <title>All Renewals</title>
</head>
<body>

<h2>All Renewal Records</h2>

<%
List<RenewalBean> list =
    (List<RenewalBean>) request.getAttribute("list");

String msg =
    (String) request.getAttribute("msg");

if(list != null && list.size() > 0) {

for(RenewalBean bean : list) {
%>

Renewal ID: <%= bean.getRenewalId() %><br>
Subscriber Name: <%= bean.getSubscriberName() %><br>
Subscription ID: <%= bean.getSubscriptionId() %><br>
Renewal Date: <%= bean.getRenewalDate() %><br>
Amount: <%= bean.getAmount() %><br>
Status: <%= bean.getStatus() %><br>
Remarks: <%= bean.getRemarks() %><br>
<hr>

<%
}

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
