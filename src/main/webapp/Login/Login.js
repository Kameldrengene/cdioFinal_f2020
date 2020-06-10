$.ajaxSetup({async: false});

$("document").ready(function () {

    //Keeps the different users stored, so they don't have to be reloaded multiple times
    PersonList("Administrator");
    PersonList("Farmaceut");
    PersonList("Produktionsleder");
    PersonList("Laborant");


    $("#administrator").click(function () {
        localStorage.setItem("loginRole", "admin");
        var admins = localStorage.getItem("Administrator");
        $("#personer").html(admins);
    });

    $("#farmaceut").click(function () {
        localStorage.setItem("loginRole", "farma");
        var farmas = localStorage.getItem("Farmaceut");
        $('#personer').html(farmas);
    });

    $("#produktionsleder").click(function () {
        localStorage.setItem("loginRole", "prodLeder");
        var prodLeaders = localStorage.getItem("Produktionsleder");
        $('#personer').html(prodLeaders);
    });

    $("#laborant").click(function () {
        localStorage.setItem("loginRole", "laborant");
        var labos = localStorage.getItem("Laborant");
        $('#personer').html(labos);
    });

    //Saves the ID of the selected user in localStorage
    $(".brugertable").on("click", "input", function () {
        localStorage.setItem("loginID", this.id);
    })

    //Switches to the right page when "sing in" button is pressed
    $(".hvr-buzz").click(function () {
        var loginRole = localStorage.getItem("loginRole");

        if(loginRole == "admin")
            switchP("AdminScreen/index.html");
        else if(loginRole == "farma")
            switchP("FarmaScreen/index.html");
        else if(loginRole == "prodLeder")
            switchP("PLeadScreen/index.html")
        else if(loginRole == "laborant")
            switchP("LabScreen/index.html");

        //todo fejlhåndtering

    });
});

async function PersonList(role) {

    //Variable to hold all the tabel rows
    var tabelData = "";

    //Get all the persons
    await $.getJSON("/BoilerPlate_war_exploded/rest/user/getRole?role=" + role, function (data) {

        //Loop through
        $.each(data, function (key, value) {

            var userID = value.userID;

            //Uses userID for label reference
            tabelData += "<tr>";
            tabelData += "<td><input type = 'radio' name = 'rolle' id ='" + userID + "'></td>";
            tabelData += "<td><Label for ='" + userID + "'>" + userID + "</Label></td>";
            tabelData += "<td><Label for ='" + userID + "'>" + value.userName + "</Label></td>";
            tabelData += "</tr>";

        });
    });

    localStorage.setItem(role, tabelData);

}