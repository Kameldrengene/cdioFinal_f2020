var ID = 'delete';
function Personslist() {
    $(document).ready(function () {
        $.getJSON("/BoilerPlate_war_exploded/rest/live/mysql_json",function (data) {
            var person_data = '';
            $.each(data,function (key,inner) {
                $.each(inner,function (key, value) {
                    var id = value.userID;
                    person_data += '<tr>';
                    person_data += '<td>'+id+'</td>';
                    person_data += '<td>'+value.userName+'</td>';
                    person_data += '<td>'+value.ini+'</td>';
                    person_data += '<td>'+value.cpr+'</td>';
                    person_data += '<td>'+value.password+'</td>';
                    person_data += '<td>'+value.rolesToString+'</td>';
                    person_data += "<td><input id='updateuser' class='update' type='button' value='update'/> </td>";
                    person_data += "<td><input id='deleteuser' class='slet' type='button' value='slet'/> </td>";
                    person_data +=  '</tr>';
                    console.log(data);
                    console.log(key, value);
                });
            });
            $('#Person_table').append(person_data);
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
            $.each(data,function (key,inner) {
                //TODO: Insert if statement to check if active
                $.each(inner,function (key, value) {
                    var auserid = value.userID;
                    person_data += '<tr>';
                    person_data += '<td>'+auserid+'</td>';
                    person_data += '<td>'+value.ini+'</td>';
                    person_data += '<td>'+value.rolesToString+'</td>';
                    person_data += "<td><input id='updateuser' class='update' type='button' value='login as'/> </td>";
                    person_data +=  '</tr>';
                });
            });
            $('#Person_table').html(person_data);
        });
    });
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
        if(confirm("Are you sure?")){
            successTest();
        }else
            alert("Try again!");
    });
}

function successTest() {
    var Pid = $("#id").val();
    var Pname = $("#name").val();
    var Page = $("#age").val();
    var Paddress = $("#address").val();
    var statuscode;
    var jsondata = {id: Pid, name: Pname, age: Page, address: Paddress};
    $.ajax({
        url: "/SinglePageWEB_war_exploded/rest/persons",
        type: 'POST',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(jsondata),
        success: function (data) {
            homepage();
        },
        error: function (jqXHR, text, error) {
            alert(jqXHR.status + text + error);
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

        window.setTimeout(switchPage('Persons.html'), 5000);
    });
}
function switchP(page) {
    $("body").load(page);
}