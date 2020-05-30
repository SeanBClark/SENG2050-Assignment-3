
$(document).ready(function () {
    
    $("#createAssignDiv").hide();

});

function showCreateAssignment() {

    if ($("#selectGroupDiv").is(":visible")) {
        $("#createAssignDiv").slideDown();
        $("#selectGroupDiv").slideUp();
        $("#showCreateAssignment").html('<i class="fas fa-plus"></i> Show Groups');
    }
    else {
        $("#createAssignDiv").slideUp();
        $("#selectGroupDiv").slideDown();
        $("#showCreateAssignment").html('<i class="fas fa-plus"></i> Create Assignment');
    }

};

$(function () {
    $('#datetimepicker1').datetimepicker();
 });