<%-- 
    Document   : appointment
    Created on : Oct 30, 2020, 5:01:29 AM
    Author     : divsi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stf" uri="/WEB-INF/tlds/ConFunction" %>
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
            <table id="staffTable" class="table  table-bordered table-hover table-striped container " 
                   style="width:100%;">
                <thead class="thead-light">
                    <tr>
                        <th>Staff ID</th>
                        <th>Nic</th>
                        <th>Name</th>
                        <th>Contact numbers</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>Role</th>
                        <th>Availability</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${stf:loadStaff()}" var="staffModel">
                        <tr>
                            <td>${staffModel.staffId}</td>
                            <td>${staffModel.nic}</td>
                            <td>${staffModel.name}</td>
                            <td>${staffModel.contact}</td>
                            <td>${staffModel.email}</td>
                            <td>${staffModel.address}</td>
                            <td>${staffModel.job}</td>
                            <td>${staffModel.status}</td>
                        </tr>
                    </c:forEach>
                </tbody> 
            </table>
        </div>
    </body>
</html>
