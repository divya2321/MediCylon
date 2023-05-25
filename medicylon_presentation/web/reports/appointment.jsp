<%-- 
    Document   : appointment
    Created on : Oct 30, 2020, 5:01:29 AM
    Author     : divsi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="apt" uri="/WEB-INF/tlds/ConFunction" %>
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
            <table  class="table  table-bordered table-hover table-striped container" 
                    style="width:100%;">
                <thead class="thead-light">
                    <tr>
                        <th>Appointment Code</th>
                        <th>Patient Nic</th>
                        <th>Test</th>
                        <th>Appointment Date</th>
                        <th>Appointment Time</th>
                        <th>Appointment No</th>
                        <th>Appointed Date</th>
                        <th>Doctor</th>
                        <th>Amount</th>
                        <th>Payment Type</th>
                        <th>Status</th>
                        <th>Appointed By</th>

                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${apt:loadAppointment()}" var="appointmentModel">
                        <tr>
                            <td>${appointmentModel.appointmentCode}</td>
                            <td>${appointmentModel.patientNic}</td>
                            <td>${appointmentModel.test}</td>
                            <td>${appointmentModel.appointmentDate}</td>
                            <td>${appointmentModel.appointmentTime}</td>
                            <td>${appointmentModel.appointmentNo}</td>
                            <td>${appointmentModel.appointedDate}</td>
                            <td>${appointmentModel.doctor}</td>
                            <td>${appointmentModel.amount}</td>
                            <td>${appointmentModel.paymentType}</td>
                            <td>${appointmentModel.status}</td>
                            <td>${appointmentModel.username}</td>
                        </tr>
                    </c:forEach>
                </tbody> 
            </table>
        </div>
    </body>
</html>
