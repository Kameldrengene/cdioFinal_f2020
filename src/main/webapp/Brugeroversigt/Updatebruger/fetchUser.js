async function fetchUser() {
    const response = await fetch("/BoilerPlate_war_exploded/rest/live/mysql_json");
    const json = await response.json();
    var foundUser;
    //const updateID = await fetch("/BoilerPlate_war_exploded/rest/live/update");
    var updateID = localStorage.getItem("userID"); //TODO: FIX for update number find

    for (const testUser of json.data) {
        if (testUser.userID == updateID) {
            foundUser = testUser;
            break;
        }
    }

    document.getElementById("usernameU").value = foundUser.userName;
    document.getElementById("iniU").value =  foundUser.ini;
    document.getElementById("cprU").value =  foundUser.cpr;
    document.getElementById("passU").value =  foundUser.password;

    for (const role of foundUser.roles){
        if (role == "Admin"){
            document.getElementById("role1U").checked = true;
        } else if (role == "Foreman"){
            document.getElementById("role2U").checked = true;
        } else if (role == "Operator"){
            document.getElementById("role3U").checked = true;
        } else if (role == "Pharmacist"){
            document.getElementById("role4U").checked = true;
        }
    }

}

fetchUser()




