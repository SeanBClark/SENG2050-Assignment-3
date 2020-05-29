$(document).ready(function () 
{
    $("#addVersionForm").hide();
    $("#addFileForm").hide();
});

function showAddFile() 
{
    if($("#folder").is(":visible")) 
    {
        $("#folder").slideUp();
        $("#addVersionForm").slideUp();
        $("#addFileForm").slideDown();
        $("#addFileBtn").html('<i class="fas fa-plus"></i> Show Files');
    }
    else    
    {
        $("#addVersionForm").slideUp();
        $("#addFileForm").slideUp();
        $("#folder").slideDown();
        $("#addFileBtn").html('<i class="fas fa-plus"></i> Add File');
    }
}

function showAddVersion(versionName)
{
    document.getElementById("newVersion").value = true; 
    document.getElementById("newVersionFileName").value = versionName;

    $("#folder").slideUp();
    $("#addVersionForm").slideDown();
    $("#addFileBtn").html('<i class="fas fa-plus"></i> Show Files');
}

function removeFile(removeName, removeVersion)
{
    // get values from document and assign new value
    document.getElementById("remove").value = true; 
    document.getElementById("removeFileName").value = removeName;
    document.getElementById("removeFileVersion").value = removeVersion;

    // submit form 
    document.getElementById("removeForm").submit(); 
}

function changeStatus(statusName, statusVersion, status)
{
    // get values from document and assign new value
    document.getElementById("status").value = status; 
    document.getElementById("statusFileName").value = statusName;
    document.getElementById("statusFileVersion").value = statusVersion;

    // submit form 
    document.getElementById("statusForm").submit(); 
}

/*
function formVersionVal()
{
    var url = document.forms["newVersionForm"]["newVersionUrl"].value; 

    if((url === "") || (urlValidation(url) === false))
    {
        $("#urlVersionInvalid").show();
        return = false; 
    }

    $("#urlVersionInvalid").hide();
    return = true; 
}
*/

function formValidation()
{
    var nameValid = true; 
    var urlValid = true;

    var name = document.forms["fileForm"]["fileName"].value; 
    var url = document.forms["fileForm"]["fileUrl"].value; 

    if((name === ""))
    {
        $("#nameInvalid").show();
        nameValid = false; 
    }

    if((url === "") || (urlValidation(url) === false))
    {
        $("#urlInvalid").show();
        urlValid = false; 
    }


    if((nameValid === false) && (urlValid === false)) // name and url are not valid
    {
        return false; 
    }
    else if((nameValid === true) && (urlValid === false)) // name is valid and url is not valid
    {
        $("#nameInvalid").hide();
        return false; 
    }
    else if((nameValid === false) && (urlValid === true)) // name is not valid and url is valid 
    {
        $("#urlInvalid").hide();
        return false; 
    }
    else
    {
        $("#nameInvalid").hide();
        $("#urlInvalid").hide();
        return true; 
    }
}

function urlValidation(url)
{
    return /^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(url);
}