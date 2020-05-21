$(document).ready(function () {

    $("#createGroupForm").hide();

});

function showCreateGroup() {

    if ($("#groupList").is(":visible")) {
        $("#createGroupForm").slideDown();
        $("#groupList").slideUp();
        $("#createGroupBtn").html('<i class="fas fa-plus"></i> Show Joined Groups');
    }
    else {
        $("#createGroupForm").slideUp();
        $("#groupList").slideDown();
        $("#createGroupBtn").html('<i class="fas fa-plus"></i> Create Group');
    }

}

// function showExistingGroup() {

//     $("#createGroupForm").slideUp();
//     $("#groupList").slideDown();
//     $("#createGroupBtn").html('<i class="fas fa-plus"></i> Create Group');

// }