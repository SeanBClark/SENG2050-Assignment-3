$(document).ready(function () {    
    hideForms();
});


function showLectForm() {
    $("#enrolForm").slideUp();
    $("#createCourseForm").slideUp();
    $("#emailSucFail").slideUp();
    $("#assignForm").slideUp();
    $("#lectForm").slideToggle();
}

function showEnrolForm() {
    $("#createCourseForm").slideUp();
    $("#lectForm").slideUp();
    $("#emailSucFail").slideUp();
    $("#assignForm").slideUp();
    $("#enrolForm").slideToggle();
}

function showCourseForm() {
    $("#lectForm").slideUp();
    $("#enrolForm").slideUp();
    $("#emailSucFail").slideUp();
    $("#assignForm").slideUp();
    $("#createCourseForm").slideToggle();
}

function showAssignForm() {

    $("#lectForm").slideUp();
    $("#enrolForm").slideUp();
    $("#emailSucFail").slideUp();
    $("#createCourseForm").slideUp();
    $("#assignForm").slideDown();

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
    let invalidEmail = $("#email-invalid-enrol");
    let noEmail = $('#no-email-enrol');
    let courseAlert =  $('#courseCodeAlert');

    if (isEmpty(userEmail) === false)
    {

        hideEnrolErrors()
        noEmail.slideDown();
        return false;

    }
    else if (isEmail(userEmail) === false) {
        
        hideEnrolErrors()
        invalidEmail.slideDown();
        return false;

    }

    else if (isEmpty(courseCode) == false) {

        hideEnrolErrors()
        courseAlert.slideDown();
        return false;

    }
    else if (isCourseCode(courseCode) == false) {

        hideEnrolErrors()
        courseAlert.slideDown();
        return false;

    }

}

function hideEnrolErrors() {

    let invalidEmail = $("#email-invalid-enrol");
    let noEmail = $('#no-email-enrol');
    let codeAlert = $('#courseCodeAlert');

    invalidEmail.hide();
    noEmail.hide();
    codeAlert.hide();

}

function formValidationCreate() {

    let courseName = document.getElementById("courseName").value;
    let courseDesc = document.getElementById("courseDesc").value;
    let courseCode = document.getElementById("courseCode").value;
    let noName = $("#noName");
    let noDesc = $("#noDesc");
    let invalidName = $("#invalidName");
    let noCourseCode = $("#noCourseCode");

    if (isEmpty(courseName) === false) {
        hideCourseErrors();
        noName.slideDown();
        return false; 
    }
    else if (isEmpty(courseDesc) === false) {
        hideCourseErrors();
        noDesc.slideDown();
        return false
    }
    else if (isEmpty(courseCode) === false) {
        hideCourseErrors();
        noCourseCode.slideDown();
        return false
    }
    else if (isCourseCode(courseCode) == false) {
        hideCourseErrors();
        invalidName.slideDown();
        return false
    }

}

function hideCourseErrors() {

    let noName = $("#noName");
    let noDesc = $("#noDesc");
    let invalidName = $("#invalidName");
    let noCourseCode = $("#noCourseCode");

    noDesc.hide();
    noName.hide();  
    noCourseCode.hide();
    invalidName.hide();

}

function formValidationAssign() {

    let email = document.getElementById("assignEmail").value;
    let code = document.getElementById("assignCode").value;
    let invalidEmail = $("#assignEmailError");
    let emptyEmail = $("#assignEmailEmpty");
    let invalidCode = $("#assignInvalidCode");
    let noCode = $("#assignNoCode");

    if ( isEmpty(email) === false ) {

        hideAssignErrors();
        showAssignErrors(emptyEmail);
        return false;

    }
    else if ( isEmail(email) === false ) {

        hideAssignErrors();
        showAssignErrors(invalidEmail);
        return false;

    }
    else if ( isEmpty(code) === false ) {

        hideAssignErrors();
        showAssignErrors(noCode);
        return false;

    }
    else if ( isCourseCode(code) === false ) {

        hideAssignErrors();
        showAssignErrors(invalidCode);
        return false;

    }

}

function hideAssignErrors() {

    let invalidEmail = $("#assignEmailError");
    let emptyEmail = $("#assignEmailEmpty");
    let invalidCode = $("#assignInvalidCode");
    let noCode = $("#assignNoCode");

    noCode.hide();
    invalidCode.hide();
    emptyEmail.hide();
    invalidEmail.hide();

}

function showAssignErrors(error) {
    error.slideDown();
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

// I hate this as well
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
    $("#assignInvalidCode").hide();
    $("#assignNoCode").hide();
    $("#assignEmailEmpty").hide();
    $("#assignEmailError").hide();
    $("#assignForm").hide();
}