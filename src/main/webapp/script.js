//Wait for onload event from window
window.onload = function () {
    document.getElementById("mysql_json").onclick = function () {
        fetch("/BoilerPlate_war_exploded/rest/live/mysql_json").then(function (response) {
            //Wait for response to be parsed as json
            response.json().then(function (json) {
                console.log(json.data[0]);
                document.getElementById("outputDiv").innerHTML = JSON.stringify(json.data[0].userName);
            })
        })
    };
};