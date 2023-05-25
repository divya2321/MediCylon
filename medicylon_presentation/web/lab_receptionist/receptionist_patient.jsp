<%-- 
    Document   : ReceptionistPatient
    Created on : Oct 18, 2020, 12:05:24 PM
    Author     : divsi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ptn" uri="/WEB-INF/tlds/ConFunction" %>
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

    <!--Patient table-->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://editor.datatables.net/extensions/Editor/css/editor.bootstrap4.min.css"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <style>
        html,body {font-family: "Raleway", sans-serif}
    </style>

    <body> 
         <jsp:include page="receptionist_navbar.jsp" >
        <jsp:param name="patient" value="w3-ios-blue" />
        </jsp:include>
        <%@ include file="../headerbar.jsp" %>

        <div  style="margin-left: 200px; margin-top: 20px; padding: 10px">


            <button type="button" class="btn btn-info" data-toggle="modal" onclick="clickAddBtn();" data-target="#newPatientPopup">New Patient</button>
            <button type="button" class="btn btn-success" data-toggle="modal" onclick="clickEditBtn();" data-target="#newPatientPopup">Update</button>


            <!--Patient Table-->
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
        </div>

        <!--Patient Model-->
        <div class="modal fade" id="newPatientPopup" tabindex="-1" role="dialog" aria-labelledby="newPatientCenter" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document" >
                <div class="modal-content">

                    <div class="modal-header w3-content">
                        <h5 class="modal-title w3-wide" id="modalLabel"></h5>

                    </div>

                    <div class="modal-body">
                        <form action="../patientservlet" method="POST" onsubmit="patientBeforeSubmit();">
                            <input id="updateNic" name="updateNic" style="display: none;" />
                            <div class="form-group">
                                <label for="staffNic">NIC:</label>
                                <input type="text" class="form-control" id="nic" name="nic" onkeyup="checkNic();" oninvalid="validate(this);" 
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
                                       oninput="validate(this);" pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$">
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




        <!--Patient table-->
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
//Patient Model
                $('#newPatientPopup').on('shown.bs.modal', function () {
                    $('#myInput').trigger('focus');
                });

//Patient Table
                $(document).ready(function () {
                    var table = $('#patientTable').DataTable({
                        lengthChange: false,
                        paging: false,
                        info: false,
                        select: true,
                        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]]

                    });

                    $('#patientTable tbody').on('click', 'tr', function () {
                        var selectRow = table.rows(this).data()[0];
                        clearTestFields();
                        $("#nic").val(selectRow[0]);
                        $("#updateNic").val(selectRow[0]);

                        var patientName = selectRow[1].split(" ");

                        if (patientName.length === 3) {
                            $("#fname").val(patientName[0]);
                            $("#mname").val(patientName[1]);
                            $("#lname").val(patientName[2]);
                        } else {
                            $("#fname").val(patientName[0]);
                            $("#lname").val(patientName[1]);
                        }

                        var patientContact = selectRow[2];

                        if (patientContact.indexOf("/") !== -1) {
                            var contact = patientContact.split("/");
                            $("#contact1").val(contact[0]);
                            $("#contact2").val(contact[1]);
                        } else {
                            $("#contact1").val(patientContact);
                        }

                        $("#email").val(selectRow[3]);

                    });

                });

                function clearTestFields() {
                    $("#nic").val("");
                    $("#updateNic").val("");
                    $("#nicmessage").html("");
                    $("#fname").val("");
                    $("#mname").val("");
                    $("#lname").val("");
                    $("#contact1").val("");
                    $("#contact2").val("");
                    $("#email").val("");
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
                    var updatenic = document.getElementById("updateNic").value;
                    var url = "../patientservlet?nic=" + typenic + "&oldnic=" + updatenic + "";
                    xmlhttp.open("GET", url, true);
                    xmlhttp.send();
                }

                function patientBeforeSubmit() {
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
                    document.getElementById("modalLabel").innerHTML = "New Patient";
                }

                function clickEditBtn() {

                    document.getElementById("modalLabel").innerHTML = "Update Patient";
                }


        </script>   

    </body>

</html> 