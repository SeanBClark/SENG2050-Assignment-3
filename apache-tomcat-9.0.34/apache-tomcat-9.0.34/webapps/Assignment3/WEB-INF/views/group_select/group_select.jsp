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

            Group Select

        </title>

        <%-- Stylesheets --%>
        <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity = "sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin = "anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel = "stylesheet" href ="../Assignment3/views/group_select/group_select.css" type = "text/css">
        <link rel = "stylesheet" href ="../Assignment3/views/application.css" type = "text/css">


        <%-- Scripts --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity = "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin = "anonymous"></script>
        <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity = "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin = "anonymous"></script>
        <script type =  'text/javascript' src = '../Assignment3/views/group_select/group_select.js'></script>
    
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

                <div class = 'border container group-list-div' >

                    <div id = 'groupList'>

                        <div class = 'h2 group-title-div'>Current Groups</div>

                        <c:forEach var = "item" items = "${sessionScope.groupList}">
                        
                            <div class="border row row-div">

                                <div class="border-right col-sm group-name-div">
                            
                                    ${item.getGroupName()}

                                </div>
                                <div class="border-right col-sm group-desc-div">
                            
                                    ${item.getGroupDesc()}

                                </div>  

                                <div class="col-sm btn-div">
                            
                                    <a class="nav-link" href="/Assignment3/GroupHome?groupid=${item.getGroupId()}">
                                        
                                            <button class="btn btn-outline-success my-2 my-sm-0" type="button">Group Page</button>
                                            
                                    </a>

                                </div>
                            
                            </div>
                        
                        </c:forEach>

                    </div>

                <div><%-- END: <div class = 'border container group-list-div'>--%>

                <div>
                </div>

                <div class = 'container create-group-div'>

                        <button class = 'btn btn-success btn-lg create-group-btn' id = "createGroupBtn" onClick = "showCreateGroup()")><i class="fas fa-plus"></i> Create Group</div>

                </div>

                <div class = 'border create-group-form-div' id = 'createGroupForm'>

                <div class = 'h2 group-title-div'>Create New Group</div>

                    <form method = "post" action = "/Assignment3/GroupSelect" onSubmit = "return formValidation()">

                        <div class = 'form-div'>

                            <div class = 'form-group row'>
                            
                                <label for = 'groupName' class = 'col-sm-2 col-form-label'>Group Name:</label>

                                <div class="col-sm-10">

                                    <input type = 'text' class = 'form-control form-input' id = 'groupName' name = 'groupName' placeholder = 'Enter Name'>

                                </div>

                                <div class = 'alert alert-danger form-input' role = 'alert' id = 'name-invalid' hidden>

                                    Group name must only contain letters and/or numbers

                                </div>

                            </div>

                            <div class = 'form-group row'>
                            
                                <label for = 'groupDesc' class = 'col-sm-2 col-form-label'>Group Description:</label>

                                <div class="col-sm-10">

                                    <textarea class = 'form-contol text-area' id = 'groupDesc' name = 'groupDesc'></textarea>

                                </div>

                            </div>

                            <div class = 'form-group row'>
                                
                                <label for = 'projectName' class = 'col-sm-2 col-form-label'>Project Name:</label>

                                <div class="col-sm-10">

                                    <input type = 'text' class = 'form-control form-input' id = 'projectName' name = 'projectName' placeholder = 'Enter Project that this group is for e.g. Assignment 1'>

                                </div>

                            </div>

                            <div class = 'form-group row'>
                                
                                <label for = 'courseCode' class = 'col-sm-2 col-form-label'>Course Code:</label>

                                <div class="col-sm-10">

                                    <input type = 'text' class = 'form-control form-input' id = 'courseCode' name = 'courseCode' placeholder = 'Enter Course Code that this group is for'>

                                </div>

                            </div>

                            <button type = "submit" class = "btn btn-success btn-lg create-group-btn2"><i class="fas fa-plus"></i> Create Group</button>

                        </div>

                    </form>

                </div>

            <div>

        </div>

    </body>

</html>