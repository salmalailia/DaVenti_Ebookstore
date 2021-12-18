<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

    <style>
        body {
        height: 100%;
        }

        body {
        display: -ms-flexbox;
        -ms-flex-align: center;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
        }

        .form-signin {
        width: 100%;
        max-width: 700px;
        padding: 15px;
        margin: auto;
        }
    </style>

<head>
<meta charset="ISO-8859-1">
<title>Da Venti Registration</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>

<body>
 <div>
  <form action="<%= request.getContextPath() %>/register" class="form-signin" method="post">
        <h1 class="h3 mb-3 font-weight-normal" align="center">Da Venti Admin Registration</h1>
        
        <div class="form-group">
            <label for="inputName">Employee ID</label>
            <input type="text" class="form-control" name="id_employee" id="inputName" placeholder="EMPxxx (xxx is your unique ID)" required>
        </div>
        
        <div class="form-group">
            <label for="inputName">Name</label>
            <input type="text" class="form-control" name="name_employee" id="inputName" placeholder="Your Name" required>
        </div>
        
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail4">Email</label>
                <input type="email" class="form-control" name="email_employee" id="inputEmail" placeholder="example@email.com" required>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword4">Password</label>
                <input type="password" class="form-control" name="password_employee" id="inputPassword" required>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputPhoneNum">Phone Number</label>
                <input type="tel" class="form-control" name="phone_num_employee" id="inputPhoneNum" placeholder="Your phone number" required>
            </div>
            <div class="form-group col-md-6">
                <label for="inputGender">Gender</label>
                <select id="inputGender" class="form-control" name="gender" required>
                    <option selected disabled=true>Choose...</option>
                    <option>Female</option>
                    <option>Male</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="inputRole">Role</label>
                <select id="inputRole" class="form-control" name="role" required>
                    <option selected disabled=true>Choose...</option>
                    <option>Manager</option>
                    <option>Human Resource</option>
                    <option>Quality Control</option>
                    <option>Administrator</option>
                </select>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
        
        <div class="mt-4 mb-3 text-center">
            <a href="<%=request.getContextPath()%>/login">Back to login</a>
        </div>
  </form>
 </div>
</body>
</html>