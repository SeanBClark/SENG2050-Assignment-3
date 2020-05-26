$(document).ready(function () {
    
    hideForms();

});

function hideForms() {

    $("#enrolForm").hide();
    $("#createCourseForm").hide();
    $("#lectForm").hide();

}

function showLectForm() {

    hideForms();
    $("#lectForm").slideDown();

}

function showEnrolForm() {

    hideForms();
    $("#enrolForm").slideDown();

}

function showCourseForm() {

    hideForms();
    $("#createCourseForm").slideDown();

}