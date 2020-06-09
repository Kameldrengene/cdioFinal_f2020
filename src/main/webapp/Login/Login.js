$("document").ready(function () {
    $("#administrator").click(function () {
        PersonList("Administrator");
    });
});

$("document").ready(function () {
    $("#farmaceut").click(function () {
        PersonList("Farmaceut");
    });
});

$("document").ready(function () {
    $("#produktionsleder").click(function () {
        PersonList("Produktionsleder");
    });
});

$("document").ready(function () {
    $("#laborant").click(function () {
        PersonList("Laborant");
    });
});

function PersonList(role) {

    //Get all the persons
    console.log(role);
    $.getJSON("/BoilerPlate_war_exploded/rest/user/getRole?role=" + role, function(data){

        //Variable to hold all the tabel rows
        var tabelData;
        var counter = 0;

        //Loop through
        $.each(data, function(key, value) {

                tabelData += "<tr>";
                tabelData += "<td><input type = 'radio' name = 'rolle' id ='" + counter + "'></td>";
                tabelData += "<td><Label for ='" + counter + "'>" + value.userID + "</Label></td>"
                tabelData += "<td><Label for ='" + counter + "'>" + value.userName + "</Label></td>";
                tabelData += "</tr>";

                counter++;
        });

        $('#personer').html(tabelData);
    });
}