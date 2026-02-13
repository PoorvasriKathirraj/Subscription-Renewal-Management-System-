<%@ page language="java" %>
<html>
<head>
    <title>View Renewal</title>
</head>
<body>
<h2>View Renewal Record</h2>
<form action="MainServlet" method="post">
<input type="hidden" name="operation" value="viewRecord">
Subscription ID:
<input type="text" name="subscriptionId" required><br><br>
Renewal Date:
<input type="date" name="renewalDate" required><br><br>
<input type="submit" value="View Record">
</form>
<br>
<a href="menu.html">Back</a>
</body>
</html>
