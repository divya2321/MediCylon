<%-- 
    Document   : AdminHome
    Created on : Oct 15, 2020, 12:18:52 AM
    Author     : divsi
--%>

<!DOCTYPE html>
<html>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediCylon Laboratory</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-teal.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-ios.css">  
    <style>
        html,body{font-family: "Raleway", sans-serif}
    </style>

    <body> 
        <jsp:include page="admin_navbar.jsp" >
            <jsp:param name="report" value="w3-ios-blue" />
        </jsp:include>
        <%@ include file="../headerbar.jsp" %>

        <div style="margin-left: 200px; margin-top: 20px; ">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a class="btn btn-success" href="../reports/appointment.jsp"  target="_blank">Appointment Reports</a>
            <a  class="btn btn-info" href="../reports/tests.jsp"  target="_blank">Test Reports</a>
            <a class="btn btn-success" href="../reports/patient.jsp"  target="_blank">Patient Reports</a>
            <a class="btn btn-info" href="../reports/staff.jsp"  target="_blank">Staff Reports</a>
        </div>




    </body>
</html> 

