
$(document).ready(function () {
    
    $("#createAssignDiv").hide();

    var gradeSlider = document.getElementById("gradeSlider");
    var rangeValue = document.getElementById("rangeValue");
    rangeValue.innerHTML = gradeSlider.value;
   
    gradeSlider.oninput = function() {
   
       rangeValue.innerHTML = this.value;
   
    }

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


 