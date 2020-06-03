<%-- Page for new users to register --%>

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

            Register

        </title>

        <%-- Stylesheets --%>
        <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity = "sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin = "anonymous">
        <link rel = "stylesheet" href ="../Assignment3/views/register/register.css" type = "text/css">
        <link rel = "stylesheet" href ="../Assignment3/views/application.css" type = "text/css">


        <%-- Scripts --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity = "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin = "anonymous"></script>
        <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity = "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin = "anonymous"></script>
        <script type =  'text/javascript' src = '../Assignment3/views/register/register.js'></script>
    
    </head>

    <body>

        <div class = 'align-self-center page-div'>

            <div class = 'header'>

                <nav class = 'navbar navbar-expand-lg navbar-light bg-light'>

                    <a class="navbar-brand" href="/Assignment3/HomePage">Group Mangement System</a>

                    <div class="collapse navbar-collapse" id="navbarNav">

                        <ul class="navbar-nav">

                            <li class="nav-item">

                                <a class="nav-link" href="/Assignment3/HomePage">
                                
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="button">Home</button>
                                    
                                </a>

                            </li>
                            <li class="nav-item">

                                <a class="nav-link" href="/Assignment3/LoginController">
                                
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="button">Login</button>
                                    
                                </a>

                            </li>
                            <li class="nav-item">

                                <a class="nav-link" href="/Assignment3/CreateAccount">
                                
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="button">Register</button>
                                    
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

                <c:if test="${not empty param.exists}" >
                    <c:if test = "${param.exists == 'true'}">

                        <div class = 'alert alert-danger form-input justify-content-center email-suc-fail' role = 'alert' id = 'emailSucFail'>
                    
                            Email Address already taken

                        </div>

                    </c:if>
                </c:if>
        
                <div class = 'login-form-div'>
                    <%-- onSubmit = 'return registerValidation()' --%>
                    <form method = "post"  onSubmit = 'return registerValidation()'  action = "/Assignment3/CreateAccount">

                        <div class = 'form-group'>
                        
                            <label for = 'userName' class = 'form-label'>Name</label>

                            <input type = 'text' class = 'form-control form-input' id = 'userName' name = 'userName' placeholder = 'Enter Name'>

                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'name-invalid'>

                                Name must only contain letters

                            </div>

                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'no-name'>

                                Please Enter Name

                            </div>

                        </div>

                        <div class = 'form-group'>
                        
                            <label for = 'userEmail' class = 'form-label'>Email Address</label>

                            <input type = 'email' class = 'form-control form-input' id = 'userEmail' name = 'userEmail' placeholder = 'Enter Email Address'>

                            <h1 id = 'exists'></h1>

                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'email-invalid'>

                                Invalid Email Address

                            </div>

                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'no-email'>

                                Please Enter Email Address
                                
                            </div>

                        </div>

                        <div class = 'form-group'>
                        
                            <label for = 'userEmail' class = 'form-label'>Confirm Email Address</label>

                            <input type = 'email' class = 'form-control form-input' id = 'userEmailConf' name = 'userEmailConf' placeholder = 'Confirm Email Address'>

                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'email-no-match'>

                                Emails do not match

                            </div>

                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'email-invalid-conf'>

                                Invalid Email Address

                            </div>

                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'no-email-conf'>

                                Please Enter Email Again
                                
                            </div>

                        </div>

                        <div class = 'form-group'>

                            <label for = 'userPassword' class = 'form-label'>Password</label>

                            <input type = 'password'  class = 'form-control form-input' id = 'userPassword' name = 'userPassword' placeholder = 'Enter Password'>

                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'no-password'>

                                Please Enter Password
                                
                            </div>

                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'password-invalid'>
                            
                                Password should only contain letters or numbers

                            </div>

                        </div>

                        <div class = 'form-group'>

                            <label for = 'userPassword' class = 'form-label'>Confirm Password</label>

                            <input type = 'password'  class = 'form-control form-input' id = 'userPasswordConf' name = 'userPasswordConf' placeholder = 'Confirm Password'>

                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'password-no-match'>

                                Password does not match
                                
                            </div>

                            <div class = 'alert alert-danger form-input' role = 'alert' id = 'no-password-conf'>

                                Please Enter Re-enter Password
                                
                            </div>

                        </div>

                        <button type = "submit" class = "btn btn-success">Submit</button>

                    </form>

                </div>

            </div>

        </div>

    </body>

</html>