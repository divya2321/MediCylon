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

    <!--Staff table-->
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
        <jsp:param name="staff" value="w3-ios-blue" />
        </jsp:include>
        <%@ include file="../headerbar.jsp" %>

        <div  style="margin-left: 200px; margin-top: 20px; padding: 10px">


            <button type="button" class="btn btn-info" data-toggle="modal" onclick="clickAddBtn();" data-target="#newStaffPopup">New Staff</button>
            <button type="button" class="btn btn-success" data-toggle="modal" onclick="clickEditBtn();" data-target="#newStaffPopup">Update</button>

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
        </div>


        <!--Staff Model-->
        <div class="modal fade" id="newStaffPopup" tabindex="-1" role="dialog" aria-labelledby="newStaffCenter" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document"  >
                <div class="modal-content">

                    <div class="modal-header w3-content">
                        <h5 class="modal-title w3-wide" id="modalLabel"></h5>

                    </div>

                    <div class="modal-body">
                        <form action="../staffservlet" method="POST" onsubmit="staffBeforeSubmit();">

                            <label id="selectStaffId" >Staff ID <strong id="staffid"></strong></label>
                            <input name="updatestaffid" id="updatestaffid" style="display: none" />
                            <div class="form-group">
                                <label for="staffNic">NIC:</label>
                                <input type="text" class="form-control" id="nic" name="nic" onkeyup="checkNic();"  oninvalid="validate(this);" 
                                       oninput="validate(this);" required pattern="^([0-9]{9}[x|X|v|V]|[0-9]{12})$">
                                 <span id="nicmessage" style="color: red;" ></span>
                            </div>

                            <div class="form-group">
                                <label for="fname">First Name:</label>
                                <input type="text" class="form-control" id="fname" name="fname" oninvalid="validate(this);" 
                                       oninput="validate(this);" required maxlength="45" pattern="[A-Za-z]{2,}" >
                            </div>

                            <div class="form-group">
                                <label for="mname">Middle Name:</label>
                                <input type="text" class="form-control" id="mname" name="mname" oninvalid="validate(this);" 
                                       oninput="validate(this);" maxlength="45" pattern="[A-Za-z]{2,}">
                            </div>

                            <div class="form-group">
                                <label for="lname">Last Name:</label>
                                <input type='text' class="form-control" id="lname" name="lname" oninvalid="validate(this);" 
                                       oninput="validate(this);" required maxlength="45" pattern="[A-Za-z]{2,}">
                            </div>

                            <div class="form-group">
                                <label for="contact1">Contact1:</label>
                                <input type="text" class="form-control" id="contact1" name="contact1" oninvalid="validate(this);" 
                                       oninput="validate(this);" required pattern="^(?:0|94|\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|5|6|7|8)\d)\d{6}$">
                            </div>

                            <div class="form-group">
                                <label for="contact2">Contact2:</label>
                                <input type="text" class="form-control" id="contact2" name="contact2" oninvalid="validate(this);" 
                                       oninput="validate(this);" >
                            </div>

                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" class="form-control" id="email" name="email" oninvalid="validate(this);" 
                                       oninput="validate(this);" required pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$">
                            </div>


                            <div class="form-group">
                                <label for="job">Job role:</label>
                                <select class="form-control browser-default custom-select empSelect" data-live-search="true" id="job" name="job">
                                    <option value="admin" data-tokens="Admin">Admin</option>
                                    <option value="receptionist" data-tokens="Receptionist">Receptionist</option>
                                    <option value="technician" data-tokens="Technician">Technician</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="no">No:</label>
                                <input type="text" class="form-control" id="no" name="no" oninvalid="validate(this);" 
                                       oninput="validate(this);" required maxlength="45">
                            </div>

                            <div class="form-group">
                                <label for="street1">Street1:</label>
                                <input type="text" class="form-control" id="street1" name="street1" oninvalid="validate(this);" 
                                       oninput="validate(this);" required maxlength="45">
                            </div>

                            <div class="form-group">
                                <label for="street2">Street2:</label>
                                <input type="text" class="form-control" id="street2" name="street2" maxlength="45">
                            </div>

                            <div class="form-group">
                                <label for="city">City:</label>
                                <input type="text" class="form-control" id="city" name="city" oninvalid="validate(this);" 
                                       oninput="validate(this);" required maxlength="45">
                            </div>

                            <div id="statusdiv">
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




        <!--Staff Table-->
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
//staff Model
                            $('#newStaffPopup').on('shown.bs.modal', function () {
                                $('#myInput').trigger('focus');
                            });
                            
                            var updateNic = "";
