<%-- Home Page for Each Group--%>

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

            Group Page

        </title>

        <%-- Stylesheets --%>
        <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity = "sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin = "anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel = "stylesheet" href ="../Assignment3/views/group_home/group_home.css" type = "text/css">
        <link rel = "stylesheet" href ="../Assignment3/views/application.css" type = "text/css">


        <%-- Scripts --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity = "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin = "anonymous"></script>
        <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity = "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin = "anonymous"></script>
        <script type =  'text/javascript' src = '../Assignment3/views/group_home/group_home.js'></script>
    
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

                        </ul>

                    </div>

                    <%-- Doesn't do anything --%>
                    <form class="form-inline">

                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">

                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>

                    </form>

                </nav>

            </div>

            <div class = 'container con-div'>

                <div class = 'row'>

                    <div class = 'border col'>
                    
                        <c:forEach var = "item" items = "${sessionScope.groupInfo}">

                            <div class = 'border-bottom group-name-div'>

                                <p class = 'h4'>${item.getGroupName()}</p>

                            </div>

                        </c:forEach>

                        <div class = 'border-bottom member-list-div'>

                            <p class = 'h5'>Members</p>

                            <ul class = 'list-group'>

                                <c:forEach var = "item" items = "${sessionScope.groupMemberList}">                                    

                                    <c:choose>
                                    
                                        <c:when test = "${item.getMemberID() == sessionScope.userID}">
                                        
                                            <li class = 'list-group-item active'>${item.getMemberName()}</li>
                                        
                                        </c:when>
                                        <c:otherwise>

                                            <li class = 'list-group-item'>${item.getMemberName()}</li>

                                        </c:otherwise>
                                    
                                    </c:choose>

                                </c:forEach>

                            </ul>

                        </div>

                        <div class = 'add-member-div'>

                            <button class = 'btn btn-success add-member-btn'><i class="fas fa-plus"></i> Add Member</button> 

                        </div>
                    
                    </div>
                    <div class = 'border col-8'>

                        <div class = 'row progress-row'>

                            <div class = 'col-1 '>

                                <p class = 'progress-text'>Progress:</p>

                            </div>
                            <div class = 'col-11'>

                                <div class = 'progress'>

                                    <div class = 'progress-bar progress-bar-striped bg-success' style = 'width: ${sessionScope.percentageComplete}%;' roll = 'progressbar' aria-valuemin = '0' aria-valuemax = '100'"></div>

                                </div>

                            </div>

                        </div>

                        <div class = 'row'>

                            <div class = 'border col info-col'>

                                <div class = 'row'>
                                
                                    <p class = 'h5 info-header border-bottom '>Appointments</p>
                                    
                                </div>

                                <div class = 'row'>
                                </div>

                            </div>
                            <div class = 'border col info-col'>

                                <div class = 'row'>
                                
                                    <p class = 'h5 info-header border-bottom'>Milestones</p>
                                    
                                </div>

                                <div class = 'border-bottom row d-flex justify-content-center'>
                                
                                    <ul class = 'list-group info-list'>
                                
                                        <li class = 'list-group-item'>Appointment</li>
                                        <li class = 'list-group-item'>Appointment</li>
                                        <li class = 'list-group-item'>Appointment</li>
                                        <li class = 'list-group-item'>Appointment</li>
                                    
                                    </ul>
                                
                                </div>

                                <div class = 'row d-flex justify-content-center info-btn'>

                                    <button class = 'btn btn-success'>Manage Appointments</button> 

                                </div>

                            </div>

                        </div>

                    </div>
                    <div class = 'border col'>

                        Stuff

                    </div>

                </div>

            </div>

        </div>

    </body>

</html>