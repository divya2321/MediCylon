<%-- 
    Document   : TechnicianDashboard
    Created on : Oct 18, 2020, 11:45:50 PM
    Author     : divsi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="apt" uri="/WEB-INF/tlds/ConFunction" %>
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

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 

        <!--Appointment Table-->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.bootstrap4.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://editor.datatables.net/extensions/Editor/css/editor.bootstrap4.min.css"/>


        <style style="background-color:#F8F9F9;">
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

        <%
            HttpSession ses = request.getSession(false);

            String name = (String) ses.getAttribute("name");
            String job = (String) ses.getAttribute("role");
            if (job != null) {
                if (!job.equalsIgnoreCase("technician")) {
                    session.invalidate();
                    response.sendRedirect("../log_in_form.jsp");
                }
            } else {
                session.invalidate();
                response.sendRedirect("../log_in_form.jsp");
            }

        %>
        <!--w3-right-align-->
        <header class="w3-ios-blue  w3-center-align" >

            <div class="w3-bar">
                <div class="w3-bar-item"><p class="w3-xlarge w3-wide ">MediCylon</p></div>
                <div class="w3-bar-item"><span >Welcome, <b><%= name%></b></span></div>
                <div class="w3-bar-item"><a class="btn btn-default btn-sm"  data-toggle="modal" data-target="#changepassword"><i class="fa fa-key" aria-hidden="true"></i>&nbsp;Change password</a></div>
                <div class="w3-bar-item"><a href="../logoutservlet" class="btn btn-default btn-sm"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;Logout</a></div>
            </div> 
        </header>


        <div style=" margin-top: 20px; ">

            <div class="w3-row w3-center w3-content" style="padding-top:10px; padding-bottom: 10px;">

                <div class="w3-quarter w3-container">
                    <div class="w3-card" style="width: 150px; height: 150px;" >
                       
                        <div style="padding-top: 30px;">
                             <p>Today Tests</p>
                            <h1 style="color: red;">20</h1>
                        </div>
                    </div> 
                </div>

                <div class="w3-quarter w3-container">
                    <div class="w3-card" style="width: 150px; height: 150px;" >
                       
                          <div style="padding-top: 30px;">
                              <p>Today Completed Tests</p>
                            <h1 style="color: red;">15</h1>
                        </div>
                    </div> 
                </div>  

                <div class="w3-quarter w3-container">
                    <div class="w3-card" style="width: 150px; height: 150px;" >
                        
                          <div style="padding-top: 30px;">
                             <p> Today Pending Tests</p>
                            <h1 style="color: red;">5</h1>
                        </div>
                    </div> 
                </div>     

                <div class="w3-quarter w3-container">
                    <div class="w3-card" style="width: 150px; height: 150px;" >
                          <div style="padding-top: 30px;">
                             <p> Today cancel Tests</p>
                            <h1 style="color: red;">2</h1>
                        </div>
                    </div> 
                </div>  
            </div>

        </div> 

        <div class="container" style="margin-top: 10px;">
            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#uploadPopup">Upload report</button>
        </div>
        <div style="margin-top: 20px; ">
            <!--Appointment Table-->
            <div class="table-responsive">
                <table id="appointmenttable" class="table table-bordered table-hover table-striped container" 
                       style="width:100%;">
                    <thead class="thead-light">
                        <tr>
                            <th>Appointment Code</th>
                            <th>Patient Nic</th>
                            <th>Test</th>
                            <th>Appointment Date</th>
                            <th>Appointment Time</th>
                            <th>Appointment No</th>
                            <th>Technician</th>
                            <th>Status</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${apt:loadAppointment()}" var="appointmentModel">
                            <c:if test="${appointmentModel.status != 'canceled'}">
                                <c:if test="${appointmentModel.technician == name}">
                                    <tr>
                                        <td>${appointmentModel.appointmentCode}</td>
                                        <td>${appointmentModel.patientNic}</td>
                                        <td>${appointmentModel.test}</td>
                                        <td>${appointmentModel.appointmentDate}</td>
                                        <td>${appointmentModel.appointmentTime}</td>
                                        <td>${appointmentModel.appointmentNo}</td>
                                        <td>${appointmentModel.technician}</td>
                                        <td><strong>${appointmentModel.status}</strong></td>
                                    </tr>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </tbody> 
                </table>
            </div>
        </div>

        <!--Upload Popup-->
        <div class="modal fade" id="uploadPopup" tabindex="-1" role="dialog" aria-labelledby="uploadCenter" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document"  >
                <div class="modal-content">

                    <div class="modal-body">

                        <form method="post" class="form-group" action="../uploadreportservlet"
                              enctype="multipart/form-data">

                            <input id="appointmentCode" name="appointmentCode" style="display: none;">
                            <input id="patientNic" name="patientNic" style="display: none;">
                            <input type="file" name="report" accept="application/pdf"/>
                            <br/>
                            <br/>
                            <button type="submit" class="btn btn-primary">Upload</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="changepassword" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document" style="width: 400px; height: 600px" >
                <div class="modal-content">

                    <div class="modal-header w3-content">
                        <h5 class="modal-title w3-wide" id="exampleModalLabel">Reset password</h5>
                    </div>

                    <div class="modal-body">
                        <form action="../changepasswordservlet" method="POST" onsubmit="beforeSubmit();">

                            <div class="form-group">
                                <label for="pwd">Current Password:</label>
                                <input type="password" class="form-control" onkeyup="checkPassword();" placeholder="&#9679; &#9679; &#9679; &#9679; &#9679; &#9679; &#9679; &#9679;" 
                                       id="currentpassword" name="currentpassword" required>
                                <span id="passwordmemessage" style="color: red;" ></span>
                            </div>

                            <div class="form-group">
                                <label for="pwd">New Password:</label>
                                <input type="password" class="form-control" placeholder="&#9679; &#9679; &#9679; &#9679; &#9679; &#9679; &#9679; &#9679;" 
                                       id="newpassword" name="newpassword" onkeypress="typing();" oninvalid="validate(this);" 
                                       oninput="validate(this);" maxlength="15" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,15}$" required>
                            </div>

                            <div class="form-group">
                                <label for="pwd">Confirm New Password:</label>
                                <input type="password" class="form-control" placeholder="&#9679; &#9679; &#9679; &#9679; &#9679; &#9679; &#9679; &#9679;" 
                                       id="confirmpassword" name="confirmpassword"  oninvalid="validate(this);" 
                                       oninput="validate(this);" required>
                            </div>

                            <div class=" text-center">
                                <button type="submit" class="btn btn-primary">Confirm</button>
                            </div>

                        </form> 
                    </div>
                </div>
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
                                           $(".custom-file-input").on("change", function () {
                                               var fileName = $(this).val().split("\\").pop();
                                               $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
                                           });
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
                                                   $("#appointmentCode").val(selectRow[0]);
                                                   $("#patientNic").val(selectRow[1]);

                                               });

                                           });

                                           //            Upload popup
                                           $('#uploadPopup').on('shown.bs.modal', function () {
                                               $('#myInput').trigger('focus');
                                           });

