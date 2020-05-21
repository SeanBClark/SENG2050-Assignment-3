$(document).ready(function () {

    hideErrors();

});

function hideErrors() {

    $("#name-invalid").hide();
    $("#no-name").hide();
    $("#email-invalid").hide();
    $("#no-email").hide();
    $("#email-no-match").hide();
    $("#email-invalid-conf").hide();
    $("#no-email-conf").hide();
    $("#no-password").hide();
    $("#password-invalid").hide();
    $("#password-no-match").hide();
    $("#no-password-conf").hide();

}

function registerValidation() {

    let userName = document.getElementById('userName').value;
    let userEmail = document.getElementById('userEmail').value;
    let userEmailConf = document.getElementById('userEmailConf').value;
    let userPassword = document.getElementById('userPassword').value;
    let userPasswordConf = document.getElementById('userPasswordConf').value;

    if (isEmpty(userName) === false)
    {

        hideErrors();
        $('#no-name').slideDown();
        return false;

    }
    else if (isEmpty(userEmail) === false)
    {

        hideErrors();
        $('#no-email').slideDown();
        return false;

    }
    else if (isEmpty(userEmailConf) === false)
    {

        hideErrors();
        $('#no-email-conf').slideDown();
        return false;

    }
    else if (isLetNum(userPassword) === false) {

        hideErrors();
        $('#password-invalid').slideDown();
        return false;
        
    }
    else if (isEmpty(userPassword) === false)
    {

        hideErrors();
        $('#no-password').slideDown();
        return false;

    }
    else if (isEmpty(userPasswordConf) === false)
    {

        hideErrors();
        $('#no-password-conf').slideDown();
        return false;

    }
    else {
        hideErrors();
    }

    if (isLetters(userName) === false){

        hideErrors();
        $('#name-invalid').slideDown();
        return false;

    }
    if (isEmail(userEmail === false)) {

        hideErrors();
        $('#email-invalid').slideDown();
        return false;

    }
    // if (compareValue(userEmail, userEmailConf) === false) {
    if (userEmail !== userEmailConf) {

        hideErrors();
        $('#email-no-match').slideDown();
        return false;

    }
    if (userPassword !== userPasswordConf) {

        hideErrors();
        $('#password-no-match').slideDown();
        return false;

    }
};

function compareValue(param1, param2) {
    if (param2 === param1) {
        return false;
    }
    else if (param1 === param2) {
        return false;
    }
    else {
        return true;
    }
}

function isLetNum(param) {
    var regex = /^[0-9a-zA-Z]+$/;
    return regex.test(param);
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

function isLetters(param) {
    var regex = /^[a-zA-Z]+$/;
    return regex.test(param);
}