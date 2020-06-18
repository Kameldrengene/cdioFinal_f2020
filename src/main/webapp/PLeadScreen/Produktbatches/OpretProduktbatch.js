$("document").ready(async function () {

    await loadRecepter();

    $("#receptTable").on("click", "button", function () {

        let confirmation = confirm("Opret produktbatch for recept ID: " + this.id);
        if (confirmation == true)
            opretProduktbatch(this.id);
    });
});

async function opretProduktbatch(activeReceptID){

    //todo brug auto increment i SQL
    const activeBatchID =  "18";

    const obj = { pbId: activeBatchID, status: "Ikke påbegyndt", receptId: activeReceptID, dato: "" };
    const myJson = JSON.stringify(obj);

    sendAjax(
        "/BoilerPlate_war_exploded/rest/produktbatch/opretProduktbatch",
        () => success(),
        err => error(err),
        "POST",
        myJson
    )
}

async function loadRecepter() {

    //Hide table and display loader while updating
    $("#receptTable").hide();
    $("#loading").show();

    await sendAjax(
        "/BoilerPlate_war_exploded/rest/Recept/getRecepts",
        () => alert("Produktbatch oprettet successfuldt"),
        err => error(err)
    );

    //Remove loader and reveal table
    $("#loading").hide();
    $("#receptTable").show();

}

function error(err){
    const status = err.status;
    if(status != 500) alert(err.responseText);
    else alert("ERROR: Fejl i forbindelse med håndtering af input. Prøv igen")
}

function viewTable(data){

    //Variable to hold all the tabel rows
    let tabelData = "";

    tabelData += "<tr>";
    tabelData += "<th>Recept ID</th>";
    tabelData += "<th>Receptnavn</th>";
    tabelData += "</tr>";

    //Loop through
    $.each(data, function (key, value) {

        //Uses userID for label reference
        tabelData += "<tr>";
        tabelData += "<td>"+value.receptId+"</td>";
        tabelData += "<td>"+value.receptNavn+"</td>";
        tabelData += "<td> <button class = hvr-buzz id =" + value.receptId + " >Opret</button>  </td>";
        tabelData += "</tr>";

    });

    $("#receptTable").html(tabelData);

}

