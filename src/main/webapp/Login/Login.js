$("document").ready(function () {

    $("#administrator").click(function () {
        localStorage.setItem("loginRole", "admin");
        PersonList("Administrator");
    });

    $("#farmaceut").click(function () {
        localStorage.setItem("loginRole", "farma");
        PersonList("Farmaceut");
    });

    $("#produktionsleder").click(function () {
        localStorage.setItem("loginRole", "prodLeder");
        PersonList("Produktionsleder");
    });

    $("#laborant").click(function () {
        localStorage.setItem("loginRole", "laborant");
        PersonList("Laborant");
    });

    //Saves the ID of the selected user in localStorage
    $(".brugertable").on("click", "input", function () {
        localStorage.setItem("loginID", this.id);
    })

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



    })

});

function PersonList(role) {

    //Get all the persons
    console.log(role);
    $.getJSON("/BoilerPlate_war_exploded/rest/user/getRole?role=" + role, function(data){

        //Variable to hold all the tabel rows
        var tabelData;

        //Loop through
        $.each(data, function(key, value) {

            var userID = value.userID;

            //Uses userID for label reference
            tabelData += "<tr>";
            tabelData += "<td><input type = 'radio' name = 'rolle' id ='" + userID + "'></td>";
            tabelData += "<td><Label for ='" + userID + "'>" + userID + "</Label></td>"
            tabelData += "<td><Label for ='" + userID + "'>" + value.userName + "</Label></td>";
            tabelData += "</tr>";
        });

        $('#personer').html(tabelData);
    });
}