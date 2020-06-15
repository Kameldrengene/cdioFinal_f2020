$("document").ready(function () {

    const batchID = localStorage.getItem("activePBId");

    loadBatch(batchID);

    $("#gem").click(function () {
        save();
    })

    //Disable enter
    $("table").keypress(function(e){
        return e.which != 13;
    });

});

async function loadBatch(batchID) {

    await $.ajax({
        url: "/BoilerPlate_war_exploded/rest/produktbatch/getBatch/" + batchID,
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
    tabelData += "<th>Bruger ID</th>";
    tabelData += "<th>Råvarebatch ID</th>";
    tabelData += "<th>Tara</th>";
    tabelData += "<th>Netto</th>";
    tabelData += "</tr>";

    //Loop through
    $.each(data, function (key, value) {

        //Uses userID for label reference
        tabelData += "<tr>";
        tabelData += "<td>"+value.receptId+"</td>";
        tabelData += "<td>"+value.rbID+"</td>";
        tabelData += "<td>"+value.tara+"</td>";
        tabelData += "<td>"+value.netto+"</td>";
        tabelData += "<td> <button class = hvr-buzz id =" + value.pbId + " >Rediger</button>  </td>"
        tabelData += "</tr>";

    });

    $("#produktbatch").html(tabelData);

}

function save(){

    const batchID = $(".brugertable").find("td").eq(0).text();
    const status = $(".brugertable").find("td").eq(1).text();
    const receptID = $(".brugertable").find("td").eq(2).text();
    const brugerID = $(".brugertable").find("td").eq(3).text();
    const råvarebatchID = $(".brugertable").find("td").eq(4).text();
    const tara = $(".brugertable").find("td").eq(5).text();
    const netto = $(".brugertable").find("td").eq(6).text();

    var obj = { pbId: batchID, status: status, receptId: receptID, userId: brugerID, rbID: råvarebatchID, tara: tara, netto: netto };
    var myJson = JSON.stringify(obj);

    $.ajax({
        type: "POST",
        url: "/BoilerPlate_war_exploded/rest/produktbatch/opdaterProduktbatch",
        data: myJson,
        dataType: "json",
        contentType: "application/json; charset=UTF-8"
    });
}