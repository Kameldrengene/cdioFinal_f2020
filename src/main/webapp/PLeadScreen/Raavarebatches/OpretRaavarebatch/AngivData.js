$("document").ready(function () {

    const raavareID = localStorage.getItem("activeRaavare");

    $("#raavareID").html("Råvare ID: " + raavareID);

    // loadBatch(batchID);
    //
    // $("#produktbatch").on("click", "button", function () {
    //     localStorage.setItem("activeRBId", this.id);
    //     switchP("PLeadScreen/Produktbatches/RedigerProduktbatch.html");
    // })

})









$("#gem").click(function () {

    const batchID = $('#batchID').val();
    const raavareID = $('#raavareID').val();
    const mængde = $('#aktuelMaengde').val();
    const oprindeligMaengde = $('#oprindeligMaengde').val();

    var obj = { rbId: batchID, raavareId: raavareID, aktuelMaengde: mængde, startMaengde: oprindeligMaengde };
    var myJson = JSON.stringify(obj);
    sendAjax("/BoilerPlate_war_exploded/rest/Raavarebatch/opretRaavarebatch", function (data) {
        alert("Råvarebatch oprettet succesfuldt");
        $("#gem").removeAttr("hover");
    }, function (data) {
        alert("Error making RaavareBatch: ERR.NO.08");
        console.log(data);
    }, "POST", myJson);
})
