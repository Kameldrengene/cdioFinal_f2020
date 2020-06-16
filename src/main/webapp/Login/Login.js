
$("document").ready(async function () {

    //To prevent false logins
    localStorage.setItem('loginID', 'None');

    //Keeps the different users in local storage. So they don't have to be reloaded multiple times
    //await to ensure functionality is only added after resources are fetched
    await loadUsers();

    //Only load user data and sign in button when everything is ready
    $("#login").load("Login/LoginTable.html");
    $(".brugertable").hide();

    $(".rolletabel").click(function () {
        $(".brugertable").show();
    })

    //Saves the ID of the selected user in localStorage
    $("#personer").on("click", "input", function () {
        localStorage.setItem("loginID", this.id);
    });

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
            alert("Vælg venligst en rolle"); return false;
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

async function loadUsers(){

    console.log("her")

    //Display loader
    $("#loader").html("<div id='loading'></div>")

    //fetch and save user data
    await loadUser("Administrator");
    await loadUser("Farmaceut");
    await loadUser("Produktionsleder");
    await loadUser("Laborant");

    //Remove loader
    $("#loader").html("")

}

async function loadUser(role, redo=0) {

     await $.ajax({
        url: "/BoilerPlate_war_exploded/rest/user/getRole?role=" + role,
        type: "GET",
        async: true,
        contentType: "application/json",
        dataType: "json",
        success: function(data){ createTable(data, role)},
        error: async function (response, error) {
            if (redo==0) {
                alert("Kunne ikke forbinde til databasen. Prøver igen");
                console.log(response);
                await loadUser(role, 1);
            }else if (redo < 4) {
                console.log(response);
                await sleep(500);
                await loadUser(role, redo + 1);

            } else {
                alert("Fejlede 5 forsøg i træk. Kontakt System administratoren.")
                console.log(response);
            }
        }
    });

}

function createTable(data, role){
    //Variable to hold all the tabel rows
    let tabelData = "";

    if(role === "Administrator"){
        tabelData += "<tr>";
        tabelData += "<td><input type = 'radio' name = 'bruger' id ='" + 0 + "'></td>";
        tabelData += "<td><Label for ='" + 0 + "'>" + 0 + "</Label></td>";
        tabelData += "<td><Label for ='" + 0 + "'>root</Label></td>";
        tabelData += "</tr>";
    }

    //Loop through
    $.each(data, function (key, value) {
        if (value.aktiv) {
            const userID = value.userID;

            //Uses userID for label reference
            tabelData += "<tr>";
            tabelData += "<td><input type = 'radio' name = 'bruger' id ='" + userID + "'></td>";
            tabelData += "<td><Label for ='" + userID + "'>" + userID + "</Label></td>";
            tabelData += "<td><Label for ='" + userID + "'>" + value.userName + "</Label></td>";
            tabelData += "</tr>";
        }
    });

    localStorage.setItem(role, tabelData)
}


