<%-- 
    Document   : Receptionistappointment
    Created on : Oct 18, 2020, 12:28:45 PM
    Author     : divsi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="apt" uri="/WEB-INF/tlds/ConFunction" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

    <!--Searchable select-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>

    <!--Appointment table-->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://editor.datatables.net/extensions/Editor/css/editor.bootstrap4.min.css"/>


    <style>
        html,body {font-family: "Raleway", sans-serif}


    </style>

    <body> 
         <jsp:include page="receptionist_navbar.jsp" >
        <jsp:param name="appointment" value="w3-ios-blue" />
        </jsp:include>
        <%@ include file="../headerbar.jsp" %>

        <div style="margin-left: 200px; margin-top: 20px; padding: 10px">




            <div class="container">
            <div class="card text-center" style="width: 80rem;">
                <div class="card-body ">

                    <form action="../appointmentservlet?name=addupdate" method="POST" class="text-center" id="appointmentform">


                        <div class="form-row container justify-content-center">
                            <div class="form-group col-md-4">
                                <label for="nic">Patient nic:</label>
                                <select class="form-control browser-default custom-select"  id="nic" name="nic" required>
                                    <c:forEach  var="patient" items="${apt:loadPatient()}" >
                                        <option value="${patient.nic}">${patient.nic}</option>
                                    </c:forEach>
                                </select>
                            </div>



                            <div class="form-group col-md-4">
                                <label for="test">Test</label>
                                <select class="form-control browser-default custom-select empSelect" id="test" name="test" required>
                                    <c:forEach  var="test" items="${apt:loadTest()}" >
                                        <option value="${test.testName}">${test.testName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group col-md-4">
                                <label for="doctor">Doctor:</label>
                                <input type="text" class="form-control" id="doctor" name="doctor">
                            </div>

                        </div>

                        <div class="form-row container justify-content-center">

                            <div class="form-group col-md-4">
                                <label for="date">Appointment date</label>
                                <input type="date" class="form-control" id="appointmentdate" name="appointmentdate" oninvalid="canPlaceAppointment(this);" 
                                       oninput="canPlaceAppointment(this);" required>
                            </div>

                            <div class="form-group col-md-4">
                                <label for="time">Appointment time</label>
                                <input type="time" class="form-control" id="appointmenttime" name="appointmenttime" value="14:30" required>
                            </div>

                        </div>

                        <div class="form-row container justify-content-center">
                            <div class="form-group">
                                <label for="amount">Amount:</label>
                                <input type="text" class="form-control" id="amount" name="amount" readonly required >
                            </div>
                        </div>

                        <div id="form-group">
                            <div class="form-check-inline">
                                <label class="form-check-label">
                                    <input type="radio" class="form-check-input" checked id="cash" value="cash" name="paymenttype">Cash
                                </label>
                            </div>
                            <div class="form-check-inline">
                                <label class="form-check-label">
                                    <input type="radio" class="form-check-input" id="card" value="card" name="paymenttype">Card
                                </label>
                            </div>
                        </div>
                        <input id="appointmentCode" name="appointmentCode" style="display: none;">
                        <input id="updateTest" name="updateTest" style="display: none;">
                        <br/>
                        <div class="form-row justify-content-center">

                            <button type="submit" class="btn btn-info">Appoint</button>
                            &nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#cancelPopup">Cancel</button>
                        </div>

                    </form> 

                </div>
            </div>
            </div>


            <div class="table-responsive">
                <table id="appointmenttable" class="table  table-bordered table-hover table-striped container" 
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

        </div>


        <!--Cancel Popup-->
        <div class="modal fade" id="cancelPopup" tabindex="-1" role="dialog" aria-labelledby="cancelCenter" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document"  >
                <div class="modal-content">

                    <div class="modal-body">
                        <form action="../appointmentservlet?name=cancel" method="POST" >

                            <label >Are you sure you want to <strong>cancel </strong>the appointment?</label>

                            <input id="cancelAppointmentCode" name="appointmentCode" style="display: none;" >

                            <br/>
                            <br/>
                            <div class="text-center">
                                <button type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close">No</button>
                                &nbsp;&nbsp;&nbsp;
                                <button type="submit" class="btn btn-primary">Yes</button>
                            </div>

                        </form> 
                    </div>
                </div>
            </div>
        </div>

        <!--Appointment Table-->
        <!--<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>-->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js|https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
        <script type="text/javascript" src="https://editor.datatables.net/extensions/Editor/js/dataTables.editor.min.js"></script>
        <script type="text/javascript" src="https://editor.datatables.net/extensions/Editor/js/editor.bootstrap4.min.js"></script>

        <script>

            //     Searchable select
            $(document).ready(function () {
                $("select").select2();
            });

            //Appointment Table
            $(document).ready(function () {
                var table = $('#appointmenttable').DataTable({
                    lengthChange: false,
                    paging: false,
                    info: false,
                    select: true

                });

                $('#appointmenttable tbody').on('click', 'tr', function () {
                    var selectRow = table.rows(this).data()[0];
                    
                    clearTestFields();
                    
                    var status = selectRow[10];
                    
                    if(status === "appointed"){
                        
                    $("#appointmentCode").val(selectRow[0]);
                    $("#cancelAppointmentCode").val(selectRow[0]);
                    $("#nic").val("selectRow[1]");
                    $("#test").val(selectRow[2]);
                    $("#updateTest").val(selectRow[2]);
                    $("#appointmentdate").val(selectRow[3]);
                    $("#appointmenttime").val(selectRow[4]);
                    $("#doctor").val(selectRow[7]);
                    $("#amount").val(selectRow[8]);
                    var payType = selectRow[9];
                    if (payType === "card") {
                        $("#card").prop("checked", true);
                    } else {
                        $("#cash").prop("checked", true);
                    }
                }
                });
            });
            
             function clearTestFields() {
                                $("#appointmentCode").val("");
                                $("#cancelAppointmentCode").val("");
                                $("#nic").val($("#nic option:first").val());
                                $("#test").val($("#test option:first").val());
                                $("#updateTest").val("");
                                $("#appointmentdate").val("");
                                $("#appointmenttime").val("");
                                $("#doctor").val("");
                                $("#amount").val("");
                                $("#cash").prop("checked", true);
                            }

//          Check availibility
            function canPlaceAppointment(field) {
                var test = $("#test").val();
                var date = field.value;

                $.ajax({
                    type: 'GET',
                    url: "../appointmentservlet?name=availibility",
                    data: {test: test, date: date},
                    success: function (data) {
                        if ($.trim(data) === "ok") {
                            field.setCustomValidity('');
                        } else {
                            field.setCustomValidity(data);
                        }
                    }
                });

            }


            $("#appointmentform").submit(function (event) {
                var test = $("#test").val();
                var date = $("#appointmentdate").val();
                $.ajax({
                    type: 'GET',
                    url: "../appointmentservlet?name=availibility",
                    data: {test: test, date: date},
                    success: function (data) {
                        if ($.trim(data) === "ok") {
                            event.currentTarget.submit();
                        } else {
                            alert(data);
                            $("#appointmentdate").setCustomValidity(data);
                        }
                    }
                });

                event.preventDefault();
            });



//            Get Test Price

            $("#test").on('change', function () {
                var test = $("#test").val();
                $.ajax({
                    type: 'GET',
                    url: "../appointmentservlet?name=price",
                    data: {test: test},
                    success: function (data) {
                        $("#amount").val(data);
                    }
                });
            });

            $(document).ready(function () {

                var test = $("#test").val();
                $.ajax({
                    type: 'GET',
                    url: "../appointmentservlet?name=price",
                    data: {test: test},
                    success: function (data) {
                        $("#amount").val(data);
                    }
                });

            });

//            Cancel popup
            $('#cancelPopup').on('shown.bs.modal', function () {
                $('#myInput').trigger('focus');
            });

        </script>

    </body>
</html> 
