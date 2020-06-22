
$("document").ready(async function () {

    //To prevent false logins
    localStorage.setItem('loginID', 'None');

    //Keeps the different users in local storage. So they don't have to be reloaded multiple times
    //Await to ensure functionality is only added after resources are fetched
    await loadUsers();

    //Hide user table until a role is selected
    $(".brugertable").hide();

    $(".rolletabel").on('click','input',function () {
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

    //Switches to the right page when "sign in" is pressed
    $(".hvr-buzz").click(function () {
        const loginRole = localStorage.getItem("loginRole");
        const ID = localStorage.getItem("loginID");

        if (ID === "None") {
            alert("Vælg venligst en rolle");
        } else {
            localStorage.setItem("localID",ID);
            if (loginRole == "admin")
                switchP("AdminScreen/Admin.html");
            else if (loginRole == "farma")
                switchP("FarmaScreen/Farma.html");
            else if (loginRole == "prodLeder")
                switchP("ProduktScreen/ProduktScreen.html")
            else if (loginRole == "laborant")
                switchP("LabScreen/Lab.html");
        }
    });

    //Activate theme switching functionality
    const darkTheme = "<link rel=\"stylesheet\" id=\"theme\" type=\"text/css\" href=\"defaultDark.css\">";
    const lightTheme = "<link rel=\"stylesheet\" id=\"theme\" type=\"text/css\" href=\"defaultLight.css\">";
    let currentTheme = "dark";

    $(".slider").click(function () {

        if(currentTheme.localeCompare("dark") != 0){
            // console.log("2")
            currentTheme = "dark";
            $("#theme").replaceWith(darkTheme);

        } else {
            // console.log("3")
            currentTheme = "light";
            $("#theme").replaceWith(lightTheme);

        }
    });

});

async function loadUsers(){

    //fetch and save user data
    await loadUser("Administrator");
    await loadUser("Farmaceut");
    await loadUser("Produktionsleder");
    await loadUser("Laborant");

}

async function loadUser(role, redo=0) {

    $(".loads").show()

    await $.ajax({
        url: "/BoilerPlate_war_exploded/rest/user/getRole?role=" + role,
        type: "GET",
        async: true,
        contentType: "application/json",
        dataType: "json",
        success: data => createTable(data, role),
        error: err => error(err)
    });

    $(".loads").hide()

}


async function createTable(data, role){

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


