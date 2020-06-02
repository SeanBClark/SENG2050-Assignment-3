<%-- Page viewed after user logs in. Will List groups the user is currently apart of or will enable the user to create a new group --%>

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

            Manage Appointments

        </title>

        <%-- Stylesheets --%>
        <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity = "sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin = "anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel = "stylesheet" href ="../Assignment3/views/appointment/appointment.css" type = "text/css">
        <link rel = "stylesheet" href ="../Assignment3/views/application.css" type = "text/css">


        <%-- Scripts --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity = "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin = "anonymous"></script>
        <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity = "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin = "anonymous"></script>
        <script type =  'text/javascript' src = '../Assignment3/views/appointment/appointment.js'></script>
    
    </head>

    <body>

        <div class = 'align-self-center page-div'>

            <div class = 'header'>

                <nav class = 'navbar navbar-expand-lg navbar-light bg-light'>

                    <a class="navbar-brand" href="/Assignment3/HomePage">Group Mangement System</a>

                    <%-- <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">

                        <span class="navbar-toggler-icon"></span>

                    </button> --%>

                    <div class="collapse navbar-collapse" id="navbarNav">

                        <ul class="navbar-nav">

                            <li class="nav-item">

                                <a class="nav-link" href="/Assignment3/HomePage">
                                
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="button">Home</button>
                                    
                                </a>

                            </li>

                             <li class="nav-item"> 

                                <a class="nav-link" href="/Assignment3/LogOutController">

                                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log Out</button>

                                </a>

                            </li>

                        </ul>

                    </div>

                    <%-- Doesn't do anything --%>
                    <form class="form-inline">

                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">

                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>

                    </form>

                </nav>

            </div>










            <div class = 'body'>












                 <div class = 'border container app-list-div' >

                    <div id = 'schedule'>

                        <div class = 'h2 app-title-div'>Appointments</div>


                        <c:forEach var="appointment" items="${sessionScope.schedule}">   

                            <div class="border row row-div">

                                <div class="border-right col-sm app-name-div">

                                    <c:out value="${appointment.getAppName()}" />

                                </div>


                                <div class="border-right col-sm app-desc-div">

                                    <c:out value="${appointment.getDescription()}" />

                                </div> 

                            
                                <div class="border-right col-sm app-date-div">

                                    <c:out value="${appointment.getAppDate()}" />

                                </div>



                                <button class="btn btn-outline-success" type="button" id="removeButton" onclick="removeAppointment('${appointment.getAppName()}')">Remove</button>


                                <c:choose> 

                                    <c:when test="${appointment.getStatus()}" >

                                        <button class="btn btn-success btn-sub" type="button" id="appStatusBtn" onclick="changeStatus('${appointment.getAppName()}', true)">Completed</button>

                                    </c:when>

                                    <c:otherwise>

                                        <button class="btn btn-outline-success btn-sub" type="button" id="appStatusBtn" onclick="changeStatus('${appointment.getAppName()}', false)">Complete</button>

                                    </c:otherwise>

                                </c:choose>




                            </div>



                        </c:forEach>


                    </div>


                <div>



                 

                <form id="removeForm" action="/Assignment3/ManageAppointments" method="POST" > 

                    <input type="hidden" name="remove" id="remove">

                    <input type="hidden" name="removeAppName" id="removeAppName">

                </form>     



                <form id="statusForm" action="/Assignment3/ManageAppointments" method="POST" >

                    <input type="hidden" name="status" id="status">

                    <input type="hidden" name="statusAppName" id="statusAppName">

                </form>  










                <div class='container add-app-div'>

                    <button class='btn btn-success btn-lg add-app-btn' id="addAppBtn" onClick="showAddApp()"><i class="fas fa-plus"></i> Add Appointment</div>

                </div>
                

                 <div class='border add-app-form-div' id='addAppForm'>

                <div class='h2 app-title-div'>Add New Appointment</div>

                    <form id="appForm" name="appForm" method="POST" action="/Assignment3/ManageAppointments" onSubmit="return formValidation()">

                        <div class='form-div'>


                            <div class='form-app row'>
                            
                                <label for='appName' class='col-sm-2 col-form-label'>Name:</label>

                                <div class="col-sm-10">

                                    <input type='text' class='form-control form-input' id='appName' name='appName' placeholder='Enter Name'>

                                </div>

                                <div class="alert alert-danger error" id='nameInvalid' role="alert">

                                    Must entre an appointment name
                                    
                                </div> 

                            </div>



                            <div class='form-app row'>
                            
                                <label for='appDesc' class='col-sm-2 col-form-label'>Description:</label>

                                <div class="col-sm-10">

                                    <textarea class='form-contol text-area' id='appDesc' name='appDesc'></textarea>

                                </div>

                            </div>



                            <div class='form-app row'>
                            
                                <label for='appDate' class='col-sm-2 col-form-label'>Date:</label>

                                <div class="col-sm-10">

                                     <input type="date" id='appDate' name='appDate'>

                                </div>

                            </div>

                            <div class="alert alert-danger error" id='dateInvalid' role="alert">

                                    Must entre an appointment date
                                    
                            </div> 



                            <div class='form-app row'>
                            
                                <label for='appTime' class='col-sm-2 col-form-label'>Time:</label>

                                <div class="col-sm-10">

                                     <input type="time" id='appTime' name='appTime'>

                                </div>

                            </div>

                            <div class="alert alert-danger error" id='timeInvalid' role="alert">

                                    Must entre an appointment time
                                    
                            </div> 



                            <br/>

                            <button type="submit" class="btn btn-success btn-lg add-app-btn2"><i class="fas fa-plus"></i> Add Appointment</button>


                        </div>

                    </form>

                </div>















            <div>

        </div>

    </body>

</html>