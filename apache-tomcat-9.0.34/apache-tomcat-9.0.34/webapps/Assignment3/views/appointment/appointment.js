$(document).ready(function () 
{
    $("#addAppForm").hide();
});

function showAddApp() 
{
    if($("#schedule").is(":visible")) 
    {
        $("#schedule").slideUp();
        $("#addAppForm").slideDown();
        $("#addAppBtn").html('<i class="fas fa-plus"></i> Show Appointments');
    }
    else    
    {
        $("#addAppForm").slideUp();
        $("#schedule").slideDown();
        $("#addAppBtn").html('<i class="fas fa-plus"></i> Add Appointment');
    }
}

function formValidation()
{
    var name = document.forms["appForm"]["appName"].value; 
    var date = document.forms["appForm"]["appDate"].value; 
    var time = document.forms["appForm"]["appTime"].value; 

    $("#nameInvalid").hide();
    $("#dateInvalid").hide();
    $("#timeInvalid").hide();

    if(name === "" || name === null)
    {
        $("#nameInvalid").show();
        return false; 
    }

    if(date === "" || date === null )
    {
        $("#dateInvalid").show();
        return false; 
    }

    if(time === "" || time === null)
    {
        $("#timeInvalid").show();
        return false; 
    }

    return true; 
}

function removeAppointment(removeName)
{
    // get values from document and assign new value
    document.getElementById("remove").value = true; 
    document.getElementById("removeAppName").value = removeName;

    // submit form 
    document.getElementById("removeForm").submit(); 
}

function changeStatus(statusName, status)
{
    // get values from document and assign new value
    document.getElementById("status").value = status; 
    document.getElementById("statusAppName").value = statusName;

    // submit form 
    document.getElementById("statusForm").submit(); 
}