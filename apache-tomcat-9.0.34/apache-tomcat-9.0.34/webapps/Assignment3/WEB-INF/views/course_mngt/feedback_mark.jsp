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

            Group Management

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
            
                <c:forEach var = "item" items = "${sessionScope.fileDetails}">

                    <div class = 'container d-flex content-align-center'>

                        <div class = 'border col col-lg-2'>

                            <div class = 'row content-align-center details-row'>

                                <p class = 'h4'>File Details</p>

                            </div>
                            <div class = 'row details-row'>

                            <p class = 'h6'>File Name: ${item.getName()}</p>

                            </div>
                            <div class = 'row content-align-center details-row'>

                                <a href="${item.getUrl()}">

                                    <button class="btn btn-success my-2 my-sm-0 " type="button">View Assignment</button>

                                </a>

                            </div>
                            <div class = 'row details-row'>

                                <c:choose>

                                    <c:when test = "${item.getStatusInt() == 1}">

                                        <p class = 'h6 alert alert-warning'>Submitted Version</p>

                                    </c:when>
                                    <c:otherwise>

                                        Provide Feedback

                                    </c:otherwise>

                                </c:choose>

                            </div>

                        </div>

                        <div class = 'border col'>

                            <form method = 'post' action = '/Assignment3/Feedback?fileID=${item.getFileID()}&groupID=${item.getGroupID()}' >

                                <div class = 'form-group feedback-div'>

                                    <label for = "feedback" class = 'content-align-center'><p class = 'h6'>Feedback</p></label>
                                    <textarea class = 'form-control' id = "feedback" name = "feedback" rows = '5'></textarea>

                                </div>

                                <div class = 'form-group feedback-div'>

                                    <label for = "grade" class = 'content-align-center'><p class = 'h6'>Grade</p></label>
                                    <input type = 'range' class = 'form-control-range' id = 'gradeSlider' name = 'gradeSlider' min = '0' max = '100' value = '50' step = '0.1'></input>
                                    <p>Value: <span id = 'rangeValue' name = 'rangeValue'</p>

                                </div>

                                <div class = 'form-group feedback-div d-flex justify-content-center'>

                                    <button class="btn btn-success my-2 my-sm-0 content-align-center" type="submit">Submit Mark</button>

                                </div>

                            </form>

                        </div>

                    </div>

                </c:forEach>

            </div>

        </div>

    </body>

</html>