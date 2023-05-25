<%-- 
    Document   : AdminHome
    Created on : Oct 15, 2020, 12:18:52 AM
    Author     : divsi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stf" uri="/WEB-INF/tlds/ConFunction" %>


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

    <!--User table-->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://editor.datatables.net/extensions/Editor/css/editor.bootstrap4.min.css"/>


    <style>
        html,body {font-family: "Raleway", sans-serif}
    </style>

    <body> 
        <jsp:include page="admin_navbar.jsp" >
        <jsp:param name="user" value="w3-ios-blue" />
        </jsp:include>
        <%@ include file="../headerbar.jsp" %>

        <div  style="margin-left: 200px; margin-top: 20px; padding: 10px">


            <button type="button" class="btn btn-info"  data-toggle="modal" data-target="#newUserPopup">New User</button>
            <button type="button" class="btn btn-success"  data-toggle="modal" data-target="#updateUserPopup">Update</button>


            <table id="userTable" class="table  table-bordered table-hover table-striped container table-responsive" 
                   style="width:100%;">
                <thead class="thead-light">
                    <tr>
                        <th>Staff ID</th>
                        <th>Nic</th>
                        <th>Name</th>
                        <th>Username</th>
                        <th>Availibility</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${stf:loadUser()}" var="userModel">
                        <tr>
                            <td>${userModel.staffId}</td>
                            <td>${userModel.staffNic}</td>
                            <td>${userModel.staffName}</td>
                            <td>${userModel.username}</td>
                            <td>${userModel.status}</td>
                        </tr>
                    </c:forEach>
                </tbody> 
            </table>
        </div>

        <!--User Model-->
        <div class="modal fade" id="newUserPopup" tabindex="-1" role="dialog" aria-labelledby="newStaffCenter" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document" >
                <div class="modal-content">

                    <div class="modal-header w3-content">
                        <h5 class="modal-title w3-wide" id="modalLabel">New user account</h5>

                    </div>

                    <div class="modal-body">
                        <form action="../userservlet?name=add" method="POST" onsubmit="beforeSubmit();">
                            
                                <div class="form-group">
                                    <label for="staff">Staff</label>
                                    <select class="form-control browser-default custom-select" required id="staff" name="staff" oninvalid="validate(this);" 
                                       oninput="validate(this);">
                                        <c:forEach  var="staff" items="${stf:loadStaffIdNic()}" >
                                            <option value="${staff.nic}">${staff.nic}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                           
                                <div class="form-group">
                                    <label for="username">Username:</label>
                                    <input type="text" class="form-control" id="username" name="username" onkeyup="checkUserName();" onkeypress="typing();" oninvalid="validate(this);" 
                                           oninput="validate(this);" maxlength="15" pattern="^(?=.*?[a-z])(?=.*?[0-9]).{8,15}$" required  >
                                    <span id="usernamemessage" style="color: red;" ></span>
                                </div>


                            <div class="form-group">
                                <label for="password">Password:</label>
                                <input type="password" class="form-control" id="password" name="password"  onkeypress="typing();" oninvalid="validate(this);" 
                                       oninput="validate(this);" maxlength="15" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,15}$" required>
                            </div>

                            <div class="form-group">
                                <label for="confirmpassword">Confirm Password:</label>
                                <input type="password" class="form-control" id="confirmpassword" onkeypress="typing();" name="confirmpassword" oninvalid="validate(this);" 
                                       oninput="validate(this);" required>
                            </div>


                            <div class=" text-center">
                                <button type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close">Cancel</button>
                                <button type="submit" class="btn btn-primary">Confirm</button>
                            </div>

                        </form> 
                    </div>
                </div>
            </div>
        </div>

        
         <!--User Model-->
        <div class="modal fade" id="updateUserPopup" tabindex="-1" role="dialog" aria-labelledby="updateStaffCenter" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document" >
                <div class="modal-content">

                    <div class="modal-header w3-content">
                        <h5 class="modal-title w3-wide" id="modalLabel"> Update user account</h5>

                    </div>

                    <div class="modal-body">
                        <form action="../userservlet?name=update" method="POST">
                            <input id="updatestaffid" name="updatestaffid" style="display: none;"/>

                            <span>Username: <strong id="updateusername"></strong></span>

                            <br/>
                            <br/>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" id="active" class="form-check-input" checked value="active" name="status">Active
                                    </label>
                                </div>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" id="deactive" class="form-check-input" value="deactive" name="status">Deactive
                                    </label>
                                </div>
   <br/>   <br/>
                            <div class=" text-center">
                                <button type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close">Cancel</button>
                                <button type="submit" class="btn btn-primary">Confirm</button>
                            </div>

                        </form> 
                    </div>
                </div>
            </div>
        </div>



        <!--User table-->
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
//User Model
                                    $('#newUserPopup').on('shown.bs.modal', function () {
                                        $('#myInput').trigger('focus');
                                    });
                                     $('#updateUserPopup').on('shown.bs.modal', function () {
                                        $('#myInput').trigger('focus');
                                    });

                                    //User Table
                                    $(document).ready(function () {
                                        var table = $('#userTable').DataTable({
                                            lengthChange: false,
                                            paging: false,
                                            info: false,
                                            select: true

                                        });

                                        $('#userTable tbody').on('click', 'tr', function () {
                                            var selectRow = table.rows(this).data()[0];
                                            clearTestFields();
                                            $("#updatestaffid").val(selectRow[0]);
                                            $("#updateusername").html(selectRow[3]);
                                            var status = selectRow[4];
                                            if (status === "active") {
                                                $("#active").prop("checked", true);
                                            } else {
                                                $("#deactive").prop("checked", true);
                                            }

                                        });
                                    });


                                    function clearTestFields() {
                                        $("#staff").val($("#staff option:first").val());
                                        $("#usernamemessage").html("");
                                        $("#username").val("");
                                        $("#password").val("");
                                        $("#confirmpassword").val("");
                                        $("#active").prop("checked", true);
                                    }


//            Validation
                                    var newPassword;
                                    function validate(field) {

                                        if (field.name === 'username') {
                                            if (field.validity.valueMissing) {
                                                field.setCustomValidity('Username required!');
                                            } else if (field.validity.patternMismatch) {
                                                field.setCustomValidity('Username must contain at leat one letter, one number and should be between 8 to 15 characters!');
                                            } else {
                                                field.setCustomValidity('');
                                            }
                                        } else if (field.name === 'password') {
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
                                        }else if (field.name === 'staff') {
                                            if (field.validity.valueMissing) {
                                                field.setCustomValidity('Staff required!');
                                            }else{
                                                  field.setCustomValidity('');
                                            }
                                        }
                                    }


                                    function checkUserName() {
                                        var xmlhttp = new XMLHttpRequest();

                                        xmlhttp.onreadystatechange = function () {
                                            if (xmlhttp.readyState == XMLHttpRequest.DONE) {
                                                if (xmlhttp.status == 200) {
                                                    document.getElementById("usernamemessage").innerHTML = xmlhttp.responseText;
                                                }
                                            }
                                        };
                                        var typeUsername = document.getElementById("username").value;
                                        var url = "../userservlet?username=" + typeUsername + "";
                                        xmlhttp.open("GET", url, true);
                                        xmlhttp.send();
                                    }

                                    function beforeSubmit() {
                                        var m = document.getElementById("usernamemessage").innerHTML;

                                        if (m.trim() === "This username alrady have") {
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

