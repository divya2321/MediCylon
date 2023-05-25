<%-- 
    Document   : ReceptionistDashboard
    Created on : Oct 17, 2020, 12:44:58 PM
    Author     : divsi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stf" uri="/WEB-INF/tlds/ConFunction" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>


        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>MediCylon Laboratory</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-teal.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-ios.css">  

        <!--Recep Test Table-->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://editor.datatables.net/extensions/Editor/css/editor.bootstrap4.min.css"/>

        <style>
            html,body {font-family: "Raleway", sans-serif}

            #visualization_wrap {
                border:1px solid gray;
                position: relative;
                padding-bottom: 80%;
                height: 0;
                overflow:hidden;
            }
            #visualization {
                position: absolute;
                top: 0;
                left: 0;
                width:100%;
                height:100%;
            }

        </style>

    </head>
    <body> 
        <jsp:include page="receptionist_navbar.jsp" >
            <jsp:param name="dashboard" value="w3-ios-blue" />
        </jsp:include>
        <%@ include file="../headerbar.jsp" %>


        <div style="margin-left: 200px; margin-top: 20px; ">

            <div class="w3-row w3-center w3-content" style="padding-top:10px; padding-bottom: 10px;">

                <div class="w3-quarter w3-container">
                    <div class="w3-card" style="width: 150px; height: 150px;" >

                        <div style="padding-top: 30px;">
                            <p>Today Patients</p>
                            <h1 style="color: red;">20</h1>
                        </div>
                    </div> 
                </div>

                <div class="w3-quarter w3-container">
                    <div class="w3-card" style="width: 150px; height: 150px;" >

                        <div style="padding-top: 30px;">
                            <p>Today Appointments</p>
                            <h1 style="color: red;">20</h1>
                        </div>
                    </div> 
                </div>  

                <div class="w3-quarter w3-container">
                    <div class="w3-card" style="width: 150px; height: 150px;" >

                        <div style="padding-top: 30px;">
                            <p >Today Completed Tests</p>
                            <h1 style="color: red;">18</h1>
                        </div>
                    </div> 
                </div>     

                <div class="w3-quarter w3-container">
                    <div class="w3-card" style="width: 150px; height: 150px;" >

                        <div style="padding-top: 30px;">
                            <p>Today Canceled Appointments</p>
                            <h1 style="color: red;">2</h1>
                        </div>
                    </div> 
                </div>  
            </div>



        </div> 

        <!--Recept Test Table-->

        <div style="margin-left: 200px; margin-top: 20px; ">
            <table id="testTable" class="table  table-bordered table-hover table-striped container table-responsive" 
                   style="width:100%;">
                <thead class="thead-light">
                    <tr>
                        <th>Test</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Maximum Count</th>
                        <th>Room No</th>
                        <th>Technician</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${stf:loadTest()}" var="testModel">
                        <tr>
                            <td>${testModel.testName}</td>
                            <td>${testModel.price}</td>
                            <td>${testModel.testCategory}</td>
                            <td>${testModel.testPerDay}</td>
                            <td>${testModel.roomNo}</td>
                            <td>${testModel.staffName}</td>
                        </tr>
                    </c:forEach>
                </tbody> 
            </table>
        </div>


        <!--Recept Test Table-->
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

//Recept Test Table
            $(document).ready(function () {
                $('#testTable').DataTable({
                    lengthChange: false,
                    paging: false,
                    info: false,
                    select: true,
                    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]]

                });
            });





        </script>   


    </body>


</html> 

