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

    <!--Test table-->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.3.1/css/select.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://editor.datatables.net/extensions/Editor/css/editor.bootstrap4.min.css"/>


    <style>
        html,body {font-family: "Raleway", sans-serif}
    </style>

    <style>
        html,body {font-family: "Raleway", sans-serif}
    </style>

    <body> 
        <jsp:include page="admin_navbar.jsp" >
        <jsp:param name="test" value="w3-ios-blue" />
        </jsp:include>
        <%@ include file="../headerbar.jsp" %>

        <div  style="margin-left: 200px; margin-top: 20px; padding: 10px">


            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#newTest" onclick="addTest();">New Test</button>
            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#newTest" onclick="updateTest();">Update Test</button>

            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#newCategory">Test Category</button>

            <!--Test Table-->
            <div class="table-responsive">
                <table id="testTable" class="table  table-bordered table-hover table-striped container" 
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
        </div>

        <!--Test Model-->
        <div class="modal fade" id="newTest" tabindex="-1" role="dialog" aria-labelledby="newStaffCenter" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document"  >
                <div class="modal-content">

                    <div class="modal-header w3-content">
                        <h5 class="modal-title w3-wide" id="testtitle"></h5>

                    </div>

                    <div class="modal-body">
                        <form action="../testservlet?name=add" method="POST" onsubmit="testBeforeSubmit();">

                            <input  id="oldtest" name="oldtest" style="display: none;">
                            <div class="form-group">
                                <label for="testname">Test Name:</label>
                                <input type="text" class="form-control" id="testname" name="testname" onkeyup="checkTest();" required maxlength="100">
                                <span id="testmessage" style="color: red;" ></span>
                            </div>

                            <div class="form-group">
                                <label for="price">Price:</label>
                                <input type="number" class="form-control" id="price" name="price" required>
                            </div>

                            <div class="form-group">
                                <label for="testcategory">Test Category:</label>
                                <select class="form-control browser-default custom-select empSelect"  id="testcategory" required name="testcategory">
                                    <c:forEach  var="cate" items="${stf:loadTestCategory()}" >
                                        <option value="${cate.testCategory}">${cate.testCategory}</option>
                                    </c:forEach>
                                </select>
                            </div>



                            <div class=" text-center">
                                <button type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close">Cancel</button>
                                <button type="submit" class="btn btn-primary">Save</button>
                            </div>

                        </form> 
                    </div>
                </div>
            </div>
        </div>

        <!--Category Model-->
        <div class="modal fade" id="newCategory" tabindex="-1" role="dialog" aria-labelledby="newStaffCenter" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document"  >
                <div class="modal-content">

                    <div class="modal-header w3-content">
                        <h5 class="modal-title w3-wide" id="exampleModalLabel">Test Category</h5>

                    </div>

                    <div class="modal-body">

                        <form action="../testservlet?name=cate" method="POST" onsubmit="categoryBeforeSubmit();">


                            <input  id="oldcategory" name="oldcategory" style="display: none;" >
                            <div class="form-group">
                                <label for="category">Category:</label>
                                <input type="text" class="form-control" onkeyup="checkCategory();" id="category" name="category" required maxlength="45">
                                <span id="categorymessage" style="color: red;" ></span>
                            </div>

                            <div class="form-group">
                                <label for="roomno">Room No:</label>
                                <input type="text" class="form-control" id="roomno" name="roomno" required maxlength="5">
                            </div>

                            <div class="form-group">
                                <label for="maximumcount">Maximum Count:</label>
                                <input type="number" class="form-control" id="maximumcount" name="maximumcount" required>
                            </div>

                            <div class="form-group">
                                <label for="technician">Technician:</label>
                                <select class="form-control browser-default custom-select empSelect" id="technician" name="technician" required>
                                    <c:forEach  var="staff" items="${stf:loadTechnicianIdName()}" >
                                        <option value="${staff.name}">${staff.name}</option>
                                    </c:forEach>
                                </select>
                            </div>


                            <div class=" text-center">
                                <button type="submit" class="btn btn-danger" onclick="addCategoryClick();">Save</button>
                                <button type="submit" class="btn btn-primary">Update</button>
                            </div>

                        </form>    
                        <br/>
                        <br/>
                        <!--Category Table-->
                        <div class="table-responsive">
                            <table id="categoryTable" class="table  table-bordered table-hover table-striped container" 
                                   style="width:100%;">
                                <thead class="thead-light">
                                    <tr>
                                        <th>Category</th>
                                        <th>Room No</th>
                                        <th>Maximum Count</th>
                                        <th>Technician</th>
                                    </tr>
                                </thead>


                                <tbody>
                                    <c:forEach items="${stf:loadTestCategory()}" var="testModel">
                                        <tr>
                                            <td>${testModel.testCategory}</td>
                                            <td>${testModel.roomNo}</td>
                                            <td>${testModel.testPerDay}</td>
                                            <td>${testModel.staffName}</td>
                                        </tr>
                                    </c:forEach> 
                                </tbody> 
                            </table>
                        </div>

                        <div class=" text-center">
                            <button type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close">Cancel</button>
                        </div>


                    </div>
                </div>
            </div>
        </div>




        <!--Test table-->
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

                                    $('#newTest').on('shown.bs.modal', function () {
                                        $('#myInput').trigger('focus');
                                    });

                                    $('#newCategory').on('shown.bs.modal', function () {
                                        $('#myInput').trigger('focus');
                                    });


                                    //Test Table
                                    $(document).ready(function () {
                                        var table = $('#testTable').DataTable({
                                            lengthChange: false,
                                            paging: false,
                                            info: false,
                                            select: true

                                        });

                                        $('#testTable tbody').on('click', 'tr', function () {
                                            var selectRow = table.rows(this).data()[0];
                                            $("#testname").val(selectRow[0]);
                                            $("#oldtest").val(selectRow[0]);
                                            $("#price").val(selectRow[1]);
                                            $("#testcategory").val(selectRow[2]);

                                        });
                                    });

                                    //Test category Table
                                    $(document).ready(function () {
                                        var table = $('#categoryTable').DataTable({
                                            lengthChange: false,
                                            paging: false,
                                            info: false,
                                            select: true

                                        });

                                        $('#categoryTable tbody').on('click', 'tr', function () {
                                            clearTestField();
                                            var selectRow = table.rows(this).data()[0];
                                            $("#category").val(selectRow[0]);
                                            $("#oldcategory").val(selectRow[0]);
                                            $("#roomno").val(selectRow[1]);
                                            $("#maximumcount").val(selectRow[2]);
                                            $("#technician").val(selectRow[3]);

                                        });
                                    });

                                    function addCategoryClick() {
                                        document.getElementById("oldcategory").value = "";
                                    }

                                    function checkCategory() {
                                        var xmlhttp = new XMLHttpRequest();

                                        xmlhttp.onreadystatechange = function () {
                                            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                                                if (xmlhttp.status === 200) {
                                                    document.getElementById("categorymessage").innerHTML = xmlhttp.responseText;
                                                }
                                            }
                                        };
                                        var typeCategory = document.getElementById("category").value;
                                        var oldcate = document.getElementById("oldcategory").value;
                                        var url = "../testservlet?name=cate&category=" + typeCategory + "&oldcate=" + oldcate + "";
                                        xmlhttp.open("GET", url, true);
                                        xmlhttp.send();
                                    }

                                    function checkTest() {
                                        var xmlhttp = new XMLHttpRequest();

                                        xmlhttp.onreadystatechange = function () {
                                            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                                                if (xmlhttp.status === 200) {
                                                    document.getElementById("testmessage").innerHTML = xmlhttp.responseText;
                                                }
                                            }
                                        };
                                        var typeTest = document.getElementById("testname").value;
                                        var oldtest = document.getElementById("oldtest").value;
                                        var url = "../testservlet?name=test&test=" + typeTest + "&oldtest=" + oldtest + "";
                                        xmlhttp.open("GET", url, true);
                                        xmlhttp.send();
                                    }

                                    function categoryBeforeSubmit() {
                                        var m = document.getElementById("categorymessage").innerHTML;

                                        if (m.trim() === "This category already have") {
                                            event.preventDefault();
                                        }
                                    }

                                    function addTest() {
                                        clearTestField();
                                        document.getElementById("testtitle").innerHTML = "New Test";
                                    }

                                    function updateTest() {
                                        document.getElementById("testtitle").innerHTML = "Update Test";
                                    }

                                    function clearTestField() {
                                        $("#testcategory").val($("#testcategory option:first").val());
                                        $("#testmessage").html("");
                                        $("#oldtest").val("");
                                        $("#testname").val("");
                                        $("#price").val("");
                                    }

                                    function testBeforeSubmit() {
                                        var m = document.getElementById("testmessage").innerHTML;
                                        
                                        if (m.trim() === "This test already have") {
                                            event.preventDefault();
                                        }
                                    }

        </script>


    </body>

</html> 

