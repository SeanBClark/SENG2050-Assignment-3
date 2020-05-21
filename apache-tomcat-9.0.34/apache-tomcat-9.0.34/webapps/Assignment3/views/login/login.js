$(document).ready(function () {

    hideErrors();

});

function hideErrors() {
    $("#email-invalid").hide();
    $("#no-email").hide();
    $("#no-password").hide();
    $("#password-invalid").hide();

}

function formValidation() {

    let userEmail = document.getElementById('userEmail').value;
    let userPassword = document.getElementById('userPassword').value;

    if (isEmpty(userEmail) === false)
    {

        hideErrors();
        $('#no-email').slideDown();
        return false;

    }
    if (isEmpty(userPassword) === false)
    {

        hideErrors();
        $('#no-password').slideDown();
        return false;

    }
    if (isLetNum(userPassword) === false) {

        hideErrors();
        $('#password-invalid').slideDown();
        return false;
        
    }
    if (isEmail(userEmail === false)) {

        hideErrors();
        $('#email-invalid').slideDown();
        return false;

    }


}

function isEmpty(param) {
    if (param === "" || param === null) {
        return false;
    }
    else {
        return true;
    }
}

function clearForm() {

    userPassword.value = "";
    userEmail.value = "";
    $("#email-invalid").slideUp();
    $("#no-email").slideUp();
    $("#no-password").slideUp();
    $("#password-invalid").slideUp();


}

function isEmail(param) {
    var regex = /^\w+([\.-]?\w+)+@\w+([\.:]?\w+)+(\.[a-zA-Z0-9]{2,3})+$/;
    return regex.test(param);
}

function isLetNum(param) {
    var regex = /^[0-9a-zA-Z]+$/;
    return regex.test(param);
}