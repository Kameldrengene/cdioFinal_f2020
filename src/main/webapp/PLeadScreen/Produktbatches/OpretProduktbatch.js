$("document").ready(async function () {

    await loadRecepter();

    $("#receptTable").on("click", "button", function () {

        let confirmation = confirm("Opret produktbatch for recept ID: " + this.id);
        if (confirmation == true)
            opretProduktbatch(this.id);
    });
});

async function opretProduktbatch(activeReceptID){

    let obj;

    //First find out what the next product batch number should be
    await sendAjax(
        "/BoilerPlate_war_exploded/rest/produktbatch/getMaxPBID",
        async maxPBID => {

            const newPBDI = maxPBID +1;
            obj = { pbId: newPBDI, status: "Ikke påbegyndt", receptId: activeReceptID, dato: "" }

            let myJson = JSON.stringify(obj);

            await sendAjax(
                "/BoilerPlate_war_exploded/rest/produktbatch/opretProduktbatch",
                () => alert("Produktbatch oprettet successfuldt"),
                err => error(err),
                "POST",
                myJson
            )
        },
        err => error(err),
    )
}

async function loadRecepter() {

    await sendAjax(
        "/BoilerPlate_war_exploded/rest/Recept/getRecepts",
        data => viewTable(data),
        err => error(err)
    );

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

