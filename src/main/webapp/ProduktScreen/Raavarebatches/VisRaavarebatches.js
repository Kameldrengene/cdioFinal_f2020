//Created by Mikkel

$("document").ready(async function(){

    await updateTable();

    $("#visTomme").click(async function () {
          await updateTable();
    });

});

async function updateTable(){

    let path;

    //Check is "vis tomme" is checked
    if($("#visTomme").is(":checked"))
        path = "getAlle";
    else
        path = "getAktuelle"

    //Get the data
    await sendAjax(
        "/BoilerPlate_war_exploded/rest/Raavarebatch/" + path,
        data => viewTable(data),
        err => error(err)
    );

};

function viewTable(data){

    //Variable to hold all the tabel rows
    let tabelData = "";

    //Header
    tabelData += "<tr>";
    tabelData += "<th>Batch ID</th>";
    tabelData += "<th>Råvare ID</th>";
    tabelData += "<th>Råvarenavn</th>";
    tabelData += "<th>Oprindelig mængde [kg]</th>";
    tabelData += "<th>Aktuel mængde [kg]</th>";
    tabelData += "<th>Leverandør</th>";
    tabelData += "</tr>";

    //Loop through data and add dynamically
    $.each(data, function (key, value) {

        tabelData += "<tr>";
        tabelData += "<td>"+value.rbId+"</td>";
        tabelData += "<td>"+value.raavareId+"</td>";
        tabelData += "<td>"+value.raavareNavn+"</td>";

        //Ensure numbers always show 4 decimal places
        const startM = value.startMaengde.toFixed(4);
        const aktuelM = value.aktuelMaengde.toFixed(4);

        tabelData += "<td>"+startM+"</td>";
        tabelData += "<td>"+aktuelM+"</td>";

        tabelData += "<td>"+value.leverandoer+"</td>";
        tabelData += "</tr>";

    });

    //Fill table
    $("#raavarebatches").html(tabelData);

}

