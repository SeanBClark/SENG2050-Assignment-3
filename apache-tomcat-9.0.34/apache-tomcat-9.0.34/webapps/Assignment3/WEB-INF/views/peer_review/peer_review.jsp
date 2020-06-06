<%-- Page viewed after user logs in. Will List groups the user is currently apart of or will enable the user to create a new group --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.Map.Entry" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

    <head>

        <meta charset = "UTF-8">
        <meta name = "viewport" content = "width=device-width, initial-scale = 1, shrink-to-fit = no">
    
        <title>

            Peer Review

        </title>

        <%-- Stylesheets --%>
        <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity = "sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin = "anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel = "stylesheet" href ="../Assignment3/views/peer_review/peer_review.css" type = "text/css">
        <link rel = "stylesheet" href ="../Assignment3/views/application.css" type = "text/css">


        <%-- Scripts --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity = "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin = "anonymous"></script>
        <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity = "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin = "anonymous"></script>
        <script type =  'text/javascript' src = '../Assignment3/views/peer_review/peer_review.js'></script>
    
    </head>

    <body>

        <div class = 'align-self-center page-div'>

            <div class = 'header'>

                <nav class = 'navbar navbar-expand-lg navbar-light bg-light'>

                    <a class="navbar-brand" href="/Assignment3/HomePage">Group Management System</a>

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
                            <c:set var = "item" value="${sessionScope.groupBean}"/> 
                            <a href = '/Assignment3/GroupHome?groupid=${item.getGroupId()}'><button class = 'btn btn-success add-member-btn'>Group Home</button></a>

                        </div>
                    
                    </div>
                    
                        <div class = 'row'>
                            
                            <div class = 'border col info-col main-col'>
                                
                                <div class = 'row'>
                                
                                    <p class = 'h5 info-header border-bottom '>Peer Review</p>
                                    
                                </div>
                                <c:choose>
                                    <c:when test = "${sessionScope.error != null}">
                                        <div class="alert alert-danger" role="alert"><i class="fa fa-exclamation-triangle"></i> Error: The total percentage cannot exceed 100%</div>
                                    </c:when>
                                </c:choose>
                                <div class="alert alert-info" role="alert"><i class="fa fa-info-circle"></i> Notice: any remainder from 100 left will be divided equally between members</div>
                                
                                <form method="post" action="/Assignment3/PeerReview" onSubmit = "return formValidation()">
                                <ul class = 'list-group info-list'>
                                    <table>
                                        <c:forEach var = "item" items = "${sessionScope.groupMemberList}" varStatus="loop"> 
                                        <tr>
                                            <td>
                                                <c:choose>

                                                    <c:when test = "${item.getMemberID() == sessionScope.userID}">

                                                        <li class = 'list-group-item active'>${item.getMemberName()}</li>


                                                    </c:when>
                                                    <c:otherwise>

                                                            <li class = 'list-group-item'>${item.getMemberName()}</li>

                                                    </c:otherwise>

                                                </c:choose>
                                            </td>

                                            <td>
                                                <label for="reviewFor${loop.index}" id="${loop.index}" class = 'col-sm-2 col-form-label'>Percentage:</label>
                                            </td>
                                            <td class="input-area-large">
                                                <input type="number" class="form-control form-input" name="reviewFor${loop.index}" min="0" max="100">
                                                <input type="hidden" name="memberIdFor${loop.index}" value="${item.getMemberID()}">
                                            </td>

                                        </tr>
                                        
                                        </c:forEach>
                                    </table>
                                </ul>
                                    <c:forEach var = "item" items = "${sessionScope.groupInfo}">
                                        <input type ="hidden" name="groupId" value="${item.getGroupId()}">
                                    </c:forEach>
                                    <input type ="hidden" name="members" value="${fn:length(sessionScope.groupMemberList)}">
                                    <button type="submit" class = "btn btn-success btn-lg submit-btn"><i class="fas fa-check"></i> Submit Peer Review</button>
                                </form>            
                                
                                
                            </div>

                        </div>
                        

                    </div>

                </div>

            </div>


        </div>

    </body>

</html>