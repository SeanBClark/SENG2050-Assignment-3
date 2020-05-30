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

            Course Management

        </title>

        <%-- Stylesheets --%>
        <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity = "sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin = "anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel = "stylesheet" href ="../Assignment3/views/course_mngt/course_mngt.css" type = "text/css">
        <link rel = "stylesheet" href ="../Assignment3/views/application.css" type = "text/css">


        <%-- Scripts --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity = "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin = "anonymous"></script>
        <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity = "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin = "anonymous"></script>
        <script type =  'text/javascript' src = '../Assignment3/views/course_mngt/course_mngt.js'></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    
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

            <div class = 'body'>

                <div class = 'container select-group-div' id = 'selectGroupDiv'>

                    <div class = 'row header-row border-bottom'>
                    
                        <div class = 'col-md-12'><p class = 'h5 d-flex justify-content-center'>Groups</p></div>
                        
                    </div>
                    
                    <c:forEach var = "item" items = "${sessionScope.groupList}">

                        <div class = 'row  justify-content-center group-row'>

                            <div class = 'col-6 col-lg-2 justify-content-center'>${item.getGroupName()}</div>
                            <div class = 'col-6 col-lg-2 justify-content-center'>${item.getProjectName()}</div>
                            <div class = 'col-2 justify-content-center'>
                            
                                <a href="/Assignment3/GroupFeedback?groupID=${item.getGroupId()}">

                                    <button class="btn btn-success my-2 my-sm-0" type="button">Select Group</button>

                                </a>
                            
                            </div>

                        </div>

                    </c:forEach>

                </div>
            </div>

            <div class = 'container create-group-div d-flex justify-content-center'>

                    <button class = 'btn btn-success btn-lg create-group-btn' id = "showCreateAssignment" onClick = "showCreateAssignment()")><i class="fas fa-plus"></i> Create Assignment</div>

            </div>

            <div class = 'container justify-content-center' id = 'createAssignDiv'>

                <div class = 'col-md-12'><p class = 'h5 d-flex justify-content-center'>Create Assignment</p></div>

                <div class = 'row  justify-content-center group-row'>

                    <form method = "post" action = "/Assignment3/CreateAssignment" >

                        <div class = 'form-group row create-assign-div'>

                            <label for = 'name' class = 'col-sm-4 col-form-label form-label-css'> Assignment Name: </label>
                            
                            <div class = 'col-sm-8'>

                                <input type = 'text' class = 'form-control' id = 'name' name = 'name' placeholder = 'Enter Assignment Name'></input>

                            </div>

                        </div>

                        <div class = 'form-group row create-assign-div'>

                            <label for = 'description' class = 'col-sm-4 col-form-label form-label-css'> Assignment Description: </label>
                            
                            <div class = 'col-sm-8'>

                                <textarea type = 'text' class = 'form-control' id = 'description' name = 'description' placeholder = 'Enter Assignment Description'></textarea>

                            </div>

                        </div>

                        <div class = 'form-group row create-assign-div'>

                            <label for = 'dueDate' class = 'col-sm-4 col-form-label form-label-css'> Due Date: </label>
                            
                            <div class = 'col-sm-8'>

                                <div class = 'input-group date' id = 'datetimepicker1'>

                                    <input type = 'text' class = 'form-control' id = 'dueDate' name = 'dueDate' placeholder = ''></input>

                                    <span class = 'input-group-addon'>

                                        <span class = 'glyphicon glyphicon-calendar'></span>

                                    </span>

                                </div>

                            </div>

                        </div>

                        <div class = 'justify-content-center form-group row create-assign-div'>
                        
                            <button type = "submit" class = "btn btn-success btn-lg create-group-btn2 "><i class="fas fa-plus"></i> Create Assignment</button>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </body>

</html>