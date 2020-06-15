$("document").ready(function(){

    updateTable();

    $("#visAfsluttede").click(function () {
        updateTable();
    })

    $("#produktbatches").on("click", "button", function () {
        localStorage.setItem("activePBId", this.id);
        switchP("PLeadScreen/Produktbatches/RedigerProduktbatches.html");
    })

});

function updateTable(){

    let path;

    if($("#visAfsluttede").is(":checked"))
        path = "getAlle";
    else
        path = "getAktuelle"

    loadBatch(path);

};


async function loadBatch(path) {

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
    tabelData += "<th>Status</th>";
    tabelData += "</tr>";

    //Loop through
    $.each(data, function (key, value) {

        //Uses userID for label reference
        tabelData += "<tr>";
        tabelData += "<td>"+value.pbId+"</td>";
        tabelData += "<td>"+value.status+"</td>";
        tabelData += "<td> <button class = hvr-buzz id =" + value.pbId + ">Rediger</button>  </td>"
        tabelData += "</tr>";

    });

    $("#produktbatches").html(tabelData);

}