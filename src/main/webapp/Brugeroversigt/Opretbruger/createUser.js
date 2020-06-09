async function createUser() {
    var user = {
        username: document.getElementById("username").value,
        ini: document.getElementById("ini").value,
        cpr: document.getElementById("cpr").value,
        pass: document.getElementById("pass").value,
        role:  document.getElementById("role").value,
        active: ((document.getElementById("active").checked) ? true : false)
    };
    if (user.username != "" && user.ini != "" && user.cpr != "" && user.pass != "" && user.role != "") {
        if (confirm("Are you sure you want to create user?")) {
            var response = await fetch("/BoilerPlate_war_exploded/rest/live/mysql_json/createUser/" + user.username + "/" + user.ini + "/" + user.cpr + "/" + user.pass + "/" + user.roles[0] + "/" + user.roles[1] + "/" + user.roles[2] + "/" + user.roles[3]);
            //console.log(JSON.stringify(user));
            //console.log(response.text());
            location.href = "../index.html";
            //load_users();
        }
        //console.log(JSON.stringify(user));
    } else {
        alert("Please fill out all columns");
    }

}

function load_users(){
    document.getElementById("content").innerHTML='<object type="text/html" data="../index.html" ></object>';
}

