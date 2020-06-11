$.ajaxSetup({async: false});

$("document").ready(function () {

    //Keeps the different users stored, so they don't have to be reloaded multiple times
    PersonList("Administrator");
    PersonList("Farmaceut");
    PersonList("Produktionsleder");
    PersonList("Laborant");

    // $(document).ajaxComplete(function(){
    //     $(".loader").css("display", "none");
    //
    // });

    $("#login").load("Login/PickRole.html");

    $("#administrator").click(function () {
        localStorage.setItem("loginRole", "admin");
        localStorage.setItem('loginID', 'None');
        var admins = localStorage.getItem("Administrator");
        $("#personer").html(admins);
    });

    $("#farmaceut").click(function () {
        localStorage.setItem("loginRole", "farma");
        localStorage.setItem('loginID', 'None');
        var farmas = localStorage.getItem("Farmaceut");
        $('#personer').html(farmas);
    });

    $("#produktionsleder").click(function () {
        localStorage.setItem("loginRole", "prodLeder");
        localStorage.setItem('loginID', 'None');
        var prodLeaders = localStorage.getItem("Produktionsleder");
        $('#personer').html(prodLeaders);
    });

    $("#laborant").click(function () {
        localStorage.setItem("loginRole", "laborant");
        localStorage.setItem('loginID', 'None');
        var labos = localStorage.getItem("Laborant");
        $('#personer').html(labos);
    });

    //Saves the ID of the selected user in localStorage
    $(".brugertable").on("click", "input", function () {
        localStorage.setItem("loginID", this.id);
    })

    //Switches tjo the right page when "sing in" button is pressed
    $(".hvr-buzz").click(function () {
        var loginRole = localStorage.getItem("loginRole");
        var ID = localStorage.getItem("loginID");

        if (ID === "None") {
            alert("Vælg venligst en rolle")
        } else {
            if (loginRole == "admin")
                switchP("AdminScreen/index.html");
            else if (loginRole == "farma")
                switchP("FarmaScreen/index.html");
            else if (loginRole == "prodLeder")
                switchP("PLeadScreen/PLeadScreen.html")
            else if (loginRole == "laborant")
                switchP("LabScreen/index.html");
        }

    });

});


async function PersonList(role) {

    //Variable to hold all the tabel rows
    var tabelData = "";

    //Get all the persons
    await $.getJSON("/BoilerPlate_war_exploded/rest/user/getRole?role=" + role, function (data) {

        //Loop through
        $.each(data, function (key, value) {
            if (value.aktiv) {
                var userID = value.userID;

                //Uses userID for label reference
                tabelData += "<tr>";
                tabelData += "<td><input type = 'radio' name = 'rolle' id ='" + userID + "'></td>";
                tabelData += "<td><Label for ='" + userID + "'>" + userID + "</Label></td>";
                tabelData += "<td><Label for ='" + userID + "'>" + value.userName + "</Label></td>";
                tabelData += "</tr>";
            }
        });
    });

    localStorage.setItem(role, tabelData);
};