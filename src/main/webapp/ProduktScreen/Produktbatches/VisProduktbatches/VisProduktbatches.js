$("document").ready(async function(){

    await updateTable();

    $("#visAfsluttede").click(async function () {
        await updateTable()
    });

    $("#produktbatches").on("click", "button", function () {

        localStorage.setItem("activePBId", this.id);

        //Get content of table
        const currentRow = $(this).closest("tr");
        const receptID = currentRow.find("td:eq(1)").text();
        const status = currentRow.find("td:eq(2)").text();
        const dato = currentRow.find("td:eq(3)").text();

        //Save content
        localStorage.setItem("activeStatus", status);
        localStorage.setItem("activeReceptID", receptID);
        localStorage.setItem("activeDato", dato);

        switchP("ProduktScreen/Produktbatches/OpretProduktbatch/AabenProduktbatch/AabenProduktbatch.html");
    });

});

async function updateTable(){

    let path;

    if($("#visAfsluttede").is(":checked"))
        path = "getAlle";
    else
        path = "getAktuelle"

    await sendAjax(
        "/BoilerPlate_war_exploded/rest/produktbatch/" + path,
        data => viewTable(data),
        err => error(err)
    )

};

function viewTable(data){

    //Variable to hold all the tabel rows
    let tabelData = "";

    tabelData += "<tr>";
    tabelData += "<th>Batch ID</th>";
    tabelData += "<th>Recept ID</th>";
    tabelData += "<th>Status</th>";
    tabelData += "<th>Oprettelses dato</th>";
    tabelData += "</tr>";

    //Loop through
    $.each(data, function (key, value) {

        //Uses userID for label reference
        tabelData += "<tr>";
        tabelData += "<td>"+value.pbId+"</td>";
        tabelData += "<td>"+value.receptId+"</td>";
        tabelData += "<td>"+value.status+"</td>";
        tabelData += "<td>"+value.dato+"</td>";
        tabelData += "<td> <button class = hvr-buzz id =" + value.pbId + " >Åben</button>  </td>"
        tabelData += "</tr>";

    });

    $("#produktbatches").html(tabelData);

}