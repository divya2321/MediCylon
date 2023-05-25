<%-- 
    Document   : AdminNavBar
    Created on : Oct 15, 2020, 12:44:59 PM
    Author     : divsi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title>MediCylon Laboratory</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-teal.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-ios.css">  

    <style>
        html,body {font-family: "Raleway", sans-serif}
    </style>
    <body style="background-color:#F8F9F9;">

        <%
            HttpSession ses = request.getSession(false);

            String name = (String) ses.getAttribute("name");
            String job = (String) ses.getAttribute("role");

                if (job != null) {
                if (!job.equalsIgnoreCase("admin")) {
                    session.invalidate();
                    response.sendRedirect("../log_in_form.jsp");
                }
                }else{
                     session.invalidate();
                    response.sendRedirect("../log_in_form.jsp");
                }


        %>
        <nav class="w3-sidebar w3-bar-block w3-collapse w3-animate-left w3-card" style="z-index:3;width:200px;" id="mySidebar">
            <h5 class="w3-padding-64 w3-center w3-black">Welcome,<b> <br><%= name%></b></h5>
            <a style="text-decoration: none" class="w3-bar-item w3-button w3-hide-large w3-large" href="javascript:void(0)" onclick="w3_close()">Close <i class="fa fa-remove"></i></a>
            <a style="text-decoration: none" class="w3-bar-item w3-button ${param.dashboard}" href="admin_dashboard.jsp"><i class="fa fa-home" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;Dashboard</a>
            <a style="text-decoration: none" class="w3-bar-item w3-button ${param.staff}" href="admin_staff.jsp"><i class="fa fa-users" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;Employee</a>
            <a style="text-decoration: none" class="w3-bar-item w3-button ${param.user}" href="admin_user.jsp"><i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;User Accounts</a>
            <a style="text-decoration: none" class="w3-bar-item w3-button ${param.test}" href="admin_test.jsp"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;Tests</a>
            <a style="text-decoration: none" class="w3-bar-item w3-button ${param.report}" href="admin_report.jsp"><i class="fa fa-file-text" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;Reports</a>
            <a style="text-decoration: none" class="w3-bar-item w3-button" data-toggle="modal" data-target="#changepassword"><i class="fa fa-key" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;Change Password</a>
            <a style="text-decoration: none" class="w3-bar-item w3-button" href="../logoutservlet"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;Logout</a>
        </nav>

        <div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" id="myOverlay"></div>

        <div class="w3-main" style="margin-left:200px;">

            <div id="myTop" class="w3-container w3-top w3-small">
                <p><i class="fa fa-bars w3-button w3-ios-blue w3-hide-large w3-large" onclick="w3_open()"></i>
                    <span id="myIntro" class="w3-hide"></span></p>
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



        <script>
//            Change password popup
            $('#changepassword').on('shown.bs.modal', function () {
                $('#myInput').trigger('focus');
            });



            // Open and close the sidebar on medium and small screens
            function w3_open() {
                document.getElementById("mySidebar").style.display = "block";
                document.getElementById("myOverlay").style.display = "block";
            }

            function w3_close() {
                document.getElementById("mySidebar").style.display = "none";
                document.getElementById("myOverlay").style.display = "none";
            }

            // Change style of top container on scroll
            window.onscroll = function () {
                myFunction();
            };
            function myFunction() {
                if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
                    document.getElementById("myTop").classList.add("w3-card-4", "w3-animate-opacity");
                    document.getElementById("myIntro").classList.add("w3-show-inline-block");
                } else {
                    document.getElementById("myIntro").classList.remove("w3-show-inline-block");
                    document.getElementById("myTop").classList.remove("w3-card-4", "w3-animate-opacity");
                }
            }

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
