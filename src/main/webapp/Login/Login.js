$("document").ready(function () {
    $("#administrator").click(function () {
        PersonList();
    });
});

function PersonList() {

    $.getJSON("/BoilerPlate_war_exploded/rest/live/getUsers", function(data){
        var person_data;
        $.each(data,function (key, inner) {
            $.each(inner,function (key, value) {
                var userID = value.userID;
                person_data += '<tr>';
                person_data += '<td>'+userID+'</td>';
                person_data += '<td>'+value.userName+'</td>';

            });
        });
        $('#personer').html(person_data);
    });
}