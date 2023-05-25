<%-- 
    Document   : AdminHome
    Created on : Oct 15, 2020, 12:18:52 AM
    Author     : divsi
--%>

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
        <jsp:include page="admin_navbar.jsp" >
        <jsp:param name="dashboard" value="w3-ios-blue" />
        </jsp:include>
        <%@ include file="../headerbar.jsp" %>


        <div style="margin-left: 200px; margin-top: 20px; ">

                <div class="w3-row w3-center w3-content" style="padding-top:10px; padding-bottom: 10px;">

                    <div class="w3-quarter w3-container">
                    <div class="w3-card" style="width: 150px; height: 150px;" >
                        <div style="padding-top: 30px;">
                        <p>Today Patients</p>
                        <h1 style="color: red;">50</h1>
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
                        <p>Today Completed Tests</p>
                        <h1 style="color: red;">15</h1>
                        </div>
                    </div> 
                    </div>     
                    
                      <div class="w3-quarter w3-container">
                    <div class="w3-card" style="width: 150px; height: 150px;" >
                          <div style="padding-top: 30px;">
                        <p>Today Pending Tests</p>
                        <h1 style="color: red;">5</h1>
                        </div>
                    </div> 
                    </div>  
                </div>
            
            
                <div class="w3-container w3-content w3-col">
                    <div id="curve_chart" class="w3-content" style="width: 1500px; height: 400px; size: 20vw"></div>
                </div>


            
        </div> 


        <!--Google chart-->

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Day', 'Income', 'Appointmments'],
                    ['Monday', 10000, 400],
                    ['Tuesday', 8500, 460],
                    ['Wednesday', 11200, 1120],
                    ['Thursday', 7850, 540],
                    ['Friday', 14300, 540],
                    ['Saturday', 4900, 540],
                    ['Sunday', 23000, 540]
                ]);

                var options = {
                    title: 'Lab Income and Appointments of the week',
                    curveType: 'function',
                    legend: {position: 'bottom'}
                };

                var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

                chart.draw(data, options);
            }


        </script>

    </body>
</html> 

