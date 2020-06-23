$.ajaxSetup({async: false});

var ID = 'delete';

async function Personslist() {
    await sendAjax("/BoilerPlate_war_exploded/rest/user/getUsers", function (data) {
            localStorage.setItem("userTable", JSON.stringify(data));
            var person_data = '<tr>\n' +
                '                <th>ID</th>\n' +
                '                <th>Name</th>\n' +
                '                <th>Initials</th>\n' +
                '                <th>Password</th>\n' +
                '                <th>Role</th>\n' +
                '                <th>Activivity</th>\n' +
                '                <th>Update</th>\n' +
                '                <th>ActiveSwitch</th>\n' +
                '            </tr>';
            $.each(data, function (key, value) {
                //console.log(value);
                var userID = value.userID;
                person_data += '<tr>';
                person_data += '<td>' + userID + '</td>';
                person_data += '<td>' + value.userName + '</td>';
                person_data += '<td>' + value.ini + '</td>';
                person_data += '<td>' + value.password + '</td>';
                person_data += '<td>' + value.job + '</td>';
                //if (value.aktiv)

                person_data += '<td>' + ((value.aktiv) ? "Aktiv" : "Ikke aktiv") + '</td>';
                person_data += "<td><input id='updateuser' class='hvr-pop' type='button' onclick='confirmUserUpdate(" + userID + ")' value='Opdater'/> </td>";
                person_data += "<td><input id='deleteuser' class='slet' type='button' value='Skift aktivitet' onclick='getcurrentActivity(" + userID + ")'/> </td>";
                person_data += '</tr>';
            });
            $('#Person_table').html(person_data);
        },
        function (data) {
            alert("Error making personList: ERR.NO.02");
            console.log(data);
        })
}

function checkIfNew() {
    if ($('#Person_table').length) {

        $.ajax({
            url: "/BoilerPlate_war_exploded/rest/user/getUsers",
            type: "GET",
            async: false,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                const x = JSON.parse(localStorage.getItem("userTable"));
                if (JSON.stringify(x) !== JSON.stringify(data)) {
                    console.log("Updated");
                    Personslist();
                } else {
                    console.log("Not updated");
                }
            },
            error: function (data) {
                alert("Error Checking if new User Data: ERR.NO.03");
                console.log(data);
            }
        });
    }
}

if(localStorage.getItem("adminLoaded") === null) {
    setInterval(checkIfNew, 3000);
    localStorage.setItem("adminLoaded", 1);
}


var currentactivity = "";

function getcurrentActivity(ID) { //opdatere brugerens aktivitet
    $(document).ready(function () {
        if (confirm("Er du sikker på at du vil opdatere bruger: " + ID + " aktivitet?")) {
            sendAjax("/BoilerPlate_war_exploded/rest/user/getactivity/" + ID, function (data) {
                currentactivity = data;
                console.log(currentactivity);
            }, function (data) {
                alert("Fejl i valg af bruger aktivitet opdatering");
                console.log(data);
            });
            var USERID = ID;
            const jsondata = {userID: USERID + "", aktiv: !currentactivity + ""};
            if (jsondata.userID.toString() !== localStorage.getItem("loginID").toString()) {
                sendAjax("/BoilerPlate_war_exploded/rest/user/activeUser", function (data) {
                    checkIfNew();
                }, function (data) {
                    alert("Fejl i skift af bruger tilstand");
                    console.log(data);
                    console.log(jsondata)
                }, "PUT", JSON.stringify(jsondata));
            } else {
                alert("Du kan ikke ændre din egen aktivitet");
            }
        }
    });
}

