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

            Milestones

        </title>

        <%-- Stylesheets --%>
        <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity = "sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin = "anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel = "stylesheet" href ="../Assignment3/views/group_milestones/group_milestones.css" type = "text/css">
        <link rel = "stylesheet" href ="../Assignment3/views/application.css" type = "text/css">


        <%-- Scripts --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity = "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin = "anonymous"></script>
        <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity = "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin = "anonymous"></script>
        <script type =  'text/javascript' src = '../Assignment3/views/group_milestones/group_milestones.js'></script>
    
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

             <div class = 'container con-div'>
                <div class = 'row con-div'>

                    <div class = 'border col sidebar'>
                    
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
                            <c:set var = "item" value="${sessionScope.groupBean}"/> 
                            <a href = '/Assignment3/GroupHome?groupid=${item.getGroupId()}'><button class = 'btn btn-success add-member-btn'>Group Home</button></a>

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

                            
                            <%-- Milestone column --%>
                            <div class = 'border col info-col'>

                                <div class = 'row'>
                                
                                    <p class = 'h5 info-header border-bottom'>Upcoming Milestones</p></a> 
                                    
                                </div>

                                <div class = 'border-bottom row d-flex justify-content-center'>
                                
                                    
                                    <ul class = 'list-group info-list'>
                                        <table>
                                        
                                        <c:forEach var = "item" items = "${sessionScope.upcomingMSList}">
                                            <tr>
                                            <td>
                                            <%-- <li class = 'list-group-item list-group-item-light'><span class = 'ms-name'>${item.getMilestoneName()} </span> </br> <span class = 'ms-date'>${item.getMilestoneDate()}</span></li> --%>

                                                <c:choose>

                                                    <c:when test = "${item.getMilestoneStatus() == 1}">

                                                        <li class = 'list-group-item list-group-item-success'><span class = 'ms-name'>${item.getMilestoneName()} </span> </br> <span class = 'ms-date'><i class = 'fas fa-check-circle'></i> Completed</span></li>

                                                    </c:when>
                                                    <c:otherwise>

                                                        <li class = 'list-group-item'><span class = 'ms-name'>${item.getMilestoneName()} </span> </br> <span class = 'ms-date'>${item.getMilestoneDate()}</span></li>

                                                    </c:otherwise>

                                                </c:choose>
                                            </td>
                                            
                                            <td>
                                                <c:choose>
                                    
                                                <c:when test = "${item.getMilestoneStatus() == 1}">
                                                
                                                    <form method = "post" action = "/Assignment3/ManageMilestones">
                                                        <input type="hidden" name="markIncomplete" value="${item.getMilestoneName()}">
                                                        <input type="hidden" name="date" value="${item.getMilestoneDate()}">
                                                        <button type = "submit" class = "btn btn-warning btn-lg"><i class="fas fa-times"></i> Mark Incomplete</button>
                                                    </form>
                                                
                                                </c:when>
                                                <c:otherwise>

                                                    <form method = "post" action = "/Assignment3/ManageMilestones">
                                                        <input type="hidden" name="markComplete" value="${item.getMilestoneName()}">
                                                        <input type="hidden" name="date" value="${item.getMilestoneDate()}">
                                                        <button type = "submit" class = "btn btn-success btn-lg"><i class="fas fa-check"></i> Mark Complete</button>
                                                    </form>

                                                </c:otherwise>
                                            
                                                </c:choose>
                                            </td>
                                            
                                            <td>
                                                <form method = "post" action = "/Assignment3/ManageMilestones">
                                                    <input type="hidden" name="markRemove" value="${item.getMilestoneName()}">
                                                    <input type="hidden" name="date" value="${item.getMilestoneDate()}">
                                                    <button type = "submit" class = "btn btn-danger btn-lg"><i class="fas fa-trash"></i> Remove Milestone</button>
                                                </form>
                                            </td>
                                            
                                            </tr>
                                            
                                        </c:forEach>
                                        
                                        </table>
                                    </ul>
                                
                                </div>

                                <div class = 'row d-flex justify-content-center info-btn'>

                                      

                                </div>

                            </div>
 

                        </div>
                        <div class = "col">

                            <div class = 'border form' id = 'createMilestoneForm'>

                                <div class = 'h5 info-header border-bottom'>Create New Milestone</div>

                                <form method = "post" action = "/Assignment3/ManageMilestones" onSubmit = "return formValidation()">

                                    <div class = 'form-div'>

                                        <div class = 'form-group row'>

                                            <label for = 'milestoneName' class = 'col-sm-2 col-form-label'>Milestone Name:</label>

                                            <div class="col-sm-10">

                                                <input type = 'text' class = 'form-control form-input align-wth-txt' id = 'milestoneName' name = 'milestoneName' placeholder = 'Enter Name'>

                                            </div>

                                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'name-invalid' hidden>

                                                Group name must only contain letters and/or numbers

                                            </div>

                                        </div>

                                        <div class = 'form-group row'>

                                            <label for = 'milestoneDate' class = 'col-sm-2 col-form-label'>Date:</label>

                                            <div class="col-sm-10">

                                                <input type="date" class="form-control form-input" id ="milestoneDate" name="milestoneDate">

                                            </div>

                                        </div>
                                        
                                        <div class = 'form-group row'>

                                            <label for = 'milestoneTime' class = 'col-sm-2 col-form-label'>Time:</label>

                                            <div class="col-sm-10">

                                                <input type="time" class="form-control form-input" id ="milestoneTime" name="milestoneTime">

                                            </div>

                                        </div>

                                        <button type = "submit" class = "btn btn-success btn-lg submit-btn"><i class="fas fa-plus"></i> Create Milestone</button>

                                    </div>

                                </form>

                            </div>
                        </div>

                    </div>

                </div>

            </div>


        </div>

    </body>

</html>