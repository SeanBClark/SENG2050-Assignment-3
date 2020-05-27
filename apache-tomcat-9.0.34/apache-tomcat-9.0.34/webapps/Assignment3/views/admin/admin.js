$(document).ready(function () {    
    hideForms();
});


function showLectForm() {
    $("#enrolForm").slideUp();
    $("#createCourseForm").slideUp();
    $("#emailSucFail").slideUp();
    $("#lectForm").slideToggle();
}

function showEnrolForm() {
    $("#createCourseForm").slideUp();
    $("#lectForm").slideUp();
    $("#emailSucFail").slideUp();
    $("#enrolForm").slideToggle();
}

function showCourseForm() {
    $("#lectForm").slideUp();
    $("#enrolForm").slideUp();
    $("#emailSucFail").slideUp();
    $("#createCourseForm").slideToggle();
}

function formValidationLect() {

    let userEmail = document.getElementById('searchUser').value;

    if (isEmpty(userEmail) === false)
    {

        $("#email-invalid").hide();
        $('#no-email').slideDown();
        return false;

    }
    else if (isEmail(userEmail) === false) {
        
        $("#no-email").hide();
        $('#email-invalid').slideDown();
        return false;

    }

}

function formValidationEnrol() {

    let userEmail = document.getElementById('userEnrol').value;
    let courseCode = document.getElementById('classCode').value;

    if (isEmpty(userEmail) === false)
    {

        $("#email-invalid-enrol").hide();
        $('#no-email-enrol').slideDown();
        return false;

    }
    else if (isEmail(userEmail) === false) {
        
        $("#no-email-enrol").hide();
        $('#email-invalid-enrol').slideDown();
        return false;

    }

    else if (isEmpty(courseCode) == false) {

        $("#no-email-enrol").hide();
        $("#email-invalid-enrol").hide();
        $('#courseCodeAlert').slideDown();
        return false;

    }
    else if (isCourseCode(courseCode) == false) {

        $("#no-email-enrol").hide();
        $("#email-invalid-enrol").hide();
        $('#courseCodeAlert').slideDown();
        return false;

    }

}

function formValidationCreate() {

    let courseName = document.getElementById("courseName").value;
    let courseDesc = document.getElementById("courseDesc").value;
    let courseCode = document.getElementById("courseCode").value;

    if (isEmpty(courseName) === false) {
        $("#noName").hide();
        $("#noDesc").hide();
        $("#invalidName").hide();
        $("#noName").slideDown();
        return false; 
    }
    else if (isEmpty(courseDesc) === false) {
        $("#noCourseCode").hide();
        $("#invalidName").hide();
        $("#noName").hide();
        $("#noDesc").slideDown();
        return false
    }
    else if (isEmpty(courseCode) === false) {
        $("#noDesc").hide();
        $("#noName").hide();
        $("#invalidName").hide();
        $("#noCourseCode").slideDown();
        return false
    }
    else if (isCourseCode(courseCode) == false) {
        $("#noDesc").hide();
        $("#noName").hide();  
        $("#noCourseCode").hide();
        $("#invalidName").slideDown();
        return false
    }

}

function isEmail(param) {
    var regex = /^\w+([\.-]?\w+)+@\w+([\.:]?\w+)+(\.[a-zA-Z0-9]{2,3})+$/;
    return regex.test(param);
}

function isEmpty(param) {
    if (param === "" || param === null) {
        return false;
    }
    else {
        return true;
    }
}

function isCourseCode(param) {
    var regex = /^[A-Z]{4}\d{4}$/;
    return regex.test(param);
}


function hideForms() {
    $("#enrolForm").hide();
    $("#createCourseForm").hide();
    $("#lectForm").hide();
    $("#email-invalid").hide();
    $("#no-email").hide();
    $("#email-invalid-enrol").hide();
    $("#no-email-enrol").hide();
    $("#courseCodeAlert").hide();
    $("#noName").hide();
    $("#noDesc").hide();
    $("#noCourseCode").hide();
    $("#invalidName").hide();
}