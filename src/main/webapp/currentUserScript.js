$("document").ready(function () {

    var loginID = localStorage.getItem("loginID");
    $("#brugerInfo").text("Bruger ID: " + loginID);
})

