$.ajaxSetup({async: false});

localStorage.setItem('loginID', 'None');
var ID = 'delete';
function loadUserTable() {
    $(document).ready(function () {
        $.getJSON("/BoilerPlate_war_exploded/rest/user/getUserList",function (data) {
            var person_data = '<tr>\n' +
                '                <th>ID</th>\n' +
                '                <th>Name</th>\n' +
                '                <th>Initials</th>\n' +
                '                <th>CPR</th>\n' +
                '                <th>Password</th>\n' +
                '                <th>Role</th>\n' +
                '                <th>Activivity</th>\n' +
                '                <th>Update</th>\n' +
                '                <th>ActiveSwitch</th>\n' +
                '            </tr>';
            $.each(data,function (key,value) {
                var userID = value.userID;
                person_data += '<tr>';
                person_data += '<td>'+userID+'</td>';
                person_data += '<td>'+value.userName+'</td>';
                person_data += '<td>'+value.ini+'</td>';
                person_data += '<td>'+value.cpr+'</td>';
                person_data += '<td>'+value.password+'</td>';
                person_data += '<td>'+value.job+'</td>';
                person_data += '<td>'+ ((value.aktiv) ? "Aktiv" : "Ikke aktiv") +'</td>';
                person_data += "<td><input id='updateuser' class='update' type='button' onclick='confirmUserUpdate("+userID+")' value='Update'/> </td>";
                person_data += "<td><input id='deleteuser' class='slet' type='button' value='Switch Activity' onclick='testalert("+userID+")'/> </td>";
                person_data +=  '</tr>';
            });
            $('#Person_table').html(person_data);
        });
    });
}

function checkIfNew() {
    $.get("/BoilerPlate_war_exploded/rest/user/getUserList", function (data) {
        var x = JSON.parse(localStorage.getItem("userTable"));
        if (JSON.stringify(x) !== JSON.stringify(data)) {
            localStorage.setItem("userTable", JSON.stringify(data));
            console.log("Updated");
            loadUserTable();
        } else{
            console.log("Not updated");
        }
    });
}
setInterval(checkIfNew, 3000);

var currentactivity = "";
function opdaterActivity(ID) { //opdatere brugerens aktivitet
    $(document).ready(function () {
        if(confirm("are you sure, you want to update user " + ID +"?")) {
            $.getJSON("/BoilerPlate_war_exploded/rest/user/switchActivity/" + ID + "", function (data) {
                currentactivity = data;
            });
            var USERID = ID;
            var bool = 1;
            if (currentactivity === "false") {
                bool = 1;
            }
            if (currentactivity === "true") {
                bool = 0;
            }
            var jsonData = {userID: USERID, aktiv: bool};
            if(jsonData.userID.toString() !== localStorage.getItem("loginID").toString()){
                $.ajax({
                    url: "/BoilerPlate_war_exploded/rest/user/activeUser",
                    type: 'PUT',
                    contentType: "application/json",
                    dataType: 'json',
                    data: JSON.stringify(jsonData),
                    success: function (data) {
                        loadUserTable();
                    },
                    error: function (jqXHR, text, error) {
                        alert(JSON.stringify(jsonData));
                    }
                });
            } else{
                alert("Unable to change activity on self");
            }
        }
    });
}

//updatere brugerens aktivitet
function testalert(ID) {
    opdaterActivity(ID);
}


var updatedID; //gemmer ID'et
function confirmUserUpdate(ID) { //metoden sender videre til update html siden.
    $(document).ready(function () {
        if(confirm("are you sure, you want to update user " + ID + "?")){
            updatedID = ID;
            switchP('Brugeroversigt/Updatebruger/index.html')
            //load info from user into page
            $(document).ready(function () {
                $.getJSON("/BoilerPlate_war_exploded/rest/user/getUser/" + updatedID, function (data) {
                    document.getElementById("UpUsername").value = data.userName;
                    document.getElementById("Upini").value =  data.ini;
                    document.getElementById("Upcpr").value =  data.cpr;
                    document.getElementById("Uppass").value =  data.password;
                    if (data.job === "Administrator") {
                        document.getElementById("Uprole1").checked = "checked";
                    }else if (data.job === "Farmaceut") {
                        document.getElementById("Uprole2").checked = "checked";
                    }else if (data.job === "Produktionsleder") {
                        document.getElementById("Uprole3").checked = "checked";
                    }else if (data.job === "Laborant") {
                        document.getElementById("Uprole4").checked = "checked";
                    }else {
                        alert("Error: No or wrong role");
                    }

                    if (data.aktiv){
                        document.getElementById("Upyes").checked = "checked";
                    } else {
                        document.getElementById("Upno").checked = "checked";
                    }


                })
            });
        }
        else {
            alert("no worries!");
        }
    });
}

