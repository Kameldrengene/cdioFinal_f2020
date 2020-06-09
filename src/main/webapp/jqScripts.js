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
                console.log(value);
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
                person_data += "<td><input id='updateuser' class='update' type='button' value='Update'/> </td>";
                person_data += "<td><input id='deleteuser' class='slet' type='button' value='Switch Activity' onclick='switchActivityUser("+userID+")'/> </td>";
                person_data +=  '</tr>';
            });
            $('#Person_table').html(person_data);
        });
    });
}

function Loginlist() {
    $(document).ready(function () {
        $.getJSON("/SinglePageWEB_war_exploded/rest/persons",function (data) {
            var person_data = '<tr>\n' +
                '                <th>ID</th>\n' +
                '                <th>Initials</th>\n' +
                '                <th>Role</th>\n' +
                '                <th>Login</th>\n' +
                '            </tr>';
            $.each(data,function (key,value) {
                if (value.aktiv) {
                    var auserid = value.userID;
                    person_data += '<tr>';
                    person_data += '<td>' + auserid + '</td>';
                    person_data += '<td>' + value.ini + '</td>';
                    person_data += '<td>' + value.job + '</td>';
                    person_data += "<td><input id='updateuser' class='update' type='button' value='login as'/> </td>";
                    person_data += '</tr>';
                }
            });
            $('#Person_table').html(person_data);
        });
    });
}


function switchActivityUser(ID) {
    //console.log("Delete user:" + ID);
    if(confirm("Are you sure you want to switch the activity for user: "+ID+"?")){
        fetch("/BoilerPlate_war_exploded/rest/user/activeUser/"+ID);
        Personslist();
    }
}

function createbutton(value, id) {
    return "</td><td><input id='update' class='edit' type='submit' value=''/> </td>";
}

function deletebutton(ID) {
    var jsondata = {id: ID};
    $.ajax({
        url: "/SinglePageWEB_war_exploded/rest/persons",
        type: 'DELETE',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsondata),
        success: function (data) {
            homepage();
        },
    });

}

function deletedata(id) {
    $(document).ready(function () {
        if(confirm("Are you sure you wanna delete?")){
            deletebutton(id);
        }else
            alert("Try again!")
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
            Ijob = "Projektleder";
        }
        else if ($('#role3').is(":checked")) {
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
        Ijob = "Projektleder";
    }
    else if ($('#role3').is(":checked")) {
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
        url: "/BoilerPlate_war_exploded/rest/live",
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

function failTest() {
    $.ajax({
        url: "/SinglePageWEB_war_exploded/rest/persons",
        type: 'POST',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsondata),
        error: function (jqXHR, text, error) {
            alert(jqXHR.status + text + error);
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