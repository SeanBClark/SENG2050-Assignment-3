function formValidation() {
    var x = document.getElementByName("members");
    var total = 0;
    for (i = 0; i < x; i++)
    {
        var index = i+1;
        var percentage = document.getElementById(index);
        total += percentage;
    }
    if (total > 100)
    {
        alert("Total Cannot exceed 100%");
        return false;
    }
    else
    {
        alert("Total less than 100%");
        return false;
    }
}