function postUserUpdate() { // metoden bliver kaldt når man trykker på opret knappen
    if(confirm("Are you sure?")){
        updateUser(); // opdatere brugeren
    }else {
        alert("No changes, you're back!");
        adminHomepage();
    }

}

function updateUser() {
    var UPid = updatedID;
    var UPuser = $("#UpUsername").val();
    var UPini = $("#Upini").val();
    var UPcpr = $("#Upcpr").val();
    var UPpass = $("#Uppass").val();
    var UPjob ="" ;
    var UPboolean = 0;
    if($('#Uprole1').is(":checked")){
        UPjob = "Administrator";
    }else if ($('#Uprole2').is(":checked")){
        UPjob = "Farmaceut";
    }else if ($('#Uprole3').is(":checked")){
        UPjob = "Produktionsleder";
    }
    else if ($('#Uprole4').is(":checked")) {
        UPjob = "Laborant";
    }
    if ($('#Upyes').is(":checked")){
        UPboolean = 1;
    }else if ($('#Upno').is(":checked")){
        UPboolean = 0;
    }
    var statuscode;
    var UPjsondata = {userID: UPid, userName: UPuser, ini: UPini, cpr: UPcpr, password: UPpass, job: UPjob, aktiv: UPboolean};
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/user/opdaterUser",
        type: 'PUT',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(UPjsondata),
        success: function (data) {
            adminHomepage();
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(UPjsondata));
        }
    });
}

function postUserData() {
    $(document).ready(function () {
        var Iuser = $("#username").val();
        var Iini = $("#ini").val();
        var Icpr = $("#cpr").val();
        var Ipass = $("#pass").val();
        var Ijob ="" ;
        var boolean = 0;
        if($('#role1').is(":checked")){
            Ijob = "Administrator";
        }else if ($('#role2').is(":checked")){
            Ijob = "Farmaceut";
        }else if ($('#role3').is(":checked")){
            Ijob = "Produktionsleder";
        }
        else if ($('#role4').is(":checked")) {
            Ijob = "Laborant";
        }
        if ($('#aktivcheckbox').is(":checked")){
            boolean = 1;
        }else if ($('#aktivcheckboxno').is(":checked")){
            boolean = 0;
        }
        alert(Ijob);
        alert(boolean);
        if(confirm("Are you sure?")){
            successTest();
        }else
            alert("Try again!");
    });
}

function successTest() {
    var Iuser = $("#username").val();
    var Iini = $("#ini").val();
    var Icpr = $("#cpr").val();
    var Ipass = $("#pass").val();
    var Ijob ="" ;
    var boolean = 0;
    if($('#role1').is(":checked")){
        Ijob = "Administrator";
    }else if ($('#role2').is(":checked")){
        Ijob = "Farmaceut";
    }else if ($('#role3').is(":checked")){
        Ijob = "Produktionsleder";
    }
    else if ($('#role4').is(":checked")) {
        Ijob = "Laborant";
    }
    if ($('#aktivcheckbox').is(":checked")){
        boolean = 1;
    }else if ($('#aktivcheckboxno').is(":checked")){
        boolean = 0;
    }
    var statuscode;
    var jsondata = {userName: Iuser, ini: Iini, cpr: Icpr, password: Ipass, job: Ijob, aktiv: boolean};
    $.ajax({
        url: "/BoilerPlate_war_exploded/rest/user/opretUser",
        type: 'POST',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsondata),
        success: function (data) {
            adminHomepage();
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(jsondata));
        }
    });
}

function adminHomepage () {
    $(function () {
        function switchPage(page) {
            return $("body").load(page);
        }

        window.setTimeout(switchPage('AdminScreen/index.html'), 5000);
    });
}
