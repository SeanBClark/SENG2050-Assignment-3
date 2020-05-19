<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.Map.Entry" %>

<!DOCTYPE html>

    <head>

        <meta charset = "UTF-8">
        <meta name = "viewport" content = "width=device-width, initial-scale = 1, shrink-to-fit = no">
    
        <title>

            User Login

        </title>

        <%-- Stylesheets --%>
        <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity = "sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin = "anonymous">
        <link rel = "stylesheet" href ="login.css" type = "text/css">
        <link rel = "stylesheet" href ="../application.css" type = "text/css">


        <%-- Scripts --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity = "sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin = "anonymous"></script>
        <script src = "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity = "sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin = "anonymous"></script>
        <script type =  'text/javascript' src = 'login.js'></script>
    
    </head>

    <body>

        <div class = 'align-self-center page-div'>

            <div class = 'login-form-div'>

                <form method = "post" action = '/Assignment3/LoginController'>

                    <div class = 'form-group'>
                    
                        <label for = 'userEmail' class = 'form-label'>Email Address</label>

                        <input type = 'email' class = 'form-control form-input' id = 'userEmail' name = 'userEmail' placeholder = 'Enter Email Address'>

                    </div>

                    <div class = 'form-group'>

                        <label for = 'userPassword' class = 'form-label'>Password</label>

                        <input type = 'password'  class = 'form-control form-input' id = 'userPassword' name = 'userPassword' placeholder = 'Enter Password'>

                    </div>

                    <button type = "submit" class = "btn btn-primary">Submit</button>

                </form>

            </div>

        </div>

    </body>

</html>