$.ajaxSetup({async: false});

var ID = 'delete';
function Personslist() {
    $(document).ready(function () {
        $.getJSON("/BoilerPlate_war_exploded/rest/user/getUsers",function (data) {
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
                //console.log(value);
                var userID = value.userID;
                person_data += '<tr>';
                person_data += '<td>'+userID+'</td>';
                person_data += '<td>'+value.userName+'</td>';
                person_data += '<td>'+value.ini+'</td>';
                person_data += '<td>'+value.cpr+'</td>';
                person_data += '<td>'+value.password+'</td>';
                person_data += '<td>'+value.job+'</td>';
                //if (value.aktiv)

                person_data += '<td>'+ ((value.aktiv) ? "Aktiv" : "Ikke aktiv") +'</td>';
                person_data += "<td><input id='updateuser' class='update' type='button' onclick='confirmUpdate("+userID+")' value='Update'/> </td>";
                person_data += "<td><input id='deleteuser' class='slet' type='button' value='Switch Activity' onclick='testalert("+userID+")'/> </td>";
                person_data +=  '</tr>';
            });
            $('#Person_table').html(person_data);
        });
    });
}

setInterval(Personslist, 3000);

var currentactivity = "";
function getcurrentActivity(ID) { //opdatere brugerens aktivitet
    $(document).ready(function () {
        if(confirm("are you sure, you want to update this user: " + ID+" ?")) {
            $.getJSON("/BoilerPlate_war_exploded/rest/user/getactivity/" + ID + "", function (data) {
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
            var jsondata = {userID: USERID, aktiv: bool};
            $.ajax({
                url: "/BoilerPlate_war_exploded/rest/user/activeUser",
                type: 'PUT',
                contentType: "application/json",
                dataType: 'json',
                data: JSON.stringify(jsondata),
                success: function (data) {
                    Personslist();
                },
                error: function (jqXHR, text, error) {
                    alert(JSON.stringify(jsondata));
                }
            });
        }
    });
}

//updatere brugerens aktivitet
function testalert(ID) {
    getcurrentActivity(ID);
}


var updatedID; //gemmer ID'et
function confirmUpdate(ID) { //metoden sender videre til update html siden.
    $(document).ready(function () {
        if(confirm("are you sure, you want to update this user ?" + ID)){
            switchP('Brugeroversigt/Updatebruger/index.html')
            updatedID = ID;
        }
        else {
            alert("no worries!");
        }
    });
}

function postUpdate() { // metoden bliver kaldt når man trykker på opret knappen
    if(confirm("are sure?")){
        updateUser(); // opdatere brugeren
    }else {
        alert("no changes, you're back!");
        homepage();
    }

}

function updateUser() {
    var UPid = updatedID;
    var UPuser = $("#UpUsername").val();
    var UPini = $("#Upini").val();
    var UPcpr = $("#Upcpr").val();
    var UPpass = $("#Uppass").val();
    var UPjob ="" ;
    var UPboolean = 0;      //todo update boolean in backend
    if($('#Uprole1').is(":checked")){   //todo update job in backend
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
        url: "/BoilerPlate_war_exploded/rest/user/updateUser",
        type: 'PUT',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(UPjsondata),
        success: function (data) {
            homepage();
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(UPjsondata));
        }
    });
}

function postdata() {
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
        if ($('#aktivcheckbox').is(":checked")){   //todo fix aktiv værdier i backend
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
        url: "/BoilerPlate_war_exploded/rest/user/createUser",
        type: 'POST',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsondata),
        success: function (data) {
            homepage();
        },
        error: function (jqXHR, text, error) {
            alert(JSON.stringify(jsondata));
        }
    });
}

function homepage () {
    $(function () {
        function switchPage(page) {
            return $("body").load(page);
        }

        window.setTimeout(switchPage('AdminScreen/index.html'), 5000);
    });
}

function viewlist(headers, link, tableName, btnHtmlfunc) {
    $(document).ready(function () {
        $.getJSON(link /*"/BoilerPlate_war_exploded/rest/Raavare/getRaavarer"*/,function (BEdata) {
            var data = '<tr>\n';
            for (let i = 0; i < headers.length; i++){
                data += '<th>'+ headers[i] +'</th>';
            }
            data += '</tr>';

            $.each(BEdata,function (key,value) {
                //console.log(value);
                data += '<tr>';
                $.each(value, function (key2, inner) {
                    data += '<td>'+inner+'</td>'
                });
                data += btnHtmlfunc(value);
                data +=  '</tr>';
            });
            $('#' + tableName).html(data);
        });
    });
}

function confirmRaavareUpdate(id){
    $(document).ready(function () {
        if(confirm("are you sure, you want to update this råvare "+ id +"?")){
            switchP('FarmaScreen/VisRaavare/UpdateRaavare/index.html')
            updatedID = id;
        }
        else {
            alert("no worries!");
        }
    });
}


function createUser() {
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
            var response = fetch("/BoilerPlate_war_exploded/rest/live/mysql_json/createUser/" + user.username + "/" + user.ini + "/" + user.cpr + "/" + user.pass + "/" + user.roles[0] + "/" + user.roles[1] + "/" + user.roles[2] + "/" + user.roles[3]);
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

function switchP(page) {
    $("body").load(page);
}