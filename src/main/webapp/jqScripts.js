var ID = 'delete';
function Personslist() {
    $(document).ready(function () {
        $.getJSON("/SinglePageWEB_war_exploded/rest/persons",function (data) {
            var person_data = '';
            $.each(data,function (key,value) {
                var id = value.id;
                person_data += '<tr>';
                person_data += '<td>'+id+'</td>';
                person_data += '<td>'+value.name+'</td>';
                person_data += '<td>'+value.age+'</td>';
                person_data += '<td>'+value.address+'</td>';
                person_data += "</td><td><input id='update' class='edit' type='submit' value='update'/> </td>";
                person_data += "</td><td><input id='' class='edit' type='submit' value='delete'/> </td>";
                person_data +=  '</tr>';

            });
            $('#Person_table').append(person_data);
        });
    });
}

function Loginlist() {
    $(document).ready(function () {
        $.getJSON("/SinglePageWEB_war_exploded/rest/persons",function (data) {
            var person_data = '';
            $.each(data,function (key,value) {
                person_data += '<tr>';
                person_data += '<td>'+value.id+'</td>';
                person_data += '<td>'+value.name+'</td>';
                person_data += '<td>'+value.name+'</td>';
                person_data += "<td><input id='login' class='edit' type='submit' value='Login as'/></td>";
                person_data +=  '</tr>';

            });
            $('#Person_table').append(person_data);
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