//            Change password popup
                                           $('#changepassword').on('shown.bs.modal', function () {
                                               $('#myInput').trigger('focus');
                                           });

//            validation
                                           var newPassword;
                                           function validate(field) {

                                               if (field.name === 'newpassword') {
                                                   if (field.validity.valueMissing) {
                                                       field.setCustomValidity('Password required!');
                                                   } else if (field.validity.patternMismatch) {
                                                       field.setCustomValidity('Password must contain at leat one uppercase letter, lowercase letter, one number and should be between 8 to 15 characters!');
                                                   } else {
                                                       newPassword = field.value;
                                                       field.setCustomValidity('');
                                                   }
                                               } else if (field.name === 'confirmpassword') {
                                                   if (field.validity.valueMissing) {
                                                       field.setCustomValidity('Confirm password required!');
                                                   } else {
                                                       if (newPassword !== field.value) {
                                                           field.setCustomValidity('Password does not match!');
                                                       } else {
                                                           field.setCustomValidity('');
                                                       }
                                                   }
                                               }
                                           }

                                           function checkPassword() {
                                               var xmlhttp = new XMLHttpRequest();

                                               xmlhttp.onreadystatechange = function () {
                                                   if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                                                       if (xmlhttp.status === 200) {
                                                           document.getElementById("passwordmemessage").innerHTML = xmlhttp.responseText;
                                                       }
                                                   }
                                               };
                                               var typepassword = document.getElementById("currentpassword").value;
                                               var url = "../changepasswordservlet?pass=" + typepassword + "";
                                               xmlhttp.open("GET", url, true);
                                               xmlhttp.send();
                                           }

                                           function beforeSubmit() {
                                               var m = document.getElementById("passwordmemessage").innerHTML;

                                               if (m.trim() === "Wrong password!") {
                                                   event.preventDefault();
                                               }
                                           }

                                           function typing() {
                                               var key = event.keyCode;
                                               if (key === 32) {
                                                   event.preventDefault();
                                               }
                                           }


        </script>   


    </body>
</html>
