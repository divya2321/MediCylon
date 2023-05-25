<%-- 
    Document   : loginform
    Created on : Oct 16, 2020, 1:55:45 PM
    Author     : divsi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!--        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <style>


            div.main{
                background: radial-gradient(ellipse at center,  #34AADC 1%,#000000 100%);  
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#0264d6', endColorstr='#1c2b5a',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
                height:calc(100vh);
                width:100%;
            }

            [class*="fontawesome-"]:before {
                font-family: 'FontAwesome', sans-serif;
            }

            * {
                box-sizing: border-box;
                margin:0px auto;

                &:before,
                    &:after {
                    box-sizing: border-box;
                }

            }

            body {

                color: #606468;
                font: 87.5%/1.5em 'Open Sans', sans-serif;
                margin: 0;
            }

            a {
                color: #eee;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }

            input {
                border: none;
                font-family: 'Open Sans', Arial, sans-serif;
                font-size: 14px;
                line-height: 1.5em;
                padding: 0;
                -webkit-appearance: none;
            }

            p {
                line-height: 1.5em;
            }

            .clearfix {
                *zoom: 1;

                &:before,
                    &:after {
                    content: ' ';
                    display: table;
                }

                &:after {
                    clear: both;
                }

            }

            .container {
                left: 50%;
                position: fixed;
                top: 50%;
                transform: translate(-50%, -50%);
            }


            #login form{
                width: 250px;
            }
            #login, .logo{
                display:inline-block;
                width:40%;
            }
            #login{
                border-right:1px solid #fff;
                padding: 0px 22px;
                width: 59%;
            }
            .logo{
                color:#fff;
                font-size:50px;
                line-height: 125px;
            }

            #login form span.fa {
                background-color: #fff;
                border-radius: 3px 0px 0px 3px;
                color: #000;
                display: block;
                float: left;
                height: 50px;
                font-size:24px;
                line-height: 50px;
                text-align: center;
                width: 50px;
            }

            #login form input {
                height: 50px;
            }
            fieldset{
                padding:0;
                border:0;
                margin: 0;

            }
            #login form input[type="text"], input[type="password"] {
                background-color: #fff;
                border-radius: 0px 3px 3px 0px;
                color: #000;
                margin-bottom: 1em;
                padding: 0 16px;
                width: 200px;
            }

            #login form input[type="submit"] {
                border-radius: 3px;
                -moz-border-radius: 3px;
                -webkit-border-radius: 3px;
                background-color: #000000;
                color: #eee;
                font-weight: bold;
                /* margin-bottom: 2em; */
                text-transform: uppercase;
                padding: 5px 10px;
                height: 30px;
            }

            #login form input[type="submit"]:hover {
                background-color: #d44179;
            }

            #login > p {
                text-align: center;
            }

            #login > p span {
                padding-left: 5px;
            }
            .middle {
                display: flex;
                width: 600px;
            }
        </style>
    </head>
    <body>
        <div class="main">


            <div class="container">
                <center>
                    <div class="middle">
                        <div id="login">

                            <form action="loginservlet" method="POST">

                                <fieldset class="clearfix">

                                    <p ><span class="fa fa-user"></span><input type="text"  name="username" Placeholder="Username" required></p>  
                                    <p><span class="fa fa-lock"></span><input type="password" name="password"  Placeholder="Password" required></p>  

                                    <div>
                                        <span style="width:48%; text-align:left;  display: inline-block;" ><a class="small-text" href="#" data-target="#exampleModalCenter" data-toggle="modal">Forgot
                                                password?</a></span>
                                        <span style="width:50%; text-align:right;  display: inline-block;"><input type="submit" value="Sign In"></span>
                                    </div>

                                </fieldset>
                                <div class="clearfix"></div>
                            </form>

                            <div class="clearfix"></div>

                        </div>  
                        <div class="logo">MediCylon

                            <div class="clearfix"></div>
                        </div>

                    </div>
                </center>
            </div>

        </div>

        <a class="small-text" href="#" data-target="#exampleModalCenter" data-toggle="modal">Forgot
            password?</a>
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document" style="width: 400px; height: 600px" >
                <div class="modal-content">

                    <div class="modal-header w3-content">
                        <h5 class="modal-title w3-wide" id="exampleModalLabel">Reset password</h5>
                        <!--                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>-->
                    </div>

                    <div class="modal-body">
                        <form accept-charset="UTF-8" role="form" id="login-form" method="post">
                            <fieldset>
                                <div class="form-group input-group">
                                    <span class="input-group-addon w3-ios-blue">
                                        @
                                    </span>
                                    <input class="form-control" placeholder="Email" name="email" type="email" required="" autofocus="">
                                </div>
                                <div class="form-group input-group">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-lock">
                                        </i>
                                    </span>
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="" required="">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary btn-block">
                                        Access
                                    </button>
                                    <p class="help-block">
                                        <a class="pull-right text-muted" href="#" id="olvidado"><small>Forgot your password?</small></a>
                                    </p>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $('#exampleModalCenter').on('shown.bs.modal', function () {
                $('#myInput').trigger('focus')
            });
        </script>

    </body>
</html>


