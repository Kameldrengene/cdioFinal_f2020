$("document").ready(function () {

    const batchID = localStorage.getItem("activePBId");
    const receptID = localStorage.getItem("activeReceptID");
    const status = localStorage.getItem("activeStatus");
    const raavarebatchID = localStorage.getItem("activeRBId");

    $("#batchID").html("Batch ID: " + batchID);
    $("#receptID").html("Recept ID: " + receptID);
    $("#status").html("Status: " + status);
    $("#raavarebatchID").html("Råvarebatch ID: " + raavarebatchID);

    $.getJSON("/BoilerPlate_war_exploded/rest/produktbatch/getBatchLine/" + batchID + "/" + raavarebatchID, function(data) {
        $("#brugerID").html(data.userId);
        $("#tara").html(data.tara);
        $("#netto").html(data.netto);

    })

    $("#gem").click(function () {
        save(batchID, receptID, status, raavarebatchID);
    })

    //Disable enter
    $("table").keypress(function(e){
        return e.which != 13;
    });

});

function save(activePBId, activeReceptID, activeStatus, activeRBId){

    const activeBrugerID = $(".brugertable").find("td").eq(0).text();
    const activeTara = $(".brugertable").find("td").eq(1).text();
    const activeNetto = $(".brugertable").find("td").eq(2).text();
    console.log(activeBrugerID);

    const obj = { pbId: activePBId, status: activeStatus, receptId: activeReceptID, userId: activeBrugerID, rbID: activeRBId, tara: activeTara, netto: activeNetto };
    const myJson = JSON.stringify(obj);

    $.ajax({
        type: "POST",
        url: "/BoilerPlate_war_exploded/rest/produktbatch/opdaterProduktbatch",
        data: myJson,
        dataType: "json",
        success: function() {
            alert("Produktbatch successfuldt opdateret");
            $("#gem").removeAttr("hover");
        },
        contentType: "application/json; charset=UTF-8"
    });
}