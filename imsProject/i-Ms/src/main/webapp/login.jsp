<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Here</title>

<!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>


</head>
<body >
<br>
<h3><b><font color="RED">${logs}</font></b></h3>
<h3><b><font color="RED">${msg}</font></b></h3>
	<fieldset>
		<legend>LOGIN HERE</legend>

		<form action="login.do" method="post">


			<div class="form-group">
				<label><b>UserName</b></label> <input type="text"
					placeholder="Example:ahrAD95" name="userName" id="userName"
					class="form-control" required>
				<!--   <span id="emailerror" class="text-danger" font-weight-bold></span>-->
			</div>

			<div class="form-group">
				<label><b>Password</b></label> <input type="password"
					name="password" id="password" required class="form-control">
				<!--    <span id="nameerror" class="text-danger" font-weight-bold></span> -->
			</div>

	  <input type="submit"  value="submit" class="btn btn-primary">
            <input type="reset"  value="reset" class="btn btn-primary">

		</form>




	</fieldset>

</body>
</html>