var updatedID; /** gemmer ID'et for personnen man opdaterer til senere brug */
function confirmUserUpdate(ID) { /** metoden sender videre til update html siden. */
    $(document).ready(function () {
        if (confirm("Er du sikker på at du vil opdatere bruger: " + ID + "?")) {
            updatedID = ID;
            switchP("AdminScreen/Brugeroversigt/Updatebruger/UpdateBruger.html")
            //load info from user into page
            $(document).ready(function () {
                sendAjax("/BoilerPlate_war_exploded/rest/user/getUser/" + updatedID, function (data) { /**Her indsættes startverdierne for den bruger man opdaterer på */
                    document.getElementById("username").value = data.userName;
                    document.getElementById("ini").value = data.ini;
                    document.getElementById("pass").value = data.password;
                    if (data.job === "Administrator") {
                        document.getElementById("role1").checked = "checked";
                    } else if (data.job === "Farmaceut") {
                        document.getElementById("role2").checked = "checked";
                    } else if (data.job === "Produktionsleder") {
                        document.getElementById("role3").checked = "checked";
                    } else if (data.job === "Laborant") {
                        document.getElementById("role4").checked = "checked";
                    } else {
                        alert("Ingen rolle valgt!");
                    }

                    if (data.aktiv) {
                        document.getElementById("aktivcheckbox").checked = true;
                    }


                }, function (data) {
                    alert("Fejl i opdatering af bruger")
                    console.log(data);
                })
            });
        }
    });
}


function userCheck() { /** Tester om data indtastet er i korrekt format */
    var errorMsg = "";
    const UPuser = $("#username").val();
    if (UPuser.length < 2 || UPuser.length > 20) {
        errorMsg += "Indtast et brugernavn mellem 2-20 karakterer \n";
    }
    const UPini = $("#ini").val();
    if (UPini.length < 2 || UPini.length > 4) {
        errorMsg += "Indtast initialer mellem 2-4 karakterer \n";
    }
    const UPpass = $("#pass").val();
    if (UPpass.length < 6 || UPpass.length > 50) {
        errorMsg += "Indtast et password mellem 6-50 karakterer \n";
    }
    return errorMsg;
}

function userHandler(z) { /** behandler data fra User og sender det afsted til backenden. */
    var APILink = "/BoilerPlate_war_exploded/rest/user/";
    var requestType = "";
    var alertMsg = "";
    if (z === "Update") {
        APILink += "updateUser";
        requestType = "PUT";
        alertMsg = "Fejl i bruger update";
    } else if (z === "Create") {
        APILink += "createUser";
        requestType = "POST";
        alertMsg = "Fejl i bruger oprettelse";
    }

    var errorMsg = userCheck(); /** indhenter data */
    var UPid = updatedID;
    var UPuser = $("#username").val();
    var UPini = $("#ini").val();
    var UPpass = $("#pass").val();
    var UPjob = "";
    var UPboolean = 0;
    if ($('#role1').is(":checked")) {
        UPjob = "Administrator";
    } else if ($('#role2').is(":checked")) {
        UPjob = "Farmaceut";
    } else if ($('#role3').is(":checked")) {
        UPjob = "Produktionsleder";
    } else if ($('#role4').is(":checked")) {
        UPjob = "Laborant";
    } else {
        errorMsg += "Ingen rolle valgt \n";
    }
    $('#aktivcheckbox').is(':checked') ? UPboolean = 1 : UPboolean = 0;
    var jsondata = {userID: UPid, userName: UPuser, ini: UPini, password: UPpass, job: UPjob, aktiv: UPboolean};
    console.log(jsondata);
    if (errorMsg != "") {
        alert(errorMsg);
    } else {
        sendAjax(APILink, function (data) { /** Opdaterer/opretter bruger i db */
            adminHomepage()
        }, function (data) {
            alert(alertMsg);
            console.log(data);
        }, requestType, JSON.stringify(jsondata));
    }
}

function adminHomepage() { /** sender tilbage til adminHomepage */ //TODO Redundant?? ift switchP?
    $(function () {
        function switchPage(page) {
            return $("body").load(page);
        }

        window.setTimeout(switchPage('AdminScreen/Admin.html'), 5000);
    });
}
