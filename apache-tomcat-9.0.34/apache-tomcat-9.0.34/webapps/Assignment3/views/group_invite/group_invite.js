var count = 1;

function addMember() {

    if (count <= 7){
    
        $(".input-row:first").clone().find(".form-input").each(function() {

            $(this).attr({

                'id' : function(_, id) { return id + count },
                'name' : function(_, name) { return name + count }

            });
            $("#inputCount").attr({ 'value' : function(_, value) { return count + 1 } });

        }).end().appendTo(".add-more-row");

        count++;

    } else {

        $("#addMemberBtn").hide();

    }

}

function formValidation() {



}
