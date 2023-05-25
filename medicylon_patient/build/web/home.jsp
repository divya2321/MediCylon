<%-- 
    Document   : home
    Created on : Oct 29, 2020, 4:43:26 PM
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
        
         <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-teal.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-ios.css">  

        <!--Appointment Table-->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://editor.datatables.net/extensions/Editor/css/editor.bootstrap4.min.css"/>

        <style>
            html,body {font-family: "Raleway", sans-serif}

        </style>
    </head>
    <body style="background-color:#F8F9F9;">
        
        <%
            HttpSession ses = request.getSession(false);

            String patientName = (String) ses.getAttribute("patientName");
            String patientId = (String) ses.getAttribute("patientId");
            
        %>
        
               <header class="w3-ios-blue  w3-center-align" >

            <div class="w3-bar">
                <div class="w3-bar-item"><p class="w3-xlarge w3-wide ">MediCylon</p></div>
                <div class="w3-bar-item"><span >Welcome, <b><%= patientName%></b></span></div>
                <div class="w3-bar-item"><a href="logoutservlet" class="btn btn-default btn-sm"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;Logout</a></div>
            </div> 
        </header>

                
                
        <div class="container" style="margin-top: 10px;">
            <a class="btn btn-info" id="viewreport" target="_blank">View report</a>
        </div>
        <div style="margin-top: 20px; ">
            <!--Appointment Table-->
            <div class="table-responsive">
                <table id="appointmenttable" class="table table-bordered table-hover table-striped container" 
                       style="width:100%;">
                    <thead class="thead-light">
                        <tr>
                            <th>Appointment Code</th>
                            <th>Test</th>
                            <th>Appointment Date</th>
                            <th>Appointment Time</th>

                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${apt:loadAppointment()}" var="appointmentModel">
                            <c:if test="${appointmentModel.patientId ==  patientId}">
                            <c:if test="${appointmentModel.status == 'completed'}">
                                <tr>
                                    <td>${appointmentModel.appointmentCode}</td>
                                    <td>${appointmentModel.test}</td>
                                    <td>${appointmentModel.appointmentDate}</td>
                                    <td>${appointmentModel.appointmentTime}</td>
                                </tr>
                            </c:if>
                            </c:if>
                        </c:forEach>
                    </tbody> 
                </table>
            </div>
        </div>

        <!--Appointment Table-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js|https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
        <script type="text/javascript" src="https://editor.datatables.net/extensions/Editor/js/dataTables.editor.min.js"></script>
        <script type="text/javascript" src="https://editor.datatables.net/extensions/Editor/js/editor.bootstrap4.min.js"></script>

        <script>
            var selectedAppointmentCode = null;
            
            //Appointment Table
            $(document).ready(function () {
                var table = $('#appointmenttable').DataTable({
                    lengthChange: false,
                    paging: false,
                    info: false,
                    select: true,
                    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]]

                });

                $('#appointmenttable tbody').on('click', 'tr', function () {
                    var selectRow = table.rows(this).data()[0];
                    
                     $("#viewreport").attr("href", "appointmentservlet?code="+selectRow[0]+"");
            
                });

            });
            
            
        </script>
    </body>
</html>
