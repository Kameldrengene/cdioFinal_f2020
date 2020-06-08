async function createUser() {
    var user = {
        username: document.getElementById("username").value,
        ini: document.getElementById("ini").value,
        cpr: document.getElementById("cpr").value,
        pass: document.getElementById("pass").value,
        roles: ["null", "null", "null", "null"]
    };
    var nrOfRoles = 0;
    for (var x = 1; x < 5; x++) {
        if (document.getElementById("role" + x).checked && document.getElementById("role" + x).value != "null") {
            user.roles[x - 1] = document.getElementById("role" + x).value;
            nrOfRoles++;
        }
    }
    if (user.username != "" && user.ini != "" && user.cpr != "" && user.pass != "" && nrOfRoles != 0) {
        if (confirm("Are you sure you want to create user?")) {
            var response = await fetch("/BoilerPlate_war_exploded/rest/live/mysql_json/createUser/" + user.username + "/" + user.ini + "/" + user.cpr + "/" + user.pass + "/" + user.roles[0] + "/" + user.roles[1] + "/" + user.roles[2] + "/" + user.roles[3]);
            console.log(JSON.stringify(user));
            console.log(response.text());
            location.href = "brugeroversigt.html";
            //load_users();
        }
        console.log(JSON.stringify(user));
    } else if(nrOfRoles > 0){
        alert("Please fill out all columns");
    } else {
        alert("Please select a role")
    }

}

function load_users(){
    document.getElementById("content").innerHTML='<object type="text/html" data="brugeroversigt.html" ></object>';
}

