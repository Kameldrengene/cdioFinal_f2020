async function updateUser() {
    //const updateID = await fetch("/BoilerPlate_war_exploded/rest/live/update");
    var updateID = localStorage.getItem("userID"); //TODO: FIX for update number find
    var user = {
        username: document.getElementById("usernameU").value,
        ini: document.getElementById("iniU").value,
        cpr: document.getElementById("cprU").value,
        pass: document.getElementById("passU").value,
        roles: ["null", "null", "null", "null"]
    };
    var nrOfRoles = 0;
    for (var x = 1; x < 5; x++) {
        if (document.getElementById("role" + x + "U").checked && document.getElementById("role" + x + "U").value != "null") {
            user.roles[x - 1] = document.getElementById("role" + x + "U").value;
            nrOfRoles++;
        }
    }
    if (user.username != "" && user.ini != "" && user.cpr != "" && user.pass != "" && nrOfRoles != 0) {
        if (confirm("Are you sure you want to update user?")) {
            var response = await fetch("/BoilerPlate_war_exploded/rest/live/mysql_json/updateUser/" + updateID + "/" + user.username + "/" + user.ini + "/" + user.cpr + "/" + user.pass + "/" + user.roles[0] + "/" + user.roles[1] + "/" + user.roles[2] + "/" + user.roles[3]);
            location.href = "brugeroversigt.html";
            //load_users();
        }
    } else if(nrOfRoles > 0){
        alert("Please fill out all columns");
    } else {
        alert("Please select a role")
    }

}

function load_users(){
    document.getElementById("content").innerHTML='<object type="text/html" data="brugeroversigt.html" ></object>';
}