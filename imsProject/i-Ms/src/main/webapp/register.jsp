<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidate Register</title>
 <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

</head>
<h1 class="text-center bg-dark text-white">REGISTER HERE</h1>
<body>

<br>
<h3><b><font color="green">${success}</font></b></h3>
<h4><b><font color="pink">${present}</font></b></h4>
<h3><b><font color="red">${error}</font></b></h3>
    <div class="container">
        <form action="register.do" method="post">
            <div class="form-group">
                <label><b>Email</b></label>
                <input type="email" placeholder="Example:example@gmail.com" name="email" id="email"  class="form-control">
                <span id="emailerror" class="text-danger" font-weight-bold></span>
            </div>

            <div class="form-group">
                <label><b>Name</b></label>
                <input type="text" placeholder="Example:Peter john" name="name" id="name" required class="form-control">
                <span id="nameerror" class="text-danger" font-weight-bold></span>
            </div>

            <div class="form-group">
                <label><b>Mobile Number</b></label>
                <input type="text" placeholder="Example:990000000" maxlength="10" name="mobile" id="mobile" required class="form-control">
                <span id="mobileerror" class="text-danger" font-weight-bold></span>
            </div>

            <div class="form-group">
                <label><b>Date of Birth</b></label>
                <input type="date" name="dateOfBirth" id="dateOfBirth" required class="form-control">
                <span id="usererror" class="text-danger" font-weight-bold></span>
            </div>


            <div class="form-group">
                <label><b>Job Code</b></label>
                <select name="jobCode" id="jobCode" required class="form-control">
                    <option selected="" value="Default">(Please select a Job Code)</option>
                    <option value="java">SE-101(JAVA)</option>
                    <option value="sql">SE-105(SQL)</option>
                    <option value="auto">JE-101(AUTOMATION)</option>
                    <option value="dotNet">SE-106(DOT NET)</option>
                    <option value="fullStack">SE-101(FULLSTACK)</option>
                </select>
                <span id="jobcodeerror" class="text-danger" font-weight-bold></span>
            </div>


            <div class="form-group">
                <label for="experience"><b>Experience</b></label>
                <input type="number" placeholder="example:02" name="experience" id="experience" min="0" step="0.1" class="form-control">
                <span id="experienceerror" class="text-danger" font-weight-bold></span>
            </div>

            <input type="submit"  value="submit" class="btn btn-primary">
            <input type="reset"  value="reset" class="btn btn-primary">
        </form>


    </div>


</body>
</html>