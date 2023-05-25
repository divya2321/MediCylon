<%-- 
    Document   : appointment
    Created on : Oct 30, 2020, 5:01:29 AM
    Author     : divsi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ptn" uri="/WEB-INF/tlds/ConFunction" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MediCylon Laboratory</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        
    </head>
    <body>
           <div class="table-responsive">
            <table id="patientTable" class="table  table-bordered table-hover table-striped container " 
                   style="width:100%;">
                <thead class="thead-light">
                    <tr>
                        <th>Nic</th>
                        <th>Name</th>
                        <th>Contact numbers</th>
                        <th>Email</th>
                        <th>Patient ID</th>
                        <th>Register date</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${ptn:loadPatient()}" var="patientModel">
                        <tr>
                            <td>${patientModel.nic}</td>
                            <td>${patientModel.name}</td>
                            <td>${patientModel.contact}</td>
                            <td>${patientModel.email}</td>
                            <td>${patientModel.id}</td>
                            <td>${patientModel.date}</td>
                        </tr>
                    </c:forEach>   
                </tbody> 
            </table>
        </div>
    </body>
</html>
