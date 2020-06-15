$("document").ready(function(){

    updateTable();

    $("#visAfsluttede").click(function () {
        updateTable();
    })

    $("#produktbatches").on("click", "button", function () {

        localStorage.setItem("activePBId", this.id);

        //Get content of table
        const currentRow = $(this).closest("tr");
        const receptID = currentRow.find("td:eq(1)").text();
        const status = currentRow.find("td:eq(2)").text();

        //Save content
        localStorage.setItem("activeStatus", status);
        localStorage.setItem("activeReceptID", receptID);

        switchP("PLeadScreen/Produktbatches/AabenProduktbatch.html");
    })

});

function updateTable(){

    let path;

    if($("#visAfsluttede").is(":checked"))
        path = "getAlle";
    else
        path = "getAktuelle"

    loadBatches(path);

};


async function loadBatches(path) {

    await $.ajax({
        url: "/BoilerPlate_war_exploded/rest/produktbatch/" + path,
        type: "GET",
        async: true,
        contentType: "application/json",
        dataType: "json",
        success: data => viewTable(data)
    });

}

function viewTable(data){

    //Variable to hold all the tabel rows
    let tabelData = "";

    tabelData += "<tr>";
    tabelData += "<th>Batch ID</th>";
    tabelData += "<th>Recept ID</th>";
    tabelData += "<th>Status</th>";
    tabelData += "</tr>";

    //Loop through
    $.each(data, function (key, value) {

        //Uses userID for label reference
        tabelData += "<tr>";
        tabelData += "<td>"+value.pbId+"</td>";
        tabelData += "<td>"+value.receptId+"</td>";
        tabelData += "<td>"+value.status+"</td>";
        tabelData += "<td> <button class = hvr-buzz id =" + value.pbId + " >Åben</button>  </td>"
        tabelData += "</tr>";

    });

    $("#produktbatches").html(tabelData);

}