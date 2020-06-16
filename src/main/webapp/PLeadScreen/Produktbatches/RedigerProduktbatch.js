$("document").ready(function () {

    const batchID = localStorage.getItem("activePBId");
    const receptID = localStorage.getItem("activeReceptID");
    const status = localStorage.getItem("activeStatus");
    const raavarebatchID = localStorage.getItem("activeRBId");

    $("#batchID").html("Batch ID: " + batchID);
    $("#receptID").html("Recept ID: " + receptID);
    $("#status").html("Status: " + status);
    $("#raavarebatchID").html("Råvarebatch ID: " + raavarebatchID);

    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/getBatchLine/" + batchID + "/" + raavarebatchID, function(data) {
        $("#brugerID").html(data.userId);
        $("#tara").html(data.tara);
        $("#netto").html(data.netto);
    }, function (data) {
        alert("Error getting produktbatch line: ERR.NO.13")
        console.log(data)
    })

    $("#gem").click(function () {
        save(batchID, receptID, status, raavarebatchID);
    })
    
    $("#slet").click(function () {
        erase(batchID, raavarebatchID);
        switchP('PLeadScreen/Produktbatches/AabenProduktbatch.html');
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

    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/opdaterProduktbatch", function(data) {
        alert("Produktbatch successfuldt opdateret");
        $("#gem").removeAttr("hover");
    }, function (data) {
        alert("Error updating produktbatch: ERR.NO.14")
        console.log(data)
    }, "POST", myJson);
}

function erase(batchID, raavarebatchID){
    sendAjax("/BoilerPlate_war_exploded/rest/produktbatch/sletProduktBatch/" + batchID + "/" + raavarebatchID, function(data) {
        alert("Produktbatch successfuldt slettet");
        $("#gem").removeAttr("hover");
    }, function (data) {
        alert("Error deleting produktbatch: ERR.NO.15")
        console.log(data)
    }, "POST");
}