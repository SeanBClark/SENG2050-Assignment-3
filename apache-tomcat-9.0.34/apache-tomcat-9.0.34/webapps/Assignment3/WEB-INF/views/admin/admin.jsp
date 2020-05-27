<%-- Admin Page for site Management --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.Map.Entry" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

    <head>

        <meta charset = "UTF-8">
        <meta name = "viewport" content = "width=device-width, initial-scale = 1, shrink-to-fit = no">
    
        <title>

            Site Admin

        </title>

        <%-- Stylesheets --%>
        <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity = "sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin = "anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel = "stylesheet" href ="../Assignment3/views/admin/admin.css" type = "text/css">
        <link rel = "stylesheet" href ="../Assignment3/views/application.css" type = "text/css">


        <%-- Scripts --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity = "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin = "anonymous"></script>
        <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity = "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin = "anonymous"></script>
        <script type =  'text/javascript' src = '../Assignment3/views/admin/admin.js'></script>
    
    </head>

    <body>

        <div class = 'align-self-center page-div'>

            <div class = 'header'>

                <nav class = 'navbar navbar-expand-lg navbar-light bg-light d-flex justify-content-center'>

                    <a class="navbar-brand" href="/Assignment3/HomePage">Admin Management Page</a>

                </nav>

            </div>

            <div class = 'body-div'>

                <div class = 'container'>

                    <div class = 'border-bottom row btn-row'>

                        <div class = 'col-sm d-flex justify-content-center btn-col'>

                            <button onClick = 'showLectForm()' class = 'btn btn-success'>Create Lecturer</button>

                        </div>

                        <div class = 'col-sm d-flex justify-content-center btn-col'>

                            <button onClick = 'showEnrolForm()' class = 'btn btn-success'>Enrol Student</button>

                        </div>

                        <div class = 'col-sm d-flex justify-content-center btn-col'>

                            <button onClick = 'showCourseForm()' class = 'btn btn-success'>Create Course</button>

                        </div>

                    </div>

                    <c:if test="${not empty param.enrolSuccess}" >
                        <c:if test="${param.enrolSuccess == 'false'}">

                            <div class = 'alert alert-danger justify-content-center email-suc-fail' role = 'alert'  id = 'emailSucFail'>
                        
                            Email does not exist

                            </div>

                        </c:if>
                    </c:if>
                    
                    <c:if test="${not empty param.enrolSuccess}" >
                        <c:if test = "${param.enrolSuccess == 'true'}">

                            <div class = 'alert alert-success form-input justify-content-center email-suc-fail' role = 'alert' id = 'emailSucFail'>
                        
                            Student Enroled!

                            </div>

                        </c:if>
                    </c:if>

                    <c:if test="${not empty param.courseExists}" >
                        <c:if test="${param.courseExists == 'false'}">

                            <div class = 'alert alert-danger justify-content-center email-suc-fail' role = 'alert'  id = 'emailSucFail'>
                        
                                Invalid Course Code

                            </div>

                        </c:if>
                    </c:if>
                    
                    <c:if test="${not empty param.courseExists}" >
                        <c:if test = "${param.courseExists == 'true'}">

                            <div class = 'alert alert-success form-input justify-content-center email-suc-fail' role = 'alert' id = 'emailSucFail'>
                        
                                Student Enroled!

                            </div>

                        </c:if>
                    </c:if>

                        

                    <div class = 'row form-row justify-content-center' id = 'lectForm' name = 'lectForm'>

                        <%-- --%>
                        <form method = "post" action = '/Assignment3/Admin?param=createLect' onSubmit = "return formValidationLect() " >

                            <div class = 'form-group-row'>

                                <label for = 'searchUser' class = 'col-sm-2 col-form-label'>Enter Email</label>

                                <div class = 'col'>

                                    <input type = 'email' class = 'form-control ' id = 'searchUser' name = 'searchUser' placeholder = 'Enter Email Address'>

                                </div>

                                <div class = 'alert alert-danger form-input' role = 'alert' id = 'email-invalid'>

                                    Invalid Email Address

                                </div>

                                <div class = 'alert alert-danger form-input' role = 'alert' id = 'no-email'>

                                    Please Enter Email Address
                                        
                                </div>

                                <div class = 'col-sm d-flex justify-content-center btn-add-lec'>

                                    <button type = 'submit' onClick = '' class = 'btn btn-success'><i class="fas fa-plus"></i> Add Lecturer</button>

                                </div>

                            </div>

                        </form>

                    </div>

                    <div class = 'border row form-row justify-content-center' id = 'enrolForm'>

                        <form method = "post" action = '/Assignment3/Admin?param=enrolStd' onSubmit = "return formValidationEnrol() " >

                            <div class = 'form-group-row'>

                                <label for = 'userEnrol' class = 'col-sm-2 col-form-label'>Enter Email</label>

                                <div class = 'col'>

                                    <input type = 'email' class = 'form-control ' id = 'userEnrol' name = 'userEnrol' placeholder = 'Enter Email Address'>

                                </div>

                                <div class = 'alert alert-danger form-input' role = 'alert' id = 'email-invalid-enrol'>

                                    Invalid Email Address

                                </div>

                                <div class = 'alert alert-danger form-input' role = 'alert' id = 'no-email-enrol'>

                                    Please Enter Email Address
                                        
                                </div>

                                <label for = 'classCode' class = 'col-sm-2 col-form-label'>Class Code</label>

                                <div class = 'col'>

                                    <input type = 'text' class = 'form-control ' id = 'classCode' name = 'classCode' placeholder = 'Enter Class Code'>

                                </div>

                                <div class = 'alert alert-danger form-input' role = 'alert' id = 'courseCodeAlert'>

                                    Please Course Code
                                        
                                </div>

                                <div class = 'col-sm d-flex justify-content-center btn-add-lec'>

                                    <button type = 'submit' onClick = '' class = 'btn btn-success'><i class="fas fa-plus"></i> Enrol Student</button>

                                </div>

                            </div>

                        </form>

                    </div>

                    <div class = 'border row form-row' id = 'createCourseForm'>

                        COR

                    </div>

                </div>

            </div>

        </div>

    </body>

</html>