//Staff Table
                            $(document).ready(function () {
                                var table = $('#staffTable').DataTable({
                                    lengthChange: false,
                                    paging: false,
                                    info: false,
                                    select: true

                                });

                                $('#staffTable tbody').on('click', 'tr', function () {
                                    var selectRow = table.rows(this).data()[0];
                                    clearTestFields();
                                    $("#staffid").html(selectRow[0]);
                                    $("#updatestaffid").val(selectRow[0]);
                                    $("#nic").val(selectRow[1]);
                                    updateNic = selectRow[1];

                                    var staffName = selectRow[2].split(" ");

                                    if (staffName.length === 3) {
                                        $("#fname").val(staffName[0]);
                                        $("#mname").val(staffName[1]);
                                        $("#lname").val(staffName[2]);
                                    } else {
                                        $("#fname").val(staffName[0]);
                                        $("#lname").val(staffName[1]);
                                    }

                                    var staffContact = selectRow[3];

                                    if (staffContact.indexOf("/") !== -1) {
                                        var contact = staffContact.split("/");
                                        $("#contact1").val(contact[0]);
                                        $("#contact2").val(contact[1]);
                                    } else {
                                        $("#contact1").val(staffContact);
                                    }

                                    $("#email").val(selectRow[4]);

                                    var staffAddress = selectRow[5].split(",");

                                    if (staffAddress.length === 4) {
                                        $("#no").val(staffAddress[0]);
                                        $("#street1").val(staffAddress[1]);
                                        $("#street2").val(staffAddress[2]);
                                        $("#city").val(staffAddress[3]);
                                    } else {
                                        $("#no").val(staffAddress[0]);
                                        $("#street1").val(staffAddress[1]);
                                        $("#city").val(staffAddress[2]);
                                    }
                                    $("#job").val(selectRow[6]);

                                    var status = selectRow[7];

                                    if (status === "active") {
                                        $("#active").prop("checked", true);
                                    } else {
                                        $("#deactive").prop("checked", true);
                                    }

                                });

                            });

                            function clearTestFields() {
                                $("#updatestaffid").val("");
                                $("#staffid").html("");
                                $("#nicmessage").html("");
                                updateNic = "";
                                $("#nic").val("");
                                $("#fname").val("");
                                $("#mname").val("");
                                $("#lname").val("");
                                $("#contact1").val("");
                                $("#contact2").val("");
                                $("#email").val("");
                                $("#job").val($("#job option:first").val());
                                $("#no").val("");
                                $("#street1").val("");
                                $("#street2").val("");
                                $("#city").val("");
                                $("#active").prop("checked", true);
                            }


                            function checkNic() {
                                var xmlhttp = new XMLHttpRequest();

                                xmlhttp.onreadystatechange = function () {
                                    if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                                        if (xmlhttp.status === 200) {
                                            document.getElementById("nicmessage").innerHTML = xmlhttp.responseText;
                                        }
                                    }
                                };
                                var typenic = document.getElementById("nic").value;
                                var url = "../staffservlet?nic=" + typenic + "&oldnic="+updateNic+"";
                                xmlhttp.open("GET", url, true);
                                xmlhttp.send();
                            }

                            function staffBeforeSubmit() {
                                var m = document.getElementById("nicmessage").innerHTML;

                                if (m.trim() === "This nic already have") {
                                    event.preventDefault();
                                }
                            }
//Validation            
                            function validate(field) {

                                if (field.name === 'nic') {
                                    if (field.validity.valueMissing) {
                                        field.setCustomValidity('Nic required!');
                                    } else if (field.validity.patternMismatch) {
                                        field.setCustomValidity('Incorrect nic!');
                                    } else {
                                        field.setCustomValidity('');
                                    }
                                }

                                if (field.name === 'fname') {
                                    if (field.validity.valueMissing) {
                                        field.setCustomValidity('First name required!');
                                    } else if (field.validity.patternMismatch) {
                                        field.setCustomValidity('Incorrect name!');
                                    } else {
                                        field.setCustomValidity('');
                                    }
                                }

                                if (field.name === 'mname') {
                                    if (field.validity.patternMismatch) {
                                        field.setCustomValidity('Incorrect name!');
                                    } else {
                                        field.setCustomValidity('');
                                    }
                                }

                                if (field.name === 'lname') {
                                    if (field.validity.valueMissing) {
                                        field.setCustomValidity('Last required!');
                                    } else if (field.validity.patternMismatch) {
                                        field.setCustomValidity('Incorrect name!');
                                    } else {
                                        field.setCustomValidity('');
                                    }
                                }

                                if (field.name === 'contact1') {
                                    if (field.validity.valueMissing) {
                                        field.setCustomValidity('Contact number required!');
                                    } else if (field.validity.patternMismatch) {
                                        field.setCustomValidity('Incorrect contact number!');
                                    } else {
                                        field.setCustomValidity('');
                                    }
                                }

                                if (field.name === 'contact2') {
                                    if (field.validity.patternMismatch) {
                                        field.setCustomValidity('Incorrect contact number!');
                                    } else {
                                        field.setCustomValidity('');
                                    }
                                }

                                if (field.name === 'email') {
                                    if (field.validity.valueMissing) {
                                        field.setCustomValidity('Email required!');
                                    } else if (field.validity.typeMismatch) {
                                        field.setCustomValidity('Incorrect email!');
                                    } else {
                                        field.setCustomValidity('');
                                    }
                                }


                                if (field.name === 'no') {
                                    if (field.validity.valueMissing) {
                                        field.setCustomValidity('Address no required!');
                                    } else {
                                        field.setCustomValidity('');
                                    }
                                }

                                if (field.name === 'street1') {
                                    if (field.validity.valueMissing) {
                                        field.setCustomValidity('Address street1 required!');
                                    } else {
                                        field.setCustomValidity('');
                                    }
                                }

                                if (field.name === 'city') {
                                    if (field.validity.valueMissing) {
                                        field.setCustomValidity('Address city required!');
                                    } else {
                                        field.setCustomValidity('');
                                    }
                                }

                                return true;
                            }

                            function clickAddBtn() {
                                clearTestFields();
                                document.getElementById("selectStaffId").style.display = "none";
                                document.getElementById("statusdiv").style.display = "none";
                                document.getElementById("modalLabel").innerHTML = "New staff";
                            }

                            function clickEditBtn() {

                                document.getElementById("selectStaffId").style.display = "inline";
                                document.getElementById("statusdiv").style.display = "inline";
                                document.getElementById("modalLabel").innerHTML = "Update staff";
                            }
        </script>   

    </body>

</html> 

