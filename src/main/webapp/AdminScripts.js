$.ajaxSetup({async: false});

localStorage.setItem('loginID', 'None');
var ID = 'delete';
function Personslist() {
    $(document).ready(sendAjax("/BoilerPlate_war_exploded/rest/user/getUsers", function (data) {
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
        $.each(data,function (key,value) {
            //console.log(value);
            var userID = value.userID;
            person_data += '<tr>';
            person_data += '<td>'+userID+'</td>';
            person_data += '<td>'+value.userName+'</td>';
            person_data += '<td>'+value.ini+'</td>';
            person_data += '<td>'+value.password+'</td>';
            person_data += '<td>'+value.job+'</td>';
            //if (value.aktiv)

            person_data += '<td>'+ ((value.aktiv) ? "Aktiv" : "Ikke aktiv") +'</td>';
            person_data += "<td><input id='updateuser' class='update' type='button' onclick='confirmUserUpdate("+userID+")' value='Update'/> </td>";
            person_data += "<td><input id='deleteuser' class='slet' type='button' value='Switch Activity' onclick='testalert("+userID+")'/> </td>";
            person_data +=  '</tr>';
        });
        $('#Person_table').html(person_data);
        },
        function (data) {
            alert("Error making personList: ERR.NO.02");
            console.log(data);
        })
    );
}

function checkIfNew() {sendAjax("/BoilerPlate_war_exploded/rest/user/getUsers", function (data) {
        var x = JSON.parse(localStorage.getItem("userTable"));
        if (JSON.stringify(x) !== JSON.stringify(data)) {
            localStorage.setItem("userTable", JSON.stringify(data));
            console.log("Updated");
            Personslist();
        } else{
            console.log("Not updated");
        }
    },
    function (data) {
        alert("Error Checking if new User Data: ERR.NO.03");
        console.log(data);
    });
}
setInterval(checkIfNew, 3000);

var currentactivity = "";
function getcurrentActivity(ID) { //opdatere brugerens aktivitet
    $(document).ready(function () {
        if(confirm("are you sure, you want to update user " + ID+"?")) {
            sendAjax("/BoilerPlate_war_exploded/rest/user/getactivity/" + ID, function (data) {
                currentactivity = data;
                console.log(currentactivity);
            }, function (data) {
                alert("Error getting activity: ERR.NO.05");
                console.log(data);
            });
            var USERID = ID;
            const jsondata = {userID: USERID + "", aktiv: !currentactivity + ""};
            if(jsondata.userID.toString() !== localStorage.getItem("loginID").toString()){
                sendAjax("/BoilerPlate_war_exploded/rest/user/activeUser",function (data) {
                    Personslist();
                },function (data) {
                    alert("Error changing activity: ERR.NO.04");
                    console.log(data);
                    console.log(jsondata)
                }, "PUT", JSON.stringify(jsondata));
            } else{
                alert("Unable to change activity on self");
            }
        }
    });
}

//updatere brugerens aktivitet
function testalert(ID) {
    getcurrentActivity(ID);
}


var updatedID; //gemmer ID'et
function confirmUserUpdate(ID) { //metoden sender videre til update html siden.
    $(document).ready(function () {
        if(confirm("are you sure, you want to update user " + ID + "?")){
            updatedID = ID;
            switchP('Brugeroversigt/Updatebruger/index.html')
            //load info from user into page
            $(document).ready(function () {sendAjax("/BoilerPlate_war_exploded/rest/user/getUser/" + updatedID, function (data) {
                document.getElementById("username").value = data.userName;
                document.getElementById("ini").value =  data.ini;
                document.getElementById("pass").value =  data.password;
                if (data.job === "Administrator") {
                    document.getElementById("role1").checked = "checked";
                }else if (data.job === "Farmaceut") {
                    document.getElementById("role2").checked = "checked";
                }else if (data.job === "Produktionsleder") {
                    document.getElementById("role3").checked = "checked";
                }else if (data.job === "Laborant") {
                    document.getElementById("role4").checked = "checked";
                }else {
                    alert("Error: No or wrong role");
                }

                if (data.aktiv){
                    document.getElementById("aktivcheckbox").checked = "checked";
                } else {
                    document.getElementById("aktivcheckboxno").checked = "checked";
                }


            }, function (data) {
                alert("Error getting user by ID: ERR:NO:08")
                console.log(data);
            })});
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

function userCheck(){
    var errorMsg = "";
    const UPuser = $("#username").val();
    if(UPuser.length < 2 || UPuser.length > 20) {console.log("why in here!!!"); errorMsg += "Please enter a username between 2-20 characters \n";}
    const UPini = $("#ini").val();
    if(UPini.length < 2 || UPini.length > 4) {errorMsg += "Please enter initials between 2-4 characters \n";}
    const UPpass = $("#pass").val();
    if(UPpass.length < 6  || UPpass.length > 50) {errorMsg += "Please enter a password between 6-50 characters \n";}
    return errorMsg;
}

function updateUser() {
    var errorMsg = userCheck();
    var UPid = updatedID;
    var UPuser = $("#username").val();
    var UPini = $("#ini").val();
    var UPpass = $("#pass").val();
    var UPjob ="" ;
    var UPboolean = 0;
    if($('#role1').is(":checked")){
        UPjob = "Administrator";
    }else if ($('#role2').is(":checked")){
        UPjob = "Farmaceut";
    }else if ($('#role3').is(":checked")){
        UPjob = "Produktionsleder";
    }
    else if ($('#role4').is(":checked")) {
        UPjob = "Laborant";
    } else {
        errorMsg += "No role selected \n";
    }
    if ($('#yes').is(":checked")){
        UPboolean = 1;
    }else if ($('#no').is(":checked")){
        UPboolean = 0;
    }
    var UPjsondata = {userID: UPid, userName: UPuser, ini: UPini, password: UPpass, job: UPjob, aktiv: UPboolean};
    console.log(UPjsondata);
    if(errorMsg != ""){
        alert(errorMsg);
    } else {
        sendAjax("/BoilerPlate_war_exploded/rest/user/updateUser", function (data) {
            adminHomepage()
            alert("Success!")
        }, function (data) {
            alert("Error updating user: ERR.NO.06");
            console.log(data);
        }, "PUT", JSON.stringify(UPjsondata));
    }
}

function postUserData() {
    $(document).ready(function () {
        var Iuser = $("#username").val();
        var Iini = $("#ini").val();
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
    var errorMsg = userCheck();
    var Iuser = $("#username").val();
    var Iini = $("#ini").val();
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
    else {
        errorMsg += errorMsg += "No role selected \n";
    }
    if ($('#aktivcheckbox').is(":checked")){
        boolean = 1;
    }else if ($('#aktivcheckboxno').is(":checked")){
        boolean = 0;
    }
    var statuscode;
    var jsondata = {userName: Iuser, ini: Iini, password: Ipass, job: Ijob, aktiv: boolean};
    if(errorMsg.length > 1){
        alert(errorMsg);
    } else {
        sendAjax("/BoilerPlate_war_exploded/rest/user/createUser", function (data) {
            adminHomepage();
        }, function (data) {
            alert("Error creating user: ERR.NO.07");
            console.log(data);
        }, "POST", JSON.stringify(jsondata))
    }

}

function adminHomepage () {
    $(function () {
        function switchPage(page) {
            return $("body").load(page);
        }

        window.setTimeout(switchPage('AdminScreen/index.html'), 5000);
    });
}
