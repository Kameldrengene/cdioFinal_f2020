
$("document").ready(function () {

    //To prevent false logins
    localStorage.setItem('loginID', 'None');

    //Keeps the different users in local storage. So they don't have to be reloaded multiple times
    //.then to ensure functionality is only added after resources are fetched
    loadUsers().then(function () {

        //Saves the ID of the selected user in localStorage
        $("#table").on("click", "input", function () {
            localStorage.setItem("loginID", this.id);
        });

        //Only load user data and sign in button when everything is ready
        $("#table").load("Login/BrugerTabel.html");
        $("#login").load("Login/RolleTabel.html");
        $("#signin").html("<button class='hvr-buzz'>Sign in</button>");

        //Attach the appropiate actions to the role tabel
        $("#administrator").click(function () {
            localStorage.setItem("loginRole", "admin");
            localStorage.setItem('loginID', 'None');
            const admins = localStorage.getItem("Administrator");
            $("#personer").html(admins);
        });

        $("#farmaceut").click(function () {
            localStorage.setItem("loginRole", "farma");
            localStorage.setItem('loginID', 'None');
            const farmas = localStorage.getItem("Farmaceut");
            $('#personer').html(farmas);
        });

        $("#produktionsleder").click(function () {
            localStorage.setItem("loginRole", "prodLeder");
            localStorage.setItem('loginID', 'None');
            const prodLeaders = localStorage.getItem("Produktionsleder");
            $('#personer').html(prodLeaders);
        });

        $("#laborant").click(function () {
            localStorage.setItem("loginRole", "laborant");
            localStorage.setItem('loginID', 'None');
            const labos = localStorage.getItem("Laborant");
            $('#personer').html(labos);
        });

        //Switches to the right page when "sing in" is pressed
        $(".hvr-buzz").click(function () {
            const loginRole = localStorage.getItem("loginRole");
            const ID = localStorage.getItem("loginID");

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
    }); //todo tilføj catch?
});

async function loadUsers(){

    //Display loader
    $("#loader").html("<div id='loading'><\div")

    //fetch and save user data
    await loadUser("Administrator");
    await loadUser("Farmaceut");
    await loadUser("Produktionsleder");
    await loadUser("Laborant");

    //Remove loader
    $("#loader").html("")

}

async function loadUser(role) {

    await $.ajax({
        url: "/BoilerPlate_war_exploded/rest/user/getRole?role=" + role,
        type: "GET",
        async: true,
        contentType: "application/json",
        dataType: "json",
        success: data => createTable(data, role)
    });

}

function createTable(data, role){
    //Variable to hold all the tabel rows
    let tabelData = "";

    //Loop through
    $.each(data, function (key, value) {
        if (value.aktiv) {
            const userID = value.userID;

            //Uses userID for label reference
            tabelData += "<tr>";
            tabelData += "<td><input type = 'radio' name = 'rolle' id ='" + userID + "'></td>";
            tabelData += "<td><Label for ='" + userID + "'>" + userID + "</Label></td>";
            tabelData += "<td><Label for ='" + userID + "'>" + value.userName + "</Label></td>";
            tabelData += "</tr>";
        }
    });

    localStorage.setItem(role, tabelData)
}


