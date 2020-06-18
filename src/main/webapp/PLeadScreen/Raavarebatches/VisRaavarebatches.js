$("document").ready(async function(){

    await updateTable();

    $("#visTomme").click(async function () {
          await updateTable();
    });

});

async function updateTable(){

    //Hide table and display loader while updating
    $("#raavarebatches").hide();
    $("#loading").show();

    let path;

    if($("#visTomme").is(":checked"))
        path = "getAlle";
    else
        path = "getAktuelle"

    await sendAjax(
        "/BoilerPlate_war_exploded/rest/Raavarebatch/" + path,
        data => viewTable(data),
        err => alert(err.responseText)
    );

    //Remove loader and reveal table
    $("#loading").hide();
    $("#raavarebatches").show();

};

function viewTable(data){

    //Variable to hold all the tabel rows
    let tabelData = "";

    tabelData += "<tr>";
    tabelData += "<th>Batch ID</th>";
    tabelData += "<th>Råvare ID</th>";
    tabelData += "<th>Oprindelig mængde</th>";
    tabelData += "<th>Aktuel mængde</th>";
    tabelData += "<th>Råvarenavn</th>";
    tabelData += "<th>Leverandør</th>";
    tabelData += "</tr>";

    //Loop through
    $.each(data, function (key, value) {

        //Uses userID for label reference
        tabelData += "<tr>";
        tabelData += "<td>"+value.rbId+"</td>";
        tabelData += "<td>"+value.raavareId+"</td>";

        //Ensure numbers always shows 4 decimal places
        const startM = (Math.round(value.startMaengde * 100) / 100).toFixed(4);
        const aktuelM = (Math.round(value.aktuelMaengde * 100) / 100).toFixed(4);

        tabelData += "<td>"+startM+"</td>";
        tabelData += "<td>"+aktuelM+"</td>";

        tabelData += "<td>"+value.raavareNavn+"</td>";
        tabelData += "<td>"+value.leverandoer+"</td>";
        tabelData += "</tr>";

    });

    $("#raavarebatches").html(tabelData);

}

