$("document").ready(async function () {

    await loadRecepter();

    $("#receptTable").on("click", "button", function () {

        let confirmation = confirm("Opret produktbatch for recept ID: " + this.id);
        if (confirmation == true)

            try{
                opretProduktbatch(this.id);
            }catch(err){
                console.log(err);
                alert(err.responseText);
            }

    })

});


async function opretProduktbatch(activeReceptID){

    //todo brug auto increment i SQL
    const activeBatchID =  "18";

    const obj = { pbId: activeBatchID, status: "Ikke påbegyndt", receptId: activeReceptID, dato: "" };
    const myJson = JSON.stringify(obj);

    await $.ajax({
        url: "/BoilerPlate_war_exploded/rest/produktbatch/opretProduktbatch",
        type: "POST",
        async: true,
        contentType: "application/json",
        dataType: "json",
        data: myJson,
    });

}

async function loadRecepter() {

    //Hide table and display loader while updating
    $("#receptTable").hide();
    $("#loading").show();

    await sendAjax(
        "/BoilerPlate_war_exploded/rest/Recept/getRecepts",
        function (data) {
            viewTable(data)
        },
        function (data) {
            alert("Error getting all/actual recept: ERR.NO.XX");
            console.log(data);
        }
    );

    //Remove loader and reveal table
    $("#loading").hide();
    $("#receptTable").show();

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

