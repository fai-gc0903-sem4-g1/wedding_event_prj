<%-- 
    Document   : join
    Created on : Aug 31, 2015, 7:44:31 PM
    Author     : SON
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../bundle/bootstrap.jsp"/>
<jsp:include page="../bundle/jstl.jsp"/>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>Wellcome to Matrimony</title>
        <meta name="generator" content="Bootply" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css" rel="stylesheet">
        <!--[if lt IE 9]>
                <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <style>
        body{
            background-color: #110022;
            color:#ddccee
        }
        #registerForm{
            margin-top: -21%;
            margin-left: 65%;
        }
        #loginWith{
            font-size: 17px;
            margin: 4% -6%;
        }
    </style>
    <body>
        <div class="row">

            <div class="col-lg-12 text-center v-center">

                <h2>Hello Matrimony</h2>
                <p class="lead">A sign-up free</p>
            </div>

        </div> <!-- /row -->
        <div class="row">

            <div id="loginWith" class="col-lg-12 text-center v-center" style="font-size:37pt;">
                <label style="font-size:18px;">Login with</label>
                <a href="#"><i class="icon-google-plus"></i></a> <a href="#"><i class="icon-facebook"></i></a>  <a href="#"><i class="icon-twitter"></i></a> <a href="#"><i class="icon-github"></i></a> <a href="#"><i class="icon-pinterest"></i></a>
            </div>

        </div>
        <div class="container-fluid col-lg-12" style="color:#ddccee">
            <div class="row">
                <form id="loginForm" action="/wedding_event_prj/account/qlogin" method="POST"
                      class="form-horizontal col-md-4">
                    <div class="form-group">
                        <!--<label class="col-sm-4 control-label">Tên đăng nhập</label>-->
                        <div class="col-sm-offset-2 col-sm-8">
                            <input class="form-control" type="text" value="${cookie.cUsername.value}" name="username"
                                   placeholder="Tên tài khoản"></input>
                        </div>
                    </div>

                    <div class="form-group">
                        <!--<label class="col-sm-4 control-label">Mật khẩu</label>-->
                        <div class="col-sm-offset-2 col-sm-8">
                            <input class="form-control" type="password" value="${cookie.cPassword.value}" name="passwordHash"
                                   placeholder="Mật khẩu" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2">
                            <div class="checkbox">
                                <label><input type="checkbox" name="rememberMe" /> Remember me</label>
                                <a class="col-sm-offset-1" style="color:#ffffff" href="/ShoppingAssignment/account/recover-account.jsp">Lấy lại mật khẩu</a>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                            <input class="btn btn-primary" type="submit" value="Đăng nhập" />
                        </div>
                    </div>
                </form>


                <form id="registerForm" action="../account/qregister"
                      onsubmit="return validationForm()" method="post" class="form-horizontal col-md-4">
                    <h2>Đăng ký</h2><br/>
                    <div style="display: none;" id="myAlert" class="alert alert-danger" role="alert"></div>

                    <div class="form-group">
                        <label style="color: red;" class="control-label col-sm-1">(*)</label>
                        <div class="col-sm-3">
                            <input id="firstName" class="form-control" type="text" name="fullName" value=""
                                   placeholder="First"></input>
                        </div>
                        <div class="col-sm-3">
                            <input id="firstName" class="form-control" type="text" name="fullName" value=""
                                   placeholder="Middle"></input>
                        </div>
                        <div class="col-sm-3">
                            <input id="firstName" class="form-control" type="text" name="fullName" value=""
                                   placeholder="Last"></input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="color: red;" class="control-label col-sm-1">(*)</label>
                        <div class="col-sm-9 has-feedback has-feedback-left">
                            <img style="display: none; padding: 6px 6px;" class="form-control-feedback"
                                 src="resources/img/loading.gif" id="userChecking"></img>
                            <i id="userOk" style="display: none; color: green;"
                               class="form-control-feedback glyphicon glyphicon-ok"></i>
                            <i id="userAlready" style="display: none; color: red"
                               class="form-control-feedback glyphicon glyphicon-exclamation-sign"></i>
                            <input id="username" onkeyup="userChecker()" class="form-control" type="text" name="username"
                                   placeholder="Tên người dùng"></input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="color: red;" class="control-label col-sm-1">(*)</label>
                        <div class="col-sm-9">
                            <input id="password" class="form-control" type="password" name="password" placeholder="Mật khẩu"></input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="color: red;" class="control-label col-sm-1">(*)</label>
                        <div class="col-sm-9">
                            <input id="rePass" class="form-control" type="password" placeholder="Nhập lại mật khẩu"></input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="color: red;" class="control-label col-sm-1">(*)</label>
                        <div class="col-sm-9 has-feedback has-feedback-left">
                            <img style="display: none; padding: 6px 6px;" class="form-control-feedback"
                                 src="resources/img/loading.gif" id="emailChecking"></img>
                            <i id="emailOk" style="display: none; color: green;"
                               class="form-control-feedback glyphicon glyphicon-ok"></i>
                            <i id="emailAlready" style="display: none; color: red;"
                               class="form-control-feedback glyphicon glyphicon-exclamation-sign"></i>
                            <input id="email" onkeyup="" class="form-control" type="email" name="email" placeholder="Email"></input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="color: red;" class="control-label col-sm-1"></label>
                        <div class="col-sm-9">
                            <input id="phoneNumber" class="form-control" type="text" name="phoneNumber"
                                   placeholder="Phone number"></input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="color: red;" class="control-label col-sm-1"></label>
                        <div class="col-sm-9">
                            <label class="control-label">Ngày sinh</label>
                        </div>
                        <br/><br/>

                        <label style="color: red;" class="control-label col-sm-1">(*)</label>
                        <div class="col-sm-9">
                            <input id="birthday" class="form-control" type="date" min="1800-01-01" max="currentDate()"
                                   name="birthday" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="color: red;" class="control-label col-sm-1"></label>
                        <div class="col-sm-3">
                            <label class="control-label">Giới tính</label>
                        </div>
                        <br/>

                        <div style="font-size: 17px;" class="checkbox" id="sexGroup">
                            <label style="color: red;" class="control-label col-sm-1"></label>
                            <label class="control-label">
                                <input type="radio" id="sex" name="sex" value="female" />
                                Nữ
                            </label>
                            <label class="control-label">
                                <input id="sex" type="radio" name="sex" value="male" />
                                Nam
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label style="color: red;" class="control-label col-sm-1"></label>
                        <div class="col-sm-4">
                            <input class="btn btn-success" style="width: 200px;" type="submit" value="Đăng ký"></input>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>