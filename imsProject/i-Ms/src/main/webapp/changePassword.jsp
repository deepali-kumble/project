<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password
</title>
</head>
<body>
<br>
<h1>reset your password</h1>
<br>
	<h6>${msg}</h6>
<br>
	<h4>User Name:${userName }</h4>	
<fieldSet>

<legend>Fill in Below To Change Password</legend>


		<div class="container">
        <form action="changePassword.do" method="post">
            <div class="form-group">
                <label><b>Old Password</b></label>
                <input type="password" name="oldPassword" id="oldPassword"  class="form-control" required>
                <span id="emailerror" class="text-danger" font-weight-bold></span>
            </div>

            <div class="form-group">
                <label><b>New Password</b></label>
                <input type="password" name="newPassword" id="newPassword" required class="form-control">
                <span id="nameerror" class="text-danger" font-weight-bold></span>
            </div>

            <div class="form-group">
                <label><b>Confirm New Password</b></label>
                <input type="password"  name="confirmPassword" id="confirmPassword" required class="form-control">
                <span id="mobileerror" class="text-danger" font-weight-bold></span>
            </div>

			<input type="submit"  value="submit" class="btn btn-primary">
            <input type="reset"  value="reset" class="btn btn-primary">

</fieldSet>

</body>
</html>