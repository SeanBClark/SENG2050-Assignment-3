<%-- Page for users login --%>

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

            User Login

        </title>

        <%-- Stylesheets --%>
        <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity = "sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin = "anonymous">
        <link rel = "stylesheet" href ="../Assignment3/views/homepage/homepage.css" type = "text/css">
        <link rel = "stylesheet" href ="../views/application.css" type = "text/css">


        <%-- Scripts --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity = "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin = "anonymous"></script>
        <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity = "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin = "anonymous"></script>
        <script type =  'text/javascript' src = '../Assignment3/views/login/login.js'></script>
    
    </head>

    <body>

        <div class = 'align-self-center page-div'>

        <div class = 'header'>

                <nav class = 'navbar navbar-expand-lg navbar-light bg-light justify-content-center'>

                    <a class="navbar-brand" href="/Assignment3/HomePage"><p class = 'h1 header-p'>GROUP MANGEMENT SYSTEM</p></a>

                </nav>

            </div>

            <div class = 'body'>

                <div class = 'container'>

                    <div class = 'row'>

                        <div class = 'col btn-div-right d-flex justify-content-center'>

                            <a href = '/Assignment3/LoginController'>

                                <button type = 'button' class = 'btn btn-success btn-lg btn-homepage'>LOGIN</button>

                            </a>

                        </div>
                        <div class = 'col btn-div-left d-flex justify-content-center'>

                            <a href = '/Assignment3/CreateAccount'>
                            
                                <button type = 'button' class = 'btn btn-info btn-lg btn-homepage'>REGISTER</button>

                            </a>

                        </div>

                    </div>

                </div>

                <img src="/Assignment3/views/homepage/Group-of-People2.jpg" class="img-fluid fixed-bottom" alt="...">

            </div>

        </div>

    </body>

</html>