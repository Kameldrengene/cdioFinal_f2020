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

            const newPBID = maxPBID + 1;
            obj = { pbId: newPBID, status: "Ikke påbegyndt", receptId: activeReceptID, dato: "" }

            let myJson = JSON.stringify(obj);

            await sendAjax(
                "/BoilerPlate_war_exploded/rest/produktbatch/opretProduktbatch",
                () => success(newPBID, activeReceptID),
                err => error(err),
                "POST",
                myJson
            )
        },
        err => error(err),
    )
}

function success(activePBID, activeReceptID){

    alert("Produktbatch oprettet successfuldt.\n" +
        "Du videresendes nu til print-siden")

    //Save the necessary data in localstorage and open the new batch afterwards
    localStorage.setItem("activePBId", activePBID);
    localStorage.setItem("activeStatus", "Ikke påbegyndt");
    localStorage.setItem("activeReceptID", activeReceptID);

    var today = new Date();
    var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
    localStorage.setItem("activeDato", date);

    localStorage.setItem("cameFrom", "OpretProduktbatch");

    switchP("ProduktScreen/Produktbatches/VisProduktbatches/AabenProduktbatch/AabenProduktbatch.html");

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

