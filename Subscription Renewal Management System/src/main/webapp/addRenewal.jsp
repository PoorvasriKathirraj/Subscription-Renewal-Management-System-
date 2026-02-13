<%@ page language="java" %>
<html>
<head>
    <title>Add Renewal</title>
</head>
<body>

<h2>Add Renewal Record</h2>

<form action="MainServlet" method="post">

<input type="hidden" name="operation" value="newRecord">

Subscriber Name:
<input type="text" name="subscriberName" required><br><br>

Subscription ID:
<input type="text" name="subscriptionId" required><br><br>

Renewal Date:
<input type="date" name="renewalDate" required><br><br>

Amount:
<input type="text" name="amount" required><br><br>

Status:
<select name="status">
    <option value="Pending">Pending</option>
    <option value="Completed">Completed</option>
</select><br><br>

Remarks:
<input type="text" name="remarks"><br><br>

<input type="submit" value="Add Record">

</form>

<br>
<a href="menu.html">Back</a>

</body>
</html>
