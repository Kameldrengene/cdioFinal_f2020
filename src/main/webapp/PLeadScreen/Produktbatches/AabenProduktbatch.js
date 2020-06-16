$("document").ready(function () {

    const batchID = localStorage.getItem("activePBId");
    const receptID = localStorage.getItem("receptID");
    const status = localStorage.getItem("status");

    $("#batchID").html("Batch ID: " + batchID);
    $("#receptID").html("Recept ID: " + receptID);
    $("#status").html("Status: " + status);

    loadBatch(batchID);

    $("#produktbatch").on("click", "button", function () {
        localStorage.setItem("activeRBId", this.id);
        switchP("PLeadScreen/Produktbatches/RedigerProduktbatch.html");
    })

});

async function loadBatch(batchID) {
    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatch/" + batchID, function (data) {
        viewTable(data);
    }, function (data) {
        alert("Error loading produktbatches: ERR.NO.11");
        console.log(data);
    })

}

function viewTable(data){

    //Variable to hold all the tabel rows
    let tabelData = "";

    tabelData += "<tr>";
    tabelData += "<th>Råvarebatch ID</th>";
    tabelData += "<th>Bruger ID</th>";
    tabelData += "<th>Tara</th>";
    tabelData += "<th>Netto</th>";
    tabelData += "</tr>";

    //Loop through
    $.each(data, function (key, value) {

        //Uses userID for label reference
        tabelData += "<tr>";
        tabelData += "<td>"+value.rbID+"</td>";
        tabelData += "<td>"+value.userId+"</td>";
        tabelData += "<td>"+value.tara+"</td>";
        tabelData += "<td>"+value.netto+"</td>";
        tabelData += "<td> <button class = hvr-buzz id =" + value.rbID + " >Rediger</button>  </td>"
        tabelData += "</tr>";

    });

    $("#produktbatch").html(tabelData);

}
