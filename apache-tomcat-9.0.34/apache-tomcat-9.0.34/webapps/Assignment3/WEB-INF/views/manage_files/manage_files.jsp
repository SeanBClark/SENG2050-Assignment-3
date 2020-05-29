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
        <link rel = "stylesheet" href ="../Assignment3/views/manage_files/manage_files.css" type = "text/css">
        <link rel = "stylesheet" href ="../Assignment3/views/application.css" type = "text/css">


        <%-- Scripts --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity = "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin = "anonymous"></script>
        <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity = "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin = "anonymous"></script>
        <script type =  'text/javascript' src = '../Assignment3/views/manage_files/manage_files.js'></script>
    
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

                <div class = 'border container file-list-div' >
                    <div id = 'folder'>
                        <div class = 'h2 file-title-div'>Files</div>

                        <c:forEach var="file" items="${sessionScope.folder}">                    
                            <div class="border row row-div">

                                <div class="border-right col-sm file-name-div">
                                    <c:out value="${file.getName()}" />
                                </div>

                                <div class="border-right col-sm file-desc-div">
                                    <c:out value="${file.getDescription()}" />
                                </div> 

                                <div class="btn-group" role="group" aria-label="Basic example">    
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="window.location.href='${file.getUrl()}';">Visit</button>                                  
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="button" id="removeButton" onclick="removeFile('${file.getName()}', '${file.getVersion()}')">Remove</button>
                                    <button class='btn btn-success add-file-btn' id="addVersionBtn" onclick="showAddVersion('${file.getName()}')"><i class="fas fa-plus"></i> Add New Version</button>         
                                </div>
                            
                            </div> 
                        </c:forEach>

                    </div>

                <div>


                <form id="removeForm" action="ManageFiles" method="POST" >           
                    <input type="hidden" name="remove" id="remove">
                    <input type="hidden" name="removeFileName" id="removeFileName">
                    <input type="hidden" name="removeFileVersion" id="removeFileVersion">
                </form>   








                


                <div class='border add-file-form-div' id='addVersionForm'>

                    <div class='container add-file-div'>
                        <button class='btn btn-success btn-lg add-file-btn' id="showFileBtn" onclick="showFiles()"><i class="fas fa-plus"></i> Show Files</button>
                    </div>

                    <div class='h2 file-title-div'> Add New Version</div>                    

                    <form id="newVersionForm" method="POST" action="/Assignment3/ManageFiles" onSubmit="return formVersionValidation()">
                        <div class='form-div'>

                            <div class='form-file row'>

                                <label for='newVersionUrl' class='col-sm-2 col-form-label'>File URL:</label>
                                <div class="col-sm-10">
                                    <input type='text' class='form-control form-input' id='newVersionUrl' name='newVersionUrl' placeholder='Enter URL'>
                                </div>

                                <div class="alert alert-danger error" id='urlVersionInvalid' role="alert">
                                    Must enter a valid URL 
                                </div> 

                            </div>


                            <div class='form-file row'>  

                                <label for='newVersionDesc' class='col-sm-2 col-form-label'>File Description:</label>
                                <div class="col-sm-10">
                                    <textarea class='form-contol text-area' id='newVersionDesc' name='newVersionDesc'></textarea>
                                </div>

                            </div>


                            <input type="hidden" name="newVersion" id="newVersion">
                            <input type="hidden" name="newVersionFileName" id="newVersionFileName">

                            <button type="submit" class="btn btn-success btn-lg add-file-btn2"><i class="fas fa-plus"></i> Add File</button>

                        </div>
                    </form>
                </div> 








            </div>

        </div>

    </body>

</html>